package com.saungkertas.backyard;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;
import org.apache.beam.sdk.options.ValueProvider;

public interface MainPipelineOptions extends PipelineOptions {

    @Description("Kafka Brokers")
    @Validation.Required
    String getKafkaBrokers();

    void setKafkaBrokers(ValueProvider<String> kafkaBrokers);

    @Description("Kafka Topics")
    @Validation.Required
    String getKafkaTopic();

    void setKafkaTopic(ValueProvider<String> kafkaTopic);

    @Description("DownStream GCS")
    @Validation.Required
    String getDownstreamGcs();

    void setDownstreamGcs(ValueProvider<String> downstreamGcs);
}
