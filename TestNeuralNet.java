/**
 * Author: Nareg Amirian Megan
 * Title: TestNeuralNet.java
 * Source Files: Matix.java, NeuralNet.java, Graph.java, Point.java
 * Purpose: Test neural network on basic data set
 */

public class TestNeuralNet 
{

	public static void main(String[] args) 
	{
		//Data must be normal between 0 and 1 otherwise network can't handle it. 
		//Normalization is easy to implement however
		double[][] XOR = {{0, 0},
						  {0, 1},
						  {1, 0},
						  {1, 1}};
		double[][] ideal = {{0},
							{1},
							{1},
							{0}};
		double[][] prediction = {{0, 0},
								 {0, 1},
								 {1, 0},
								 {1, 1}};
		
		Matrix XData = new Matrix(XOR);
		Matrix YData = new Matrix(ideal);
		Matrix predict = new Matrix(prediction);
	
		NeuralNetwork net = new NeuralNetwork
				(2, 3, 1, 0.8, 0.3, 0.3, 0.2, 10000);
		net.learn(XData, YData);
		
		System.out.println("PREDICTION:");
		Matrix output = net.predict(predict);
		System.out.print(output);
		System.out.println("Final Global Error: " + net.getFinalError());
		
	}

}
