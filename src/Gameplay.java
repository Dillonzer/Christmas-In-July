import lombok.Getter;
import lombok.Setter;
import models.Game;
import models.Gift;
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
			user.gift = getRandomGift();
		}
	}

	public void trade(User user) {
		User tradeUser = getRandomUserWithGift();

		if (tradeUser == null) {
			return;
		}

		if (!user.hasGift) {
			takeOne(user);
		}

		swap(tradeUser, user);

	}

	public void shiftRight() {
		for (Integer i = 0; i < game.users.size(); i++) {
			if (i.equals(0)) {
				swap(game.users.getLast(), game.users.getFirst());
				continue;
			}

			swap(game.users.get(i), game.users.get(i++));
		}
	}

	public void shiftLeft() {
		for (Integer i = game.users.size() - 1; i > 0; i--) {
			if (i.equals(game.users.size() - 1)) {
				swap(game.users.getLast(), game.users.getFirst());
				continue;
			}

			swap(game.users.get(i), game.users.get(i--));
		}
	}

	public void steal(User user) {
		if (!user.hasGift) {
			User stealUser = getRandomUserWithGift();

			if (stealUser == null) {
				return;
			}

			user.gift = stealUser.gift;
			stealUser.gift = null;
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

	private Gift getRandomGift() {
		Random rand = new Random();
		Integer n = rand.nextInt(game.gifts.size());
		Gift returnGift = game.gifts.get(n);
		game.gifts.remove(n.intValue());
		return returnGift;
	}

	private void swap(User user1, User user2) {
		Gift temp = user1.gift;
		user1.gift = user2.gift;
		user2.gift = temp;
	}

}
