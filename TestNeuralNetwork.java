//test format from web

import java.text.*;

public class TestNeuralNetwork 
{
	public static void main(String args[])
	{
	 //CREATE DATABASE OF INPUTS(xor inputs is just the basic binary pattern for training; can be any database)
		double xorInput[][] =
			{{0.0, 0.0},
			 {1.0, 0.0},
			 {0.0, 1.0},
			 {1.0, 1.0}};

		double xorIdeal[][] =
			{{0.0},
			 {1.0},
			 {1.0},
			 {0.0}};
  
		double predict[] = {0.0, 0.0};

		System.out.println("\tLearn:");

		Network network = new Network(2,3,1,0.7,0.9);
		
		Mind mind = new Mind(network, xorInput, xorIdeal);
		
		mind.learn(10000);
		
		//Create prediction and print outputs
		double[] prediction = mind.predict(predict);
		
		for(int i=0; i<predict.length; i++)
		{
			System.out.print(predict[i] + "::");
		}
		System.out.print("   ");
		for(int i=0; i<prediction.length;i++)
		{
			System.out.println(prediction[i]);
		}
		/*

		NumberFormat percentFormat = NumberFormat.getPercentInstance(); //turns number into percent to represent error
		percentFormat.setMinimumFractionDigits(4);


		for (int i=0;i<100000;i++) 
		{
			for (int j=0;j<xorInput.length;j++) 
			{
				network.computeOutputs(xorInput[j]);
				network.calcError(xorIdeal[j]);
				network.learn();
			}
			System.out.println( "Trial #" + i + ",Error:" +
			percentFormat .format(network.getError(xorInput.length)) );
		}
  
  //print recall
		System.out.println("\tRecall:");
		for (int i=0;i<xorInput.length;i++) 
		{

			for (int j=0;j<xorInput[0].length;j++) 
			{
				System.out.print( xorInput[i][j] +":" );
			}

			double out[] = network.computeOutputs(xorInput[i]);
			System.out.println("="+out[0]);
		}
  
  
  //print prediction
		System.out.println("\tPredict:");
		for(int i=0;i<predict.length;i++)
		{
			System.out.print(predict[i] + ":");
		}
		double predictedOut[] = network.computeOutputs(predict);
		System.out.print("="+predictedOut[0]);
		*/
	}//END MAIN
	
}//END CLASS
