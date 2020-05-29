package io.quarkus.kafka.streams.runtime;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "kafka-streams", phase = ConfigPhase.RUN_TIME)
public class KafkaStreamsRuntimeConfig {

    /**
     * A unique identifier for this Kafka Streams application.
     * If not set, defaults to quarkus.application.name.
     */
    @ConfigItem(defaultValue = "${quarkus.application.name}")
    public String applicationId;

    /**
     * A comma-separated list of host:port pairs identifying the Kafka bootstrap server(s)
     */
    @ConfigItem(defaultValue = "localhost:9012")
    public List<InetSocketAddress> bootstrapServers;

    /**
     * A unique identifier of this application instance, typically in the form host:port.
     */
    @ConfigItem
    public Optional<String> applicationServer;

    /**
     * A comma-separated list of topic names.
     * The pipeline will only be started once all these topics are present in the Kafka cluster.
     */
    @ConfigItem
    public List<String> topics;

    @Override
    public String toString() {
        return "KafkaStreamsRuntimeConfig [applicationId=" + applicationId + ", bootstrapServers=" + bootstrapServers
                + ", applicationServer=" + applicationServer + ", topics=" + topics + "]";
    }

    public List<String> getTrimmedTopics() {
        return topics.stream().map(String::trim).collect(Collectors.toList());
    }
}
