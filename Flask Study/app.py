from flask import Flask
from flask import render_template

app = Flask(__name__)

@app.route('/')
def index():
	return 'Index Page'

@app.route('/hello')
def hello():
	return 'Hello World!'

FILE_PATH = '/home/honeycomb/SparkTeam/part-00000'

@app.route('/path')
def path():
    return FILE_PATH

@app.route('/result')
def result():
    return 'Result'

@app.route('/user/<username>')
def show_user_profile(username):
	return 'User %s' % username

# http methods GET and POST
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        do_the_login()
    else:
        show_the_login_form()

# render template
@app.route('/hello/')
@app.route('/hello/<name>')
def hello(name=None):
    return render_template('hello.html', name=name)


if __name__ == '__main__':
	app.run()

