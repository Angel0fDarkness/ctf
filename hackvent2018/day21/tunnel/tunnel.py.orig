#!/usr/bin/env python

import json

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
