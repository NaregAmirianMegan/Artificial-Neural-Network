# Artificial Neural Network
Implementation of a java neural network. 

### Specifications: 
There are three layers: input; hidden; and output. There is also a sigmoid activation function to introduce nonlinearity to the model. The model is optimized using stochastic gradient descent (described below). Optimization is controlled by a learning rate and momentum factor. NeuralNetwork.java also includes an option without momentum to prevent possible overfitting in some models. Point.java and Graph.java allow for the efficiency of the model to be graphed and visualized.
The example used in the code is a simple XOR logic function. Using momentum, the computer can accurately model this function with under 500 epochs of training starting with randomly generated weights. The modeling is much slower without the use of momentum.
Momentum is mathematically much like a bouncing ball on an error gradient. The steeper the gradient the faster the ball moves down the gradient; however, if the ball gets going too fast, the model can experience overfitting. In other words, the ball will bounce back up, causing greater error in the model. It is for this reason that momentum is optional when using this code base.  

Concise mathematical explanation of forward and backward propagation in a three layer neural network using a sigmoid activation function, momentum, and stochastic gradient descent.

![](neural_net_math.png)

 <br />

# Tests and Moving Forward

### Sample Error Log

![errorlog](https://user-images.githubusercontent.com/22607081/34455159-8e26cc3c-ed3e-11e7-933e-c9793c7ea86c.JPG)

Example Comparisons Between Momentum and No Momentum when modeling the XOR function. Each graph plots the global model error for each training iteration:

### NO MOMENTUM VS. MOMENTUM Graphs
![m_no-m_comparison](https://user-images.githubusercontent.com/22607081/34455125-e3102f96-ed3d-11e7-98a1-e192b65f3aa8.JPG) <br />
![comparison2](https://user-images.githubusercontent.com/22607081/34455148-5836721c-ed3e-11e7-9fc6-7f5924da4514.JPG)

## Moving Forward

It is clear from both the percent error logs and graphs that the implementation of momentum in a neural network is often times beneficial because it trains the model to a lower error rate and it does this faster.
In the future I would like to implement multi-layer functionality for the hidden layers as well as a dropout rate and a choice of activation functions. Multi-layer networks, or Deep Neural Nets, are better at modeling, dropout helps prevent overfitting, and other activation functions have recently been proven better than the sigmoid activation function (e.g., the rectified linear, or relu, function).

With proper computational scaling and the modifications described above, this model could be applied to many current machine learning problems such as image classification, natural language processing, and large-scale data analysis. 
