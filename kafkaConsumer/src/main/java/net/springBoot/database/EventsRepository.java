package net.springBoot.database;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface EventsRepository extends JpaRepository<Wikimedia, String >{
}
