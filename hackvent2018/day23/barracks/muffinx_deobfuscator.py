import base64
import sys
import re

with open(sys.argv[1], "r") as infile, open(sys.argv[1] + ".clear", "w") as outfile:
	data = infile.read()
	
	while "exec" in data:
		pattern = r";([a-zA-Z]+)=base64\.b64decode"
		matches = re.search(pattern, data)
		if (matches == None):
			print data
		else:
			baseFunction = matches.group(1)
			print baseFunction
			p = r"" + re.escape(baseFunction) + "\('([a-zA-Z0-9=]+)'\)"
			encoded = re.finditer(p, data);
			data = ""
			for (matchNum, match) in enumerate(encoded, start=1):
				data += base64.b64decode(match.group(1))

	outfile.write(data)
