import java.util.Scanner;

public class MatrixWiz {

	public static void matrixPrint(int[][] matrix) {
		for(int i = 0; i < matrix.length ; i++ ) {
			System.out.println("");
			System.out.print("| ");
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
				
				if(j == (matrix[0].length-1))
					System.out.print("|");
			}
			

		}
		
		System.out.println("");

	}

	public static void rowSwitch(int[][] matrix, int rowNumOne, int rowNumTwo) {
		int[][] temp = new int[matrix.length][matrix[0].length];


		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				temp[i][j] = matrix[i][j];
			}
		}
		for(int i = 0; i < matrix[0].length; i++) {
			temp[rowNumOne][i] = matrix[rowNumTwo][i];
			temp[rowNumTwo][i] = matrix[rowNumOne][i];

		}

		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = temp[i][j];
			}
		}

	}

	public static void rowScale(int[][] matrix, int rowNum, int scalar, boolean multiply) {
		
		if(multiply == true) {
			for(int i = 0; i < matrix[rowNum].length; i++) {
				matrix[rowNum][i] = scalar * matrix[rowNum][i];
			}
		}
		else
			for(int i = 0; i < matrix[rowNum].length; i++) {
				matrix[rowNum][i] =  matrix[rowNum][i]/scalar;
			}

	}

	public static void rowAdd(int[][] matrix,  int rowNumOne, int rowNumTwo, boolean add) {
		if(add == true){
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[rowNumOne][j] = matrix[rowNumOne][j] + matrix[rowNumTwo][j];
			}
		}
		else
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[rowNumOne][j] = matrix[rowNumOne][j] - matrix[rowNumTwo][j];
			}


	}

	public static void REF(int[][] matrix) {


		for(int k = 0; k < (matrix.length-1); k++) {

			int pivCoordx = 0;
			int pivCoordy = 0;
			outerloop: 
				for(int i = k; i < matrix[0].length; i++) {
					for(int j = k; j < matrix.length; j++) {

						if(matrix[j][i] != 0) {
							pivCoordx = i;
							pivCoordy = j;
							break outerloop;

						}


					}
				}


			rowSwitch(matrix, pivCoordy, k);
			if(pivCoordy != k) {
			System.out.println("Switching row " + (pivCoordy+1) + " and row " + (k + 1));
			}
			matrixPrint(matrix);

			for(int j = (1+k); j < matrix.length; j++) {
				if(matrix[j][pivCoordx] != 0 && matrix[k][pivCoordx] != 0) {
					int scalar = matrix[j][pivCoordx]/matrix[k][pivCoordx];
					if(scalar != 0) {
					rowScale(matrix, k, scalar, true);
					rowAdd(matrix, j , k , false);
					rowScale(matrix, k, scalar, false);
					
					System.out.println("Subtracting row " + (k+1) + " from row " + (j+1) + ", " + scalar + " times");
					
					}

				}

			}
			
			matrixPrint(matrix);

		}
	}

	public static void RREF(int[][] matrix) {
		REF(matrix);
		
		int pivx = 0;
		int pivy = 0;
		int l = matrix.length-1;

		for(int k = 0; k < matrix[0].length; k++ ) {
		
		overloop:
			for(int i = l - k ; i >0; i--){
				for(int j = 0 ; j < matrix[i].length; j++) {
					if(matrix[i][j] != 0) {
						pivy = i;
						pivx = j;


						break overloop;
					}
				}
			}

		if(matrix[pivy][pivx] != 1) {
			
			System.out.println("Dividing row " + (pivy + 1) + " by " + matrix[pivy][pivx]);
			rowScale(matrix, pivy, matrix[pivy][pivx], false);
			matrixPrint(matrix);
		}

		for(int i = pivy-1; i >= 0; i--) {
			if(matrix[i][pivx] != 0) {
				
				for(int j = Math.abs(matrix[i][pivx]); j > 0; j--) {
					if(matrix[i][pivx] > 0) {
					System.out.println("Subtracting row " + (pivy + 1) + " from " + (i+1) + ", " + matrix[i][pivx] + " times");
					rowAdd(matrix, i, pivy, false);
					}
					else {
					System.out.println("Adding row " + (pivy + 1) + " to " + (i+1) + ", " + matrix[i][pivx] + " times");
					rowAdd(matrix, i, pivy, true);
					}
				}
				
			}
		}
		
		matrixPrint(matrix);
		
		}
	}

	private static int menu(String heading, String[] options) {
		Scanner menu = new Scanner(System.in);
		while(true) {

			System.out.println(heading);
			for(int i = 0; i < options.length; i++)
				System.out.println((i+1) + ": " + options[i]);

			int choice = menu.nextInt();
			menu.nextLine();


			if(choice>0 && choice<=options.length) {
				return choice;
			}
			else
				System.out.println("Value is out of range, please try again");

		}
	}

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int rows = 1;
		int columns = 1;
		System.out.println("GET READY FOR MATRICES!!");
		while(true){
			System.out.print("Enter Matrix Rows: ");
			rows = in.nextInt();
			in.nextLine();
			System.out.print("Enter Matrix Columns: ");
			columns = in.nextInt();
			in.nextLine();
			if(rows != 0 && columns != 0)
				break;
			else
				System.out.println("wtf man");
		}

		int [][] matrix = new int[rows][columns];

		System.out.println("Please enter Matrix values, left to right, top to bottom, pressing enter after each value:");
		for(int i = 0; i < rows; i++ ) {
			for(int j = 0; j < columns; j++) {
				matrix[i][j] = in.nextInt();
				in.nextLine();
			}		
		}

		matrixPrint(matrix);

		while(true) {

			System.out.println("");
			int choice = menu("What would you like to do?", new String[]{"Row switch!", "Multiply!", "Divide!", "Add Rows!", "Subtract Rows!", "REF!", "RREF!","New Matrix", "Quit"});

			if(choice == 1) {
				System.out.print("Which rows would you like to switch? (rows go top to bottom) ");
				int row1 = (in.nextInt()-1);
				in.nextLine();

				System.out.print(" and ");
				int row2 = (in.nextInt()-1);
				in.nextLine();

				if(row1 < matrix.length && row2 <matrix.length){
					rowSwitch(matrix, row1, row2);
					matrixPrint(matrix);
				}
			}
			else if(choice == 2) {
				System.out.print("Which row would you like to multiply? (rows go top to bottom) ");
				int row = (in.nextInt()-1);
				in.nextLine();
				System.out.print("What would you like to multiply by? ");
				int scalar = in.nextInt();
				in.nextLine();



				if(row < matrix.length ){
					rowScale(matrix, row, scalar, true);
					matrixPrint(matrix);
				}

			}
			else if(choice == 3) {

				System.out.print("Which row would you like to divide? (rows go top to bottom) ");

				int row = (in.nextInt()-1);
				in.nextLine();
				System.out.print("What would you like to divide by? ");
				int scalar = in.nextInt();
				in.nextLine();



				if(row < matrix.length ){
					rowScale(matrix, row, scalar, false);
					matrixPrint(matrix);
				}

			}
			else if(choice == 4){
				System.out.print("Which row would you like to add to? (rows go top to bottom) ");
				int row1 = (in.nextInt()-1);
				in.nextLine();

				System.out.print("Which row would you like to add the first row? ");
				int row2 = (in.nextInt()-1);
				in.nextLine();

				System.out.print("How many times? ");
				int times = in.nextInt();
				in.nextLine();

				if(row1 < matrix.length && row2 <matrix.length){
					for(int i = 0; i < times; i++) {
						rowAdd(matrix, row1, row2, true);
					}
					matrixPrint(matrix);

				}
			}
			else if(choice == 5){
					System.out.print("Which row would you like to subtract from? (rows go top to bottom) ");
					int rowf = (in.nextInt()-1);
					in.nextLine();

					System.out.print("Which row would you like to subtract from the first row? ");
					int rowg = (in.nextInt()-1);
					in.nextLine();

					System.out.print("How many times? ");
					int timess = in.nextInt();
					in.nextLine();

					if(rowf < matrix.length && rowg <matrix.length){
						for(int i = 0; i < timess; i++) {
							rowAdd(matrix, rowf, rowg, false);
						}
						matrixPrint(matrix);

					}
			}
			else if(choice == 6){
						REF(matrix);
						matrixPrint(matrix);
					}

				
				else if(choice == 7){
					RREF(matrix);
					matrixPrint(matrix);

				}
				else if(choice == 8){
					while(true){
						System.out.print("Enter Matrix Rows: ");
						rows = in.nextInt();
						in.nextLine();
						System.out.print("Enter Matrix Columns: ");
						columns = in.nextInt();
						in.nextLine();

						if(rows != 0 && columns != 0)
							break;
						else
							System.out.println("wtf man");
					}


					matrix = new int[rows][columns];

					System.out.println("Please enter Matrix values, left to right, top to bottom, pressing enter after each value:");
					for(int i = 0; i < rows; i++ ) {
						for(int j = 0; j < columns; j++) {
							matrix[i][j] = in.nextInt();
							in.nextLine();
						}		
					}

					matrixPrint(matrix);
				}
				else if(choice == 9){
					System.out.println("Adieu");
					in.close();
					System.exit(0);

				}


			}

			
	}
		


	}
	
