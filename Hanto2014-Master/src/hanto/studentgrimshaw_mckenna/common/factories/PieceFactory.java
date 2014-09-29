package hanto.studentgrimshaw_mckenna.common.factories;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.WalkMoveValidator;

public class PieceFactory {

	HantoGameID id;

	/**
	 * Default private descriptor.
	 */
	public PieceFactory(HantoGameID id) {
		this.id = id;
	}

	public ConcreteHantoPiece makeHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		ConcreteHantoPiece piece = null;
		switch (id) {
		case BETA_HANTO:
			piece = createBetaPiece(color, type);
			break;
		case GAMMA_HANTO:
			piece = createGammaPiece(color, type);
			piece.setValidator(new WalkMoveValidator());
			break;
		case DELTA_HANTO:
			break;
		default:
			break;
		}
		return piece;
	}

	private ConcreteHantoPiece createGammaPiece(HantoPlayerColor color, HantoPieceType type) {
		// TODO fix this shiz
		return new ConcreteHantoPiece(color, type);
	}

	private ConcreteHantoPiece createBetaPiece(HantoPlayerColor color, HantoPieceType type) {
		return new ConcreteHantoPiece(color, type);
	}
}
