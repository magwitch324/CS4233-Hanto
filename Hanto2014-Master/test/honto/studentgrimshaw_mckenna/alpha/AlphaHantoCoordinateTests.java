package honto.studentgrimshaw_mckenna.alpha;

import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoCoordinate;

import org.junit.Test;

public class AlphaHantoCoordinateTests {

	@Test
	public void coordinateReturns0s() {
		HantoCoordinate coordinate = new AlphaHantoCoordinate(0, 0);
		assertEquals(0, coordinate.getX());
		assertEquals(0, coordinate.getY());
	}
	
	@Test
	public void coordinateReturns11(){
		HantoCoordinate coordinate = new AlphaHantoCoordinate(1, 1);
		assertEquals(1, coordinate.getX());
		assertEquals(1, coordinate.getY());
	}
	
	@Test
	public void coordinateReturns23(){
		HantoCoordinate coordinate = new AlphaHantoCoordinate(2, 3);
		assertEquals(2, coordinate.getX());
		assertEquals(3, coordinate.getY());
	}

}
