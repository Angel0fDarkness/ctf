from flask import Flask, request, render_template, render_template_string

app = Flask(__name__, static_url_path='/static')
app.jinja_env.globals.update(render_template=render_template)
app.jinja_env.globals.update(render_template_string=render_template_string)
