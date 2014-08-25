# The AWS Resource Objects Library for Java

An object-oriented abstraction over the clients from the [AWS SDK for Java][sdk].

Currently in an early developer preview mode with support for [Amazon EC2][ec2],
[Amazon Identity and Access Management][iam] and [Amazon Glacier][glacier]. It more
or less works, but there might still be rough edges left until we get closer to
1.0. We're mainly looking for feedback about 

## Overview

This library provides a set of **Resource Objects** that model the cloud resources you
provision through Amazon Web Services. These objects provide a simple, easy-to-use
interface for interacting with your cloud resources. Instead of a single "client"
class for each service that directly maps the service's API, Resource Objects
provide contextual information about what you can do with a particular resource
object.

For example, given an EC2 `Vpc` object, you can easily iterate through all of the
`Instance` objects representing the EC2 Instances you have provisioned in that VPC,
printing their DNS names and starting them (if they're currently stopped).

    Vpc myVpc = ec2.getVpc("vpc-xxxxxxx");
    for (Instance instance : vpc.getInstances()) {
        System.out.println(instance.getDnsName());
        if (instance.getState().equals("STOPPED")) {
            instance.start();
        }
    }

## Getting Started

* Download the [latest preview release][releases].
* Or pick it up from Maven at:
    <dependency>
      <groupId>com.amazonaws.resources</groupId>
      <artifactId>resource-objects</artifactId>
      <version>0.0.1</version>
    </dependency>
<!--- * Read the introduction below. -->
* Read the [API documentation][api-docs].
* Give us [feedback][issues]!

[sdk]: https://github.com/aws/aws-sdk-java
