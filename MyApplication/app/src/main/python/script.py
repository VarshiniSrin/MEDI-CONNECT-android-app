"""
import numpy as np
import os
# import pandas as pd


def main():
    str = os.getcwd()
    arr = np.array([1,2,3])
    return str

main()
"""


import numpy as np
import pandas as pd
import os
from os.path import dirname, join


SYMPTOMS = ['back_pain', 'constipation', 'abdominal_pain', 'diarrhoea', 'mild_fever', 'yellow_urine',
      'yellowing_of_eyes', 'acute_liver_failure', 'fluid_overload', 'swelling_of_stomach',
      'swelled_lymph_nodes', 'malaise', 'blurred_and_distorted_vision', 'phlegm', 'throat_irritation',
      'redness_of_eyes', 'sinus_pressure', 'runny_nose', 'congestion', 'chest_pain', 'weakness_in_limbs',
      'fast_heart_rate', 'pain_during_bowel_movements', 'pain_in_anal_region', 'bloody_stool',
      'irritation_in_anus', 'neck_pain', 'dizziness', 'cramps', 'bruising', 'obesity', 'swollen_legs',
      'swollen_blood_vessels', 'puffy_face_and_eyes', 'enlarged_thyroid', 'brittle_nails',
      'swollen_extremeties', 'excessive_hunger', 'extra_marital_contacts', 'drying_and_tingling_lips',
      'slurred_speech', 'knee_pain', 'hip_joint_pain', 'muscle_weakness', 'stiff_neck', 'swelling_joints',
      'movement_stiffness', 'spinning_movements', 'loss_of_balance', 'unsteadiness',
      'weakness_of_one_body_side', 'loss_of_smell', 'bladder_discomfort', 'foul_smell_of urine',
      'continuous_feel_of_urine', 'passage_of_gases', 'internal_itching', 'toxic_look_(typhos)',
      'depression', 'irritability', 'muscle_pain', 'altered_sensorium', 'red_spots_over_body', 'belly_pain',
      'abnormal_menstruation', 'dischromic _patches', 'watering_from_eyes', 'increased_appetite', 'polyuria','family_history', 'mucoid_sputum',
      'rusty_sputum', 'lack_of_concentration', 'visual_disturbances', 'receiving_blood_transfusion',
      'receiving_unsterile_injections', 'coma', 'stomach_bleeding', 'distention_of_abdomen',
      'history_of_alcohol_consumption', 'fluid_overload', 'blood_in_sputum', 'prominent_veins_on_calf',
      'palpitations', 'painful_walking', 'pus_filled_pimples', 'blackheads', 'scurring', 'skin_peeling',
      'silver_like_dusting', 'small_dents_in_nails', 'inflammatory_nails', 'blister', 'red_sore_around_nose',
      'yellow_crust_ooze']


DISEASE = ['Fungal infection', 'Allergy', 'GERD', 'Chronic cholestasis', 'Drug Reaction',
           'Peptic ulcer diseae', 'AIDS', 'Diabetes', 'Gastroenteritis', 'Bronchial Asthma', 'Hypertension',
           ' Migraine', 'Cervical spondylosis',
           'Paralysis (brain hemorrhage)', 'Jaundice', 'Malaria', 'Chicken pox', 'Dengue', 'Typhoid', 'hepatitis A',
           'Hepatitis B', 'Hepatitis C', 'Hepatitis D', 'Hepatitis E', 'Alcoholic hepatitis', 'Tuberculosis',
           'Common Cold', 'Pneumonia', 'Dimorphic hemmorhoids(piles)',
           'Heartattack', 'Varicoseveins', 'Hypothyroidism', 'Hyperthyroidism', 'Hypogly`cemia', 'Osteoarthristis',
           'Arthritis', '(vertigo) Paroymsal  Positional Vertigo', 'Acne', 'Urinary tract infection', 'Psoriasis',
           'Impetigo']


