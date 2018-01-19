a = open("a.txt", "r").read()
b = open("b.txt", "r").read()
c = open("c.txt", "r").read()
d = open("d.txt", "r").read()
o = open("out", "w")

#print("a: {}".format(a))
#print("b: {}".format(b))
#print("c: {}".format(c))
#print("d: {}".format(d))

i = 0
o.write('P1\n')
o.write('25 25\n')

while i < min(len(a), len(b), len(c), len(d)):
    if a[i] in "\r\n":
        o.write(a[i])
    else:
        in_a = int(a[i])
        in_b = int(b[i])
        in_c = int(c[i])
        in_d = int(d[i])

        res = (((not in_a) and in_b) or in_c) ^ in_d
        o.write(str(res) + ' ')
    i += 1
