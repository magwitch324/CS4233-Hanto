package hanto.studentgrimshaw_mckenna.tournament;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.factories.HantoBoardFactory;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.studentgrimshaw_mckenna.epsilon.EpsilonHantoPolicy;

public class GameAiFactory {
	private static final GameAiFactory INSTANCE = new GameAiFactory();

	/**
	 * Default private descriptor.
	 */
	private GameAiFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static GameAiFactory getInstance() {
		return INSTANCE;
	}

	public GameAi makeGameAi(HantoGameID gameId, HantoPlayerColor movesFirst, boolean doIMoveFirst) {
		HantoPolicy policy = new EpsilonHantoPolicy(gameId, movesFirst);
		HantoBoard board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
		GameAi game = new EpsilonSimpleGameAi(policy, board, doIMoveFirst);
		
		return game;
	}
	
	

}
