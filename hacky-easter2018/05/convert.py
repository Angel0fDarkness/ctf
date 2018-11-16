import base64

with open("data", "r") as inp, open("out", "wb") as out:
    out.write( base64.b64decode(inp.read()) )
