/**
 * Author: Nareg Amirian Megan
 * Title: NeuralNetwork.java
 * Source Files: Matix.java
 * Purpose: Provide a medium level implementation of 
 * a neural network using gradient descent and one 
 * hidden layer
 */

import java.util.ArrayList;

public class NeuralNetwork {

	private int inNodes, hiddenNodes, outNodes, iterations, iteration;
	private Matrix W_IH, W_HO, prevDeltaW_HO, prevDeltaW_IH;
	private double LR, Bias1, Bias2, momentum;
	//ArrayList to log error and efficiency
	private ArrayList<Point> errorLog = new ArrayList<Point>();
			
	/**
	 * Construct a Neural Network object
	 * 
	 * @param inputNodes
	 * 				features in input layer
	 * @param hiddNodes
	 * 				hidden Nodes
	 * @param outputNodes
	 * 				output features
	 * @param learningRate
	 * 				Rate of gradient descent
	 * @param bias1
	 * 				bias on input layer
	 * @param bias2
	 * 				bias on hidden layer
	 * @param m
	 * 				momentum of learning rate
	 * @param i
	 * 				training iterations
	 */
	public NeuralNetwork(int inputNodes, int hiddNodes, 
			int outputNodes,
			double learningRate, double bias1, 
			double bias2, double m, int i) {
		inNodes = inputNodes;
		hiddenNodes = hiddNodes;
		outNodes = outputNodes;
		W_IH = new Matrix(inputNodes, hiddNodes);
		W_HO = new Matrix(hiddNodes, outputNodes);
		W_IH.fill(-1);//randomly fill weights
		W_HO.fill(-1);
		LR = learningRate;//how fast the weights are adjusted and the computer learns
		Bias1 = bias1;//How strict the computer is when restricting learning
		Bias2 = bias2;
		momentum = m;//get the ball rolling on descent, make process more efficient
		prevDeltaW_IH = new Matrix(inNodes, hiddenNodes);
		prevDeltaW_HO = new Matrix(hiddenNodes, outNodes);
		prevDeltaW_IH.fill(-1);
		prevDeltaW_HO.fill(-1);
		iterations = i;
		iteration = 0;
	}
	
	/**
	 * Adjust weights and biases using partial derivatives (i.e. gradient descent)
	 * @param XData
	 * 				XData to be trained
	 * @param YData
	 * 				YData to be trained
	 */
	public void propogate(Matrix XData, Matrix YData) {	
		//forward propagation
			//first layer
		Matrix hidden = XData.multiply(W_IH).
				addToAllElements(Bias1).sigmoid();
			//second layer
		Matrix output = hidden.multiply(W_HO).
				addToAllElements(Bias2).sigmoid();
		
		//calculate error
		Matrix globalE = (YData.subtract(output).abs());
		double percentError = globalE.averageElements()*5;
		
		//add percent error to error log 
		int step = iterations/1000;
		if(iteration % step == 0) {
			errorLog.add(new Point(iteration/step, percentError));
			System.out.println("Epoch " + iteration/step + ": \n   "
					+ "Error: " + percentError);
		}
		
		//back propagation
		Matrix deltaFactor = output.subtract(YData).hadamard
				(output.hadamard(output.scaleReturn(-1).addToAllElements(1)));
		Matrix deltaW_HO = (deltaFactor.transpose().multiply(hidden)).
				scaleReturn(LR).transpose();
		Matrix deltaW_IH = (XData.transpose().multiply
				(deltaFactor.multiply(W_HO.transpose()).hadamard(hidden.hadamard
						(hidden.scaleReturn(-1).
								addToAllElements(1))))).scaleReturn(LR);
		
		//apply momentum
		deltaW_HO = prevDeltaW_HO.scaleReturn(momentum).subtract(deltaW_HO);
		deltaW_IH = prevDeltaW_IH.scaleReturn(momentum).subtract(deltaW_IH);
		
		prevDeltaW_IH = deltaW_IH;
		prevDeltaW_HO = deltaW_HO;
		
		//update weights
		W_IH  = W_IH.add(deltaW_IH);
		W_HO = W_HO.add(deltaW_HO);
	}
	
	/**
	 * Propagation one without momentum 
	 * @param XData
	 * 				XData to be trained
	 * @param YData
	 * 				YData to be trained
	 */
	public void propogate2(Matrix XData, Matrix YData) {	
		//forward propagation
			//first layer
		Matrix hidden = XData.multiply(W_IH).
				addToAllElements(Bias1).sigmoid();
			//second layer
		Matrix output = hidden.multiply(W_HO).
				addToAllElements(Bias2).sigmoid();
		
		//calculate error
		Matrix globalE = (YData.subtract(output).abs());
		double percentError = globalE.averageElements()*5;
		
		//add percent error to error log 
		int step = iterations/1000;
		if(iteration % step == 0) {
			errorLog.add(new Point(iteration/step, percentError));
		}
		
		//back propagation
		Matrix deltaFactor = output.subtract(YData).hadamard(output.hadamard
				(output.scaleReturn(-1).addToAllElements(1)));
		Matrix deltaW_HO = (deltaFactor.transpose().multiply(hidden)).
				scaleReturn(LR).transpose();
		Matrix deltaW_IH = (XData.transpose().multiply(deltaFactor.multiply
				(W_HO.transpose()).hadamard(hidden.hadamard
						(hidden.scaleReturn(-1).
								addToAllElements(1))))).scaleReturn(LR);
		
		//update weights
		W_IH  = W_IH.subtract(deltaW_IH);
		W_HO = W_HO.subtract(deltaW_HO);
	}
	
	//Graphing numbers are specific to data case
	//They should be adjusted when usin other data
	
	/**
	 * Iterate through propagate iterations number of times
	 * @param XData
	 * @param YData
	 */
	public void learn(Matrix XData, Matrix YData) {
		while(iteration < iterations) {
			propogate(XData, YData);
			iteration++;
		}
		Graph graph = new Graph(errorLog);
		graph.show();
	}
	
	/**
	 * learn2 implementing propogate2
	 * @param XData
	 * @param YData
	 */
	public void learn2(Matrix XData, Matrix YData) {
		while(iteration < iterations) {
			propogate2(XData, YData);
			iteration++;
		}
		Graph graph = new Graph(errorLog);
		graph.show();
	}
	
	/**
	 * Use model to predict input data
	 * @param XData
	 * 				Data to base prediction on 
	 * @return
	 * 				Prediction
	 */
	public Matrix predict(Matrix XData) {
		//forward propagation
			//first layer
		Matrix hiddenRaw = XData.multiply(W_IH).
				addToAllElements(Bias1);
		Matrix hidden = hiddenRaw.sigmoid();
			//second layer
		Matrix outputRaw = hidden.multiply(W_HO).
				addToAllElements(Bias2);
		Matrix output = outputRaw.sigmoid();
		return output;
	}
	
	/**
	 * Get final error of trained model
	 * @return
	 * 				final error
	 */
	public double getFinalError() {
		return (errorLog.get(errorLog.size()-1).getY()/5);
	}

}
