f = open('output_sort.txt', 'r')
o = open('me.csv', 'w')

lines = f.readlines()
vectors = []
for line in lines:
	line = line.split('\n')[0]
	vector = line.split(',')
	vectors.append(vector)

for i in range(1, len(vectors)):
	o.write(','.join(vectors[0] + vectors[i] + ['1']) + '\n')

# lines = f.readlines()
# m = {}
# for line in lines:
# 	num, line = line.split(' ')
# 	vector = line.split('\n')[0].split(',')
# 	if num in m:
# 		for i in range(len(m[num])):
# 			o.write(','.join(m[num][i] + vector + ['1']) + '\n')
# 		m[num].append(vector)
# 	else:
# 		m[num] = [vector]

# for key1 in m.keys():
# 	vectors1 = m[key1]
# 	for key2 in m.keys():
# 		if key1 != key2:
# 			vectors2 = m[key2]
# 			for i in range(len(vectors1)):
# 				for j in range(len(vectors2)):
# 					o.write(','.join(vectors1[i] + vectors2[j] + ['0']) + '\n')
