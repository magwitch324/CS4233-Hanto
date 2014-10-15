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

public class EpsilonSimpleGameAi extends EpsilonHantoGame implements GameAi {

	boolean doIMoveFirst;

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

	private HantoMoveRecord aiMovePiece() throws HantoException {
		HantoMoveRecord move;
		List<HantoMoveRecord> availableMoves = board.checkPlayerHasAvailableMove(activePlayer.getColor());
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

	private HantoMoveRecord aiPlacePiece() throws HantoException {
		ConcreteHantoCoordinate coord;
		HantoMoveRecord record;
		List<ConcreteHantoCoordinate> availablePositions = board.getAvailablePlacementPlace(activePlayer.getColor(),
				turnNumber);
		if (availablePositions.size() == 0) {
			record = null;
		} else {
			Random generator = new Random();
			int i = generator.nextInt(availablePositions.size());
			boolean endangersButterfly = true;
			coord = availablePositions.get(i);
			while (availablePositions.size() > 1 && endangersButterfly) {
				
				if (surroundsButterfly(coord)) {
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

	private boolean surroundsButterfly(ConcreteHantoCoordinate coord) {
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

	public HantoBoard getBoard() {
		return board;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setTurn(int i) {
		turnNumber = i;

	}

}
