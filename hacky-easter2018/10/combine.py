

def getStrings(n):
    strings = [ "7034353577307264355f3472335f6330306c",
                "7034353577307264355f052d066b15035433",
                "70343535773072105d6c6b05032d0f546f4c",
                "7034353577307264355f3406033b5749114c" ]
    
    ret = []
    if (n & 1) == 1:
        ret.append(strings[0])

    if (n & 2) == 2:
        ret.append(strings[1])

    if (n & 4) == 4:
        ret.append(strings[2])

    if (n & 8) == 8:
        ret.append(strings[3])
    
    return ret


def xorString(a, b):
    return "".join("{:x}".format(int(x, 16) ^ int(y, 16)) for x, y in zip(a, b))


def hexToASCII(s):
    i = 0
    out = ""
    while i < len(s):
        #print("{} to int({}) to chr({})".format(s[i:i+2], int(s[i:i+2], 16), chr(int(s[i:i+2], 16))))
        out += chr(int(s[i:i+2], 16))
        i += 2
    return out


# main

for i in range(1, 16):
    combine = getStrings(i)
   
    out = "000000000000000000000000000000000000"
    for e in combine:
        out = xorString(out, e)

    s = hexToASCII(out)

    print("{:04b}: {} - {}".format(i, out, s))
