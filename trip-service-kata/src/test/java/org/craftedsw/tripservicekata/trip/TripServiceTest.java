package org.craftedsw.tripservicekata.trip;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

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
		mockLoggedInUser(user);

		when(tripDao.findTripsByUser(any(User.class))).thenReturn(singletonList(new Trip()));
		List<Trip> trips = service.getTripsByUser(user);
				
		assertEquals(1,trips.size());
		verify(tripDao, times(1)).findTripsByUser(any(User.class));
		verify(userSession, times(1)).getLoggedUser();
	}
	

	private void mockLoggedInUser(User user) {
		User loggedUser = new User();
		user.addFriend(loggedUser);

		when(userSession.getLoggedUser()).thenReturn(loggedUser);
	}

}