package net.springBoot.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final EventsRepository eventsRepository;

    public KafkaConsumer(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage) {

        LOGGER.info(String.format("Message recieved -> %s", eventMessage));

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(eventMessage);
            JsonNode dataNode = jsonNode.get("meta");
            String id = dataNode.get("id").asText();
            String requestId = dataNode.get("request_id").asText();
            String topic = dataNode.get("topic").asText();
            String timestamp = dataNode.get("dt").asText();
            Wikimedia wikimedia = new Wikimedia(id, requestId, topic, timestamp);
            eventsRepository.save(wikimedia);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
