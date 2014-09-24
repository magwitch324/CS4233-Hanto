package hanto.common;

import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoPolicy;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoGame;

public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame {

	public GammaHantoTestGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		// TODO Auto-generated method stub
		for(PieceLocationPair pair : initialPieces) {
			
		}

	}

	@Override
	public void setTurnNumber(int turnNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		// TODO Auto-generated method stub

	}

}
