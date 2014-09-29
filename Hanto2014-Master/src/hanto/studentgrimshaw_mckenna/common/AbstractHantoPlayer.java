package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import java.util.Map;

public abstract class AbstractHantoPlayer implements HantoPlayer {

	private HantoPlayerColor color;
	private Map<HantoPieceType, Integer> hand;

	/**
	 * The default constructor for BetaHantoPlayer
	 * 
	 * @param policy
	 *            The game policy that gives the player their starting hand
	 * @param color
	 *            The color of the player
	 */
	protected AbstractHantoPlayer(HantoPlayerColor color) {
		this.color = color;
	}

	@Override
	public boolean canPlacePiece(int turnNumber, HantoPieceType type) {
		boolean canPlace = false;
		// if the piece type is valid
		if (hand.containsKey(type)) {
			// if its the fourth turn and the butterfly hasn't been placed yet
			if (turnNumber > 3 && hand.get(HantoPieceType.BUTTERFLY) != 0) {
				// Make sure the piece being placed is a butterfly
				canPlace = (type == HantoPieceType.BUTTERFLY);
			} else {
				canPlace = hand.get(type) > 0;
			}
		}
		return canPlace;
	}

	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	@Override
	public void decrementPieceCount(HantoPieceType pieceType) {
		int remainingPieces = hand.get(pieceType);
		remainingPieces--;
		hand.put(pieceType, remainingPieces);
	}

	@Override
	public void setStartingHand(Map<HantoPieceType, Integer> startingHand) {
		hand = startingHand;
		
	}

	@Override
	public boolean canMovePiece() {
		return hand.get(HantoPieceType.BUTTERFLY) == 0;
		
	}

}
