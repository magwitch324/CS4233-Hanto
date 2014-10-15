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
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.validators.FlyMoveValidator;
import hanto.studentgrimshaw_mckenna.common.validators.JumpMoveValidator;
import hanto.studentgrimshaw_mckenna.common.validators.WalkMoveValidator;

/**
 * Factory for HantoPieces
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class HantoPieceFactory {

	HantoGameID id;

	/**
	 * Factory constructor
	 * 
	 * @param id
	 *            Game version
	 */
	public HantoPieceFactory(HantoGameID id) {
		this.id = id;
	}

	/**
	 * Creates a hantoPiece based on the rules for the gameId
	 * 
	 * @param color
	 *            Color of the player who owns the piece
	 * @param type
	 *            Type of piece to create
	 * @return A correctly configured piece
	 */
	public ConcreteHantoPiece makeHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		ConcreteHantoPiece piece = null;
		switch (id) {
		case BETA_HANTO:
			piece = createBetaPiece(color, type);
			break;
		case GAMMA_HANTO:
			piece = createGammaPiece(color, type);
			break;
		case DELTA_HANTO:
			piece = createDeltaPiece(color, type);
			break;
		case EPSILON_HANTO:
			piece = createEpsilonPiece(color, type);
		default:
			break;
		}
		return piece;
	}

	/**
	 * Creates BetaHanto pieces
	 * 
	 * @param color
	 *            Color of the piece
	 * @param type
	 *            Type of piece to create
	 * @return A correctly configured piece
	 */
	private ConcreteHantoPiece createBetaPiece(HantoPlayerColor color, HantoPieceType type) {
		return new ConcreteHantoPiece(color, type);
	}

	/**
	 * Creates GammaHanto pieces
	 * 
	 * @param color
	 *            Color of the piece
	 * @param type
	 *            Type of piece to create
	 * @return A correctly configured piece
	 */
	private ConcreteHantoPiece createGammaPiece(HantoPlayerColor color, HantoPieceType type) {
		ConcreteHantoPiece piece = new ConcreteHantoPiece(color, type);
		piece.setValidator(new WalkMoveValidator());
		return piece;
	}

	/**
	 * Creates DeltaHanto pieces
	 * 
	 * @param color
	 *            Color of the piece
	 * @param type
	 *            Type of piece to create
	 * @return A correctly configured piece
	 */
	private ConcreteHantoPiece createDeltaPiece(HantoPlayerColor color, HantoPieceType type) {
		ConcreteHantoPiece piece = new ConcreteHantoPiece(color, type);
		switch (type) {
		case BUTTERFLY:
		case CRAB:
			piece.setValidator(new WalkMoveValidator());
			break;
		case SPARROW:
			piece.setValidator(new FlyMoveValidator());
			break;
		default:
			break;
		}
		return piece;
	}
	
	
	/**
	 * Creates EpsilonHanto pieces
	 * 
	 * @param color
	 *            Color of the piece
	 * @param type
	 *            Type of piece to create
	 * @return A correctly configured piece
	 */
	private ConcreteHantoPiece createEpsilonPiece(HantoPlayerColor color, HantoPieceType type) {
		ConcreteHantoPiece piece = new ConcreteHantoPiece(color, type);
		switch (type) {
		case BUTTERFLY:
		case CRAB:
			piece.setValidator(new WalkMoveValidator());
			break;
		case SPARROW:
			piece.setValidator(new FlyMoveValidator(4));
			break;
		case HORSE:
			piece.setValidator(new JumpMoveValidator());
			break;
		default:
			break;
		}
		return piece;
	}
}
