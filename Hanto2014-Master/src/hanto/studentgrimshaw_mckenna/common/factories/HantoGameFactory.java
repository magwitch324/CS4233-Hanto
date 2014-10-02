/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common.factories;

import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoGame;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoGame;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoPolicy;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.studentgrimshaw_mckenna.delta.DeltaHantoGame;
import hanto.studentgrimshaw_mckenna.delta.DeltaHantoPolicy;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoGame;
import hanto.studentgrimshaw_mckenna.gamma.GammaHantoPolicy;

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto game.
 * 
 * @author gpollice
 * @version Feb 5, 2013
 */
public class HantoGameFactory
{
	private static final HantoGameFactory INSTANCE = new HantoGameFactory();
	
	/**
	 * Default private descriptor.
	 */
	private HantoGameFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoGameFactory getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 * Create the specified Hanto game version with the Blue player moving
	 * first.
	 * @param gameId the version desired.
	 * @return the game instance
	 */
	public HantoGame makeHantoGame(HantoGameID gameId)
	{
		return makeHantoGame(gameId, HantoPlayerColor.BLUE);
	}
	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @param movesFirst the player color that moves first
	 * @return the game instance
	 */
	public HantoGame makeHantoGame(HantoGameID gameId, HantoPlayerColor movesFirst) {
		HantoGame game = null;
		HantoPolicy policy = null;
		HantoBoard board = null;
		switch (gameId) {
		case ALPHA_HANTO:
			game = new AlphaHantoGame(movesFirst);
			break;
		case BETA_HANTO:
			policy = new BetaHantoPolicy(gameId, movesFirst);
			board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
			game = new BetaHantoGame(policy, board);
			break;
		case GAMMA_HANTO:
			policy = new GammaHantoPolicy(gameId, movesFirst);
			board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
			game = new GammaHantoGame(policy, board);
			break;
		case DELTA_HANTO:
			policy = new DeltaHantoPolicy(gameId, movesFirst);
			board = HantoBoardFactory.getInstance().makeHantoBoard(gameId);
			game = new DeltaHantoGame(policy, board);
		default:
			break;
		}
		return game;
	}
}
