import sys
import subprocess
import re

regex = r"([a-zA-Z0-9. -]).+\(z\.zip\)"
pw = ""

while True:
	with open("z.hash", "w") as f:
		subprocess.call(["zip2john", "z.zip"], stdout=f)
	out = subprocess.check_output(["/home/hacker/tools/JohnTheRipper/run/john", "z.hash"])
	print pw

	matches = re.search(regex, out, re.MULTILINE)
	if (not matches):
		print "Could not find PW!"
		sys.exit()

	print matches.group(1)
	pw += matches.group(1)
	subprocess.call(["unzip", "-P", matches.group(1), "-d", ".", "-o", "z.zip"])

print pw
