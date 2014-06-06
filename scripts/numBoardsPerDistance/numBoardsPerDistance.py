from __future__ import division

import numpy as np
import scipy as sy
import random
import pylab
from scipy.optimize import curve_fit

dataFile = "numBoardsPerDistance.txt"

data = pylab.loadtxt(dataFile)  
x = data[:,0]
y = data[:,1]

print "plotting data"
fig = pylab.figure()
ax = fig.add_subplot(111)

"""
def func(x, a, b):
  return a * x**b

p0 = sy.array([-5, 1])
coeffs, matcov = curve_fit(func, x, y, p0)
print coeffs
print matcov
yaj = func(x, coeffs[0], coeffs[1])

for i in range(len(yaj)):
	yaj[i] = yaj[i] - 200000000
"""
print sum(y)
ax.set_xlim(0, 52)
ax.set_title("Number of configurations per distance.")
pylab.xlabel("Minimal distance from nearest solution")
pylab.ylabel("Number of configurations")
ax.plot(x, y, 'x')#, x, yaj, 'r-')
pylab.savefig(dataFile[:-4])