#!/usr/bin/python

import numpy as np
import matplotlib.pyplot as plt

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
            print ("Quantity: {}".format(quantity))
        elif count == 1:
            str = line.strip().split(': ')
            length = float(str[1])
            print ("Length: {}".format(length))
        elif count > 2:
            str = line.strip().replace(': [ ', ' ')
            str = str.replace(' ]', '')
            str = str.split(' ')
            print ("Id: {}, Radius: {}, X: {}, Y:{}".format(str[0], str[1], str[2], str[3]))

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
            print ("Time: {} ms".format(time))
        elif count == 1:
            str = line.strip().split(': ')
            interactionRadius = float(str[1])
            print ("Interaction Radius: {}".format(interactionRadius))
        elif count > 2:
            str = line.strip().replace(': [ ', ' ')
            str = str.replace(' ]', '')
            str = str.split(' ')
            print ("Id: {}".format(str[0]))
            print ('Neigbours: ')
            print (str)

        count += 1

if __name__ == "__main__":
    main(sys.argv[1:])