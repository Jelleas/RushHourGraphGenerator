from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "numberOfSolvableConfigurationsPerCluster.txt"

x = []
y = []

numberOfConfigurationsList = []
weights = []
with open(dataFile) as f:
	for line in f:
		numberOfConfigurations, count = line.split()
		weights.append(int(count))
		numberOfConfigurationsList.append(int(numberOfConfigurations))

# the histogram of the data
weights[-1] = 1
pylab.hist(numberOfConfigurationsList, weights = weights, bins = range(0, max(numberOfConfigurationsList) + 100000, 20000))

pylab.xlabel('Number of solvable configurations within clustergroup')
pylab.ylabel('Number of clustergroups')
pylab.title('Histogram: Number of solvable configurations per clustergroup')
#pylab.grid(True)
#pylab.yscale('log')
#pylab.ylim(1, 10**8)
pylab.yscale('log', nonposy='clip')
#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])

print max(numberOfConfigurationsList)