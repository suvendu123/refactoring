package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	
	private TripService service;
	
	@Before
	public void setUp(){
		service = new TripService();
	}
	
	@Test
	public void should_return_trips(){
		List<Trip> trips = service.getTripsByUser(new User());
		assertNotNull(trips);
	}
	
}
