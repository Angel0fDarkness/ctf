import json
import string
import gzip
import base64
import sys

def peel_map(data, map_from, map_to):
    trans = str.maketrans(map_from, map_to)
    return data.translate(trans)

def peel_gzip(data):
    zipped = base64.b64decode(data)
    return gzip.decompress(zipped).decode('ascii')

def peel_b64(data):
    return base64.b64decode(data).decode('ascii')

def peel_nul(data):
    return data

def peel_xor(data, mask):
    b64data = base64.b64decode(data)
    b64mask = int.from_bytes(base64.b64decode(mask), sys.byteorder)
    return bytes(map(lambda x: int(x) ^ b64mask, b64data)).decode('ascii')

def peel_rev(data):
    return data[::-1]



with open(sys.argv[1]) as json_data:
    d = json.load(json_data)
    print("Peel " + sys.argv[1])
    
    i = 1
    while d[0]["op"]:
        idx = len(d) - 1
        content = d[idx]["content"]
        op = d[idx]["op"]

        print( "#{0:2d}: op={1:s}".format(i, op) )
        print( "Array length: {}".format(len(d)) )

        if op == "map":
            peeled = peel_map(content, d[idx]["mapFrom"], d[idx]["mapTo"])
        elif op == "gzip":
            peeled = peel_gzip(content)
        elif op == "b64":
            peeled = peel_b64(content)
        elif op == "nul":
            peeled = peel_nul(content)
        elif op == "xor":
            peeled = peel_xor(content, d[idx]["mask"])
        elif op == "rev":
            peeled = peel_rev(content)
        elif op == "flag":
            print( "FLAG: " + content )
            sys.exit()
        else:
            print("Unrecognized operation " + op)
            with open("out", "w") as outfile:
                json.dump(d, outfile)
                sys.exit()

        i += 1
        d = json.loads(peeled)
    
    print( content )
