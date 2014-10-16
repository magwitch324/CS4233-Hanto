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
import hanto.tournament.HantoMoveRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Move validator for flying
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class FlyMoveValidator extends MoveValidator {

	int maxDistance;

	/**
	 * Default constructor for FlyMoveValidator
	 */
	public FlyMoveValidator() {
		maxDistance = Integer.MAX_VALUE;
	}

	/**
	 * Constructor for FlyMoveValidator that specifies the mad distance
	 * 
	 * @param maxDistance
	 *            Maximum distance it is allowed to fly
	 */
	public FlyMoveValidator(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	@Override
	protected void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {
		int distance = from.getDistance(to);

		if (distance > maxDistance) {
			throw new HantoException("Distance is greater than allowed " + maxDistance);
		}
	}

	@Override
	public List<HantoMoveRecord> getAvailableMoves(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate currentPosition) {

		List<HantoMoveRecord> availableMoves = new ArrayList<HantoMoveRecord>();

		// checks if it can move next to every neighbor of each piece
		Iterator<Map.Entry<ConcreteHantoCoordinate, ConcreteHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<ConcreteHantoCoordinate, ConcreteHantoPiece> pair = it.next();
			for (ConcreteHantoCoordinate coord : pair.getKey().getNeighborCoordinates()) {
				try {
					validateMove(board, currentPosition, coord);
					availableMoves
							.add(new HantoMoveRecord(board.get(currentPosition).getType(), currentPosition, coord));
				} catch (HantoException e) {
					e.getMessage();
				}
			}
		}
		return availableMoves;
	}

}
