package com.saungkertas.backyard;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

public class MainPipeline {

    public static void main(String[] args) {

        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline p = Pipeline.create(options);

        p.apply(KafkaIO.read()
                .withBootstrapServers("your Kafka Brokers ip(s)")
                .withTopic("your kafka topic"))
                .apply(ParDo.of(new DoFn<Object, String>() {

                    @ProcessElement
                    public void processElement(ProcessContext context) {
                        context.output(context.element().toString());
                    }

                }))
                .apply(TextIO.write().to("your gcs address")
                        .withoutSharding()
                        .withSuffix(".txt")
                        .withWindowedWrites());
    }
}
