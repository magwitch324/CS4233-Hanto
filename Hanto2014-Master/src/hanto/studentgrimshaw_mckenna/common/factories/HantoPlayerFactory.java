package hanto.studentgrimshaw_mckenna.common.factories;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPlayer;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPlayer;

import java.util.Map;

public class HantoPlayerFactory {

	private static final HantoPlayerFactory INSTANCE = new HantoPlayerFactory();

	/**
	 * Default private descriptor.
	 */
	private HantoPlayerFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoPlayerFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * 
	 * @param gameId
	 *            the version desired.
	 * @param movesFirst
	 *            the player color that moves first
	 * @return the game instance
	 */
	public HantoPlayer makeHantoPlayer(HantoGameID gameId, HantoPlayerColor color,
			Map<HantoPieceType, Integer> startingHand) {
		HantoPlayer player = new ConcreteHantoPlayer(color);

		player.setStartingHand(startingHand);

		return player;
	}
}
