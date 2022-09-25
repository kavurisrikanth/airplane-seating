package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AirplaneSeater;

class AirlineTest {
	private AirplaneSeater as;

	@BeforeEach
	void init() {
		as = new AirplaneSeater();
	}

	@Test
	void testReadingInputs() {
		int[][] seating = { { 2, 3 }, { 3, 3 }, { 2, 3 } };
		as.seatAirplane(seating, 10);
	}

	@Test
	void testTwo() {
		int[][] seating = { { 3, 2 }, { 4, 3 }, { 2, 3 }, { 3, 4 } };
		as.seatAirplane(seating, 30);
	}

	@Test
	void testEmptyPlane() {
		int[][] seating = {};
		as.seatAirplane(seating, 10);
	}

	@Test
	void testNoPassengers() {
		int[][] seating = { { 1, 2 }, { 2, 3 } };
		as.seatAirplane(seating, 0);
	}

	@Test
	void testOnlySingleRows() {
		int[][] seating = { { 1, 3 }, { 1, 5 }, { 1, 3 } };
		as.seatAirplane(seating, 8);
	}

	@Test
	void testOnlySingleCols() {
		int[][] seating = { { 3, 1 }, { 5, 1 }, { 3, 1 } };
		as.seatAirplane(seating, 8);
	}

	@Test
	void testOnlySingleSeats() {
		int[][] seating = { { 1, 1 } };
		as.seatAirplane(seating, 3);
	}

	@Test
	void testOnlyRowsSingleSeat() {
		int[][] seating = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
		as.seatAirplane(seating, 3);
	}

	@Test
	void testOnlyRowsSingleSeatWithExtraPassengers() {
		int[][] seating = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } };
		as.seatAirplane(seating, 8);
	}

	@Test
	void testWith5Aisles() {
		int[][] seating = { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 }, { 3, 1 } };
		as.seatAirplane(seating, 11);
	}

	@Test
	void testWith5AislesWithLesserPassengers() {
		int[][] seating = { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 }, { 3, 1 } };
		as.seatAirplane(seating, 9);
	}

	@Test
	void testWith5AislesWithExtraPassengers() {
		int[][] seating = { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 }, { 3, 1 } };
		as.seatAirplane(seating, 16);
	}
}
