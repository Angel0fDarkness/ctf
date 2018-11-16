import sys



def xor(text, key):
    out = ""
    for c in text:
        out += chr(c ^ key)
    return out

def search(text, needle):
    if needle in text:
        return True
    return False


# main
with open(sys.argv[1], 'rb') as infile:
    text = infile.read()

    for i in range(0, 256):
        print("Testing key {:02X}".format(i))
        dexored = xor(text, i)
        if search(dexored, "PNG"):
            print("FOUND")
            with open(sys.argv[2], 'w') as outfile:
                outfile.write(dexored)

