# Project Description
This project to consume Kafka messages using Apache Beam

## Dev Environment Setup for OS X.
* Java v8
```
$ brew cask install java8
```
[To install java please refer](https://docs.oracle.com/javase/10/install/overview-jdk-10-and-jre-10-installation.htm)
* Gradle v5.0

[To install gradle please refer](https://gradle.org/install/)

## Test instructions
```
$ ./gradlew clean test
```

## Build instructions
```
$ ./gradlew build
```

## Code Coverage instructions
```
$ ./gradlew clean build jacocoTestReport
```

## Code complexity
```
$ ./gradlew clean build check
```

## How to use
Fill all pipeline parameters in file deploy_pipeline.sh :<br/><br/>
--project= <br/>
--kafkaBrokers= <br/>
--kafkaTopic=  <br/>
--downstreamGcs=  <br/>
--jobName= <br/>
--runner=DataflowRunner <br/>
--network= <br/>
--subnetwork= <br/>
--zone= <br/>
--tempLocation= <br/>
--stagingLocation= <br/>
--filesToStage"

run pipeline by execute file:
```
$ ./deploy_pipeline.sh

```

### Author
Syarif Hidayataullah
