import subprocess
import sys

print "Password: " + sys.argv[1]

for c in sys.argv[1]:
	print c
	subprocess.call(["unzip", "-P", c, "-d", ".", "-o", "z.zip"])

print "Finished"
