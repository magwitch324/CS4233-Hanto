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

import hanto.common.HantoGameID;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.validators.AllyNeighborPlacementValidator;
import hanto.studentgrimshaw_mckenna.common.validators.AnyNeighborPlacementValidator;

/**
 * Factory for HantoBoards
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class HantoBoardFactory {
	private static final HantoBoardFactory INSTANCE = new HantoBoardFactory();

	/**
	 * Default private descriptor.
	 */
	private HantoBoardFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoBoardFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates a HantoBoard based on the gameId
	 * 
	 * @param gameId
	 *            Version of the board for which to create the game
	 * @return A HantoBoard
	 */
	public HantoBoard makeHantoBoard(HantoGameID gameId) {
		HantoBoard board = new ConcreteHantoBoard();
		switch (gameId) {
		case BETA_HANTO:
			board.setPlacementNeighborValidator(new AnyNeighborPlacementValidator());
			break;
		default:
			board.setPlacementNeighborValidator(new AllyNeighborPlacementValidator());
			break;
		}

		return board;
	}
}
