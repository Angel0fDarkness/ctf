import qrtools
import urllib

qr = qrtools.QR()

for i in range(1000):
    urllib.urlretrieve ("http://challenges.hackvent.hacking-lab.com:4200/", "qr.png")
    qr.decode("qr.png")
    print qr.data
    if qr.data.startswith("HV"):
        break
