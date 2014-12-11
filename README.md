# AWS Resource APIs for Java

An object-oriented abstraction over the clients from the [AWS SDK for Java][sdk].

Currently in a developer preview mode with support for [Amazon EC2][ec2],
[AWS Identity and Access Management][iam], [Amazon Glacier][glacier], [Amazon SNS][sns],
and [Amazon SQS][sqs].

As the library is still at an early stage, there may still be rough edges left until we
get closer to GA. At this point we're mainly looking to start conversations about the design
of the API while we simultaneously work on adding support for more AWS services
and battle-hardening the implementation.

## Overview

This library provides a set of **Resource** objects that model the cloud resources you
provision through Amazon Web Services. These objects provide a simple, easy-to-use
interface for interacting with your cloud resources. Instead of a single "client"
class for each service that directly maps the service's API, Resource objects
provide contextual information about what you can do with a particular resource
object.

For example, given an EC2 `Vpc` object, you can easily iterate through all of the
`Instance` objects representing the EC2 Instances you have provisioned in that VPC,
printing their DNS names and starting them (if they're currently stopped).

```java
    Vpc myVpc = ec2.getVpc("vpc-xxxxxxx");
    for (Instance instance : vpc.getInstances()) {
        System.out.println(instance.getPublicDnsName());
        if (instance.getState().equals("STOPPED")) {
            instance.start();
        }
    }   
```

## Getting Started

* Download the [latest preview release][releases] or pick it up from Maven:
```xml
    <dependency>
      <groupId>com.amazonaws.resources</groupId>
      <artifactId>aws-resources</artifactId>
      <version>0.0.3</version>
      <type>jar</type>
    </dependency>
```
* Read the introduction below.
* Read the [API documentation][api-docs].
* Give us [feedback][issues]!



## Creating a Service object

A Service object is your main point of entry into the Resource APIs. You can create
a service object for the service of your choice by using the [ServiceBuilder][servicebuilder]
class:

```java
    EC2 ec2 = ServiceBuilder.forService(EC2.class)
        .withRegion(Region.getRegion(Regions.US_WEST_2))
        .build();
```
Service objects are immutable and threadsafe - typically you should share a single
instance of this service object throughout your application. The currently-supported
service objects include [EC2][ec2service], [IdentityManagement][iamservice], and
[Glacier][glacierservice].

## Identifying Resources

At its core, a Resource object is a wrapper for a set of *identifiers* that uniquely
name a particular AWS resource. To create a Resource object from scratch, you must
provide values for these identifiers. For example, an EC2 instance is uniquely
identified by its InstanceId.

```java
    Instance instance = ec2.getInstance("i-xxxxxxxx");
```

A Resource object will always happily give you the values for its identifiers via
handy getter methods:

```java
    // Guaranteed not to require a remote service call, and will never block or
    // throw an exception.
    System.out.println(instance.getId());
```

Resource objects are *lazy* - they can be created without a round-trip to the service.
This means that if you create a Resource object with bogus identifiers, you won't find
out until you try to use the object to make a call to the service.

## Navigating the Resource graph

From the Service object, you can navigate to all of the Resource objects exposed by
the service. As we saw above, you can directly navigate to a resource by providing
its identifier(s):

```java
    // Immediately returns an Instance object wrapping the given
    // instanceId; does not make a remote service call.
    Instance instance = ec2.getInstance("i-xxxxxxxx");
```

You can navigate to entire collections of resources:

```java
    // Lazily calls EC2's DescribeInstances API as needed to
    // enumerate all the instances for this account.
    for (Instance instance : ec2.getInstances()) {
        ...        
    }
```

And you can follow links from one resource to another via getter methods exposed
on Resource objects. For example, an EC2 Instance may belong to a VPC, in which
case its `getVpc()` method will return you the corresponding VPC resource.
Likewise, a VPC links to the collection of Instances that it contains via its
`getInstances` method.

```java
    Instance instance = ec2.getInstance("i-xxxxxxxx");

    Vpc vpc = instance.getVpc();
    if (vpc != null) {
        for (Instance other : vpc.getInstances()) {
            ...
        }
    }
```

## Describing Resources

One obvious use of a Resource object is to retrieve data from the service about the
actual cloud resource it represents. Resource objects expose this data through getter
methods:

```java
    Instance instance = ec2.getInstance("i-xxxxxxxx");
    
    System.out.println("Public DNS Name: " + instance.getPublicDnsName());
    System.out.println("State: " + instance.getState());
    System.out.println("Launched at: " + instance.getLaunchTime());
```

Resource objects are lazily loaded the first time you access one of their data
attributes. In the above example, the call to `getPublicDnsName` will make a
call to EC2 to describe the instance. Subsequent calls in the above example will
return data from this cached response and not make another call to the service.

You can check whether an instance's data members have been loaded already by
calling the `isLoaded` method, and you can proactively load an instance by calling
the `load` method (optionally passing additional arguments for the service call):

```java
    if (!instance.isLoaded()) {
        instance.load();
    }
```

## Acting on Resources

Other methods on a Resource object are *actions* that can be taken on the resource. Each
action method will always result in a single call to the service. Parameters to the call
which can be inferred from the identifiers or data attributes of the resource will be
specified for you, so you don't need to explicitly specify them.

```java
    Instance instance = ec2.getInstance("i-xxxxxxxx");

    // Call StopInstances, passing in the id of this instance and
    // 'true' for the 'Force' parameter.
    instance.stop(new StopInstancesRequest().withForce(true));


    // Call TerminateInstances, passing in this instance's InstanceId.
    instance.terminate();
```

Some actions are exposed directly on the Service object, typically for creating new
resources. These actions return a new resource object prepopulated with the identifiers
of the created resource:

```java
    Vpc newVpc = ec2.createVpc(new CreateVpcRequest("10.0.0.0/16"));
    System.out.println(newVpc.getState());
```

## Working with Collections

References to collections of resources are represented by ResourceCollection objects.
Resource collections are, like resources themselves, lazy - they can be created without
making any calls to the service.

```java
    // No calls to the service have been made yet.
    InstanceCollection instances = ec2.getInstances();
```

Resource collections implement the `Iterable` interface, which is typically the easiest
way to consume them. The returned iterator will lazily call the service to enumerate the
collection of resources one page at a time as you read from it:

```java
    // Will lazily call EC2's DescribeInstances API as needed.
    for (Instance instance : instances) {
        System.out.println(instance.getPublicDnsName());
    }
```

Alternatively, you can directly access the pages of the response by calling the `pages` method
on the collection:

```java
    // Each call to Iterator.next will make a call to the service
    for (List<Instance> page : instances.pages()) {
        System.out.println("There were " + page.size() + " instances in this page.");
    }
```

Or for the most control you can manually step through the pages of the result set:

```java
    // Makes a call to retrieve the first page of instances.
    ResourcePage<Instance> firstPage = instances.firstPage();
    System.out.println("First page has " + firstPage.size() + " instances.");

    if (firstPage.hasNextPage()) {
        // Makes a call to retrieve the second page of instances.
        ResourcePage<Instance> nextPage = firstPage.nextPage();
        System.out.println("Second page has " + nextPage.size() + " instances.");
    }
```

[sdk]: https://github.com/aws/aws-sdk-java
[ec2]: http://aws.amazon.com/ec2/
[iam]: http://aws.amazon.com/iam/
[glacier]: http://aws.amazon.com/glacier/
[sns]: http://aws.amazon.com/sns/
[sqs]: http://aws.amazon.com/sqs/
[releases]: https://github.com/awslabs/aws-sdk-java-resources/releases
[api-docs]: http://docs.aws.amazon.com/AWSJavaSDKResources/latest/
[issues]: https://github.com/awslabs/aws-sdk-java-resources/issues
[servicebuilder]: http://docs.aws.amazon.com/AWSJavaSDKResources/latest/com/amazonaws/resources/ServiceBuilder.html
[ec2service]: http://docs.aws.amazon.com/AWSJavaSDKResources/latest/com/amazonaws/resources/ec2/EC2.html
[iamservice]: http://docs.aws.amazon.com/AWSJavaSDKResources/latest/com/amazonaws/resources/identitymanagement/IdentityManagement.html
[glacierservice]: http://docs.aws.amazon.com/AWSJavaSDKResources/latest/com/amazonaws/resources/glacier/Glacier.html