# TESTING DATA df -------------------------------------------------------------------------------------
def testingDataDf():
    filename1 = join(dirname(__file__), "Training.csv")
    df = pd.read_csv(filename1,encoding='utf-8')



    df.replace({'prognosis': {'Fungal infection': 0, 'Allergy': 1, 'GERD': 2, 'Chronic cholestasis': 3, 'Drug Reaction': 4,
                              'Peptic ulcer diseae': 5, 'AIDS': 6, 'Diabetes ': 7, 'Gastroenteritis': 8,
                              'Bronchial Asthma': 9, 'Hypertension ': 10,
                              'Migraine': 11, 'Cervical spondylosis': 12,
                              'Paralysis (brain hemorrhage)': 13, 'Jaundice': 14, 'Malaria': 15, 'Chicken pox': 16,
                              'Dengue': 17, 'Typhoid': 18, 'hepatitis A': 19,
                              'Hepatitis B': 20, 'Hepatitis C': 21, 'Hepatitis D': 22, 'Hepatitis E': 23,
                              'Alcoholic hepatitis': 24, 'Tuberculosis': 25,
                              'Common Cold': 26, 'Pneumonia': 27, 'Dimorphic hemmorhoids(piles)': 28, 'Heart attack': 29,
                              'Varicose veins': 30, 'Hypothyroidism': 31,
                              'Hyperthyroidism': 32, 'Hypoglycemia': 33, 'Osteoarthristis': 34, 'Arthritis': 35,
                              '(vertigo) Paroymsal  Positional Vertigo': 36, 'Acne': 37, 'Urinary tract infection': 38,
                              'Psoriasis': 39,
                              'Impetigo': 40}}, inplace=True)

    return df

# TRAINING DATA tr --------------------------------------------------------------------------------

'''filename2 = join(dirname(__file__), "Testing.csv")
tr = pd.read_csv(filename2,encoding='utf-8')

tr.replace({'prognosis': {'Fungal infection': 0, 'Allergy': 1, 'GERD': 2, 'Chronic cholestasis': 3, 'Drug Reaction': 4,
                          'Peptic ulcer diseae': 5, 'AIDS': 6, 'Diabetes ': 7, 'Gastroenteritis': 8,
                          'Bronchial Asthma': 9, 'Hypertension ': 10,
                          'Migraine': 11, 'Cervical spondylosis': 12,
                          'Paralysis (brain hemorrhage)': 13, 'Jaundice': 14, 'Malaria': 15, 'Chicken pox': 16,
                          'Dengue': 17, 'Typhoid': 18, 'hepatitis A': 19,
                          'Hepatitis B': 20, 'Hepatitis C': 21, 'Hepatitis D': 22, 'Hepatitis E': 23,
                          'Alcoholic hepatitis': 24, 'Tuberculosis': 25,
                          'Common Cold': 26, 'Pneumonia': 27, 'Dimorphic hemmorhoids(piles)': 28, 'Heart attack': 29,
                          'Varicose veins': 30, 'Hypothyroidism': 31,
                          'Hyperthyroidism': 32, 'Hypoglycemia': 33, 'Osteoarthristis': 34, 'Arthritis': 35,
                          '(vertigo) Paroymsal  Positional Vertigo': 36, 'Acne': 37, 'Urinary tract infection': 38,
                          'Psoriasis': 39,
                          'Impetigo': 40}}, inplace=True)

X_test = tr[SYMPTOMS]
y_test = tr[["prognosis"]]
np.ravel(y_test)'''


def DecisionTree(Symptom1, Symptom2, Symptom3, Symptom4, Symptom5, X, y):

    from sklearn import tree

    persons_symptoms = []
    # If symptom selected is empty
    for x in SYMPTOMS:
        persons_symptoms.append(0)

    clf3 = tree.DecisionTreeClassifier()   # empty model of the decision tree
    clf3 = clf3.fit(X,y, sample_weight=None, check_input=True)

    # calculating accuracy-------------------------------------------------------------------
    # from sklearn.metrics import accuracy_score
    # y_pred=clf3.predict(X_test)
    # print(accuracy_score(y_test, y_pred))
    # print(accuracy_score(y_test, y_pred,normalize=False))
    # -----------------------------------------------------

    psymptoms = {}
    psymptoms[Symptom1] = 1
    psymptoms[Symptom2] = 1
    psymptoms[Symptom3] = 1
    psymptoms[Symptom4] = 1
    psymptoms[Symptom5] = 1

    for k in range(0,len(SYMPTOMS)):
        if(SYMPTOMS[k] in psymptoms):
            persons_symptoms[k]=1

    inputtest = [persons_symptoms]
    inputtest_numpy_array = np.array(inputtest)
    predict = clf3.predict(inputtest_numpy_array)
    predicted=predict[0]

    h='no'
    for a in range(0,len(DISEASE)):
        if(predicted == a):
            h='yes'
            break

    if (h=='yes'):
        return DISEASE[a]
    else:
        return "Not Found"





