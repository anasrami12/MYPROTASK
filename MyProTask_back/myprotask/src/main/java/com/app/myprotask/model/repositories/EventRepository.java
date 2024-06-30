package com.app.myprotask.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.myprotask.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	/**
	 * @param idUser
	 * @return all events by user
	 */
	@Query(value = "SELECT * FROM events WHERE user_id = ?1", nativeQuery = true)
	List<Event> displayEventsByIdUser(Long idUser);
}
