/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.tournament;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.studentgrimshaw_mckenna.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;
import java.util.Random;

/**
 * A simple game ai designed to play epsilon hanto
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class EpsilonSimpleGameAi extends EpsilonHantoGame implements GameAi {

	boolean doIMoveFirst;

	/**
	 * Default constructor for EpsilonSimpleGameAi
	 * 
	 * @param policy
	 *            Game rules
	 * @param board
	 *            Game board
	 * @param doIMoveFirst
	 *            True if i move first
	 */
	public EpsilonSimpleGameAi(HantoPolicy policy, HantoBoard board, boolean doIMoveFirst) {
		super(policy, board);
		this.doIMoveFirst = doIMoveFirst;
	}

	@Override
	public HantoMoveRecord MakeAiMove() throws HantoException {

		HantoMoveRecord record = null;

		if (!activePlayer.isHandEmpty()) {
			record = aiPlacePiece();
		}
		if (record == null) {
			record = aiMovePiece();
		}

		return record;
	}

	/**
	 * Determines a random move for the player
	 * 
	 * @return MoveRecord of move
	 * @throws HantoException
	 */
	private HantoMoveRecord aiMovePiece() throws HantoException {
		HantoMoveRecord move;
		List<HantoMoveRecord> availableMoves = board.getPlayersAvailableMoves(activePlayer.getColor());
		if (availableMoves.size() == 0) {
			move = new HantoMoveRecord(null, null, null);
		} else {
			Random generator = new Random();
			int i = generator.nextInt(availableMoves.size());
			move = availableMoves.get(i);
			makeMove(move.getPiece(), move.getFrom(), move.getTo());
		}
		return move;
	}

	/**
	 * Determines a random position to place a piece
	 * 
	 * @return MoveRecord of the placement
	 * @throws HantoException
	 */
	private HantoMoveRecord aiPlacePiece() throws HantoException {
		ConcreteHantoCoordinate coord;
		HantoMoveRecord record;
		List<ConcreteHantoCoordinate> availablePositions = board.getPlayersAvailablePlacements(activePlayer.getColor(),
				turnNumber);
		if (availablePositions.size() == 0) {
			record = null;
		} else {
			Random generator = new Random();
			int i = generator.nextInt(availablePositions.size());
			boolean endangersButterfly = true;
			coord = availablePositions.get(i);
			while (availablePositions.size() > 1 && endangersButterfly) {

				if (doesSurroundButterfly(coord)) {
					availablePositions.remove(coord);
					i = generator.nextInt(availablePositions.size());
					coord = availablePositions.get(i);
				} else {
					endangersButterfly = false;
				}
			}

			HantoPieceType type = getRandomPieceType();
			makeMove(type, null, coord);
			record = new HantoMoveRecord(type, null, coord);
		}
		return record;
	}

	/**
	 * determines if a placement will surround the butterfly
	 * 
	 * @param coord
	 *            Placement coord
	 * @return True if the placement will surround the butterfly
	 */
	private boolean doesSurroundButterfly(ConcreteHantoCoordinate coord) {
		boolean inDanger = false;
		for (ConcreteHantoCoordinate neighbor : coord.getNeighborCoordinates()) {
			HantoPiece piece = board.getPieceAt(neighbor);
			if (piece != null && piece.getType() == HantoPieceType.BUTTERFLY) {
				int x = 0;
				for (ConcreteHantoCoordinate bNeighbor : neighbor.getNeighborCoordinates()) {
					if (board.getPieceAt(bNeighbor) != null) {
						x++;
					}
				}
				if (x == 5) {
					inDanger = true;
				}
			}
		}
		return inDanger;
	}

	/**
	 * Gets a random piece type that the player has available
	 * 
	 * @return A random piece type
	 */
	private HantoPieceType getRandomPieceType() {
		HantoPieceType type = null;
		if (turnNumber == 3) {
			type = HantoPieceType.BUTTERFLY;
		} else {
			HantoPieceType[] types = { HantoPieceType.CRAB, HantoPieceType.HORSE, HantoPieceType.SPARROW };
			int i = 0;
			boolean isValidPiece = false;
			while (!isValidPiece) {
				type = types[i];

				if (activePlayer.canPlacePiece(turnNumber, type)) {
					isValidPiece = true;
				} else {
					i++;
				}
			}
		}
		return type;
	}

	/**
	 * Gets the board. used for testing
	 * 
	 * @return board
	 */
	public HantoBoard getBoard() {
		return board;
	}

	/**
	 * sets the gameover condition. used for testing
	 * 
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * sets the current turn count. used for testing
	 * 
	 * @param i
	 *            Turn
	 */
	public void setTurn(int i) {
		turnNumber = i;

	}

}
