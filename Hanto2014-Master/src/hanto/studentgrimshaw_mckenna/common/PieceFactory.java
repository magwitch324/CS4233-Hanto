package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

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
			break;
		case DELTA_HANTO:
			break;
		default:
			break;
		}
		return piece;
	}

	private ConcreteHantoPiece createBetaPiece(HantoPlayerColor color, HantoPieceType type) {
		return new ConcreteHantoPiece(color, type);
	}
}
