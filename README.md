# Sample akka application

This is a sample akka application.

## Configure the resolver

> [!TIP]
> The Akka dependencies are available from Akka’s secure library repository. To access them you need to use a secure, tokenized URL as specified at https://account.akka.io/token.

Set your Akka tokenized URL before running `sbt` (either option works):

```bash
export AKKA_REPO_URL='https://repo.akka.io/your-tokenized-path'
```

Or store it once in a local file in the project root:

```bash
echo 'https://repo.akka.io/your-tokenized-path' > .akka-repo-url
```

## Java requirement

Install a JDK (not a JRE). JDK 17 is a good default:

```bash
sudo apt update
sudo apt install openjdk-17-jdk-headless
```

## Interacting with the sample

Run `sbt run` to start the application. 
