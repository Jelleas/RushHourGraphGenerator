from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "numberOfSubclustersPerCluster.txt"

x = []
y = []

numberOfSubclustersList = []
weights = []
with open(dataFile) as f:
	for line in f:
		numberOfSubclusters, count = line.split()
		weights.append(int(count))
		numberOfSubclustersList.append(int(numberOfSubclusters))

# the histogram of the data
pylab.hist(numberOfSubclustersList, weights = weights, bins = range(0, max(numberOfSubclustersList) + 1, 5))

pylab.xlabel('Number of clusters within clustergroup')
pylab.ylabel('Number of clustergroups')
pylab.title('Histogram: Number of clusters per clustergroup')
#pylab.grid(True)
#pylab.yscale('log')
pylab.ylim(1, 10**8)
pylab.yscale('log', nonposy='clip')
#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  

print max(numberOfSubclustersList)