


def checkPW(pw):
    if len(pw) != 10:
        return False

    digits = []
    i = 1
    for c in pw:
        if c in digits:
            return False
        digits.append(c)

        if int(pw[:i]) % i != 0:
            return False
        i += 1

    return True


even = [ 2, 4, 6, 8 ]
odd  = [ 1, 3, 5, 7, 9 ]

for c1 in odd:
    c1_odd = list(odd)
    c1_odd.remove(c1)

    for c2 in even:
        c2_even = list(even)
        c2_even.remove(c2)

        for c3 in c1_odd:
            c3_odd = list(c1_odd)
            c3_odd.remove(c3)

            for c4 in c2_even:
                c4_even = list(c2_even)
                c4_even.remove(c4)

                for c5 in c3_odd:
                    c5_odd = list(c3_odd)
                    c5_odd.remove(c5)

                    for c6 in c4_even:
                        c6_even = list(c4_even)
                        c6_even.remove(c6)
                        c8 = c6_even[0]

                        for c7 in c5_odd:
                            c7_odd = list(c5_odd)
                            c7_odd.remove(c7)
                            c9 = c7_odd[0]

                            pw = "{}{}{}{}{}{}{}{}{}0".format(c1, c2, c3, c4, c5, c6, c7, c8, c9)
                            if checkPW(pw):
                                print "Found password: {}".format(pw)
