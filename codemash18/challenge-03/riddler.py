from pwn import *
import re

number_str = list("00000000000000000000\n")
for i in range(20):
    test = 5
    for j in range(10):
        number_str[i] = "{}".format(test)
        #print "Sending: {}".format(test)

        r = remote("codemash.hacking-lab.com", 8357)
        r.recvuntil("Make an educated guess, dude:")
        r.send("".join(number_str))

        r.recvline()
        res = r.recvline()
        print res

        o = re.search("(\d+)", res)        
        if o == None:
            print "Found Number: {}".format("".join(number_str))
            print r.recvall()
            exit(0)
        else:
            r.recvall()

        if int(o.group(0)) == (i+1):
            print "Number: {}".format("".join(number_str))
            break
        else:
            if '<' in res:
                test += 1
            else:
                test -= 1
