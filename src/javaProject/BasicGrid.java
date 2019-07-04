package javaProject;

//this class generates a matrix with several components
//X a random location chosen for the player to start
//Y a random location chosen for enemies
//*__* a random location chosen for the King to be defeated

public class BasicGrid {

//VARIABLES ______

	// VARIABLES
	final int size;
	final int numberOfBaddies;
	final int numberOfFriends;
	String[][] myGrid;

	// constructor
	BasicGrid(int numberOfBaddies, int numberOfFriends, int size) {
		this.size = size;
		this.numberOfBaddies = numberOfBaddies;
		this.numberOfFriends = numberOfFriends;
		this.myGrid = new String[size][size];
	}
	
///METHODS RELATED TO CONSTRUCTION OF THE GRID ______

	// METHOD: select random location in grid
	static int[] selectRandomLocation(int maxX, int maxY, int minX, int minY) {
		// select random x [0-1] multiply with the length of the grid [0-lenghtOfGrid]
		int x = (int) (Math.random() * (maxX - minX + 1)) + minX;
		int y = (int) (Math.random() * (maxY - minY + 1)) + minY;
		int[] coords = { x, y };
		return coords;
	}

	
	// METHOD: initialize random a single tile
	void initializeTiles(int numberNeeded, String nameOf, int maxX, int maxY, int minX, int minY) {
		// set other locations
		int characters = 0;
		while (characters < numberNeeded) {
			int[] newLocation = selectRandomLocation(maxX, maxY, minX, minY);
			int xCo = newLocation[0];
			int yCo = newLocation[1];
			if (myGrid[xCo][yCo] == null) {
				myGrid[xCo][yCo] = nameOf;
				characters = characters + 1;
			}
		}
	}

	// METHOD: initialize the center of castle or village
	void initializeCentre(String nameOfCentre, int[] location) {
		myGrid[location[0]][location[1]] = nameOfCentre;
		// IN THE CASTLE: set a buffer of 2 empty cells around the king
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				if (myGrid[location[0] + i][location[1] + j] == null) {
					// this makes that within structures like castle and village, no other characters will apear
					myGrid[location[0] + i][location[1] + j] = " ";    
				}
			}
		}
	}

	// METHOD: make a buffer around a cell with 4 entrances
	void setBuffer(String symbol, int range, int[] location) {
		// WALLS: set walls at distance 3 around the king
		for (int i = -range; i <= range; i++) {
			myGrid[location[0] + i][location[1] + range] = symbol;
		}
		for (int i = -range; i <= range; i++) {
			myGrid[location[0] + i][location[1] - range] = symbol;
		}
		for (int j = -range; j <= range; j++) {
			myGrid[location[0] + range][location[1] + j] = symbol;
		}
		for (int j = -range; j <= range; j++) {
			myGrid[location[0] - range][location[1] + j] = symbol;
		}
		// ENTRANCES: create places to enter the castle
		myGrid[location[0]][location[1] + range] = " ";
		myGrid[location[0]][location[1] - range] = " ";
		myGrid[location[0] + range][location[1]] = " ";
		myGrid[location[0] - range][location[1]] = " ";
	}

	// METHOD: build castle
	void initializeCastle() {
		int[] newLocation = selectRandomLocation(size - 7, size - 7, 7, 7);
		initializeCentre("Princes", newLocation);
		setBuffer("#", 3, newLocation);
		setBuffer("   ", 4, newLocation);
		setBuffer("  ", 5, newLocation);
	}
	
	// METHOD: initialize village
	void initializeVillage() {
		while (true) {
			int[] newLocation = selectRandomLocation(size - 4, size - 4, 4, 4);
			int sum = 0;
			for (int i = -3; i <= 3; i++) {
				for (int j = -3; j <= 3; j++) {
					if (myGrid[newLocation[0] + i][newLocation[1] + j] != null) {
						sum = +1;
					}
				}
			}
			if (sum == 0) {
				initializeCentre("Lancelot", newLocation);
				setBuffer("#", 3, newLocation);
				break;
			}
		}
	}

	// METHOD: initialize all single cell characters
	void initializeRandoms() {
//		initializeTiles(1, "X", size - 1, size - 1, 0, 0); 
		initializeTiles(numberOfBaddies, "-", size - 1, size - 1, 0, 0);
		initializeTiles(numberOfFriends, "+", size - 1, size - 1, 0, 0);
	}

	
	// METHOD: print grid method
	void printGrid() {
		for (int i = 0; i < size; i++) {
			System.out.print("\n");
			for (int j = 0; j < myGrid[0].length; j++) {
				System.out.print(myGrid[i][j] + " ");
			}
		}
	}
}