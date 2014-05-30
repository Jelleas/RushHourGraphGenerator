from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "hardnessDifference_withMaxDistanceBiggerThan30.txt"

xs = []
ys = []

with open(dataFile) as f:
	x = []
	y = []
	prevX = -1

	for line in f:
		xEntry, yEntry = line.split()
		xEntry = int(xEntry)
		yEntry = float(yEntry)

		if xEntry < prevX:
			xs.append(x)
			ys.append(y)
			x = []
			y = []

		x.append(xEntry)
		y.append(yEntry)
		prevX = xEntry

	xs.append(x)
	ys.append(y)

print len(ys)
# the histogram of the data


for x, y in zip(xs, ys):
	pylab.plot(x, y)

pylab.xlabel('Distance')
pylab.ylabel('Number of Configurations')
pylab.title('')
#pylab.xlim(0,50000)
pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig(dataFile[:-4])  
