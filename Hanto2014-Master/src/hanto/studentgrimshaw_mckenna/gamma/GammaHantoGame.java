package hanto.studentgrimshaw_mckenna.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoGame;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;

public class GammaHantoGame extends AbstractHantoGame {

	public GammaHantoGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected MoveResult checkResigning(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo) {
		return null;
	}
}
