/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * Our implementation of HantoPlayer. Makes moves based on the GameAi logic
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class HantoPlayer implements HantoGamePlayer {

	GameAi game;
	HantoPlayerColor myColor;

	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst) {
		this.myColor = myColor;
		HantoPlayerColor movesFirst;
		if (doIMoveFirst) {
			movesFirst = myColor;
		} else {
			movesFirst = myColor == HantoPlayerColor.BLUE ? HantoPlayerColor.RED : HantoPlayerColor.BLUE;
		}
		game = GameAiFactory.getInstance().makeGameAi(version, movesFirst, doIMoveFirst);

	}

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		HantoMoveRecord move;
		try {
			if (opponentsMove != null) {
				game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
			}

			move = game.MakeAiMove();

		} catch (HantoException e) {
			System.out.println(e.getMessage());
			move = null;
		}
		return move;
	}

	public GameAi getGameAi() {
		return game;
	}
}
