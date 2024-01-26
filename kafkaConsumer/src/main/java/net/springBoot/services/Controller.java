package net.springBoot.services;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import net.springBoot.database.Wikimedia;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/events")
@Tag(name = "Events API")
public class Controller {

    private final WikiServices services;

    public Controller(WikiServices services) {
        this.services = services;
    }

    @Operation(summary = "Get all events", description = "Returns a list of all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public List<Wikimedia> getAllEvents() {
        return services.getEvents();
    }

    @Operation(summary = "Get event by id", description = "Returns an event as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The event was not found")
    })
    @GetMapping("/id/{eventId}")
    public ResponseEntity<Wikimedia> getEvent(@PathVariable("eventId") @Parameter(name = "eventId", description =
            "Event id") String eventId) {
        return services.getEvent(eventId);
    }

    @Operation(summary = "Delete one/multiple event/s by id", description = "Deletes an event as per the id/s")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully processed delete request"),
    })
    @DeleteMapping
    public void deleteEvents(@RequestParam @Parameter(name = "eventIds", description = "Event ids")
                                 List<String> eventIds) {
        services.deleteEvents(eventIds);
    }
}
