/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.beta;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import hanto.common.HantoCoordinate;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoCoordinate;

import org.junit.Test;

/**
 * Tests for BetaHantoCoordinate
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoCoordinateTest {

	@Test
	public void coordinateReturns0s() {
		HantoCoordinate coordinate = new BetaHantoCoordinate(0, 0);
		assertEquals(0, coordinate.getX());
		assertEquals(0, coordinate.getY());
	}

	@Test
	public void coordinateReturns11() {
		HantoCoordinate coordinate = new BetaHantoCoordinate(1, 1);
		assertEquals(1, coordinate.getX());
		assertEquals(1, coordinate.getY());
	}

	@Test
	public void coordinateReturns23() {
		HantoCoordinate coordinate = new BetaHantoCoordinate(2, 3);
		assertEquals(2, coordinate.getX());
		assertEquals(3, coordinate.getY());
	}

	@Test
	public void shouldHaveHash961() {
		BetaHantoCoordinate coord = new BetaHantoCoordinate(0, 0);
		assertEquals(961, coord.hashCode());
	}

	@Test
	public void testGetNeighborCoordinates() {
		BetaHantoCoordinate coord = new BetaHantoCoordinate(0, 0);
		BetaHantoCoordinate[] coordArray = coord.getNeighborCoordinates();
		assertEquals(coordArray[0], new BetaHantoCoordinate(0, 1));
		assertEquals(coordArray[1], new BetaHantoCoordinate(1, 0));
		assertEquals(coordArray[2], new BetaHantoCoordinate(1, -1));
		assertEquals(coordArray[3], new BetaHantoCoordinate(0, -1));
		assertEquals(coordArray[4], new BetaHantoCoordinate(-1, 0));
		assertEquals(coordArray[5], new BetaHantoCoordinate(-1, 1));
	}

	@Test
	public void testEqualsObject() {
		BetaHantoCoordinate coord = new BetaHantoCoordinate(0, 0);
		GregorianCalendar gc = new GregorianCalendar();
		assertNotEquals(coord, gc);
	}

	@Test
	public void testEqualsWithSelf() {
		BetaHantoCoordinate coord = new BetaHantoCoordinate(0, 0);
		assertEquals(coord, coord);
	}

	@Test
	public void testEqualsWithOtherCoord() {
		BetaHantoCoordinate coord1 = new BetaHantoCoordinate(0, 0);
		BetaHantoCoordinate coord2 = new BetaHantoCoordinate(0, 1);
		assertNotEquals(coord1, coord2);

		BetaHantoCoordinate coord3 = new BetaHantoCoordinate(1, 0);
		assertNotEquals(coord1, coord3);
	}

}
