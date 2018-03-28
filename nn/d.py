import math

def main():
	train_total_file = open("my.csv", "r")
	lines = train_total_file.readlines()
	total_abs = 0
	for line in lines:
		abs_sum = 0
		vector = line.split('\n')[0].split(',')
		label = vector.pop()
		vector = map(lambda x: float(x), vector)
		for i in range(len(vector)/2):
			abs_sum += vector[i] - vector[i+len(vector)/2] if (vector[i] - vector[i+len(vector)/2]) > 0 else  vector[i+len(vector)/2] - vector[i]
			total_abs += abs_sum
	print total_abs

if __name__ == '__main__':
	main()