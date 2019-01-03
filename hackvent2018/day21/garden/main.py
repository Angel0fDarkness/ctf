#!/usr/bin/env python

import time
import os
import hashlib
import json
import thread
from datetime import datetime
from datetime import timedelta

from flask import Flask, request, render_template
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.ext.hybrid import hybrid_property, hybrid_method


from flask import Flask, request, render_template, render_template_string
from werkzeug.serving import run_simple

import base64
import yaml
import hashlib
import re
import random

#from database import *
from config import *

# APP Settings
#APP_SECRET_KEY = '7229ab3f61eaf19e48ff039ceba4768c07a3666a2b8b99393048aded1d41fa1afc4113c1230ded7b5acc08c9219c68098f715ed87999cb46b1621bc3cdc309ae'
APP_SECRET_KEY = os.urandom(64)


app = Flask(__name__, static_url_path='/static')
app.secret_key = APP_SECRET_KEY
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////var/garden/garden.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)


class Vegetable(db.Model):
    __tablename__ = 'vegetables'
    id = db.Column(db.Integer, primary_key=True)
    md5 = db.Column(db.String(128), nullable=True)
    name = db.Column(db.String(128), nullable=True)




db.drop_all()
db.create_all()


db.session.add(Vegetable(
    name = 'banana',
    md5 = '72b302bf297a228a75730123efef7c41'
))
db.session.add(Vegetable(
    name = 'orange',
    md5 = 'fe01d67a002dfa0f3ac084298142eccd'
))

db.session.commit()


def fake_flag(amount):
    flags = ""
    for i in range(0, amount):
        flags += "muffinCTF{" + hashlib.sha1(os.urandom(32)).hexdigest() + "}\n"
    return flags

@app.route('/', methods=['GET'])
def index(): return render_template('index.html')

@app.route('/home', methods=['GET'])
def home(): return render_template('home.html')


@app.route('/get', methods=['GET', 'POST'])
def get():
    hash = request.args.get('hash')
    if hash:
        veg_txt = ''
        try:
            db_vegs = db.session.execute( "SELECT * FROM vegetables WHERE md5=:h", {'h': hash} ).fetchall()

            for db_veg in db_vegs:
                veg_txt += db_veg.name + '\n'
        except:
                return fake_flags(random.randint(1, 50))

        return veg_txt

    return render_template('get.html')

@app.route('/add', methods=['GET', 'POST'])
def add():
    veg = request.form['veg']
    veg_name = ''

    if veg:
        try:
            veg = base64.b64decode(veg)
            print 'b64d'
            veg = yaml.safe_load(veg)
            print 'yml'

            veg_name = veg['veg']['name']
            if (re.match("^muffinCTF\{[a-z0-9]{40}\}$", veg_name) == None):
                return fake_flag(random.randint(1, 10))
            veg_hash = hashlib.md5(veg_name).hexdigest()

            print veg_name
            print veg_hash
            print 'hash'
            db.session.add(Vegetable(
                name = veg_name,
                md5 = veg_hash
            ))

            print 'added'

            db.session.commit()
            db.session.flush()

            print 'added'

        except Exception as e:
            print str(e)
            return fake_flag(random.randint(1, 50))
            

    return veg_name


run_simple('0.0.0.0', 8082, app, threaded=True)
