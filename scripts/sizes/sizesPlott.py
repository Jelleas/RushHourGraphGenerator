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
size = 39
inOrExcluding = "excluding"

data = pylab.loadtxt('sizes_withMaxDistanceBiggerThan' + str(size) + '_' + inOrExcluding + 'Subclusters.txt')  
x = data[:,0]
y = data[:,1]


# the histogram of the data
bins = [1000 * i for i in range(50)]
n, bins, patches = pylab.hist(y, bins = bins)

pylab.xlabel('Smarts')
pylab.ylabel('Probability')
pylab.title(r'$\mathrm{Histogram\ of\ IQ:}\ \mu=100,\ \sigma=15$')
#pylab.axis([40, 160, 0, 0.03])
pylab.grid(True)

#ax.hist(bins, y, 'k--', lineWidth = 1.5)
pylab.savefig('sizes_withMaxDistanceBiggerThan' + str(size) + '_' + inOrExcluding + 'Subclusters')  

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