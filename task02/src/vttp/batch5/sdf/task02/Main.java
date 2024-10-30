package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	// send sos please, i cannot deadass

	// global variable
	static ArrayList<String> gameBoard = new ArrayList<>();
	static ArrayList<Integer> canPutAt = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		// Pass in board 3, based on run.sh
		// basically should take in any file
		// later change to args0
		FileReader reader = new FileReader(args[0]);
		BufferedReader bReader = new BufferedReader(reader);

		String lines;
		String combinedLines = "";

		// UNCOMMENT LATER
		System.out.println("Processing: " + args[0]);
		System.out.println("Processing: ");

		while ((lines = bReader.readLine()) != null) {
			combinedLines = combinedLines + lines;
		}

		// close the reader
		bReader.close();

		// System.out.println("printing");
		// System.out.println(combinedLines);

		// Load board into an array secondary processing
		for (int i = 0; i < 9; i++) {
			gameBoard.add(Character.toString(combinedLines.charAt(i)));
			// System.out.println(combinedLines.charAt(i));
		}

		// print the board
		printBoard(gameBoard);

	}

	// Function to print board
	public static void printBoard(ArrayList<String> board) {
		System.out.println("Board:");
		System.out.printf("%s%s%s%n", board.get(0), board.get(1), board.get(2));
		System.out.printf("%s%s%s%n", board.get(3), board.get(4), board.get(5));
		System.out.printf("%s%s%s%n", board.get(6), board.get(7), board.get(8));
		System.out.println();

		// ADD SECONADARY FUNCITON TO PRINT THE OTHER LINES
		// Print available positions to put
		availPosition();

	}

	// Find empty spaces available to put
	public static void availPosition() {
		// Asses the board if XO means cannot
		// . MEANS EMPTY
		for (int i = 0; i < gameBoard.size(); i++) {
			if (gameBoard.get(i).equals(".")) {
				canPutAt.add(i);
			} else {
				// canPutAt.add(0);
			}
		}

		// System.out.printf("Available Positions : %s%n", canPutAt);
		// System.out.println(canPutAt.size());

		System.out.println("--------------------------------------------");

		for (int i = 0; i < canPutAt.size(); i++) {

			switch (canPutAt.get(i)) {
				case 0:
					System.out.printf("y=0, x=0, utility=%s%n", getScore(gameBoard, i));
					break;
				case 1:
					System.out.printf("y=0, x=1, utility=%s%n", getScore(gameBoard, i));
					break;
				case 2:
					System.out.printf("y=0, x=2, utility=%s%n", getScore(gameBoard, i));
					break;
				case 3:
					System.out.printf("y=1, x=0, utility=%s%n", getScore(gameBoard, i));
					break;
				case 4:
					System.out.printf("y=1, x=1, utility=%s%n", getScore(gameBoard, i));
					break;
				case 5:
					System.out.printf("y=1, x=2, utility=%s%n", getScore(gameBoard, i));
					break;
				case 6:
					System.out.printf("y=2, x=0, utility=%s%n", getScore(gameBoard, i));
					break;
				case 7:
					System.out.printf("y=2, x=1, utility=%s%n", getScore(gameBoard, i));
					break;
				case 8:
					System.out.printf("y=2, x=2, utility=%s%n", getScore(gameBoard, i));
					break;
				default:
					System.out.println("testing");
					break;
			}
		}
	}

	// TAKE AVAIL LIST
	// ITERATE THROUGH THE LIST,
	// FOR POS 1, CHECK ALL THE X IF NO WIN, ON X WIN RETURN 1,
	// NOW CHECK CIRCLE
	// CHECK CIRCLE WIN = -1, ELSE IS DRAW 0

	public static int getScore(ArrayList<String> gameboardIn, int put) {

		// Clone board
		ArrayList<String> cloneX = gameboardIn;
		ArrayList<Integer> canPutClone = canPutAt;

		// If x play on this element
		cloneX.set(put, "X");

		// System.out.println(cloneX);

		// CHECK FOR X WIN AFTER PLACING
		if (check3(cloneX, "X", put) == true) {
			return 1;
		}

		// Check if O wins. //WHY IS NOT NOT PCONTINUEING AFTER A WIN
		
		
		// canPutClone.remove(put);

		// System.out.println(canPutClone);

		for (int i = 0; i < canPutClone.size(); i++) {
			if (check3(cloneX, "O", canPutClone.get(i)) == true) {
				return -1;
			}
			return -1;
		}

		return 0;
	}


	// CHECKS FOR X AND O
	public static boolean check3(ArrayList<String> cloneBoard, String xo, int put) {

		// check this move for a win
		if (put == 0) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(1).equals(xo) && cloneBoard.get(2).equals(xo)) {
				return true;
			} else if (cloneBoard.get(0).equals(xo) && cloneBoard.get(3).equals(xo) && cloneBoard.get(6).equals(xo)) {
				return true;
			} else if (cloneBoard.get(0).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			}
		} else if (put == 1) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(1).equals(xo) && cloneBoard.get(2).equals(xo)) {
				return true;
			} else if (cloneBoard.get(1).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(7).equals(xo)) {
				return true;
			}
		} else if (put == 2) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(1).equals(xo) && cloneBoard.get(2).equals(xo)) {
				return true;
			} else if (cloneBoard.get(2).equals(xo) && cloneBoard.get(5).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(2).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(6).equals(xo)) {
				return true;
			}
		} else if (put == 3) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(3).equals(xo) && cloneBoard.get(6).equals(xo)) {
				return true;
			} else if (cloneBoard.get(3).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(5).equals(xo)) {
				return true;
			}
		} else if (put == 4) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(2).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(6).equals(xo)) {
				return true;
			} else if (cloneBoard.get(3).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(5).equals(xo)) {
				return true;
			} else if (cloneBoard.get(1).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(7).equals(xo)) {
				return true;
			}
		} else if (put == 5) {
			if (cloneBoard.get(2).equals(xo) && cloneBoard.get(5).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(3).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(5).equals(xo)) {
				return true;
			}
		} else if (put == 6) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(3).equals(xo) && cloneBoard.get(6).equals(xo)) {
				return true;
			} else if (cloneBoard.get(6).equals(xo) && cloneBoard.get(7).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(2).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(6).equals(xo)) {
				return true;
			}
		} else if (put == 7) {
			if (cloneBoard.get(6).equals(xo) && cloneBoard.get(7).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(1).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(7).equals(xo)) {
				return true;
			}
		} else if (put == 8) {
			if (cloneBoard.get(0).equals(xo) && cloneBoard.get(4).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(6).equals(xo) && cloneBoard.get(7).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			} else if (cloneBoard.get(2).equals(xo) && cloneBoard.get(5).equals(xo) && cloneBoard.get(8).equals(xo)) {
				return true;
			}
		}

		// else
		return false;
	}

}
