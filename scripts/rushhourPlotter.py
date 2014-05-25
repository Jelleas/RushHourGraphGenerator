from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

"""
print "reading data file"
data = []
with open("distributionMaxDistance.txt") as dataFile:
	for line in dataFile:
		data.append(int(line.split(",")[0]))

print "finished reading data file"
"""

data = pylab.loadtxt('distributionMaxDistance.txt')  
x = data[:,0]
y = data[:,1]

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

def func(x, a, b, c):
  return a * x**b + c

p0 = sy.array([3.1,-2.5, 100])
coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
yaj = func(x, coeffs[0], coeffs[1], coeffs[2])

for i in range(len(x)):
	x[i] -= 1

ax.set_xlim(0, 52)
ax.set_title("Distribution of max distance among clusters")
pylab.xlabel("Max distance within cluster")
pylab.ylabel("Number of clusters")
ax.plot(x, y, 'x', x, yaj, 'r-')
pylab.savefig('DistributionMaxDistanceGraph')

"""
sequentialTimes, dataProcessed, dataErr = processData(data)
x = [d.mappers for d in dataProcessed if d.matrixSize == 500]

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

yerr = dataErr[:4]
y = [d.time for d in dataProcessed if d.matrixSize == 500]
ax.errorbar(np.arange(len(x)), y, yerr = yerr, label="500x500 matrix")

yerr = dataErr[4:8]
y = [d.time for d in dataProcessed if d.matrixSize == 1000]
ax.errorbar(np.arange(len(x)), y, yerr = yerr, label="1000x1000 matrix")

yerr = dataErr[8:]
yerr[0] = 800
y = [d.time for d in dataProcessed if d.matrixSize == 2000]
ax.errorbar(np.arange(len(x)), y, yerr = yerr, label="2000x2000 matrix")

ax.set_title("")
ax.legend(loc="upper right")

pylab.xlabel("Mappers")
pylab.ylabel("Time (s)")

#pylab.ylim([-1000000, 400000])
#pylab.ylabel("Speed up")
#ax.xscale('log', basex=4)

ax.set_xticks(np.arange(len(x)))
ax.set_xticklabels(x, rotation = 0)
ax.set_xlim(-0.2, len(x) - 0.8)
pylab.savefig('graph')
print "finished plotting data"

pylab.show()"""