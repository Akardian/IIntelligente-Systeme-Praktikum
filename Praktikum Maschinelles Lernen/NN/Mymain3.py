import network3
from network3 import ConvPoolLayer, FullyConnectedLayer, SoftmaxLayer
from network3 import Network, ReLU

# Set dataset
data = "../data/cifar10.pkl.gz"

# Trainingsdaten erweitern: execfile('expand_mnist.py')
# data = "../data/mnist_expanded.pkl.gz"

training_data, validation_data, test_data = network3.load_data_shared(data)

mini_batch_size = 10

# CIFAR10
# Dimensions 32x32x3
# Output Layer 1x1x10

#Typical Filter 5x5xImage Depth

#Pooling Size 2x2 (Controlls overfitting)
# overlapping pooling 3x2

net = Network(
    [
        # image_shape = mini-batch size, the number of input feature maps, the image height, and the image width
        # filter_shape = number of filters, the number of input feature maps, the filter height, and the filter width
        # poolsize = the y and x pooling sizes
        # border_mode = padding on x and y
        ConvPoolLayer(input_shape=(mini_batch_size, 3, 32, 32),
                      filter_shape=(20, 3, 5, 5),
                      pool_size=(2, 2),
                      border_mode=(0, 0),
                      activation_fn=ReLU),
        ConvPoolLayer(input_shape=(mini_batch_size, 20, 10, 10),
                      filter_shape=(40, 20, 5, 5),
                      pool_size=(2, 2),
                      border_mode=(0, 0),
                      activation_fn=ReLU),
        FullyConnectedLayer(n_in=40 * 4 * 4, n_out=1000, activation_fn=ReLU, p_dropout=0.5),
        FullyConnectedLayer(n_in=1000, n_out=1000, activation_fn=ReLU, p_dropout=0.1),
        SoftmaxLayer(n_in=1000, n_out=10, p_dropout=0.1)
    ],
    mini_batch_size)

net.SGD(training_data, 1, mini_batch_size, 0.03, validation_data, test_data)
