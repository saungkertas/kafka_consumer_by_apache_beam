#!/usr/bin/env bash

gradle run \
--args="--project= \
--KafkaBrokers= \
--kafkaTopic= \
--downstreamGcs= \
--jobName= \
--runner=DataflowRunner \
--network= \
--subnetwork= \
--zone= \
--tempLocation= \
--stagingLocation= \
--filesToStage"
