public class Tester {

	public static void main(String[] args) 
	{
		int inNodes = 2;
		int hiddenNodes = 3;
		int outNodes = 1;
		Matrix W_IH = new Matrix(inNodes, hiddenNodes);
		Matrix W_HO = new Matrix(hiddenNodes, outNodes);
		W_IH.fill(-1);//randomly fill weights
		W_HO.fill(-1);
		double LR = 0.7;//how fast the weights are adjusted and the computer learns
		double Bias1 = 0.1;//How strict the computer is when restricting learning
		double Bias2 = 0.1;
		double[][] XOR = {{0, 0},
						  {0, 1},
						  {1, 0},
						  {1, 1}};
		double[][] ideal = {{0},
							{1},
							{1},
							{0}};
		Matrix XData = new Matrix(XOR);
		Matrix YData = new Matrix(ideal);
		
		//forward propagation
		//first layer
	Matrix hidden = XData.multiply(W_IH).addToAllElements(Bias1).sigmoid();
		//second layer
	Matrix output = hidden.multiply(W_HO).addToAllElements(Bias2).sigmoid();
	
	/*System.out.println(XData);
	System.out.println(YData);
	System.out.println(hidden);
	System.out.println(output);
	*/
	//back propagation
	Matrix deltaFactor = output.subtract(YData).hadamard(output.hadamard(output.scaleReturn(-1).addToAllElements(1)));
	Matrix deltaW_HO = (deltaFactor.transpose().multiply(hidden)).scaleReturn(LR).transpose();
	
	System.out.println(deltaW_HO);
	
	Matrix deltaW_IH = (XData.transpose().multiply(deltaFactor.multiply(W_HO.transpose()).hadamard(hidden.hadamard(hidden.scaleReturn(-1).addToAllElements(1))))).scaleReturn(LR);
	System.out.println(deltaW_IH);
	
	//update weights
	W_IH.subtract(deltaW_IH);
	W_HO.subtract(deltaW_HO);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		Matrix a = new Matrix(2,2);
		Matrix b = new Matrix(2,1);
		a.fill(-1);
		b.fill(-1);
		
		System.out.println(a);
		System.out.println(b);
		//System.out.println(a.add(b));
		//System.out.println(a.multiply(b));
		//System.out.println(a.transpose());
		//System.out.println(a.sumColumns());
		//System.out.println(a.sigmoid());
		//System.out.println(b.sigmoidDeriv());
		//System.out.println(a.getValAt(0, 0));
		//System.out.println(a.positionMultiply(b));
		Matrix scaled = b.scaleReturn(-1);
		System.out.println(scaled);
		*/
	}

}
