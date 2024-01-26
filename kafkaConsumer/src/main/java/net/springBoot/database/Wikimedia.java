package net.springBoot.database;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "wiki_events")
@Getter
public class Wikimedia {
    @Id
    private String id;

    @Column(name = "request_id")
    private String requestId;

    @Column
    private String topic;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private String timestamp;

    public Wikimedia() {
    }

    public Wikimedia(String id, String requestId, String topic, String timestamp) {
        this.id = id;
        this.requestId = requestId;
        this.topic = topic;
        this.timestamp = timestamp;
    }
}
