/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package honto.studentgrimshaw_mckenna.alpha;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import hanto.common.HantoCoordinate;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoCoordinate;

import org.junit.Test;

/**
 * Tests for AlphaHantoCoordinate
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class AlphaHantoCoordinateTest {

	@Test
	public void coordinateReturns0s() {
		HantoCoordinate coordinate = new AlphaHantoCoordinate(0, 0);
		assertEquals(0, coordinate.getX());
		assertEquals(0, coordinate.getY());
	}

	@Test
	public void coordinateReturns11() {
		HantoCoordinate coordinate = new AlphaHantoCoordinate(1, 1);
		assertEquals(1, coordinate.getX());
		assertEquals(1, coordinate.getY());
	}

	@Test
	public void coordinateReturns23() {
		HantoCoordinate coordinate = new AlphaHantoCoordinate(2, 3);
		assertEquals(2, coordinate.getX());
		assertEquals(3, coordinate.getY());
	}

	@Test
	public void shouldHaveHash961() {
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

	@Test
	public void testEqualsWithSelf() {
		AlphaHantoCoordinate coord = new AlphaHantoCoordinate(0, 0);
		assertEquals(coord, coord);
	}

	@Test
	public void testEqualsWithOtherCoord() {
		AlphaHantoCoordinate coord1 = new AlphaHantoCoordinate(0, 0);
		AlphaHantoCoordinate coord2 = new AlphaHantoCoordinate(0, 1);
		assertNotEquals(coord1, coord2);

		AlphaHantoCoordinate coord3 = new AlphaHantoCoordinate(1, 0);
		assertNotEquals(coord1, coord3);
	}

}
