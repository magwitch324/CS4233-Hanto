/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.epsilon;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoGame;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

/**
 * Epsilon implementation of hanto game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class EpsilonHantoGame extends AbstractHantoGame {

	public EpsilonHantoGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
	}

	@Override
	protected MoveResult checkResigning(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo)
			throws HantoPrematureResignationException {
		MoveResult result = super.checkResigning(pieceType, hFrom, hTo);
		if (result != null) {
			if (!activePlayer.isHandEmpty()) {
				List<ConcreteHantoCoordinate> availablePlacements = board.getPlayersAvailablePlacements(
						activePlayer.getColor(), turnNumber);
				if (availablePlacements.size() > 0) {
					throw new HantoPrematureResignationException();
				}
			}

			List<HantoMoveRecord> availableMoves = board.getPlayersAvailableMoves(activePlayer.getColor());
			if (availableMoves.size() > 0) {
				throw new HantoPrematureResignationException();
			}
		}
		return result;
	}

}
