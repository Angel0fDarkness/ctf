import base64

with open("Wishlist.txt", "r") as f:
    data = f.read()

for i in range(32):
    data = base64.b64decode(data)
print(data)
