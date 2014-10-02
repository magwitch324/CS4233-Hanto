/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Move validator for walking
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class WalkMoveValidator extends MoveValidator {

	@Override
	protected void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {

		boolean isNextTo = false;

		for (ConcreteHantoCoordinate coord : from.getNeighborCoordinates()) {
			if (coord.equals(to)) {
				isNextTo = true;
				break;
			}
		}

		if (!isNextTo) {
			throw new HantoException("Distance is greater than 1");
		}

		checkSlideToDest(board, from, to);

	}

	/**
	 * Checks to make sure the piece can slide to the destination
	 * 
	 * @param board
	 *            Board containing the pieces
	 * @param from
	 *            Source
	 * @param to
	 *            Destination
	 * @throws HantoException
	 *             If the piece cannot slide to the destination
	 */
	private void checkSlideToDest(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {

		// calculate the intersection of the neighbors
		List<ConcreteHantoCoordinate> toList = new ArrayList<ConcreteHantoCoordinate>(Arrays.asList(to
				.getNeighborCoordinates()));
		List<ConcreteHantoCoordinate> fromList = new ArrayList<ConcreteHantoCoordinate>(Arrays.asList(from
				.getNeighborCoordinates()));
		toList.retainAll(fromList);

		boolean canSlide = false;

		for (ConcreteHantoCoordinate coord : toList) {
			if (!board.containsKey(coord)) {
				canSlide = true;
				break;
			}
		}

		if (!canSlide) {
			throw new HantoException("Cannot slide to destination");
		}

	}

}
