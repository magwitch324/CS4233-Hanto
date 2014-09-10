package hanto.studentgrimshaw_mckenna.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

public class AlphaHantoGame implements HantoGame {
	private HantoPlayerColor activePlayer;
	private boolean isFirstTurn;

	public AlphaHantoGame(HantoPlayerColor movesFirst) {
		this.setActivePlayer(movesFirst);
		this.isFirstTurn = true;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		//Ensure the piece type is correct for this version of Hanto
		validatePieceType(pieceType); 
		//Validate the piece location
		validatePieceLocation(to);
		
		//Upon the player making a valid move switch the active player.
		changeActivePlayer();
		
		return MoveResult.OK;
	}
	
	/**
	 * 
	 * @param to
	 * @throws HantoException
	 */
	private void validatePieceLocation(HantoCoordinate to) throws HantoException {
		// TODO Auto-generated method stub
		if(isFirstTurn) {
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("Invalid first move!");
			}
		}
		else {
			// TODO implement validation to check adjacent pieces.
		}
	}

	/**
	 * 
	 * @param pieceType
	 * @throws HantoException
	 */
	private void validatePieceType(HantoPieceType pieceType)
			throws HantoException {
		if (pieceType != HantoPieceType.BUTTERFLY) {
			throw new HantoException("Invalid Piece Type");
		}
	}

	/**
	 * 
	 */
	private void changeActivePlayer() {
		activePlayer = (activePlayer == HantoPlayerColor.BLUE) ? HantoPlayerColor.RED
				: HantoPlayerColor.BLUE;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the activePlayer
	 */
	public HantoPlayerColor getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param activePlayer
	 *            the activePlayer to set
	 */
	public void setActivePlayer(HantoPlayerColor activePlayer) {
		this.activePlayer = activePlayer;
	}

}
