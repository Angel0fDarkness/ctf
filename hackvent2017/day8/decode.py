import re

with open("True.1337", "r") as file:
    data = file.read()

matches = re.findall("(Test\+)*", data)
print matches

for m in matches:
    cnt = m.count("Test")
#    print chr(cnt)
