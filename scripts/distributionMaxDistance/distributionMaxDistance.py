from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from math import log
from scipy.optimize import curve_fit

dataFile = "distributionMaxDistance.txt"

x = []
y = []

maxDistanceList = []
weights = []
with open(dataFile) as f:
	for line in f:
		maxDistance, count = line.split()
		weights.append(int(count))
		maxDistanceList.append(int(maxDistance) - 1)

# the histogram of the data
pylab.hist(maxDistanceList, weights = weights, bins = range(0, max(maxDistanceList) + 1, 1))
pylab.xlabel('Number of clusters within clustergroup')
pylab.ylabel('Number of clustergroups')
pylab.title('Histogram: Number of clusters per clustergroup')
#pylab.grid(True)
#pylab.yscale('log')
pylab.ylim(1, 10**8)
pylab.yscale('log', nonposy='clip')
#ax.hist(bins, y, 'k--', lineWidth = 1.5)

data = pylab.loadtxt('distributionMaxDistance.txt')  
x = data[:,0]
y = data[:,1]


for i, yElem in enumerate(y):
	y[i] = log(yElem, 10)

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

def func(x, a, b):
  return a * x + b

p0 = sy.array([1,1])

coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
yaj = func(x, coeffs[0], coeffs[1])

for i in range(len(x)):
	x[i] -= 1
"""
for i, yElem in enumerate(y):
	y[i] = 10**yElem
	yaj[i] = 10**yaj[i]
"""
pylab.plot(x, yaj, 'r-')


pylab.savefig(dataFile[:-4])