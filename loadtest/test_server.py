from flask import Flask
app = Flask(__name__)

# FLASK_APP=test_server.py flask run

@app.route("/send", methods=["POST"])
def send():
    return ""

