package models;

import lombok.Data;

import java.util.LinkedList;

@Data
public class Game {
	public final Integer TOTAL_ROUNDS = 23;
	public LinkedList<User> users = new LinkedList<>();
	public Integer gifts = TOTAL_ROUNDS;
	public Integer roundTracker = 0;

	public void populateUsers() {
		while (users.size() < TOTAL_ROUNDS) {
			users.add(new User());
		}
	}

	public int roundTrackerUpdate() {
		roundTracker++;
		if (roundTracker.equals(TOTAL_ROUNDS)) {
			roundTracker = -1;
		}

		return roundTracker++;
	}

}
