package hanto.studentgrimshaw_mckenna.gamma;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoPolicy;

public class GammaHantoPolicy extends AbstractHantoPolicy {

	public GammaHantoPolicy(HantoGameID id, HantoPlayerColor movesFirst) {
		super(id, movesFirst);
	}
	
	@Override
	public int getMaxTurns() {
		return 20;
	}

}
