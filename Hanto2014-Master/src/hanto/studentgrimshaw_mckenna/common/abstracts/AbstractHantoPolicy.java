package hanto.studentgrimshaw_mckenna.common.abstracts;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.factories.HantoPlayerFactory;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPlayer;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHantoPolicy implements HantoPolicy {


	private HantoPlayerColor movesFirst;
	private HantoPlayerColor movesSecond;
	private HantoGameID id;

	protected AbstractHantoPolicy(HantoGameID id, HantoPlayerColor movesFirst) {
		this.id = id;
		this.movesFirst = movesFirst;
		movesSecond = movesFirst == HantoPlayerColor.BLUE ? HantoPlayerColor.RED : HantoPlayerColor.BLUE;
	}

	protected Map<HantoPieceType, Integer> getStartingHand() {

		Map<HantoPieceType, Integer> hand = new HashMap<HantoPieceType, Integer>();

		hand.put(HantoPieceType.BUTTERFLY, 1);
		hand.put(HantoPieceType.SPARROW, 5);

		return hand;
	}
	
	@Override
	public HantoPlayer constructPlayer1() {
		HantoPlayer player1 = HantoPlayerFactory.getInstance().makeHantoPlayer(id, movesFirst, getStartingHand());
		return player1;
	}

	@Override
	public HantoPlayer constructPlayer2() {
		HantoPlayer player2 = HantoPlayerFactory.getInstance().makeHantoPlayer(id, movesSecond, getStartingHand());
		return player2;
	}

	@Override
	public int getMaxTurns() {
		return Integer.MAX_VALUE;
	}

	@Override
	public HantoGameID getId() {
		return id;
	}
	
}