def randomforest(Symptom1, Symptom2, Symptom3, Symptom4, Symptom5, X, y):

    from sklearn.ensemble import RandomForestClassifier

    persons_symptoms = []
    # If symptom selected is empty
    for x in SYMPTOMS:
        persons_symptoms.append(0)


    clf4 = RandomForestClassifier()
    clf4 = clf4.fit(X,np.ravel(y))

    # calculating accuracy-------------------------------------------------------------------
    from sklearn.metrics import accuracy_score
    #y_pred=clf4.predict(X_test)
    # print(accuracy_score(y_test, y_pred))
    # print(accuracy_score(y_test, y_pred,normalize=False))
    # -----------------------------------------------------

    psymptoms = {}
    psymptoms[Symptom1] = 1
    psymptoms[Symptom2] = 1
    psymptoms[Symptom3] = 1
    psymptoms[Symptom4] = 1
    psymptoms[Symptom5] = 1

    for k in range(0,len(SYMPTOMS)):
        if(SYMPTOMS[k] in psymptoms):
            persons_symptoms[k]=1


    inputtest = [persons_symptoms]
    inputtest_numpy_array = np.array(inputtest)
    predict = clf4.predict(inputtest_numpy_array)
    predicted=predict[0]

    h='no'
    for a in range(0,len(DISEASE)):
        if(predicted == a):
            h='yes'
            break

    if (h=='yes'):
        return DISEASE[a]
    else:
        return "Not Found"


def NaiveBayes(Symptom1, Symptom2, Symptom3, Symptom4, Symptom5, X, y):

    from sklearn.naive_bayes import GaussianNB

    persons_symptoms = []
    # If symptom selected is empty
    for x in SYMPTOMS:
        persons_symptoms.append(0)

    gnb = GaussianNB()
    gnb=gnb.fit(X,np.ravel(y))

    # calculating accuracy-------------------------------------------------------------------
    from sklearn.metrics import accuracy_score
    #y_pred=gnb.predict(X_test)
    # print(accuracy_score(y_test, y_pred))
    # print(accuracy_score(y_test, y_pred,normalize=False))
    # -----------------------------------------------------

    psymptoms = {}
    psymptoms[Symptom1] = 1
    psymptoms[Symptom2] = 1
    psymptoms[Symptom3] = 1
    psymptoms[Symptom4] = 1
    psymptoms[Symptom5] = 1

    for k in range(0,len(SYMPTOMS)):
        if(SYMPTOMS[k] in psymptoms):
            persons_symptoms[k]=1

    inputtest = [persons_symptoms]
    predict = gnb.predict(inputtest)
    predicted=predict[0]


    h='no'
    for a in range(0,len(DISEASE)):
        if(predicted == a):
            h='yes'
            break

    if (h=='yes'):
        return DISEASE[a]
    else:
        return "Not Found"

# main function-------------------------------------------------------------------------------

def compareStrings(symptom):
    if symptom == "foul_smell_of_urine":
        return "foul_smell_of urine"
    if symptom == "toxic_look(typhos)":
        return "toxic_look_(typhos)"
    if symptom == "dischromic_patches":
        return "dischromic _patches"
    return symptom


def main(Symptom1, Symptom2, Symptom3, Symptom4, Symptom5):

# Was initially global
    df = testingDataDf()
    X = df[SYMPTOMS]
    #X = df.as_matrix()
    y = df[["prognosis"]]
    np.ravel(y)


    if Symptom1 == "Select":
        Symptom1=""

    if Symptom2 == "Select":
        Symptom2=""

    if Symptom3 == "Select":
        Symptom3=""

    if Symptom4 == "Select":
        Symptom4=""

    if Symptom5 == "Select":
        Symptom5=""


# Altering symptoms to match the word in the array SYMPTOMS and the CSVs
    symptom1 = Symptom1.replace(" ", "_").lower()
    symptom2 = Symptom2.replace(" ", "_").lower()
    symptom3 = Symptom3.replace(" ", "_").lower()
    symptom4 = Symptom4.replace(" ", "_").lower()
    symptom5 = Symptom5.replace(" ", "_").lower()

    symptom1 = compareStrings(symptom1)
    symptom2 = compareStrings(symptom2)
    symptom3 = compareStrings(symptom3)
    symptom4 = compareStrings(symptom4)
    symptom5 = compareStrings(symptom5)


    d1 = DecisionTree(symptom1, symptom2, symptom3, symptom4, symptom5, X, y)
    d2 = randomforest(symptom1, symptom2, symptom3, symptom4, symptom5, X, y)
    d3 = NaiveBayes(symptom1, symptom2, symptom3, symptom4, symptom5, X, y)


    print(d1 + ", " + d2 + " or " + d3)

    if (d1 == d2) and (d2 == d3):
        return d1

    if (d1 == d2) and (d2 != d3):
        return d1 + " or " + d3

    if (d1 != d2) and (d2 == d3):
        return d1 + " or " + d2

    if (d1 == d3) and (d3 != d2):
        return d3 + " or " + d2

    return d1 + ", " + d2 + " or " + d3
