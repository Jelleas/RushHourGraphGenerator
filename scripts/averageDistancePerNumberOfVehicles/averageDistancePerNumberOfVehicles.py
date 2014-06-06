from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "averageDistancePerNumberOfVehicles.txt"

data = pylab.loadtxt(dataFile)  
x = data[:,1]
y = data[:,2]

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)
"""
def func(x, a, b, c):
  return a * x**b + c

p0 = sy.array([1, 1, 1])
coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
yaj = func(x, coeffs[0], coeffs[1], coeffs[2])
"""
#ax.set_xlim(0, 52)
ax.set_title("Average distance within cluster per number of vehicles")
pylab.ylabel("Average distance")
pylab.xlabel("Number of vehicles")
ax.plot(y, x, 'x')
pylab.savefig(dataFile[:-4])