from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "numberOfCarsOverNumberOfTrucksRatio100.txt"

data = pylab.loadtxt(dataFile)  
x = data[:,0]
y = data[:,1]

x, y = y, x

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

#ax.set_xlim(0, 16)
ax.set_title("")
pylab.ylabel("Max distance within cluster")
pylab.xlabel("Ratio of number of cars over number of trucks")
ax.plot(x, y, 'x')
pylab.savefig(dataFile[:-4])