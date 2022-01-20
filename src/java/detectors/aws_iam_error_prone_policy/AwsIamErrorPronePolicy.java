/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.aws_iam_error_prone_policy;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateRoleRequest;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class AwsIamErrorPronePolicy {
    // {fact rule=aws-iam-error-prone-policy@v1.0 defects=1}
    public void iamPolicyNoncompliant(final String roleName, String userArn) {
        final AmazonIdentityManagement iamClient = AmazonIdentityManagementClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        String policyDocument = "{\n" +
                " \"Version\": \"2012-10-17\",\n" +
                "  \"Statement\": [\n" +
                "   {\n" +
                "      \"Effect\": \"Allow\",\n" +
                "      \"Principal\": {\n" +
                "        \"AWS\": \"" + userArn + "\"\n" +
                "      },\n" +
                "      \"Action\": \"sts:AssumeRole\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        final CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        // Noncompliant: creates an IAM role/policy manually.
        createRoleRequest.withPath("path").withRoleName(roleName).withAssumeRolePolicyDocument(policyDocument);
        iamClient.createRole(createRoleRequest);
    }
    // {/fact}

    // {fact rule=aws-iam-error-prone-policy@v1.0 defects=0}
    public void iamPolicyCompliant(final String roleName) throws UnsupportedEncodingException {
        final AmazonIdentityManagement iamClient = AmazonIdentityManagementClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        final String policyDocument = URLDecoder.decode(iamClient.getRolePolicy(new GetRolePolicyRequest()).getPolicyDocument(), "UTF-8");
        final CreateRoleRequest createRoleRequest = new CreateRoleRequest()
                .withRoleName(roleName)
                .withAssumeRolePolicyDocument(policyDocument);
        // Compliant: creates an IAM role/policy automatically.
        final String policyArn = iamClient.createRole(createRoleRequest).getRole().getArn();
    }
    // {/fact}
}
