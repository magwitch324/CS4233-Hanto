/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.common;

import hanto.studentgrimshaw_mckenna.common.factories.HantoBoardFactory;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.studentgrimshaw_mckenna.delta.DeltaHantoPolicy;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoPolicy;

/**
 * Description
 * 
 * @version Sep 22, 2014
 */
public class HantoTestGameFactory {
	private static final HantoTestGameFactory INSTANCE = new HantoTestGameFactory();

	/**
	 * Default private descriptor.
	 */
	private HantoTestGameFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoTestGameFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Create the specified Hanto game version with the Blue player moving
	 * first.
	 * 
	 * @param gameId
	 *            the version desired.
	 * @return the game instance
	 */
	public HantoTestGame makeHantoTestGame(HantoGameID gameId) {
		return makeHantoTestGame(gameId, HantoPlayerColor.BLUE);
	}

	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * 
	 * @param gameId
	 *            the version desired.
	 * @param movesFirst
	 *            the player color that moves first
	 * @return the game instance
	 */
	public HantoTestGame makeHantoTestGame(HantoGameID gameId, HantoPlayerColor movesFirst) {
		HantoPolicy policy = null;
		HantoTestGame game = null;
		HantoBoard board = null;
		switch (gameId) {
		case GAMMA_HANTO:
			policy = new GammaHantoPolicy(gameId, movesFirst);
			board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
			game = new GammaHantoTestGame(policy, board);
			break;
		case DELTA_HANTO:
			policy = new DeltaHantoPolicy(gameId, movesFirst);
			board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
			game = new DeltaHantoTestGame(policy, board);
			break;
		default:
			break;
		}
		return game;
	}
}
