from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "histogramDistanceInSizes500.txt"

x = []
y = []

with open(dataFile) as f:
	for line in f:
		xEntry, sumMaxDistances, nNodes = line.split()
		if int(nNodes) == 0:
			yEntry = 0
		else:
			yEntry = float(sumMaxDistances) / float(nNodes)
		x.append(float(xEntry))
		y.append(yEntry)

# the histogram of the data
pylab.plot(x, y, 'x')

pylab.xlabel('Size')
pylab.ylabel('Avarage maxDistance')
pylab.title('Histogram: avarage maxdistance per size bin')
pylab.xlim(0,50000)
pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  
