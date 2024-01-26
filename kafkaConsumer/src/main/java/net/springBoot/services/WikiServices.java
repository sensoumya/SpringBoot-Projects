package net.springBoot.services;

import jakarta.transaction.Transactional;
import net.springBoot.database.EventsRepository;
import net.springBoot.database.Wikimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WikiServices {
    private final EventsRepository eventsRepository;

    @Autowired
    public WikiServices(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<Wikimedia> getEvents() {
        return eventsRepository.findAll();
    }

    @Transactional
    public void deleteEvents(List<String> eventIds) {
        eventsRepository.deleteAllByIdInBatch(eventIds);
    }

    public ResponseEntity<Wikimedia> getEvent(String idToCheck) {
        Optional<Wikimedia> event = eventsRepository.findById(idToCheck);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
