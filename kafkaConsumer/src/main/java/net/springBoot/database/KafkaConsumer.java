package net.springBoot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final Repository repository;

    public KafkaConsumer(Repository repository) {
        this.repository = repository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage) {

        LOGGER.info(String.format("Message recieved -> %s", eventMessage));
        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setEventData(eventMessage);

        repository.save(wikimedia);
    }
}
