#!/usr/bin/python

import numpy as np
import matplotlib.pyplot as plt
import matplotlib.collections

import sys, getopt, re

def main(argv):
    inputfile = ''
    outputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
    except getopt.GetoptError:
        print ('neighbour.py -i <inputfile> -o <outputfile>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print ('neighbour.py -i <inputfile> -o <outputfile>')
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
        elif opt in ("-o", "--ofile"):
            outputfile = arg
    print ('Input file is "{}"'.format(inputfile))
    print ('Output file is "{}"'.format(outputfile))

    particleIds = []
    particleRadius = []
    particleXCoords = []
    particleYCoords = []
    neighbours = []
    time = 0
    quantity = 0
    length = 0
    interactionRadius = 0

    # Read input file
    file1 = open(inputfile, 'r')
    InputLines = file1.readlines()

    count = 0
    # Strips the newline character
    for line in InputLines:
        if count == 0:
            str = line.strip().split(': ')
            quantity = int(str[1])
        elif count == 1:
            str = line.strip().split(': ')
            length = float(str[1])
        elif count > 2:
            str = line.strip().replace(': [ ', ' ')
            str = str.replace(' ]', '')
            str = str.split(' ')
            particleIds.append(int(str[0]))
            particleRadius.append(float(str[1]))
            particleXCoords.append(float(str[2]))
            particleYCoords.append(float(str[3]))

        count += 1

    # Read output file
    file2 = open(outputfile, 'r')
    OutputLines = file2.readlines()

    count = 0
    # Strips the newline character
    for line in OutputLines:
        if count == 0:
            str = line.strip().replace(' ms', '')
            str = str.split(': ')
            time = int(str[1])
        elif count == 1:
            str = line.strip().split(': ')
            interactionRadius = float(str[1])
        elif count > 2:
            str = line.strip().replace(': [ ', ' ')
            str = str.replace(' ]', '')
            str = str.split(' ')
            aux = []
            j = 1
            while j < len(str):
                aux.append(int(str[j]))
                j += 1
            neighbours.append(aux)

        count += 1

    exit = 0

    while exit == 0:
        # Preguntar que particula quiere graficar vecinos
        val = input("Ingrese el id de particula o exit para salir: ")
        if val == 'exit':
            exit = 1
            break
        val = int(val)

        while val < 1 or val > quantity:
            val = input("Error, no existe esa particula. ingrese de nuevo: ")
            val = int(val)

        colours = ['black'] * quantity

        colours[val - 1] = 'r'
        for n in neighbours[val -1]:
            colours[n - 1] = 'g'

        lim = 0, length
        ## Scatter plot

        fig, ax = plt.subplots()

        i = 0
        while i < quantity:
            draw_circle = plt.Circle((particleXCoords[i], particleYCoords[i]), particleRadius[i], color=colours[i])
            ax.add_artist(draw_circle)
            i += 1
        draw_circle = plt.Circle((particleXCoords[val - 1], particleYCoords[val -1]), interactionRadius,fill=False)
        ax.add_artist(draw_circle)

        # Plot's aesthetics
        ax.set_aspect('equal')
        ax.set_title('Neighbours')
        ax.set_xlabel('x')
        ax.set_ylabel('y')
        ax.set_xlim(lim)
        ax.set_ylim(lim)

        # Show
        plt.show()
        plt.close(fig)


if __name__ == "__main__":
    main(sys.argv[1:])