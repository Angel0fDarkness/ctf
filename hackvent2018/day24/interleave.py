import sys

with open(sys.argv[1], "r") as red_flag, open(sys.argv[2], "r") as blue_flag, open("flag.png", "wb") as flag:
	red = red_flag.read()
	blue = blue_flag.read()

	for (x, y) in zip(red, blue):
		a = ord(x)
		b = ord(y)
		#print "a: %02X, b: %02X" % (a, b)
		flag.write( chr((a & 0xF0) | ((b & 0xF0) >> 4)) )
		flag.write( chr(((a & 0x0F) << 4) | (b & 0x0F)) )
