# Contributing Guidelines

Thank you for your interest in contributing to this project! Please read and follow these guidelines to help us maintain
a high-quality codebase.

## Code of Conduct

This project adheres to the Contributor Covenant
[code of conduct](https://github.com/yvasyliev/dz4j?tab=coc-ov-file#readme). By participating, you
are expected to uphold this code. Please report unacceptable behavior to ye.vasyliev@gmail.com.

## Using GitHub Issues

We use GitHub issues to track questions, bugs and enhancements.

If you are reporting a bug, please help to speed up problem diagnosis by providing as much information as possible.
Ideally, that would include a small sample project that reproduces the problem.

## Reporting Security Vulnerabilities

If you think you have found a security vulnerability in the project, please *DO NOT* disclose it publicly until we've
had a chance to fix it. Please don't report security vulnerabilities using GitHub issues; instead, head over to
[Security policy](https://github.com/yvasyliev/dz4j?tab=security-ov-file#readme) and learn how to
disclose them responsibly.

## Code Conventions and Housekeeping

* This project requires JDK 21 to build. We recommend using the Gradle Wrapper (included in the repository), which
  automatically downloads the required JDK version if it is not already installed. Published binaries target Java 17
  for runtime compatibility.
* Please respect the project's code style. The project primarily follows IntelliJ IDEA's default style conventions.
* Make sure that your contribution embeds well into the already existing code. For example, the unified folder/package
  structure must be used wherever possible.
* Document your source code thoroughly. This is a requirement to keep the Javadoc complete and accessible to developers.
  Please ensure you have documented all public classes, methods, and fields in your contribution.
* All new code should be covered with unit tests and, if relevant, with integration tests. The project aims for 100%
  test coverage.
* Leverage Java 17 language features to write clean and expressive code. Use the `var` keyword for type inference,
  `java.util.stream.Stream` and lambda expressions over traditional for loops, switch expressions, and pattern matching
  where applicable.
* The project relies on [JSpecify](https://jspecify.dev/) annotations to prevent null pointer exceptions. When creating
  a new package, add a `package-info.java` file annotated with `@NullMarked`. Use `@Nullable` annotations on parameters
  and return types where null values are acceptable.
* Make sure that your contribution does not break the build. You can check this by running the following command:

  On Linux/MacOS:
  
  ```bash
  ./gradlew build
  ```

  On Windows:
  
  ```bash
  gradlew.bat build
  ```

## Contribution Flow

1. Create a fork from this repository.
2. Create a branch in your fork in which you develop your contribution (one branch per feature/fix).
3. Create meaningful and well-separated commits.
4. Make sure your contribution follows the contribution guidelines above.
5. Create a pull request from your feature branch to the <ins>default</ins> branch of this project. The PR title should
   follow the [Conventional Commits](https://www.conventionalcommits.org) format, as pull requests will be squashed and
   merged, making the PR title a commit message. We use conventional commit messages to calculate the next release
   versions and generate changelogs.

**Do not commit secrets** such as passwords, API keys, or credentials.

---

Thank you for helping us improve this project!
