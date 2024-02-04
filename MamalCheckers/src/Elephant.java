
public class Elephant extends Mammal {

	public Elephant(String symbol,String owner) {
		super(symbol,owner);
	}

	public boolean moveUserLegal(int toRow, int toColumn, int beforeRow, int beforeColumn) {
		int middleRow = (beforeRow+toRow) / 2;
		int middleColumn = (beforeColumn+toColumn) / 2;
		if (this.owner.equals("1")) {
			if((beforeRow - toRow) == 1) {
				return (toRow < beforeRow) && (((beforeRow - toRow) == 1) || (beforeRow - toRow) == 2) && isValidMove() && gameBoard[toRow][toColumn] instanceof EmptyCell;
			}
			else if((beforeRow - toRow) == 2) {
				return isValidMove() && gameBoard[toRow][toColumn] instanceof EmptyCell && gameBoard[middleRow][middleColumn] instanceof EmptyCell ;
			}
		}
		return false;
	}

	public boolean movePieceUser(int toRow, int toColumn, int beforeRow, int beforeColumn) {
		if (moveUserLegal(toRow, toColumn, beforeRow, beforeColumn)) {
			gameBoard[toRow][toColumn] = this;
			gameBoard[beforeRow][beforeColumn] = new EmptyCell("*","0");
			return true;
		} else {
			System.out.println("Invalid move");
		} return false;
	}


}
