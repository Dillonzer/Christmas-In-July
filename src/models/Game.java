package models;

import lombok.Data;

import java.util.LinkedList;

@Data
public class Game {
	public final Integer TOTAL_ROUNDS = 23;
	public LinkedList<User> users = new LinkedList<>();
	public LinkedList<Gift> gifts = new LinkedList<>();
	public Integer roundTracker = 0;

	public void populateGame() {
		populateUsers();
		populateGifts();
	}

	private void populateUsers() {
		while (users.size() < TOTAL_ROUNDS) {
			users.add(new User());
		}
	}

	private void populateGifts() {
		while (gifts.size() < TOTAL_ROUNDS) {
			gifts.add(new Gift());
		}
	}

	public void roundTrackerUpdate() {
		roundTracker++;
		if (roundTracker.equals(TOTAL_ROUNDS)) {
			roundTracker = -1;
		}
		roundTracker++;
	}

}
