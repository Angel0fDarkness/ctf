#!/usr/bin/env python3

from OpenSSL import crypto, SSL
import requests



def generateCSR(key, state):
    req = crypto.X509Req()
    req.get_subject().stateOrProvinceName = state

    req.set_pubkey(key)
    req.sign(key, "sha1")

    return crypto.dump_certificate_request(crypto.FILETYPE_PEM, req)



# Generate small RSA key
key = crypto.PKey()
key.generate_key(crypto.TYPE_RSA, 512)

url = "http://challenges.hackvent.hacking-lab.com:1088/php/api.php?function=csr&argument=&key=E7g24fPcZgL5dg78"
state = "CA"
chars = "^°!\"§$%&/()=?`{[]}\\´+*~#'-_.:,;<>|"

for char in chars:
    csr = generateCSR(key, state + char)
    r = requests.post(url, data = { 'csr': csr.decode('ascii') });
    if r.status_code != 200:
        print("{}: {}".format(state + char, r.status_code))
