package org.craftedsw.tripservicekata.trip;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

	@Autowired
	private UserSession userSession;

	@Autowired
	private TripDAO tripDao;

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

		return user.isFriend(verifyLoggedUser()) ? tripDao.findTripsByUser(user) : emptyList();

	}

	private User verifyLoggedUser() {
		User loggedUser = userSession.getLoggedUser();
		if (isNull(loggedUser)) {
			throw new UserNotLoggedInException();
		}
		return loggedUser;
	}

}
