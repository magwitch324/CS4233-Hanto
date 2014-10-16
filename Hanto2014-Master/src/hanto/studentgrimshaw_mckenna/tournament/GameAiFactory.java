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

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.factories.HantoBoardFactory;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.studentgrimshaw_mckenna.epsilon.EpsilonHantoPolicy;

/**
 * Factory for GameAis
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class GameAiFactory {
	private static final GameAiFactory INSTANCE = new GameAiFactory();

	/**
	 * Default private descriptor.
	 */
	private GameAiFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static GameAiFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates a game ai for the specified game id
	 * 
	 * @param gameId
	 *            Game version
	 * @param movesFirst
	 *            Who moves first
	 * @param doIMoveFirst
	 *            If I move first
	 * @return new GameAi
	 */
	public GameAi makeGameAi(HantoGameID gameId, HantoPlayerColor movesFirst, boolean doIMoveFirst) {
		HantoPolicy policy = new EpsilonHantoPolicy(gameId, movesFirst);
		HantoBoard board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
		GameAi game = new EpsilonSimpleGameAi(policy, board, doIMoveFirst);

		return game;
	}

}
