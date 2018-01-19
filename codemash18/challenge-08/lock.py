cipher = "?8hiyKT5fw*W^J~art3t.47i"
key = "lockpickingisfun"

codeword = ""
i = 0
while i < len(cipher):
    codeword += chr(ord(key[i % len(key)]) - ord(cipher[i]) + 54)
    i += 1

print(codeword)
