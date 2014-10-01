package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

import java.util.HashMap;
import java.util.Map;

public abstract class MoveValidator {
	public MoveValidator() {

	}

	public void validateMove(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {
		checkDestinationValid(board, to);
		checkCanLeave(board, from, to);
		checkDestinationReachable(board, from, to);
		
	}

	private void checkDestinationValid(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate to) throws HantoException {
		if(board.containsKey(to)){
			throw new HantoException("Destination already occupied");
		}

	}

	private void checkCanLeave(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {
		
		//clone the board
		Map<ConcreteHantoCoordinate, ConcreteHantoPiece> newBoard = new HashMap<ConcreteHantoCoordinate, ConcreteHantoPiece>();
		newBoard.putAll(board);
		
		//Move the piece
		newBoard.put(to, board.get(from));
		newBoard.remove(from);
		
		//initialize the status Map
		Map<ConcreteHantoCoordinate, Boolean> status = new HashMap<ConcreteHantoCoordinate, Boolean>();
		for(ConcreteHantoCoordinate coord : newBoard.keySet()){
			status.put(coord, false);
		}
		
		status.put(to, true);
		
		checkSingleBlock(newBoard, status, to);
		
		//Check that all statuses are true
		for(Boolean coordStatus : status.values()){
			if(!coordStatus){
				throw new HantoException("Move breaks chain");
			}
		}

	}
	
	public void checkSingleBlock(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, Map<ConcreteHantoCoordinate, Boolean> status, ConcreteHantoCoordinate centerCoord){
		for(ConcreteHantoCoordinate coord : centerCoord.getNeighborCoordinates() ){
			if(status.containsKey(coord) && !status.get(coord)){
				status.put(coord, true);
				board.get(coord).checkSingleBlock(board, status, coord);
			}
		}
	}

	protected abstract void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException;
}
