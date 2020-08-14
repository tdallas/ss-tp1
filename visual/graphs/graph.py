#!/usr/bin/python

import matplotlib.pyplot as plt
from statistics import mean

# Read input file
file = open("visual/graphs/n_variation_15.txt", 'r')
InputLines = file.readlines()

times = []
ns = []

for line in InputLines:
    str = line.strip().split(' ')
    times.append(int(str[0]))
    ns.append(int(str[1]))


fig, ax = plt.subplots()
ax.scatter(ns, times)
ax.set_title("Tiempo en función de N con M = 15")
ax.set_xlabel('N')
ax.set_ylabel('Tiempo (nanosegundos)')

# Show
plt.show()
plt.close(fig)