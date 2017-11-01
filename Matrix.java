/**
 * Author: Nareg Amirian Megan
 * Title: Matrix.java
 * Source Files: -
 * Purpose: Provide a matrix structure to pass data through a
 * neural network gradient descent model
 */

import java.util.Random;
import java.util.Scanner;

public class Matrix {
	private int rows, columns;
	private double[][] myMatrix;
	
	/**
	 * Construct matrix with row column size parameters
	 * 
	 * @param r
	 * 			rows
	 * @param c
	 * 			columns
	 */
	public Matrix(int r, int c) {
		rows = r;
		columns = c;
		myMatrix = new double[rows][columns];
	}
	
	/**
	 * Construct a matrix directly from a 2-D double array
	 * 
	 * @param dataSet
	 * 				2-D double array
	 */
	public Matrix(double[][] dataSet) {
		rows = dataSet.length;
		columns = dataSet[0].length;
		
		myMatrix = new double[rows][columns];
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				myMatrix[r][c] = dataSet[r][c];
			}
		}
	}
	
	/**
	 * Construct a matrix from a 1-D double array
	 * 
	 * @param dataSet
	 * 				1-D double array
	 */
	public Matrix(double[] dataSet) {
		columns = dataSet.length;
		rows = 1;
		
		myMatrix = new double[rows][columns];
		
		for(int c = 0; c < columns-1; c++) {
			myMatrix[0][c] = dataSet[c];
		}
	}
	
	/**
	 * Get rows of matrix 
	 * 
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Get columns of matrix
	 * 
	 * @return columns
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * Get value of specific element in array
	 * 
	 * @param r
	 * 			row
	 * @param c
	 * 			column
	 * @return element value
	 */
	public double getValAt(int r, int c) {
		double positionVal = myMatrix[r][c];
		return positionVal;
	}
	
	/**
	 * Set value of element in array
	 * 
	 * @param r
	 * 			row
	 * @param c
	 * 			column
	 * @param n
	 * 			value to set
	 */
	public void setValAt(int r, int c, double n) {
		myMatrix[r][c] = n;
	}
	
	/**
	 * Print matrix 
	 * 
	 * @return output
	 */
	public String toString() {
		String output = "";
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				output += " ";
				output += myMatrix[r][c];
			}
			output += '\n';
		}
		return output;
	}
	
	/**
	 * Fill array with values
	 * 
	 * @param type
	 * 			1 = fill with input values from user
	 * 			-1 = fill with random values
	 * 			0.5 = fill with 0.5's
	 */
	public void fill(double type) {
		Random generator = new Random();
		Scanner input = new Scanner(System.in);
		int value = 0;
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				if(type == -1) {
					myMatrix[r][c] = generator.nextInt(3);
				}
				if(type == 1) {
					System.out.println("Integer: ");
					myMatrix[r][c] = input.nextInt();
				}
				if(type == 0.5) {
					myMatrix[r][c] = 0.5;
				}
			}
		}
	}
	
	/**
	 * Add two matrices
	 * 
	 * @param m
	 * 			matrix to add
	 * @return
	 * 			resultant matrix
	 */
	public Matrix add(Matrix m) {
		Matrix matrixSum = new Matrix(rows, columns);
		if(m.getRows() == rows && m.getColumns() == columns) {
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < columns; c++) {
					double x = m.getValAt(r, c) + myMatrix[r][c];
					matrixSum.setValAt(r, c, x);
				}
			}
		}else
			System.out.println("These matrices cannot be added "
					+ "because they have unequal dimensions!");
		return matrixSum;
	}
	
	/**
	 * Subtract two matrices
	 * 
	 * @param m
	 * 			matrix to subtract
	 * @return 
	 * 			resultant matrix
	 */
	public Matrix subtract(Matrix m) {
		Matrix matrixDifference = new Matrix(rows, columns);
		if(m.getRows() == rows && m.getColumns() == columns) {
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < columns; c++) {
					double x = myMatrix[r][c] - m.getValAt(r, c);
					matrixDifference.setValAt(r, c, x);
				}
			}
		}else
			System.out.println("These matrices cannot be subtracted "
					+ "because they have unequal dimensions!");
		return matrixDifference;
	}
	
	/**
	 * Multiply two matrices
	 * 
	 * @param m
	 * 			matrix to multiply
	 * @return
	 * 			resultant matrix
	 */
	public Matrix multiply(Matrix m) {
		Matrix matrixProduct = new Matrix(rows, m.getColumns());
		double x;
		
		if(columns == m.getRows()) {
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < m.getColumns(); c++) {
					x = 0;
					for(int i = 0; i < columns; i++) {
						x += myMatrix[r][i] * m.getValAt(i, c); 
					}
					matrixProduct.setValAt(r, c, x);
				}
			}
			return matrixProduct;
		}else{
			System.out.println("The dimensions of these matrices "
					+ "do not allow for multiplication");
			return null;
		}
	}
	
	/**
	 * Multiply matrix by scalar
	 * 
	 * @param n
	 * 			scalar
	 */
	public void scale(double n) {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				myMatrix[r][c] = myMatrix[r][c] * n;
			}
		}
	}
	
	/**
	 * Same as .scale(double n) except it returns a new matrix
	 * instead of mutating the one called upon
	 * 
	 * @param n
	 * 			scalar
	 * @return 
	 * 			resultant matrix
	 */
	public Matrix scaleReturn(double n) {
		Matrix result = new Matrix(myMatrix.length, 
				myMatrix[0].length);
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				double x = myMatrix[r][c] * n;
				result.setValAt(r, c, x);
			}
		}
		return result;
	}
	
	/**
	 * Turns each column into a row
	 * 
	 * @return
	 * 			Transposed matrix
	 */
	public Matrix transpose() {
		Matrix m = new Matrix(columns, rows);
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				double x = myMatrix[r][c];
				m.setValAt(c, r, x);
			}
		}
		return m;
	}
	
	/**
	 * Horizontally concatenate two matrices
	 * 
	 * @param m
	 * 			matrix to concatenate onto other matrix
	 * @return
	 * 			concatenated matrix
	 */
	public Matrix concatenate(Matrix m) {
		Matrix concatenated = new Matrix(rows, 
				(m.getColumns() + columns));
		double x;
		if(rows == m.getRows()) {
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < concatenated.getColumns(); c++) {
					if(c < columns) {
						x = myMatrix[r][c];
						concatenated.setValAt(r, c, x);
					}else
						x = m.getValAt(r, (c - 
								(concatenated.getColumns()/2)));
						concatenated.setValAt(r, c, x);
				}
			}
			return concatenated;
		}else
		return null;
	}
	
	/**
	 * Position multiply matrices
	 * 
	 * @param m
	 * 			matrix to multiply
	 * @return 
	 * 			resultant matrix
	 */
	public Matrix hadamard(Matrix m) {
		Matrix hadamard = new Matrix(rows, columns);
		if(rows == m.getRows() && columns == m.getColumns()) {
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < columns; c++) {
					double x = myMatrix[r][c] * m.getValAt(r, c);
					hadamard.setValAt(r, c, x);
				}
			}
		}
		return hadamard; 
	}
	
	/**
	 * Multiply a matrix by a matrix of scalars
	 * 
	 * @param m
	 * 			Matrix to multiply
	 * @return
	 * 			Resultant matrix
	 */
	public Matrix Kronecker(Matrix m) {
		Matrix kronecker = new Matrix(rows*m.getRows(),
				columns*m.getColumns());
		double x;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				for(int r = 0; r < m.getRows(); r++) {
					for(int c = 0; c < m.getColumns(); c++) {
						x = myMatrix[i][j] * m.getValAt(r, c);
						kronecker.setValAt(((2*i) + r), ((2*j) + c), x);
					}
				}
			}
		}
		return kronecker;
	}
	
	/**
	 * Determine action potential for neurons
	 * 
	 * @return action potential 
	 */
	public Matrix sigmoid() {
		Matrix sigmoid = new Matrix(rows, columns);
		
		for(int r = 0; r < myMatrix.length; r++) {
			for(int c = 0; c < myMatrix[0].length; c++) {
				double x = 1/(1 + (Math.pow(Math.E, -(myMatrix[r][c]))));
				sigmoid.setValAt(r, c, x);
			}
		}
		return sigmoid;
	}
	
	/**
	 * Determine gradient for descent
	 * 
	 * @return gradient
	 */
	public Matrix sigmoidDeriv() {
		Matrix sigmoidDeriv = new Matrix(rows, columns);
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				double x = (Math.pow(Math.E, myMatrix[r][c]))/Math.pow((1 +
						(Math.pow(Math.E, myMatrix[r][c]))), 2);
				sigmoidDeriv.setValAt(r, c, x);
			}
		}
		return sigmoidDeriv;
	}
	
	/**
	 * Sums columns of matrix
	 * 
	 * @return 
	 * 			summed matrix
	 */
	public Matrix sumColumns() {
		Matrix sum = new Matrix(1, columns);
		double x = 0;
		
		for(int c = 0; c < myMatrix[0].length; c++) {
			x = 0;
			for(int r = 0; r < myMatrix.length; r++) {
				x += myMatrix[r][c];
			}
			sum.setValAt(0, c, x);
		}
		return sum;
	}
	
	/**
	 * Adds n to all elements in myMatrix
	 * 
	 * @param n
	 * 			number to add
	 * @return 
	 * 			Matrix of added numbers
	 */
	public Matrix addToAllElements(double n) {
		Matrix added = new Matrix(rows, columns);
		
		for(int r = 0; r < myMatrix.length; r++) {
			for(int c = 0; c < myMatrix[0].length; c++) {
				double x = myMatrix[r][c] + n; 
				added.setValAt(r, c, x);
			}
		}
		return added;
	}
	
	/**
	 * Sum rows of matrix
	 * 
	 * @return
	 * 			matrix of summed rows
	 */
	public Matrix sumRows() {
		Matrix result = new Matrix(myMatrix.length, 1);
		for(int r = 0; r < myMatrix.length; r++) {
			double x = 0;
			for(int c = 0; c < myMatrix[0].length; c++) {
				x += myMatrix[r][c];
			}
			result.setValAt(r, 0, x);
		}
		return result;
	}
	
	/**
	 * Round elements in a matrix
	 */
	public void roundMatrix() {
		for(int r = 0; r < myMatrix.length; r++) {
			for(int c = 0; c < myMatrix[0].length; c++) {
				myMatrix[r][c] = Math.round((float)myMatrix[r][c]);
			}
		}
	}
	
	/**
	 * Takes absolute value of all elements in matrix
	 * 
	 * @return
	 * 			abs matrix
	 */
	public Matrix abs() {
		Matrix result = new Matrix(myMatrix.length, myMatrix[0].length);
		for(int r = 0; r < myMatrix.length; r++) {
			for(int c = 0; c < myMatrix[0].length; c++) {
				double x = Math.abs(myMatrix[r][c]);
				result.setValAt(r, c, x);
			}
		}
		return result;
	}
	
	/**
	 * Element-wise division
	 * 
	 * @param m
	 * 			matrix to divide by
	 * @return
	 * 			quotient matrix
	 */
	public Matrix divideByElement(Matrix m) {
		Matrix quotient = new Matrix(myMatrix.length, myMatrix[0].length);
		for(int r = 0; r < myMatrix.length; r++) {
			for(int c = 0; c < myMatrix[0].length; c++) {
				double x = myMatrix[r][c] / m.getValAt(r, c);
				quotient.setValAt(r, c, x);
			}
		}
		return quotient;
	}
	
	/**
	 * Take average of all elements in matrix
	 * 
	 * @return average
	 */
	public double averageElements() {
		double total = 0;
		int count = 0;
		for(int r = 0; r < myMatrix.length; r++) {
			for(int c = 0; c < myMatrix[0].length; c++) {
				total += myMatrix[r][c];
				count++;
			}
		}
		return total / count;
	}
	
}
