import urllib.request
import json
from colour import Color
from sklearn import svm
import os.path
import sys
import base64
import re


def learn(n):
    train_data = []
    train_target = []
    for i in range(0, n):
        print("Learning {}".format(i))
        with urllib.request.urlopen('http://whale.hacking-lab.com:2222/train') as f:
            j = json.load(f)
            o = [None] * 9
            o[0] = 1 if (j["g3nd3r"] == "male") else 0
            c = Color(j["c0l0r"])
            o[1] = c.red
            o[2] = c.blue
            o[3] = c.green

            o[4] = j["sp00n"]
            o[5] = j["t41l"]
            o[6] = j["w31ght"]
            o[7] = j["ag3"]
            o[8] = j["l3ngth"]

            target = 1 if (j["g00d"]) else 0 

            train_data.append(o)
            train_target.append(target)

    return (train_data, train_target)


def convert_data(data):
    arr = []

    for l in data:
        o = [None] * 9
        o[0] = 1 if (l[1] == "male") else 0
        c = Color(l[3])
        o[1] = c.red
        o[2] = c.blue
        o[3] = c.green

        o[4] = l[6]
        o[5] = l[7]
        o[6] = l[4]
        o[7] = l[2]
        o[8] = l[5]

        arr.append(o)

    return arr


def classify(data, target):
    clf = svm.SVC()
    #print(data)
    #print(target)
    
    clf.fit(data, target)

    print("Get data to classify..")
    with urllib.request.urlopen('http://whale.hacking-lab.com:2222/gate') as f:
        dat = json.load(f)
        cookie = f.getheader('Set-Cookie')
        to_classify = convert_data(dat["data"])

        #res = []
        print("Classify..")
        #ifor l in to_classify:
        #    res.append( clf.predict(l) )
        res = clf.predict(to_classify)

        print("Classified, submit data")

        arr = res.tolist()
        #print(arr)
        submit = json.dumps(arr).encode('ascii')
        req = urllib.request.Request(url='http://whale.hacking-lab.com:2222/predict', data=submit, method='POST')
        req.add_header('Content-Type', 'application/json')
        req.add_header('Cookie', cookie)
        with urllib.request.urlopen(req) as o:
            score = o.read().decode('ascii')
            print(score)
            m = re.search("SCORE: ([a-zA-Z0-9=/-]*)", score)
            if m != None:
                print("Score: {}".format(base64.b64decode(m.group(1).decode('ascii'))))


        print("Cookie: {}".format(cookie))

        reward = urllib.request.Request('http://whale.hacking-lab.com:2222/reward')
        reward.add_header('Cookie', cookie)
        with urllib.request.urlopen(reward) as o, open("out", "wb") as pic:
            pic.write(o.read())



# main()
train_data = []
if os.path.isfile("train_data"):
    with open("train_data", "r") as d:
        train_data = json.load(d)

train_target = []
if os.path.isfile("train_target"):
    with open("train_target", "r") as d:
        train_target = json.load(d)

n = 100
if len(sys.argv) == 2:
    n = int(sys.argv[1])
(data, target) = learn(n)
train_data.extend(data)
train_target.extend(target)

print("Training data: {}".format(len(train_data)))

with open("train_data", "w+") as d, open("train_target", "w+") as t:
    json.dump(train_data, d)
    json.dump(train_target, t)

classify(train_data, train_target)

