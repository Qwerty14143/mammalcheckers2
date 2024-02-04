import java.util.Scanner;
public class Game {
static Scanner sc = new Scanner(System.in);
public static Mammal[][] gameBoard = new Mammal[8][8];
public static int toRow;
public static int toColumn;
public static int beforeColumn;
public static int beforeRow;
protected String symbol;
protected String owner;

	public static void main(String[] args) {
		initializeGame();
		while (true) {
			UserMove();
			CompMove();

		}
	}

	private static void CompMove() {
		boolean computerMoved = false;// a helper flag to us
		if (caneat()) {//default move is to check if he can eat a pawn
			} else {
				do {
			    int randomRow = (int) (Math.random() * 8);
			    int randomColumn = (int) (Math.random() * 8);

			    if (gameBoard[randomRow][randomColumn].getOwner()=="2") {
			        // You have found a cell with a computer mammal

			        if (gameBoard[randomRow][randomColumn].canComputerMoveTwice(randomRow, randomColumn)) {
			            gameBoard[randomRow][randomColumn].moveCompRandomly(randomRow, randomColumn);
			            computerMoved = true;
			            break;
			        }

			        if (gameBoard[randomRow][randomColumn].canComputerMove(randomRow, randomColumn)) {
			            gameBoard[randomRow][randomColumn].movePieceComp(randomRow, randomColumn);
			            computerMoved = true;
			            break;
			        }
			    }

			} while (true);


		}
		System.out.println("Computer has played. ");
		printBoard();
	}

	private static boolean caneat() {// will check if the computer can eat a pawn
		Mammal[][] board_Temp = new Mammal[8][8];//build a helper 2Darray to eliminate fined W
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Mammal originalMammal = gameBoard[i][j];
				if (gameBoard[i][j] != null){
					 board_Temp[i][j] = originalMammal.deepCopy();
				}
			}
		}

		int randomRow = (int) (Math.random() * 8);
		 int randomColumn = (int) (Math.random() * 8);

		while (CheckIfExist2(board_Temp)) {//check if there is still w left in our helper 2Darray

			while (board_Temp[randomRow][randomColumn].getOwner() != "2" ) {
				randomRow = (int) (Math.random() * 8);
				randomColumn = (int) (Math.random() * 8);
				}
			if (gameBoard[randomRow][randomColumn].canEatComputer(randomRow,randomColumn)) {
				return true;
			}

			board_Temp[randomRow][randomColumn].setOwner("?");// change the 2 to ? in our helper array
		}

		return false;
	}



	private static boolean CheckIfExist2(Mammal[][] board_Temp) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board_Temp[i][j].getOwner()=="2") {
					return true;
				}
			}
		}
		return false;}

	public static void UserMove(){

		System.out.println("");
		System.out.println("It's your turn, please enter your move.");
		boolean did = true;
		while (did) {
		String playerMove = sc.next();
		if ("STOP".equals(playerMove)) {
			System.out.println("Sorry, computer has won :(");
			System.exit(0);
		}
		if (playerMove.length() == 5) {
			if (inputvalid(playerMove)) {
				String t1 = playerMove.substring(0, 1);
				String t2 = playerMove.substring(1, 2);
				String t3 = playerMove.substring(3, 4);
				String t4 = playerMove.substring(4, 5);

				int toRow = Integer.parseInt(t1);
				int toColumn = Integer.parseInt(t2);
				int beforeRow = Integer.parseInt(t3);
				int beforeColumn = Integer.parseInt(t4);

				beforeRow = 8 - beforeRow;
				beforeColumn = beforeColumn - 1;
				toRow = 8 - toRow;
				toColumn = toColumn - 1;
				Mammal movingPiece = gameBoard[beforeRow][beforeColumn];
				if(movingPiece.movePieceUser(toRow,toColumn,beforeRow,beforeColumn)) { 
				printBoard(); // Print the board state after the attempted move
				did=false;
				}
				if(movingPiece.canEatUser(toRow, toColumn, beforeRow, beforeColumn)) {////eatingggggg
					movingPiece.eat(toRow, toColumn, beforeRow, beforeColumn);
					printBoard(); // Print the board state after the attempted move
					did=false;
				}
			} else {
				System.out.println("The input is not valid, please enter your move again.");
			} 
		}
		else{
			System.out.println("The input is not valid, please enter your move again.");
		}
		}
	}
	private static boolean inputvalid(String player_move) {
		for (int i=0;i<player_move.length();i++) {
			char currentChar = player_move.charAt(i);
			if ((currentChar < '1' || currentChar > '8') && currentChar != '-') {
				return false;
			}
		}
		return true;
	}
	
	public static void defualtBoard() {

		gameBoard[0][1]= new Cat ("C","2");
		gameBoard[0][3]= new Dog ("D","2");
		gameBoard[0][5]= new Elephant ("E","2");
		gameBoard[0][7]= new Mouse ("M","2");
		gameBoard[1][0]= new Dog ("D","2");
		gameBoard[1][2]= new Elephant ("E","2");
		gameBoard[1][4]= new Mouse ("M","2");
		gameBoard[1][6]= new Cat ("C","2");
		gameBoard[2][1]= new Elephant ("E","2");
		gameBoard[2][3]= new Mouse ("M","2");
		gameBoard[2][5]= new Cat ("C","2");
		gameBoard[2][7]= new Dog ("D","2");

		gameBoard[7][0]= new Cat ("C","1");
		gameBoard[7][2]= new Dog ("D","1");
		gameBoard[7][4]= new Elephant ("E","1");
		gameBoard[7][6]= new Mouse ("M","1");
		gameBoard[6][1]= new Dog ("D","1");
		gameBoard[6][3]= new Elephant ("E","1");
		gameBoard[6][5]= new Mouse ("M","1");
		gameBoard[6][7]= new Cat ("C","1");
		gameBoard[5][6]= new Elephant ("E","1");
		gameBoard[5][4]= new Mouse ("M","1");
		gameBoard[5][2]= new Cat ("C","1");
		gameBoard[5][0]= new Elephant ("E","1");
	}


	 protected static void printBoard() {
	        System.out.println("The board:");
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if (!(gameBoard[i][j] instanceof Mammal) && ((i + j) % 2 == 0)) {
	                    gameBoard[i][j] = new UnreacheballeCell("*", "0");
	                } else if (!(gameBoard[i][j] instanceof Mammal) && ((i + j) % 2 != 0)) {
	                    gameBoard[i][j] = new EmptyCell("-", "0");
	                }
	                System.out.print(gameBoard[i][j].toString());
	                System.out.print('\t');
	            }
	            System.out.println();
	            System.out.println();
	        }
	    }
	 public static void initializeGame() {
	        defualtBoard();
	        printBoard();
	    }
}
