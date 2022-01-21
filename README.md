## Overview

Amazon CodeGuru Reviewer is an AWS service that uses program analysis and machine learning to detect potential defects that are difficult for developers to find and offers suggestions for improvement. 

CodeGuru Reviewer finds defects on Java and Python code. For more information about how to set up and use CodeGuru Reviewer, see the Amazon CodeGuru Reviewer User Guide.

This repo demonstrates some of CodeGuru Reviewer's Java detectors. To see the Python repo, click here.

## Getting Started with the CodeGuru Reviewer GitHub Action

You can use this code repository to try out CodeGuru Reviewer at no cost to your AWS account

### Prequisites

In order to use the CodeGuru Reviewer GitHub Action to run a scan on this repo, you will first need to first create a suitable Role, S3 Bucket, and Policy in your AWS account. You can do this by using the AWS CDK for Typescript and following the instructions here.

### Setup
A CodeGuru Reviewer GitHub Action workflow template has already been added to this repo. To see CodeGuru Reviewer in action:

1. Fork this repo
2. In .github/workflows/analyze.yml, add your ARN, region and S3 bucket name from the prequisite step above and commit this change
3. Click on the Actions tab (next to pull requests)
4. Click on the Reviewer Workflow
5. Click "Run workflow" 
6. Navigate to the Security tab to see results (It should take 5-10 min)

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

