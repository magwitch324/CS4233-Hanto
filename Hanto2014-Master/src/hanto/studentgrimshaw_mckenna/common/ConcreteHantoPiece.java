/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common;

import java.util.Map;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.validators.MoveValidator;

/**
 * Concrete implementation of HantoPiece
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class ConcreteHantoPiece implements HantoPiece {
	private HantoPlayerColor color;
	private HantoPieceType type;
	private MoveValidator validator;

	/**
	 * Default constructor for ConcreteHantoPiece.
	 * 
	 * @param color
	 *            Color of the piece
	 * @param type
	 *            Type of piece
	 */
	public ConcreteHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
	}

	/**
	 * Sets the validator for the piece
	 * 
	 * @param validator
	 */
	public void setValidator(MoveValidator validator) {
		this.validator = validator;
	}

	/**
	 * Validates that the piece is able to make the specified move
	 * 
	 * @param board
	 *            The board containing all the pieces
	 * @param from
	 *            Original coordinate of the piece
	 * @param to
	 *            Desired destination
	 * @throws HantoException
	 *             If the move is invalid
	 */
	public void validateMove(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {
		validator.validateMove(board, from, to);

	}

	/**
	 * Recursive function that checks to make sure that all pieces can be
	 * reached after a move is made
	 * 
	 * @param board
	 *            The board containing all the pieces
	 * @param status
	 *            Map containing the status of each piece
	 * @param centerCoord
	 *            Curretn coordinate
	 */
	public void checkSingleBlock(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			Map<ConcreteHantoCoordinate, Boolean> status, ConcreteHantoCoordinate centerCoord) {
		validator.checkSingleBlock(board, status, centerCoord);

	}

	@Override
	public HantoPlayerColor getColor() {

		return color;
	}

	@Override
	public HantoPieceType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String toString() {
		return "Color: " + color + "\tType: " + type;
	}

}
