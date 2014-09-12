package honto.studentgrimshaw_mckenna.alpha;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoCoordinate;

import org.junit.Test;

public class AlphaHantoCoordinateTest {


	@Test
	public void shouldHaveHash961(){
		AlphaHantoCoordinate coord = new AlphaHantoCoordinate(0, 0);
		assertEquals(961, coord.hashCode());
	}
	
	@Test
	public void testGetNeighborCoordinates() {
		AlphaHantoCoordinate coord = new AlphaHantoCoordinate(0, 0);
		AlphaHantoCoordinate[] coordArray = coord.getNeighborCoordinates();
		assertEquals(coordArray[0], new AlphaHantoCoordinate(0, 1));
		assertEquals(coordArray[1], new AlphaHantoCoordinate(1, 0));
		assertEquals(coordArray[2], new AlphaHantoCoordinate(1, -1));
		assertEquals(coordArray[3], new AlphaHantoCoordinate(0, -1));
		assertEquals(coordArray[4], new AlphaHantoCoordinate(-1, 0));
		assertEquals(coordArray[5], new AlphaHantoCoordinate(-1, 1));
	}

	@Test
	public void testEqualsObject() {
		AlphaHantoCoordinate coord = new AlphaHantoCoordinate(0, 0);
		GregorianCalendar gc = new GregorianCalendar();
		assertNotEquals(coord, gc);
	}

}
