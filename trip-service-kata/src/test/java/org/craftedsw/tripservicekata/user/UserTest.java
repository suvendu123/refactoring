package org.craftedsw.tripservicekata.user;

import static org.junit.Assert.assertEquals;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void setup(){
		user = new User();
	}
	
	@Test
	public void should_add_friends(){
		user.addFriend(new User());
		assertEquals(1, user.getFriends().size());
	}
	
	@Test
	public void should_add_trips(){
		user.addTrip(new Trip());
		assertEquals(1, user.trips().size());
	}
	

}
