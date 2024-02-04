
public class Mouse extends Elephant {

	public Mouse(String symbol,String owner) {
		super(symbol,owner);
		// TODO Auto-generated constructor stub
	}
	public void eat() {
		if (canEat()) {
			gameBoard[toRow][toColumn] = this; // Place the mammal in the captured position
			gameBoard[beforeRow][beforeColumn] = new EmptyCell("*",null); // Make the original position empty
		}
	}
	public Boolean canEat(){
		int middleRow = (beforeRow+toRow) / 2;
		int middleColumn = (beforeColumn+toColumn) / 2;

		if (isValidMove()) {
			if (gameBoard[middleRow][middleColumn] instanceof Mammal) {
				Mammal middleMammal = (Mammal) gameBoard[middleRow][middleColumn];
				if (!middleMammal.getOwner().equals(this.getOwner())) {
					return true;
				}
			}
		}
		return false;
	}
	public Boolean canEatUser(int toRow, int toColumn, int beforeRow, int beforeColumn) {
        int middleRow = (beforeRow + toRow) / 2;
        int middleColumn = (beforeColumn + toColumn) / 2;
        if (isValidMove()) {
            if (gameBoard[middleRow][middleColumn].getOwner().equals("2") && gameBoard[toRow][toColumn].getSymbol().equals("*")) {
                return true;
            }
        }
        return false;
    }

    public void eat(int toRow, int toColumn, int beforeRow, int beforeColumn) {
        int middleRow = (beforeRow + toRow) / 2;
        int middleColumn = (beforeColumn + toColumn) / 2;
        if (canEatUser(toRow, toColumn, beforeRow, beforeColumn)) {
            gameBoard[toRow][toColumn] = this; // Place the mammal in the captured position
            gameBoard[beforeRow][beforeColumn] = new EmptyCell("*", "0"); // Make the original position empty
        } else {
            System.out.println("Cannot capture");
        }
    }

	
}
