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
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPlayer;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPlayer;

import java.util.Map;

/**
 * Factory for HantoPlayers
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class HantoPlayerFactory {

	private static final HantoPlayerFactory INSTANCE = new HantoPlayerFactory();

	/**
	 * Default private descriptor.
	 */
	private HantoPlayerFactory() {
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoPlayerFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates a HantoPlayer based on the game id
	 * 
	 * @param gameId
	 *            Version of the game
	 * @param color
	 *            Color of the player to create
	 * @param startingHand
	 *            Hand with which the player starts
	 * @return A HantoPlayer
	 */
	public HantoPlayer makeHantoPlayer(HantoGameID gameId, HantoPlayerColor color,
			Map<HantoPieceType, Integer> startingHand) {
		HantoPlayer player = new ConcreteHantoPlayer(color);

		player.setStartingHand(startingHand);

		return player;
	}
}
