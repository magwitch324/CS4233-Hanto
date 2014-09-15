package hanto.studentgrimshaw_mckenna.beta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.HantoGamePolicy;

import java.util.Map;

public class BetaPlayer {
	HantoPlayerColor color;
	Map<HantoPieceType, Integer> hand;

	BetaPlayer(HantoGamePolicy policy, HantoPlayerColor color) {
		hand = policy.getStartingHand();
		this.color = color;
	}

	boolean canPlacePiece(double halfTurns, HantoPieceType type) {
		boolean canPlace = false;
		//if the piece type is valid
		if (hand.containsKey(type)) {
			//if its the fourth turn and the butterfly hasnt been placed yet
			if (halfTurns / 2 > 3 && hand.get(HantoPieceType.BUTTERFLY) != 0) {
				//Make sure the piece being placed is a butterfly
				canPlace = (type == HantoPieceType.BUTTERFLY);
			} else {
				canPlace = hand.get(type) > 0;
			}
		}
		return canPlace;
	}

	public HantoPlayerColor getColor() {
		return color;
	}

	public void decrementPieceCount(HantoPieceType pieceType) {
		int remainingPieces = hand.get(pieceType);
		remainingPieces--;
		hand.put(pieceType, remainingPieces);
	}
}
