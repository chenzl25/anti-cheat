f = open('output_reorder.txt', 'r')

lines = f.readlines()

m = {}

for line in lines:
	full_name, _, vector_string = line.split('\n')[0].split(' ')
	basename = full_name.split('/')[-1].split('-')[0]
	vector = vector_string.split(',')
	if basename in m:
		m[basename].append(vector)
	else:
		m[basename] = [vector]

o = open('me.csv', 'w')

for key in m.keys():
	vectors = m[key]
	for i in range(len(vectors)):
		for j in range(i+1, len(vectors)):
			o.write(','.join(vectors[i] + vectors[j] + ['1'])+ '\n')

count = 0

# for key1 in m.keys():
# 	for key2 in m.keys():
# 		count += 1
# 		if key1 != key2 and count % 30 == 0:
# 			o.write(','.join(m[key1][0] + m[key2][0] + ['0']) + '\n')
