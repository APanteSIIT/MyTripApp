package com.myTrips.persistance;

import com.myTrips.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/*
		Spring @Repository annotation is used to indicate
		that the class provides the mechanism for storage,
		retrieval, search, update and delete operation on object of type user.
		*/

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	@Query(
	value = "SELECT user.id,user.address, user.city, user.first_name, user.last_name, user.password, user.phone, user.username\n" +
			" FROM USER JOIN TRIP ON user.id=trip.id;",
			nativeQuery = true)
	User findUser(Long tripId);
	User findByUsername(String username);
	
	
}
