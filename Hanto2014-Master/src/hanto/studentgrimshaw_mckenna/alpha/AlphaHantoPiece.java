/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Alpha implementation of HantoPiece
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class AlphaHantoPiece implements HantoPiece {
	HantoPlayerColor color;
	HantoPieceType type;

	/**
	 * Default constructor for AlphaHantoPiece.
	 * @param color Color of the piece
	 * @param type Type of piece
	 */
	public AlphaHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
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
