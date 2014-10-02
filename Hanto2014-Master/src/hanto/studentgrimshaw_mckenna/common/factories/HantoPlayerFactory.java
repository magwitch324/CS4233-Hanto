package hanto.studentgrimshaw_mckenna.common.factories;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoPlayer;
import hanto.studentgrimshaw_mckenna.common.HantoPlayer;
import hanto.studentgrimshaw_mckenna.delta.DeltaHantoPlayer;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoPlayer;

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
		HantoPlayer player = null;
		switch (gameId) {
		case BETA_HANTO:
			player = new BetaHantoPlayer(color);
			break;
		case GAMMA_HANTO:
			player = new GammaHantoPlayer(color);
			break;
		case DELTA_HANTO:
			player = new DeltaHantoPlayer(color);
			break;
		default:
			break;
		}
		if (player != null) {
			player.setStartingHand(startingHand);
		}
		return player;
	}
}
