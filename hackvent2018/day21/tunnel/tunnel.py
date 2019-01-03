#!/usr/bin/env python

import json
import requests

from tornado import gen
from tornado.ioloop import IOLoop
from tornado.tcpclient import TCPClient


AUTH_TOKEN = ''
with open('/home/tunnel/auth_token', 'rb') as read_auth_token: AUTH_TOKEN = read_auth_token.read().replace('\n','').replace('\r','')


tunnel_stream = None
vuln_box_stream = None


@gen.coroutine
def handle_vulnbox_answer(vuln_box_answer):
    global vuln_box_stream
    global tunnel_stream

    vuln_box_stream.close()

    print vuln_box_answer

    tunnel_stream.write(vuln_box_answer)
    tunnel_stream.read_bytes(4096, callback=handle_http_request, partial=True)


@gen.coroutine
def handle_http_request(http_request):
    global vuln_box_stream
    global tunnel_stream

    vuln_box_stream = yield TCPClient().connect('localhost', 80)

    print http_request
    if "/garden/get?hash=" in http_request and "GET" == http_request[:3]:
        url = http_request[4:].split("\n")[0].split("=")[1].split(" HTTP")[0]
        req = requests.get("http://127.0.0.1:8082/get?hash=" + url)
        http_data = """HTTP/1.1 200 OK
Date: %s
Server: Werkzeug/0.14.1 Python/2.7.13
Content-Type: text/html; charset=utf-8
Content-Length: %s
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive

""" + req.text
        data = http_data % (req.headers["Date"], req.headers["Content-Length"])
        print data
        vuln_box_stream.close()
        tunnel_stream.write(data.encode('utf-8'))
        tunnel_stream.read_bytes(4096, callback=handle_http_request, partial=True)
    else: 
        vuln_box_stream.write(http_request)
        vuln_box_stream.read_bytes(4096, callback=handle_vulnbox_answer, partial=True)



@gen.coroutine
def setup_tunnel_stream():
    global vuln_box_stream
    global tunnel_stream

    tunnel_stream = yield TCPClient().connect('whale.hacking-lab.com', 9181)
    print 'connected'

    tunnel_stream.write(str(json.dumps(
        {
            'AUTH_TOKEN' : AUTH_TOKEN
        }
    )))
    tunnel_stream.read_bytes(4096, callback=handle_http_request, partial=True)


setup_tunnel_stream()
IOLoop.current().start()
