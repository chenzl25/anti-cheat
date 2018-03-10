import random

def dis(v1, v2):
	d = 0
	for i in range(len(v1)):
		d += (v1[i] - v2[i]) * (v1[i] - v2[i])
	return d

def main():
	input_file = open("output_total.txt", "r")
	# concat_vector_file = open("concat_vector.txt", "w")
	lines = input_file.readlines()
	m = {}
	for line in lines:
		full_name, _, vector_string = line.split(' ')
		dir_name, file_name = full_name.split('/')[-2:]
		file_id = dir_name.split('_')[1]
		short_name = dir_name + file_name
		vector = vector_string.split('\n')[0].split(',')
		vector = map(lambda x: int(x), vector)
		if file_id not in m:
			m[file_id] = [vector]
		else:
			m[file_id].append(vector)

	ct = 0

	# for key in m.keys():
	# 	ct += 1
	# 	# print ct
	# 	vector = m[key]
	# 	for i in range(len(vector)):
	# 		for j in range(i+1, len(vector)):
	# 			pair = vector[i] + vector[j]
	# 			label = '1'
	# 			print dis(vector[i], vector[j])
	# 			# concat_vector_file.write(','.join(pair) + ":" + label + '\n')
	ct = 0
	for key1 in m.keys():
		ct += 1
		# print ct
		count = 0
		for key2 in m.keys():
			count += 1
			if count % 100 == 0 and key1 != key2:
				pair = m[key1][0] + m[key2][0]
				label = '0'
				print dis(m[key1][0], m[key2][0])
				# concat_vector_file.write(','.join(pair) + ":" + label + '\n')
				



if __name__ == '__main__':
	main()