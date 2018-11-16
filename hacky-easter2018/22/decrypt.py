from pwn import *


with open("secret.enc", "rb") as enc:
    r = remote("whale.hacking-lab.com", 5555)
    r.send(enc.read())
    print r.recvall()
    r.close()
