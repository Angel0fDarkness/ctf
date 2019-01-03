import base64
from Crypto.Cipher import AES

flag = "xQ34V+MHmhC8V88KyU66q0DE4QeOxAbp1EGy9tlpkLw="
key  = "uQA\\-nM@=1wl\x1EbN!"

flag = base64.b64decode(flag)

modified_key = chr(0x78)
for i in range(1, len(key)):
	modified_key += chr(ord(key[i:i+1]) + 0x3)

print("Key: " + modified_key)

cipher = AES.new(modified_key, AES.MODE_ECB)
print("Flag: " + cipher.decrypt(flag))
