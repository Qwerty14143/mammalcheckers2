
public class Cat extends Mammal{

	public Cat(String symbol,String owner) {

		super(symbol, owner);
	}

	public boolean moveUserLegal(int toRow, int toColumn, int beforeRow, int beforeColumn) {
		if (this.owner.equals("1")) {
			if (((toRow <= beforeRow) && ((beforeRow - toRow) == 1 || (beforeRow - toRow) == 0 )) && gameBoard[toRow][toColumn].getOwner()=="0")  {
				if(Math.abs(beforeColumn-toColumn)==1||Math.abs(beforeColumn-toColumn)==0) {
					return true;
				}
			}
		}
		return false;
	}

	public void movePiece() {
		if (moveUserLegal(toRow, toColumn, beforeRow, beforeColumn) && gameBoard[toRow][toColumn].getSymbol().equals("*")) {
			gameBoard[toRow][toColumn] = this;
			gameBoard[beforeRow][beforeColumn] = new EmptyCell("*",null);
		} else if (moveUserLegal(toRow, toColumn, beforeRow, beforeColumn) && gameBoard[toRow][toColumn].getSymbol().equals("-")) {
			gameBoard[toRow][toColumn] = this;
			gameBoard[beforeRow][beforeColumn] = new UnreacheballeCell("-", null);
		} else {
			System.out.println("Invalid move");
		}
	}



}
