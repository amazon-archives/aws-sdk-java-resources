package com.amazonaws.resources.sns;

import java.io.IOException;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.resources.ServiceBuilder;

public class SNSIntegrationTestBase {

    protected static SNS sns;

    protected static void setUpSNS() throws IOException {
        sns =  ServiceBuilder.forService(SNS.class)
                .withCredentials(new ProfileCredentialsProvider())
                    .build();
    }

}
