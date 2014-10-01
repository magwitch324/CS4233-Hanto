package hanto.studentgrimshaw_mckenna.delta;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoPolicy;

public class DeltaHantoPolicy extends AbstractHantoPolicy {

	public DeltaHantoPolicy(HantoGameID id, HantoPlayerColor movesFirst) {
		super(id, movesFirst);
	}

	@Override
	protected Map<HantoPieceType, Integer> getStartingHand() {

		Map<HantoPieceType, Integer> hand = new HashMap<HantoPieceType, Integer>();

		hand.put(HantoPieceType.BUTTERFLY, 1);
		hand.put(HantoPieceType.SPARROW, 4);
		hand.put(HantoPieceType.CRAB, 4);

		return hand;
	}
	
}
