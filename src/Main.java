import models.Game;

public class Main {
	final Integer TOTAL_ROUNDS = 23;

	public static void main(String[] args) {
		Integer gameCount = 1000;
		Integer turns = 0;

		for (int i = 0; i < gameCount; i++) {
			Gameplay gameplay = new Gameplay();
			gameplay.setGame(new Game());
			gameplay.game.populateUsers();


			while (gameplay.game.gifts > 0) {
				gameplay.selectRandomAction();
				turns++;
			}
		}


		System.out.println("The average amount of turns in " + gameCount + " games is " + turns / gameCount);

	}

}