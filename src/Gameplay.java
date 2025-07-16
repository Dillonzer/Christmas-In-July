import lombok.Getter;
import lombok.Setter;
import models.Game;
import models.User;

import java.util.List;
import java.util.Random;

public class Gameplay {
	final static Integer ACTION_COUNT = 5;
	@Setter
	@Getter
	Game game;

	public void takeOne(User user) {
		if (!user.hasGift) {
			user.hasGift = true;
			game.gifts = game.gifts - 1;
		}
	}

	public void trade(User user) {
		User tradeUser = getRandomUser();

		if (!user.hasGift) {
			takeOne(user);
			//TODO: would trade the object here
		}

		//TODO: Swap objects w/ swap function
	}

	public void shiftLeft() {
		//TODO: With a Gift Object, you would just do it on that rather than the User object
		game.users.add(game.users.removeFirst());
	}

	public void shiftRight() {
		//TODO: With a Gift Object, you would just do it on that rather than the User object
		game.users.addFirst(game.users.removeLast());
	}

	public void steal(User user) {
		if (!user.hasGift) {
			User stealUser = getRandomUserWithGift();

			if (stealUser == null) {
				return;
			}
			
			stealUser.hasGift = false;
			user.hasGift = true;
		}
	}

	public void selectRandomAction() {
		Random rand = new Random();
		Integer n = rand.nextInt(ACTION_COUNT);
		User user = game.users.get(game.roundTracker);

		switch (n) {
			case 0: {
				takeOne(user);
				break;
			}
			case 1: {
				trade(user);
				break;
			}
			case 2: {
				shiftLeft();
				break;
			}
			case 3: {
				shiftRight();
				break;
			}
			case 4: {
				steal(user);
				break;
			}
		}

		game.roundTrackerUpdate();
	}

	private User getRandomUser() {
		Random rand = new Random();
		Integer n = rand.nextInt(game.users.size());
		if (n.equals(game.roundTracker)) {
			return getRandomUser();
		}
		return game.users.get(n);
	}

	private User getRandomUserWithGift() {
		Random rand = new Random();
		List<User> withGift = game.users.stream()
				.filter(u -> u.hasGift)
				.toList();

		if (withGift.isEmpty()) {
			return null;
		}

		Integer n = rand.nextInt(withGift.size());

		return withGift.get(n);
	}

}
