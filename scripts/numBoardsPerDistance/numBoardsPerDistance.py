from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from math import log
from scipy.optimize import curve_fit

"""
print "reading data file"
data = []
with open("distributionMaxDistance.txt") as dataFile:
	for line in dataFile:
		data.append(int(line.split(",")[0]))

print "finished reading data file"
"""

data = pylab.loadtxt('numBoardsPerDistance.txt')  
x = data[:,0]
y = data[:,1]


for i, yElem in enumerate(y):
	y[i] = log(yElem, 10)

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

def func(x, a, b):
  return a * x + b

#p0 = sy.array([3.1,-2.5, 100])
p0 = sy.array([1,1])

coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
#yaj = func(x, coeffs[0], coeffs[1], coeffs[2])
yaj = func(x, coeffs[0], coeffs[1])

for i in range(len(x)):
	x[i] -= 1

for i, yElem in enumerate(y):
	y[i] = 10**yElem
	yaj[i] = 10**yaj[i]

ax.set_xlim(0, 52)
ax.set_title("Distribution of hardness among configurations.")
pylab.xlabel("Length of the minimal path to the nearest solution")
pylab.ylabel("Number of configurations")
ax.plot(x, y, 'x', x, yaj, 'r-')
pylab.yscale('log')
pylab.savefig('numBoardsPerDistanceGraph')
