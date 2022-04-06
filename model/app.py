from flask import Flask, url_for,request
import joblib
import os
import numpy as np
import pickle

app = Flask(__name__)



@app.route('/result', methods=['GET','POST'])
def result():
    age = int(request.json['age'])
    sex = int(request.json['sex'])
    cp = int(request.json['cp'])
    trestbps = float(request.json['trestbps'])
    chol = float(request.json['chol'])
    fbs = float(request.json['fbs'])
    restecg = float(request.json['restecg'])
    thalach = float(request.json['thalach'])
    exang = int(request.json['exang'])
    oldpeak = float(request.json['oldpeak'])
    slope = int(request.json['slope'])
    ca = int(request.json['ca'])
    thal = int(request.json['thal'])
    x = np.array([[age, sex, cp, trestbps, chol, fbs, restecg,
                  thalach, exang, oldpeak, slope, ca, thal]])



    model_path = os.path.join(os.path.dirname(os.path.realpath('__file__')), 'rfc.sav')
    rf = joblib.load(model_path)

    y = rf.predict(x)
    print(y[0])
    print(y)  

   
    if y == 0:
      return 'Safe'

    else:
      return 'Not Safe'



if __name__ == '__main__':
	app.run(debug=True) 