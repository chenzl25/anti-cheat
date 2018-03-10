import math

def main():
	train_total_file = open("me.csv", "r")
	train_file = open("my.csv", "w")
	lines = train_total_file.readlines()
	for line in lines:
		vector = line.split('\n')[0].split(',')
		label = vector.pop()
		vector = map(lambda x: int(x), vector)
		l = len(vector) / 2
		v1 = vector[:l]
		v2 = vector[l:]

		# d = []
		# for i in range(len(v1)):
		# 	d.append(v1[i] - v2[i])

		# new_vector = v1 + d

		new_vector = v1 + v2

		sum = 0.0
		for i in range(len(new_vector)):
			sum += new_vector[i]
		avg = sum / len(new_vector)

		sigma = 0.0
		for i in range(len(new_vector)):
			sigma += (new_vector[i] - avg) * (new_vector[i] - avg)

		sigma = math.sqrt(sigma/len(new_vector))

		if sigma == 0.0:
			continue

		for i in range(len(new_vector)):
			new_vector[i] = str((new_vector[i] - avg) / sigma)

		new_vector.append(label)

		train_file.write(','.join(new_vector) + "\n")





if __name__ == '__main__':
	main()