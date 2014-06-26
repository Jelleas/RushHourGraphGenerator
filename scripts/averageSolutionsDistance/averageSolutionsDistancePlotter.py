from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "averageSolutionsDistance10_0.txt"

maxDistances = []
distances = []

with open(dataFile) as f:
	nLines = 0
	for line in f:
		nLines += 1

		clusterId, maxDistance, distance, sumSubclusterNumSolutions, size = line.split()
		maxDistances.append(maxDistance)
		sumSubclusterNumSolutions = ((int(sumSubclusterNumSolutions) - 1))
		if sumSubclusterNumSolutions <= 0:
			sumSubclusterNumSolutions = 1
		distances.append(int(distance) / sumSubclusterNumSolutions**2)
		
# the histogram of the data
pylab.plot(maxDistances, distances, 'x')

pylab.xlabel('Size')
pylab.ylabel('Avarage maxDistance')
pylab.title('Histogram: avarage maxdistance per size bin')
#pylab.xlim(0,50000)
#pylab.yscale('log')
pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  

