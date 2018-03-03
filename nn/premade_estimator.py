#  Copyright 2016 The TensorFlow Authors. All Rights Reserved.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.
"""An Example of a DNNClassifier for the Iris dataset."""
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import argparse
import tensorflow as tf

import iris_data


parser = argparse.ArgumentParser()
parser.add_argument('--batch_size', default=100, type=int, help='batch size')
parser.add_argument('--train_steps', default=10000, type=int,
                    help='number of training steps')

def main(argv):
    args = parser.parse_args(argv[1:])

    # Fetch the data
    (train_x, train_y), (test_x, test_y) = iris_data.load_data()
    # Feature columns describe how to use the input.
    my_feature_columns = []
    for key in train_x.keys():
        my_feature_columns.append(tf.feature_column.numeric_column(key=key))

    # Build 2 hidden layer DNN with 10, 10 units respectively.
    classifier = tf.estimator.DNNClassifier(
        feature_columns=my_feature_columns,
        # Two hidden layers of 10 nodes each.
        hidden_units=[10, 10],
        # The model must choose between 3 classes.
        n_classes=3)

    # Train the Model.
    classifier.train(
        input_fn=lambda:iris_data.train_input_fn(train_x, train_y,
                                                 args.batch_size),
        steps=args.train_steps)

    # Evaluate the model.
    eval_result = classifier.evaluate(
        input_fn=lambda:iris_data.eval_input_fn(test_x, test_y,
                                                args.batch_size))

    print('\nTest set accuracy: {accuracy:0.3f}\n'.format(**eval_result))

    # Generate predictions from the model
    expected = ['YES', 'YES', 'YES']
    predict_x = {
        'c0': [0,0,0],
        'c1': [9,9,9],
        'c2': [9,9,9],
        'c3': [8,8,8],
        'c4': [0,0,0],
        'c5': [19,19,19],
        'c6': [9,9,9],
        'c7': [15,15,15],
        'c8': [17,17,17],
        'c9': [0,0,0],
        'c10': [0,0,0],
        'c11': [9,9,9],
        'c12': [9,9,9],
        'c13': [0,0,0],
        'c14': [0,0,0],
        'c15': [0,0,0],
        'c16': [5,5,5],
        'c17': [0,0,0],
        'c18': [0,0,0],
        'c19': [115,115,111],
        'c20': [11,11,11],
        'c21': [11,11,11],
        'c22': [0,0,0],
        'c23': [0,0,0],
        'c24': [0,0,0],
        'c25': [0,0,0],
        'c26': [1,1,1],
        'c27': [0,0,0],
        'c28': [37,37,37],
        'c29': [0,0,0],
        'c30': [14,14,14],
        'c31': [0,0,0],
        'c32': [0,0,0],
        'c33': [0,0,0],
        'c34': [0,0,0],
        'c35': [0,0,0],
        'c36': [5,5,5],
        'c37': [5,5,5],
        'c38': [0,0,0],
        'c39': [2,2,2],
        'c40': [0,0,0],
        'c41': [2,2,2],
        'c42': [0,0,0],
        'c43': [2,2,2],
        'c44': [0,0,0],
        'c45': [0,0,0],
        'c46': [1,1,1],
        'c47': [21,21,21],
        'c48': [18,18,18],
        'c49': [0,0,0],
        'c50': [0,0,0],
        'c51': [1,1,1],
        'c52': [0,0,0],
        'c53': [0,0,0],
        'c54': [33,33,29],
        'c55': [2,2,4],
        'c56': [2,2,4],
        'c57': [4,4,2],
        'c58': [3,3,3],
        'c59': [13,13,11],
        'c60': [0,0,0],
        'c61': [4,4,2],
        'c62': [1,1,1],
        'c63': [0,0,0],
        'c64': [0,0,0],
        'c65': [3,3,3],
        'c66': [73,73,68],
        'c67': [2,2,2],
        'c68': [39,39,39],
        'c69': [59,59,53],
        'c70': [10,10,11],
        'c71': [1,1,1],
        'c72': [0,0,0],
        'c73': [1,1,1],
        'c74': [40,40,39],
        'c75': [52,52,48],
        'c76': [18,18,18],
        'c77': [0,0,0],
        'c78': [0,0,0],
        'c79': [0,0,0],
        'c80': [4,4,4],
        'c81': [74,74,68],
        'c82': [18,18,19],
        'c83': [5,5,5],
        'c84': [5,5,5],
        'c85': [4,4,4],
        'c86': [40,40,39],
        'c87': [25,25,21],
        'c88': [18,18,18],
        'c89': [9,9,9],
        'c90': [12,12,12],
        'c91': [6,6,6],
        'c92': [2,2,2],
        'c93': [73,73,67],
        'c94': [17,17,18],
        'c95': [4,4,4],
        'c96': [5,5,5],
        'c97': [9,9,9],
        'c98': [40,40,39],
        'c99': [15,15,11],
        'c100': [10,10,10],
        'c101': [21,21,21],
        'c102': [12,12,12],
        'c103': [12,12,12],
        'c104': [2,2,2],
        'c105': [42,42,39],
        'c106': [13,13,11],
        'c107': [19,19,17],
        'c108': [14,14,16],
        'c109': [20,20,20],
        'c110': [40,40,39],
        'c111': [15,15,11],
        'c112': [3,3,3],
        'c113': [8,8,8],
        'c114': [12,12,12],
        'c115': [32,32,32],
        'c116': [2,2,2],
        'c117': [18,18,16],
        'c118': [31,31,30],
        'c119': [5,5,5],
        'c120': [25,25,21],
        'c121': [29,29,31],
        'c122': [40,40,39],
        'c123': [13,13,9],
        'c124': [3,3,3],
        'c125': [4,4,3],
        'c126': [4,4,4],
        'c127': [46,46,47],
        'c128': [0,0,0],
        'c129': [18,18,16],
        'c130': [9,9,9],
        'c131': [27,27,26],
        'c132': [7,7,7],
        'c133': [49,49,47],
        'c134': [38,38,37],
        'c135': [2,2,2],
        'c136': [7,7,5],
        'c137': [10,10,7],
        'c138': [6,6,5],
        'c139': [47,47,49],
        'c140': [0,0,0],
        'c141': [2,2,2],
        'c142': [0,0,0],
        'c143': [9,9,9],
        'c144': [9,9,9],
        'c145': [8,8,8],
        'c146': [0,0,0],
        'c147': [19,19,19],
        'c148': [9,9,9],
        'c149': [15,15,15],
        'c150': [17,17,17],
        'c151': [0,0,0],
        'c152': [0,0,0],
        'c153': [9,9,9],
        'c154': [9,9,9],
        'c155': [0,0,0],
        'c156': [0,0,0],
        'c157': [0,0,0],
        'c158': [5,5,5],
        'c159': [0,0,0],
        'c160': [0,0,0],
        'c161': [109,107,109],
        'c162': [11,11,11],
        'c163': [11,11,11],
        'c164': [0,0,0],
        'c165': [0,0,0],
        'c166': [0,0,0],
        'c167': [0,0,0],
        'c168': [1,1,1],
        'c169': [0,0,0],
        'c170': [37,37,37],
        'c171': [0,0,0],
        'c172': [14,14,14],
        'c173': [0,0,0],
        'c174': [0,0,0],
        'c175': [0,0,0],
        'c176': [0,0,0],
        'c177': [0,0,0],
        'c178': [5,5,5],
        'c179': [5,5,5],
        'c180': [0,0,0],
        'c181': [2,2,2],
        'c182': [0,0,0],
        'c183': [2,2,2],
        'c184': [0,0,0],
        'c185': [2,2,2],
        'c186': [0,0,0],
        'c187': [0,0,0],
        'c188': [2,1,1],
        'c189': [21,14,21],
        'c190': [18,18,18],
        'c191': [0,0,0],
        'c192': [0,0,0],
        'c193': [1,1,1],
        'c194': [0,0,0],
        'c195': [0,0,0],
        'c196': [33,21,27],
        'c197': [2,8,5],
        'c198': [2,8,5],
        'c199': [1,0,1],
        'c200': [3,3,3],
        'c201': [10,10,11],
        'c202': [0,0,0],
        'c203': [1,0,1],
        'c204': [1,1,1],
        'c205': [0,0,0],
        'c206': [0,0,0],
        'c207': [6,0,2],
        'c208': [67,64,66],
        'c209': [2,2,2],
        'c210': [39,39,39],
        'c211': [51,49,51],
        'c212': [12,11,11],
        'c213': [1,1,1],
        'c214': [0,0,0],
        'c215': [1,1,1],
        'c216': [39,39,39],
        'c217': [47,44,46],
        'c218': [18,18,18],
        'c219': [0,0,0],
        'c220': [0,0,0],
        'c221': [0,0,0],
        'c222': [4,4,4],
        'c223': [66,64,66],
        'c224': [20,19,19],
        'c225': [5,5,5],
        'c226': [5,5,5],
        'c227': [4,4,4],
        'c228': [39,39,39],
        'c229': [20,17,19],
        'c230': [18,18,18],
        'c231': [9,9,9],
        'c232': [12,12,12],
        'c233': [6,6,6],
        'c234': [2,2,2],
        'c235': [65,63,65],
        'c236': [19,18,18],
        'c237': [4,4,4],
        'c238': [5,5,5],
        'c239': [9,9,9],
        'c240': [39,39,39],
        'c241': [10,7,9],
        'c242': [10,10,10],
        'c243': [21,21,21],
        'c244': [12,12,12],
        'c245': [12,12,12],
        'c246': [2,2,2],
        'c247': [38,37,38],
        'c248': [10,9,10],
        'c249': [16,15,16],
        'c250': [18,18,17],
        'c251': [20,20,20],
        'c252': [39,39,39],
        'c253': [10,7,9],
        'c254': [3,3,3],
        'c255': [8,8,8],
        'c256': [12,12,12],
        'c257': [32,32,32],
        'c258': [2,2,2],
        'c259': [16,16,16],
        'c260': [29,28,29],
        'c261': [5,5,5],
        'c262': [19,17,19],
        'c263': [33,33,32],
        'c264': [39,39,39],
        'c265': [8,5,7],
        'c266': [3,3,3],
        'c267': [3,3,3],
        'c268': [4,4,4],
        'c269': [47,47,47],
        'c270': [0,0,0],
        'c271': [16,16,16],
        'c272': [9,9,9],
        'c273': [25,24,25],
        'c274': [7,7,7],
        'c275': [47,45,46],
        'c276': [37,37,37],
        'c277': [2,2,2],
        'c278': [4,3,4],
        'c279': [6,5,6],
        'c280': [5,5,5],
        'c281': [50,49,49],
        'c282': [0,0,0],
        'c283': [2,2,2],

    }

    predictions = classifier.predict(
        input_fn=lambda:iris_data.eval_input_fn(predict_x,
                                                labels=None,
                                                batch_size=args.batch_size))

    for pred_dict, expec in zip(predictions, expected):
        template = ('\nPrediction is "{}" ({:.1f}%), expected "{}"')

        class_id = pred_dict['class_ids'][0]
        probability = pred_dict['probabilities'][class_id]

        print(template.format(iris_data.ISCHEAT[class_id],
                              100 * probability, expec))


if __name__ == '__main__':
    tf.logging.set_verbosity(tf.logging.INFO)
    tf.app.run(main)
