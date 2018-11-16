import sys


with open(sys.argv[1], "rb") as infile, open(sys.argv[2], "wb") as outfile:
    outbytes = bytearray()
    for c in bytearray(infile.read()):
        outbytes.append(c ^ 0xFF)
    outfile.write(outbytes)
