package hanto.studentgrimshaw_mckenna.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.tournament.HantoMoveRecord;

public interface GameAi extends HantoGame {

	HantoMoveRecord MakeAiMove() throws HantoException;
	
	
}
