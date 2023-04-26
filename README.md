## Amazon CodeGuru Reviewer Java Detector Examples

Amazon CodeGuru Reviewer is an AWS service that uses program analysis and machine learning to detect potential defects that are difficult for developers to find and offers suggestions for improvement. 

CodeGuru Reviewer finds defects in Java and Python code. For more information about how to set up and use CodeGuru Reviewer, see the [Amazon CodeGuru Reviewer User Guide](https://docs.aws.amazon.com/codeguru/latest/reviewer-ug/welcome.html).

This repo demonstrates some of CodeGuru Reviewer's Java detectors. For more descriptions of each detector, see our [Detector Library](https://docs.aws.amazon.com/codeguru/detector-library/index.html). To see the Python code examples repo, click [here](https://github.com/aws-samples/amazon-codeguru-reviewer-python-detectors).

## Try out the CodeGuru Reviewer GitHub Action on this repo

You can use this code repository to try out CodeGuru Reviewer using your AWS credentials.

### Prerequisites

To use the CodeGuru Reviewer GitHub Action to scan a fork of this repo, you will first need to create a suitable Role, S3 Bucket, and Policy in your AWS account. You can do this automatically by following [these instructions](https://github.com/aws-samples/aws-codeguru-reviewer-cicd-cdk-sample#cdk-typescript-project-to-set-up-the-codeguru-reviewer-cicd-integration).

### Setup
A CodeGuru Reviewer GitHub Action workflow template has already been added to this repo. To see CodeGuru Reviewer in action:

1. Fork this repo.
2. In `.github/workflows/analyze.yml`, replace the following three fields with the values obtained from the prerequisites step above: your Role ARN (`role-to-assume`), your Region (`aws-region`), and your S3 bucket name (`s3_bucket`).
3. Click on the Actions tab (next to pull requests).
4. Click on the CodeGuru Reviewer Workflow.
5. Click "Run workflow".
6. Navigate to the Security tab to see results (it should take 5-10 min). GitHub only enables the security tab for free on public repositories.

## Try out the CodeGuru Reviewer GitHub Action on your own repo

You can copy the CodeGuru Reviewer GitHub Action `analyze.yml` that you made in the Setup step to your own personal repo. However, please take note of the following items:

- If you use a build system other than Gradle, you may need to edit the build step using the appropriate command for your build system.
- If your build system doesn't place the resulting .jar files in `build/libs`, you may need to edit the `build_path` field in the `analyze.yml` workflow.
- If you do not have GitHub Advanced Security, you will still be able to view your findings within the AWS Console. You can also use tools like `jq` within your workflow to postprocess the findings. If you print some of the findings to `stdout`, you will see them in your workflow's output log.

## Getting Help

Use the community resources below for getting help with AWS CodeGuru Reviewer.

- Use GitHub issues to report bugs and request features.
- Open a support ticket with [AWS Support](https://docs.aws.amazon.com/awssupport/latest/user/getting-started.html).
- For contributing guidelines, refer to [CONTRIBUTING](https://github.com/aws-samples/amazon-codeguru-reviewer-python-detectors/blob/main/CONTRIBUTING.md).

## Contributing

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This project is licensed under the Apache-2.0 License. See the [LICENSE](LICENSE) file.
