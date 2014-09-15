package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoPieceType;

import java.util.Map;

public interface HantoGamePolicy {
	Map<HantoPieceType, Integer> getStartingHand();
}
