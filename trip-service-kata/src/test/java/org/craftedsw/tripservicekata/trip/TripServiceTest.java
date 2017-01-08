package org.craftedsw.tripservicekata.trip;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TripServiceTest {

	@InjectMocks
	private TripService service = new TripService();

	@Mock
	private UserSession userSession;

	@Mock
	private TripDAO tripDao;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void should_return_trips() {
		User user = new User();
		user.addFriend(mockLoggedInUser());

		when(tripDao.findTripsByUser(any(User.class))).thenReturn(singletonList(new Trip()));
		List<Trip> trips = service.getTripsByUser(user);
				
		assertEquals(1,trips.size());
		verify(tripDao, times(1)).findTripsByUser(user);
		verify(userSession, times(1)).getLoggedUser();
	}
	
	@Test(expected=UserNotLoggedInException.class)
	public void should_throw_exception_user_not_logged_in() {
		
		service.getTripsByUser(new User());
		
		verify(userSession, times(1)).getLoggedUser();
	}
	
	@Test
	public void should_not_invoke_trip_dao_if_not_friend() {
		mockLoggedInUser();
		
		List<Trip> trips = service.getTripsByUser(new User());
		
		assertTrue(trips.isEmpty());
		verify(userSession, times(1)).getLoggedUser();
		verify(tripDao, never()).findTripsByUser(any(User.class));
	}
	
	
	private User mockLoggedInUser() {
		User loggedUser = new User();
		when(userSession.getLoggedUser()).thenReturn(loggedUser);
		return loggedUser;
	}

}