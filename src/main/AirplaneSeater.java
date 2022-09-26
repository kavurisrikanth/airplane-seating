package main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class AirplaneSeater {
	private static final String FILLED_SLOT_POSTFIX = "|";
	private int numWindowSeats = 0;
	private int numAisleSeats = 0;
	private int numCenterSeats = 0;

	private int aisleIdx = 0;
	private int windowIdx = 0;
	private int centerIdx = 0;

	private int numPassengers;
	private boolean seatingDone;
	private int seatsLeft;
	private int passengersSeated;

	private int numDigits;

	public void seatAirplane(int[][] seating, int numPassengers) {
		System.out.println("***********************************************************");
		if (seating == null) {
			System.out.println("*** No seats available. " + numPassengers + " passengers unseated.");
			return;
		}

		System.out.println("*** Seating: " + Arrays.deepToString(seating) + ", Passengers: " + numPassengers + " ***");
		this.numPassengers = numPassengers;

		int numP = numPassengers < 0 ? numPassengers * -1 : numPassengers;
		numDigits = String.valueOf(numP).length();

		// Window seats
		// Potential change
		if (seating.length != 0) {
			numWindowSeats += seating[0][0];
			if (seating.length != 1) {
				numWindowSeats += seating[seating.length - 1][0];
			}
		}

		// Aisle seats
		for (int i = 0; i < seating.length; i++) {
			numAisleSeats += getNumAisleSeats(seating, i, true);
			numCenterSeats += getNumCenterSeats(seating, i, true);
		}

		aisleIdx = 1;
		windowIdx = numAisleSeats + 1;
		centerIdx = numAisleSeats + numWindowSeats + 1;

		int maxRows = Arrays.stream(seating).map(x -> x[0]).max(Integer::compare).orElse(0);
		StringBuilder sb = new StringBuilder();

		seatsLeft = numWindowSeats + numAisleSeats + numCenterSeats;
		passengersSeated = 0;
		seatingDone = false;

		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < seating.length; j++) {
				int[] row = seating[j];
				// Potential change
				int numSeats = row[1];
				// Potential change
				if (i < row[0]) {
					// Valid row
					int windowLeft = 0, aisleLeft = 0, center = 0, aisleRight = 0, windowRight = 0;

					if (j == 0) {
						windowLeft = 1;
						center = getNumCenterSeats(seating, j, false);
						aisleRight = getNumAisleSeats(seating, j, false);
					} else if (j == seating.length - 1) {
						windowRight = 1;
						aisleLeft = getNumAisleSeats(seating, j, false);
						center = getNumCenterSeats(seating, j, false);
					} else {
						// No window seats
						aisleLeft = 1;
						if (numSeats >= 2) {
							aisleRight = 1;
							center = numSeats - 2;
						}
					}

					for (int x = 0; !seatingDone && x < windowLeft; x++) {
						// TODO: Unnecessary loop
						writeWindowSeatAssignment(sb);
					}

					for (int x = 0; !seatingDone && x < aisleLeft; x++) {
						writeAisleSeatAssignment(sb);
					}

					for (int x = 0; !seatingDone && x < center; x++) {
						writeCenterSeatAssignment(sb);
					}

					for (int x = 0; !seatingDone && x < aisleRight; x++) {
						writeAisleSeatAssignment(sb);
					}

					for (int x = 0; !seatingDone && x < windowRight; x++) {
//            TODO: Unnecessary loop
						writeWindowSeatAssignment(sb);
					}
				} else {
					// Print spaces
					for (int x = 0; x < numSeats; x++) {
						sb.append(getNoSeatsSlot());
					}
				}

				if (j != seating.length - 1) {
					// End of aisle
					sb.append(" || ");
				}
			}
			sb.append("\n");
		}

		System.out.println();
		System.out.println(sb.toString());
		int passengersLeft = numPassengers - passengersSeated;
		if (passengersLeft > 0) {
			System.out.println("*** All seats allocated. " + passengersLeft + " passengers unseated.");
		}
		System.out.println("***********************************************************");
	}

	private void writeWindowSeatAssignment(StringBuilder sb) {
		if (windowIdx > numPassengers) {
			sb.append(getEmptySlot());
			return;
		}

		sb.append(getFormattedNumber(windowIdx)).append(FILLED_SLOT_POSTFIX);
		windowIdx++;
		seatPassenger();
	}

	private void writeAisleSeatAssignment(StringBuilder sb) {
		if (aisleIdx > numPassengers) {
			sb.append(getEmptySlot());
			return;
		}

		sb.append(getFormattedNumber(aisleIdx)).append(FILLED_SLOT_POSTFIX);
		aisleIdx++;
		seatPassenger();
	}

	private void writeCenterSeatAssignment(StringBuilder sb) {
		if (centerIdx > numPassengers) {
			sb.append(getEmptySlot());
			return;
		}

		sb.append(getFormattedNumber(centerIdx)).append(FILLED_SLOT_POSTFIX);
		centerIdx++;
		seatPassenger();
	}

	private void seatPassenger() {
		passengersSeated++;
		seatsLeft--;
		seatingDone = seatsLeft == 0 || passengersSeated == numPassengers;
	}

	private String getEmptySlot() {
		return "X".repeat(numDigits) + FILLED_SLOT_POSTFIX;
	}

	private String getNoSeatsSlot() {
		return " ".repeat(numDigits) + " ";
	}

	private String getFormattedNumber(int idx) {
		NumberFormat formatter = new DecimalFormat("0".repeat(numDigits));
		String s = formatter.format(idx);
		return s;
	}

	private static int getNumAisleSeats(int[][] seating, int i, boolean entireAisle) {
		// Potential change
		int numRows = seating[i][0];
		int numSeats = seating[i][1];
		int aisleSeats = 0;

		if (i == 0 || i == seating.length - 1) {
			if (numSeats > 1) {
				aisleSeats = 1;
			}
		} else {
			if (numSeats == 1) {
				aisleSeats = 1;
			}
			if (numSeats >= 2) {
				aisleSeats = 2;
			}
		}

		return aisleSeats * (entireAisle ? numRows : 1);
	}

	private static int getNumCenterSeats(int[][] seating, int i, boolean entireAisle) {
		// Potential change
		int numRows = seating[i][0];
		int numSeats = seating[i][1];
		int centerSeats = 0;

		if (i == 0 || i == seating.length - 1) {
			if (numSeats > 2) {
				centerSeats = numSeats - 2;
			}
		} else {
			if (numSeats >= 2) {
				centerSeats = numSeats - 2;
			}
		}

		return centerSeats * (entireAisle ? numRows : 1);
	}
}
