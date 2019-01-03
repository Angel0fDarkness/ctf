#!/usr/bin/python

from pwn import *
import base64
import sys
import math


r = remote('whale.hacking-lab.com', 4242)
r.recv(6)
b = 'fffd03fffb22fffa220301000003620304020f05000007621c08020409421a0a027f0b02150f0211100213110000120000fff0fffd01'
r.send(b.decode('hex'))
r.recv(27)
b = '1b5b37313b3152'
r.send(b.decode('hex'))

linecount = 0
flag = ""
movement = [ None, None, 49 ]
move_idx = 0
try:
        while r.closed != True:
		linecount += 1
                s = r.recvline()
                #print(s)
                line = s.encode('hex')

                printable = line[20:180]
                if (printable[2:4] != "20"):
			move_idx += 1
			linecount = 0
                        flag += printable[2:4].decode('hex')
           
                # Find "bird" position
                pos = -1
                dot = line[190:194]
                if (len(dot)):
                        dot = dot.decode('hex')
                        try:
                                pos = int(dot)
                        except ValueError as e:
                                pos = int(dot[0:1])
           
                output = printable.decode('hex')

		# Move "bird"
		bird = "."
		if (len(movement) > move_idx) and (movement[move_idx] != None):
			if (linecount > 2):
				if (movement[move_idx] > 0):
					spaces = int(math.ceil(movement[move_idx] / (22 - linecount)))
					r.send(" " * spaces);
					movement[move_idx] -= spaces
					bird = "+" * spaces

		# Print "bird"
		if (pos >= 0):
			output = output[0:pos-1] + bird + output[pos:]
		print(output)

except EOFError as e:
        print("Flag: {:s}".format(flag))
