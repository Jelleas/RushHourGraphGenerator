from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "vehiclesOnRowsOverVehiclesOnColumnsRatio100.txt"

data = pylab.loadtxt(dataFile)  
x = data[:,0]
y = data[:,1]

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

def func(x, a, b, c):
  return a * x**b + c

p0 = sy.array([1,1, 1])
coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
yaj = func(x, coeffs[0], coeffs[1], coeffs[2])

for i in range(len(x)):
	x[i] -= 1

#ax.set_xlim(0, 52)
ax.set_title("Distribution of max distance among clusters")
pylab.xlabel("Max distance within cluster")
pylab.ylabel("Ratio of number of vehicles on rows\n over number of vehicles on columns")
ax.plot(x, y, 'x', x, yaj, 'r-')
pylab.savefig(dataFile[:-4])