import pandas as pd
import tensorflow as tf

TRAIN_URL = "http://download.tensorflow.org/data/iris_training.csv"
TEST_URL = "http://download.tensorflow.org/data/iris_test.csv"

CSV_COLUMN_NAMES = ['SepalLength', 'SepalWidth',
                    'PetalLength', 'PetalWidth', 'ISCHEAT']

CSV_COLUMN_NAMES = ['c0','c1','c2','c3','c4','c5','c6','c7','c8','c9','c10','c11','c12','c13','c14','c15','c16','c17','c18','c19','c20','c21','c22','c23','c24','c25','c26','c27','c28','c29','c30','c31','c32','c33','c34','c35','c36','c37','c38','c39','c40','c41','c42','c43','c44','c45','c46','c47','c48','c49','c50','c51','c52','c53','c54','c55','c56','c57','c58','c59','c60','c61','c62','c63','c64','c65','c66','c67','c68','c69','c70','c71','c72','c73','c74','c75','c76','c77','c78','c79','c80','c81','c82','c83','c84','c85','c86','c87','c88','c89','c90','c91','c92','c93','c94','c95','c96','c97','c98','c99','c100','c101','c102','c103','c104','c105','c106','c107','c108','c109','c110','c111','c112','c113','c114','c115','c116','c117','c118','c119','c120','c121','c122','c123','c124','c125','c126','c127','c128','c129','c130','c131','c132','c133','c134','c135','c136','c137','c138','c139','c140','c141','c142','c143','c144','c145','c146','c147','c148','c149','c150','c151','c152','c153','c154','c155','c156','c157','c158','c159','c160','c161','c162','c163','c164','c165','c166','c167','c168','c169','c170','c171','c172','c173','c174','c175','c176','c177','c178','c179','c180','c181','c182','c183','c184','c185','c186','c187','c188','c189','c190','c191','c192','c193','c194','c195','c196','c197','c198','c199','c200','c201','c202','c203','c204','c205','c206','c207','c208','c209','c210','c211','c212','c213','c214','c215','c216','c217','c218','c219','c220','c221','c222','c223','c224','c225','c226','c227','c228','c229','c230','c231','c232','c233','c234','c235','c236','c237','c238','c239','c240','c241','c242','c243','c244','c245','c246','c247','c248','c249','c250','c251','c252','c253','c254','c255','c256','c257','c258','c259','c260','c261','c262','c263','c264','c265','c266','c267','c268','c269','c270','c271','c272','c273','c274','c275','c276','c277','c278','c279','c280','c281','c282','c283', 'ISCHEAT']

ISCHEAT = ['NO', 'YES']

def maybe_download():
    # train_path = tf.keras.utils.get_file(TRAIN_URL.split('/')[-1], TRAIN_URL)
    # test_path = tf.keras.utils.get_file(TEST_URL.split('/')[-1], TEST_URL)
    train_path = 'my.csv'
    test_path = 'my.csv'
    return train_path, test_path

def load_data(y_name='ISCHEAT'):
    """Returns the iris dataset as (train_x, train_y), (test_x, test_y)."""
    train_path, test_path = maybe_download()

    train = pd.read_csv(train_path, names=CSV_COLUMN_NAMES, header=0)
    train_x, train_y = train, train.pop(y_name)

    test = pd.read_csv(test_path, names=CSV_COLUMN_NAMES, header=0)
    test_x, test_y = test, test.pop(y_name)

    return (train_x, train_y), (test_x, test_y)


def train_input_fn(features, labels, batch_size):
    """An input function for training"""
    # Convert the inputs to a Dataset.
    dataset = tf.data.Dataset.from_tensor_slices((dict(features), labels))

    # Shuffle, repeat, and batch the examples.
    dataset = dataset.shuffle(1000).repeat().batch(batch_size)

    # Return the dataset.
    return dataset


def eval_input_fn(features, labels, batch_size):
    """An input function for evaluation or prediction"""
    features=dict(features)
    if labels is None:
        # No labels, use only features.
        inputs = features
    else:
        inputs = (features, labels)

    # Convert the inputs to a Dataset.
    dataset = tf.data.Dataset.from_tensor_slices(inputs)

    # Batch the examples
    assert batch_size is not None, "batch_size must not be None"
    dataset = dataset.batch(batch_size)

    # Return the dataset.
    return dataset


# The remainder of this file contains a simple example of a csv parser,
#     implemented using a the `Dataset` class.

# `tf.parse_csv` sets the types of the outputs to match the examples given in
#     the `record_defaults` argument.
CSV_TYPES = [[0.0], [0.0], [0.0], [0.0], [0]]

def _parse_line(line):
    # Decode the line into its fields
    fields = tf.decode_csv(line, record_defaults=CSV_TYPES)

    # Pack the result into a dictionary
    features = dict(zip(CSV_COLUMN_NAMES, fields))

    # Separate the label from the features
    label = features.pop('ISCHEAT')

    return features, label


def csv_input_fn(csv_path, batch_size):
    # Create a dataset containing the text lines.
    dataset = tf.data.TextLineDataset(csv_path).skip(1)

    # Parse each line.
    dataset = dataset.map(_parse_line)

    # Shuffle, repeat, and batch the examples.
    dataset = dataset.shuffle(1000).repeat().batch(batch_size)

    # Return the dataset.
    return dataset
