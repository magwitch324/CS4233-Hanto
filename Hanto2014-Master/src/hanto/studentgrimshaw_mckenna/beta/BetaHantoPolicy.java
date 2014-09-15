package hanto.studentgrimshaw_mckenna.beta;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoPieceType;
import hanto.studentgrimshaw_mckenna.common.HantoGamePolicy;

public class BetaHantoPolicy implements HantoGamePolicy {


	@Override
	public Map<HantoPieceType, Integer> getStartingHand() {
		
		Map<HantoPieceType, Integer> hand = new HashMap<HantoPieceType, Integer> (); 
		
		hand.put(HantoPieceType.BUTTERFLY, 1);
		hand.put(HantoPieceType.SPARROW, 5);
		
		return hand;
	}

}
