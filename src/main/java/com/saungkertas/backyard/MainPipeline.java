package com.saungkertas.backyard;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.windowing.AfterWatermark;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.joda.time.Duration;

public class MainPipeline {

    public static void main(String[] args) {

        Window<String> window = Window.<String>into(FixedWindows.of(Duration.standardMinutes(1)))
                .triggering(AfterWatermark.pastEndOfWindow())
                .discardingFiredPanes()
                .withAllowedLateness(Duration.standardMinutes(5));

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
                .apply(window)
                .apply(TextIO.write().to("your gcs address")
                        .withoutSharding()
                        .withSuffix(".txt")
                        .withWindowedWrites());
    }
}
