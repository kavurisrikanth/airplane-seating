package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AirplaneSeater;

class AirlineTest {
	private AirplaneSeater as;
	private AirplaneSeater asInv;
	private int[][] seating;
	private int numPassengers;

	@BeforeEach
	void init() {
		as = new AirplaneSeater();
		asInv = new AirplaneSeater().invert();
	}

	private void seatAndTest() {
		int totalSeats = 0;
		for (var x : seating) {
			totalSeats += x[0] * x[1];
		}

		int numPassengersSeated = numPassengers <= totalSeats ? numPassengers : totalSeats;
		int numPassengersUnseated = numPassengers - numPassengersSeated;
		int numSeatsLeft = totalSeats - numPassengersSeated;

		as.seatAirplane(seating, numPassengers);
		Assertions.assertEquals(numPassengersSeated, as.getNumPassengersSeated());
		Assertions.assertEquals(numPassengersUnseated, as.getNumPassengersUnseated());
		Assertions.assertEquals(numSeatsLeft, as.getNumSeatsLeft());
		
		asInv.seatAirplane(seating, numPassengers);
		Assertions.assertEquals(numPassengersSeated, asInv.getNumPassengersSeated());
		Assertions.assertEquals(numPassengersUnseated, asInv.getNumPassengersUnseated());
		Assertions.assertEquals(numSeatsLeft, asInv.getNumSeatsLeft());
	}

	@Test
	void testReadingInputs() {
		seating = new int[][] { { 2, 3 }, { 3, 3 }, { 2, 3 } };
		numPassengers = 10;
		seatAndTest();
	}

	@Test
	void testTwo() {
		seating = new int[][] { { 3, 2 }, { 4, 3 }, { 2, 3 }, { 3, 4 } };
		numPassengers = 30;
		seatAndTest();
	}

	@Test
	void testEmptyPlane() {
		seating = new int[][] {};
		numPassengers = 10;
		seatAndTest();
	}

	@Test
	void testNoPassengers() {
		seating = new int[][] { { 1, 2 }, { 2, 3 } };
		numPassengers = 0;
		seatAndTest();
	}

	@Test
	void testOnlySingleRows() {
		seating = new int[][] { { 1, 3 }, { 1, 5 }, { 1, 3 } };
		numPassengers = 8;
		seatAndTest();
	}

	@Test
	void testOnlySingleCols() {
		seating = new int[][] { { 3, 1 }, { 5, 1 }, { 3, 1 } };
		numPassengers = 8;
		seatAndTest();
	}

	@Test
	void testOnlySingleSeats() {
		seating = new int[][] { { 1, 1 } };
		numPassengers = 3;
		seatAndTest();
	}

	@Test
	void testOnlyRowsSingleSeat() {
		seating = new int[][] { { 1, 1 }, { 1, 1 }, { 1, 1 } };
		numPassengers = 3;
		seatAndTest();
	}

	@Test
	void testOnlyRowsSingleSeatWithExtraPassengers() {
		seating = new int[][] { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } };
		numPassengers = 8;
		seatAndTest();
	}

	@Test
	void testWith5Aisles() {
		seating = new int[][] { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 }, { 3, 1 } };
		numPassengers = 11;
		seatAndTest();
	}

	@Test
	void testWith5AislesWithLesserPassengers() {
		seating = new int[][] { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 }, { 3, 1 } };
		numPassengers = 9;
		seatAndTest();
	}

	@Test
	void testWith5AislesWithExtraPassengers() {
		seating = new int[][] { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 }, { 3, 1 } };
		numPassengers = 16;
		seatAndTest();
	}
}
