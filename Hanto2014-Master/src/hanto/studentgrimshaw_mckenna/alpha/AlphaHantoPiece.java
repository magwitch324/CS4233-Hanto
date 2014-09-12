package hanto.studentgrimshaw_mckenna.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public class AlphaHantoPiece implements HantoPiece {
	HantoPlayerColor color;
	HantoPieceType type;
	
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

}
