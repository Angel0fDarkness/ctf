from decimal import * 

getcontext().prec = 10

def fPart(i):
    s = "{}".format(i)
    idx = s.find('.')

    if idx == -1:
        return 0
    else:
        f = int(s[(idx+1):])
        #print "fPart({}): {}".format(i, f)
        return f


def enigma(m):
    n = Decimal(8956)
    e = 0
    c = 1

    while (n > 0 or m > 0):
        n = Decimal(int(n)) * Decimal(0.5)
        m = Decimal(int(m)) * Decimal(0.5)
        e += c * (fPart(n) ^ fPart(m))
        c *= 2
        #print "n:{}, m:{}, e:{}, c:{}".format(n, m, e, c)

    return e



for i in range(0, 1000000):
    e = enigma(Decimal(i))

    print "{}: {}".format(i, e)

    if e == 9191:
        exit()
