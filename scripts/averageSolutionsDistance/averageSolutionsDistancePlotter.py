from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "averageSolutionsDistance10.txt"

maxDistances = []
distances = []

with open(dataFile) as f:
	maxDistance = 0
	nLines = 0
	for line in f:
		nLines += 1
		if nLines % 10 == 0:
			maxDistance += 1

		clusterId, distance, sumSubclusterNumSolutions = line.split()
		maxDistances.append(maxDistance)
		distances.append(int(distance) / int(sumSubclusterNumSolutions))
		
# the histogram of the data
pylab.plot(maxDistances, distances, 'x')

pylab.xlabel('Size')
pylab.ylabel('Avarage maxDistance')
pylab.title('Histogram: avarage maxdistance per size bin')
#pylab.xlim(0,50000)
pylab.yscale('log')
pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  

