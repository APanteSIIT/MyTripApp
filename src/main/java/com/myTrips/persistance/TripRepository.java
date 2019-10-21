package com.myTrips.persistance;

import com.myTrips.model.Trip;
import com.myTrips.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
	Spring @Repository annotation is used to indicate
 that the class provides the mechanism for storage,
 retrieval, search, update and delete operation on object of type trip.
 */
@Repository
public interface TripRepository extends CrudRepository<Trip,Long> {
	
	
	}
