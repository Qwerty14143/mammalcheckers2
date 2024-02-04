public class Mammal extends Game {
	private String name; // Example field in Mammal class
   

    public Mammal(String symbol, String owner) {
        this.symbol = symbol;
        this.owner = owner;
    }
    public Mammal(String name) {
        this.name = name;
       
    }
    public Mammal(String name, String symbol, String owner) {
        this.name = name;
        this.symbol = symbol;
        this.owner = owner;
    }
    public Mammal deepCopy() {
        return new Mammal(this.name, this.symbol, this.owner);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  

	public String getOwner() {
        return owner;
    }
    

    public String getSymbol() {
        return symbol;
    }

    public void setOwner(String s) {
        this.owner = s;
    }



  

    public boolean canComputerMoveTwice(int randomRow, int randomColumn) {
        boolean canMoveLeft = false;
        boolean canMoveRight = false;
        if (randomColumn - 1 >= 0 && gameBoard[randomRow + 1][randomColumn - 1].getOwner()=="0"){
        		
            canMoveRight = true;
        }
        if (randomColumn + 1 < 8 && gameBoard[randomRow + 1][randomColumn + 1] .getOwner()=="0") {
            canMoveLeft = true;
        }
        return canMoveRight && canMoveLeft;
    }

    public boolean canComputerMove(int randomRow, int randomColumn) {
        boolean canMoveLeft = false;
        boolean canMoveRight = false;
        if (randomColumn - 1 >= 0 && gameBoard[randomRow + 1][randomColumn - 1].getOwner()=="0") {
            canMoveRight = true;
            gameBoard[randomRow + 1][randomColumn - 1] = gameBoard[randomRow][randomColumn];
            return true;

        }
        if (randomColumn + 1 < 8 && gameBoard[randomRow + 1][randomColumn + 1].getOwner()=="0"){
            canMoveLeft = true;
            gameBoard[randomRow + 1][randomColumn + 1] = gameBoard[randomRow][randomColumn];
            return true;
        }
        return false;
    }

    public void moveCompRandomly(int randomRow, int randomColumn) {
        boolean moveRight = Math.random() <= 0.3;
        if (canComputerMoveTwice(randomRow, randomColumn)) {
            if (moveRight) {
                gameBoard[randomRow + 1][randomColumn - 1] = gameBoard[randomRow][randomColumn];
                gameBoard[randomRow][randomColumn] = new EmptyCell("*", "0");
            } else {
                gameBoard[randomRow + 1][randomColumn + 1] = gameBoard[randomRow][randomColumn];
                gameBoard[randomRow][randomColumn] = new EmptyCell("*","0");
            }

        }
    }

    public boolean moveUserLegal(int toRow, int toColumn, int beforeRow, int beforeColumn) {
        if (this.owner.equals("1")) {
            if ((toRow < beforeRow) && (beforeRow - toRow == 1) && isValidMove() && gameBoard[toRow][toColumn].getSymbol().equals("*")) {
                return Math.abs(beforeColumn - toColumn) == 1;
            }
        }
        return false;
    }

    public boolean movePieceUser(int toRow, int toColumn, int beforeRow, int beforeColumn) {
        if (moveUserLegal(toRow, toColumn, beforeRow, beforeColumn)) {
            gameBoard[toRow][toColumn] = gameBoard[beforeRow][beforeColumn];
            gameBoard[beforeRow][beforeColumn] = new EmptyCell("*", "0");
            return true;
            // Additional logic here if needed
        } else {
            System.out.println("Invalid move");
            return false;
        }
    }

    public void movePieceComp(int randomRow, int randomColumn) {
            gameBoard[randomRow][randomColumn] = this;
            gameBoard[randomRow][randomColumn] = new EmptyCell("*", "0");
    }

    public Boolean canEatComputer(int randomRow, int randomColumn) {
        
            if ((randomRow >= 0 && randomRow <= 6 && randomRow + 2 < 8 && randomColumn >= 2 && randomColumn <= 6 )&& ((gameBoard[randomRow + 1][randomColumn - 1].getOwner()=="1") && gameBoard[randomRow + 2][randomColumn - 2].getSymbol()=="*")){// check edges of the eating
            	gameBoard[randomRow + 1][randomColumn - 1]= new EmptyCell("*", "0");
            	gameBoard[randomRow + 2][randomColumn - 2]=gameBoard[randomRow][randomColumn];
            	gameBoard[randomRow][randomColumn]= new EmptyCell("*", "0");
                return true;
            }
            if((randomRow >= 0 && randomRow <= 6 && randomRow + 2 < 8 && randomColumn >= 2 && randomColumn <= 6 )&& ((gameBoard[randomRow + 1][randomColumn + 1].getOwner()=="1") && gameBoard[randomRow + 2][randomColumn + 2].getSymbol()=="*") ) {
            	gameBoard[randomRow + 1][randomColumn + 1]= new EmptyCell("*", "0");
            	gameBoard[randomRow + 2][randomColumn + 2]=gameBoard[randomRow][randomColumn];
            	gameBoard[randomRow][randomColumn]= new EmptyCell("*", "0");
                return true;
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
            gameBoard[middleRow][middleColumn] = new EmptyCell("*", "0");
            gameBoard[beforeRow][beforeColumn] = new EmptyCell("*", "0"); // Make the original position empty
        } else {
            System.out.println("Cannot capture");
        }
    }

    public boolean isValidMove() {
        if (toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8) {
            return true;
        }
        return false;
    }

    public String toString() {
        return symbol + owner; // You can customize this to return the name or any other information
    }
}
