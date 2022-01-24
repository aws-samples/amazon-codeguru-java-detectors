## Overview

Amazon CodeGuru Reviewer is an AWS service that uses program analysis and machine learning to detect potential defects that are difficult for developers to find and offers suggestions for improvement. 

CodeGuru Reviewer finds defects in Java and Python code. For more information about how to set up and use CodeGuru Reviewer, see the [Amazon CodeGuru Reviewer User Guide](https://docs.aws.amazon.com/codeguru/latest/reviewer-ug/welcome.html)

This repo demonstrates some of CodeGuru Reviewer's Java detectors. For more descriptions on each detector, see our Detector Library. To see the Python code repo, click [here](https://github.com/aws-samples/amazon-codeguru-reviewer-python-detectors).

## Try out the CodeGuru Reviewer GitHub Action on this example repo

You can use this code repository to try out CodeGuru Reviewer at no cost to your AWS account

### Prequisites

In order to use the CodeGuru Reviewer GitHub Action to run a scan on this repo, you will first need to first create a suitable Role, S3 Bucket, and Policy in your AWS account. You can do this by using the AWS CDK for Typescript and following the instructions [here](https://github.com/aws-samples/aws-codeguru-reviewer-cicd-cdk-sample).

### Setup
A CodeGuru Reviewer GitHub Action workflow template has already been added to this repo. To see CodeGuru Reviewer in action:

1. Fork this repo
2. In .github/workflows/analyze.yml, add your ARN, region and S3 bucket name from the prequisite step above and commit this change
3. Click on the Actions tab (next to pull requests)
4. Click on the CodeGuru Reviewer Workflow
5. Click "Run workflow" 
6. Navigate to the Security tab to see results (It should take 5-10 min). GitHub only enables the security tab for free on public repositories. 

## Try out the CodeGuru Reviewer GitHub Action on your own repo

You can copy and paste the CodeGuru Reviewer GitHub Action .yml you made in the Setup step to your own personal repo. You may need to update the build logic and build_path on the .yml file to make sure it matches your build system. If you do not have GitHub Advanced Security, you will be able to view your findings within the AWS Console.

## Getting Help

Use the community resources below for getting help with AWS CodeGuru Reviewer

Use GitHub issues to report bugs and request features.
Open a support ticket with AWS Support.
If you think you may have found a bug, open a bug report.
For contributing guidelines, refer to CONTRIBUTING.md.

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This project is licensed under the Apache-2.0 License. See the LICENSE file.

