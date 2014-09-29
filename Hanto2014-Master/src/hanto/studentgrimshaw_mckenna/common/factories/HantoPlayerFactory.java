package hanto.studentgrimshaw_mckenna.common.factories;

import java.util.Map;

import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoGame;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoGame;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoPlayer;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoPolicy;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoPlayer;
import hanto.studentgrimshaw_mckenna.common.HantoPolicy;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoGame;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoPlayer;

public class HantoPlayerFactory {

private static final HantoPlayerFactory INSTANCE = new HantoPlayerFactory();
	
	/**
	 * Default private descriptor.
	 */
	private HantoPlayerFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoPlayerFactory getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @param movesFirst the player color that moves first
	 * @return the game instance
	 */
	public HantoPlayer makeHantoPlayer(HantoGameID gameId, HantoPlayerColor color,  Map<HantoPieceType, Integer> startingHand) {
		HantoPlayer player = null;
		switch (gameId) {
		case BETA_HANTO:
			player = new BetaHantoPlayer(color);
			break;
		case GAMMA_HANTO:
			player = new GammaHantoPlayer(color);
			break;
		default:
			break;
		}
		player.setStartingHand(startingHand);
		return player;
	}
}
