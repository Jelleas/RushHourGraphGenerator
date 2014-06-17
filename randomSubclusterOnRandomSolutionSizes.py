from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "randomSubclusterOnRandomSolutionSizes.txt"

x = []
y = []


sumSize = 0
with open(dataFile) as f:
	for line in f:
		clusterId, size = line.split()
		sumSize += int(size)

print sumSize

"""
# the histogram of the data
pylab.plot(x, y, 'x')

pylab.xlabel('Size')
pylab.ylabel('Avarage maxDistance')
pylab.title('Histogram: avarage maxdistance per size bin')
pylab.xlim(0,50000)
pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  
"""
