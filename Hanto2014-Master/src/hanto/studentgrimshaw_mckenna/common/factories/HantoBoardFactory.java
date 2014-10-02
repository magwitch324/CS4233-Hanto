package hanto.studentgrimshaw_mckenna.common.factories;

import hanto.common.HantoGameID;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.validators.AllyNeighborPlacementValidator;
import hanto.studentgrimshaw_mckenna.common.validators.AnyNeighborPlacementValidator;

public class HantoBoardFactory {
	private static final HantoBoardFactory INSTANCE = new HantoBoardFactory();

	/**
	 * Default private descriptor.
	 */
	private HantoBoardFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoBoardFactory getInstance() {
		return INSTANCE;
	}

	public HantoBoard makeHantoBoard(HantoGameID gameId) {
		HantoBoard board = new ConcreteHantoBoard();
		switch (gameId) {
		case BETA_HANTO:
			board.setPlacementNeighborValidator(new AnyNeighborPlacementValidator());
			break;
		default:
			board.setPlacementNeighborValidator(new AllyNeighborPlacementValidator());
			break;
		}

		return board;
	}
}
