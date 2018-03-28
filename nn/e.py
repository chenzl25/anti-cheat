import math

def main():
	train_total_file = open("1.csv", "r")
	train_file = open("small_train.csv", "w")
	lines = train_total_file.readlines()
	total_abs = 0
	one = 0
	zero = 0
	for line in lines:
		abs_sum = 0
		vector = line.split('\n')[0].split(',')
		label = vector.pop()
		# vector = map(lambda x: float(x), vector)
		if label == '1':
			one += 1
		else:
			zero += 1
		if label == '1' and one <= 5000:
			train_file.write(','.join(vector+['1']) + '\n')
		if label == '0' and zero <= 5000:
			train_file.write(','.join(vector+['0']) + '\n')

if __name__ == '__main__':
	main()