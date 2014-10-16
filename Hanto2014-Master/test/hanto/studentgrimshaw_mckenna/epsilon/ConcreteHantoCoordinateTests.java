package hanto.studentgrimshaw_mckenna.epsilon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;

import java.util.List;

import org.junit.Test;

public class ConcreteHantoCoordinateTests {

	@Test
	public void test() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(2, -1);
		int distance = coord1.getDistance(coord2);
		assertEquals(2, distance);
	}

	@Test
	public void testInv() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(2, -1);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, 0);
		int distance = coord1.getDistance(coord2);
		assertEquals(2, distance);
	}

	@Test
	public void test2() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(1, -1);
		int distance = coord1.getDistance(coord2);
		assertEquals(1, distance);
	}

	@Test
	public void test2Inv() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(1, -1);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, 0);
		int distance = coord1.getDistance(coord2);
		assertEquals(1, distance);
	}

	@Test
	public void test3() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(1, -2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-4, 5);
		int distance = coord1.getDistance(coord2);
		assertEquals(7, distance);
	}

	@Test
	public void test3Inv() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(-4, 5);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(1, -2);
		int distance = coord1.getDistance(coord2);
		assertEquals(7, distance);
	}

	@Test
	public void test4() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(1, -2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(3, 0);
		int distance = coord1.getDistance(coord2);
		assertEquals(4, distance);
	}

	@Test
	public void test5() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(-1, -1);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-1, 1);
		int distance = coord1.getDistance(coord2);
		assertEquals(2, distance);
	}

	@Test
	public void test6() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(-2, 2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, 0);
		int distance = coord1.getDistance(coord2);
		assertEquals(2, distance);
	}

	@Test
	public void test7() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(2, -1);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, 1);
		int distance = coord1.getDistance(coord2);
		assertEquals(2, distance);
	}

	@Test
	public void test8() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, 0);
		int distance = coord1.getDistance(coord2);
		assertEquals(0, distance);
	}

	@Test
	public void test9() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(-2, -2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(2, 2);
		int distance = coord1.getDistance(coord2);
		assertEquals(8, distance);
	}

	@Test
	public void test10() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(-1, -1);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-3, 3);
		int distance = coord1.getDistance(coord2);
		assertEquals(4, distance);
	}

	@Test
	public void test11() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(2, 2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, -2);
		int distance = coord1.getDistance(coord2);
		assertEquals(6, distance);
	}

	@Test
	public void test12() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(-2, 4);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, -2);
		int distance = coord1.getDistance(coord2);
		assertEquals(6, distance);
	}

	@Test
	public void test13() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, -2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(2, 2);
		int distance = coord1.getDistance(coord2);
		assertEquals(6, distance);
	}

	@Test
	public void test14() {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, -2);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-2, 4);
		int distance = coord1.getDistance(coord2);
		assertEquals(6, distance);
	}

	@Test
	public void testIsStraigtLine1() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(2, -2);
		List<ConcreteHantoCoordinate> straightLineCoords = coord1.getStraightLineTo(coord2);
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(1, -1)));
		assertEquals(1, straightLineCoords.size());

	}

	@Test
	public void testIsStraigtLine2() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(2, 0);
		List<ConcreteHantoCoordinate> straightLineCoords = coord1.getStraightLineTo(coord2);
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(1, 0)));
		assertEquals(1, straightLineCoords.size());

	}

	@Test
	public void testIsStraigtLine3() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, 2);
		List<ConcreteHantoCoordinate> straightLineCoords = coord1.getStraightLineTo(coord2);
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(0, 1)));
		assertEquals(1, straightLineCoords.size());

	}

	@Test
	public void testIsStraigtLine4() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-2, 2);
		List<ConcreteHantoCoordinate> straightLineCoords = coord1.getStraightLineTo(coord2);
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(-1, 1)));
		assertEquals(1, straightLineCoords.size());

	}

	@Test
	public void testIsStraigtLine5() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(0, -2);
		List<ConcreteHantoCoordinate> straightLineCoords = coord1.getStraightLineTo(coord2);
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(0, -1)));
		assertEquals(1, straightLineCoords.size());

	}

	@Test
	public void testIsStraigtLine6() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-6, 0);
		List<ConcreteHantoCoordinate> straightLineCoords = coord1.getStraightLineTo(coord2);
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(-1, 0)));
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(-2, 0)));
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(-3, 0)));
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(-4, 0)));
		assertTrue(straightLineCoords.contains(new ConcreteHantoCoordinate(-5, 0)));
		assertEquals(5, straightLineCoords.size());

	}

	@Test(expected = HantoException.class)
	public void testIsNotStraigtLine() throws HantoException {
		ConcreteHantoCoordinate coord1 = new ConcreteHantoCoordinate(0, 0);
		ConcreteHantoCoordinate coord2 = new ConcreteHantoCoordinate(-1, 2);
		coord1.getStraightLineTo(coord2);

	}

}
