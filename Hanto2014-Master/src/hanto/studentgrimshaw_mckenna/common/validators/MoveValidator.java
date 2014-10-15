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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for move validators.
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public abstract class MoveValidator {

	/**
	 * Validates that the piece can move from src to dst
	 * 
	 * @param board
	 *            Board containing the pieces
	 * @param from
	 *            Source
	 * @param to
	 *            Destination
	 * @throws HantoException
	 *             If the move is invalid
	 */
	public void validateMove(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {
		checkDestinationValid(board, to);
		checkCanLeave(board, from, to);
		checkDestinationReachable(board, from, to);

	}

	/**
	 * Makes sure the destination if not occupied
	 * 
	 * @param board
	 *            Board containing the pieces
	 * @param to
	 *            Destination
	 * @throws HantoException
	 *             If the destination is occupied
	 */
	private void checkDestinationValid(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate to) throws HantoException {
		if (board.containsKey(to)) {
			throw new HantoException("Destination already occupied");
		}

	}

	/**
	 * Validates that the piece moving does not break the chain
	 * 
	 * @param board
	 *            Board containing the pieces
	 * @param from
	 *            Source
	 * @param to
	 *            Destination
	 * @throws HantoException
	 *             If the move breakes the chain
	 */
	private void checkCanLeave(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {

		// clone the board
		Map<ConcreteHantoCoordinate, ConcreteHantoPiece> newBoard = new HashMap<ConcreteHantoCoordinate, ConcreteHantoPiece>();
		newBoard.putAll(board);

		// Move the piece
		newBoard.put(to, board.get(from));
		newBoard.remove(from);

		// initialize the status Map
		Map<ConcreteHantoCoordinate, Boolean> status = new HashMap<ConcreteHantoCoordinate, Boolean>();
		for (ConcreteHantoCoordinate coord : newBoard.keySet()) {
			status.put(coord, false);
		}

		status.put(to, true);

		checkSingleBlock(newBoard, status, to);

		// Check that all statuses are true
		for (Boolean coordStatus : status.values()) {
			if (!coordStatus) {
				throw new HantoException("Move breaks chain");
			}
		}
	}

	/**
	 * Recursively checks to make sure all pieces can be reached after move is
	 * made
	 * 
	 * @param board
	 *            Board containing the pieces
	 * @param status
	 *            Status of the pieces
	 * @param centerCoord
	 *            Current piece
	 */
	public void checkSingleBlock(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			Map<ConcreteHantoCoordinate, Boolean> status, ConcreteHantoCoordinate centerCoord) {
		for (ConcreteHantoCoordinate coord : centerCoord.getNeighborCoordinates()) {
			if (status.containsKey(coord) && !status.get(coord)) {
				status.put(coord, true);
				board.get(coord).checkSingleBlock(board, status, coord);
			}
		}
	}

	/**
	 * Checks to make sure the piece can get to the destination
	 * 
	 * @param board
	 *            Board containing the pieces
	 * @param from
	 *            Source
	 * @param to
	 *            Destination
	 * @throws HantoException
	 *             If the destination is unreachable
	 */
	protected abstract void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException;

	public abstract List<HantoMoveRecord> checkNoMoveAvailable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate currentPosition);
}
