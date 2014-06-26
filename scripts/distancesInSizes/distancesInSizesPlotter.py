from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from math import log
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

		if (float(nNodes) > 1000):
			x.append(float(xEntry))
			y.append(yEntry)

def func(x, a, b):
  return x ** a + b

#p0 = sy.array([3.1,-2.5, 100])
p0 = sy.array([2,0])

coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
#yaj = func(x, coeffs[0], coeffs[1], coeffs[2])
yaj = func(x, coeffs[0], coeffs[1])


# the histogram of the data
pylab.plot(x, y, 'x')#, x, yaj, 'r-')

pylab.xlabel('Number of configurations within a cluster group')
pylab.ylabel('Average maximum distance')
pylab.title('Influence of number of configurations within a cluster group\n on maximum distance.')
#pylab.xlim(0,50000)
#pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  
