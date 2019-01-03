import gmpy2

c=0x7E65D68F84862CEA3FCC15B966767CCAED530B87FC4061517A1497A03D2
p=0xDD8E05FF296C792D2855DB6B5331AF9D112876B41D43F73CEF3AC7425F9
b=0x7BBE3A50F28B2BA511A860A0A32AD71D4B5B93A8AE295E83350E68B57E5

inv_b = gmpy2.invert(b, p)
a = c * inv_b % p

for i in range(0, 10000):
	n = a + i * p
	hexa = str(hex(n).lstrip("0x"))
	
	if (len(hexa) % 2):
		hexa = hexa[0:len(hexa)-1]
	text = hexa.decode("hex")

	if ("HV18" in text):
		print("%d: %s" % (i, text))
