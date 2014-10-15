package hanto.studentgrimshaw_mckenna.tournament;
import static org.junit.Assert.assertNull;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

public class HantoPlayerTest {
	int playerNum = 1;

	@Test
	public void test() {
		HantoPlayer player1 = new HantoPlayer();
		HantoPlayer player2 = new HantoPlayer();
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord turnResult;
		turnResult = player1.makeMove(null);
		printResult(turnResult);
		turnResult = player2.makeMove(turnResult);
		printResult(turnResult);
		for (int i = 0; i < 100; i++) {
			turnResult = player1.makeMove(turnResult);
			if(turnResult == null){
				break;
			}
			printResult(turnResult);
			turnResult = player2.makeMove(turnResult);
			if(turnResult == null){
				break;
			}
			printResult(turnResult);
		}
	}
	
	@Test
	public void test2() {
		HantoPlayer player1 = new HantoPlayer();
		HantoPlayer player2 = new HantoPlayer();
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
		HantoMoveRecord turnResult;
		turnResult = player1.makeMove(null);
		printResult(turnResult);
		turnResult = player2.makeMove(turnResult);
		printResult(turnResult);
		for (int i = 0; i < 100; i++) {
			turnResult = player1.makeMove(turnResult);
			if(turnResult == null){
				break;
			}
			printResult(turnResult);
			turnResult = player2.makeMove(turnResult);
			if(turnResult == null){
				break;
			}
			printResult(turnResult);
		}
	}
	
	@Test
	public void testExceptionCatching(){
		HantoPlayer player1 = new HantoPlayer();
		HantoPlayer player2 = new HantoPlayer();
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
		((EpsilonSimpleGameAi)(player1.getGameAi())).setGameOver(true);
		assertNull(player1.makeMove(null));	
	}	
	

	public void printResult(HantoMoveRecord record) {
		System.out.println("Player " + playerNum + " moved " + record.getPiece() + " from " + record.getFrom() + " to "
				+ record.getTo());
		playerNum = playerNum == 1 ? 2 : 1;
	}

}
