import random
def main():
	f = open('output_0-15.txt', 'r')
	o = open('me2.csv', 'w')
	lines = f.readlines()
	m = {}
	for line in lines:
		line = line.split('\n')[0]
		full_path, _, vector_string = line.split(' ')
		file_name = full_path.split('/')[-1]
		dir_name = full_path.split('/')[-2]
		vector = vector_string.split(',')
		if dir_name in m:
			m[dir_name].append({'vector':vector, 'full_path': full_path})
		else:
			m[dir_name] =[{'vector':vector, 'full_path': full_path}]
	
	vs = m['1']
	for i in range(500):
		print  vs[i]['full_path']
		o.write(','.join(vs[0]['vector'] + vs[i]['vector'] + ['1'])+'\n')

	# for key in m.keys():
	# 	vs = m[key]
	# 	for i in range(1500):
	# 		r1 = random.randint(0, 499)
	# 		r2 = random.randint(0, 499)
	# 		o.write(','.join(vs[r1] + vs[r2] + ['1'])+'\n')
		# for i in range(len(vs)):
		# 	o.write(','.join(vs[0] + vs[i] + ['1'])+'\n')
	# for k1 in m.keys():
	# 	for k2 in m.keys():
	# 		if k1 != k2:
	# 			for i in range(100):
	# 				r1 = random.randint(0, 499)
	# 				r2 = random.randint(0, 499)
	# 				o.write(','.join(m[k1][r1] + m[k2][r2] + ['0'])+'\n')
	# for i in range(11, 16):
	# 	key = str(i)
	# 	vs = m[key]
	# 	for k in range(3000):
	# 		r1 = random.randint(0, 499)
	# 		r2 = random.randint(0, 499)
	# 		o.write(','.join(vs[r1] + vs[r2] + ['1'])+'\n')
	# for i in range(11, 16):
	# 	for j in range(i, 16):
	# 		k1 = str(i)
	# 		k2 = str(j)
	# 		if k1 != k2:
	# 			for k in range(500):
	# 				r1 = random.randint(0, 499)
	# 				r2 = random.randint(0, 499)
	# 				o.write(','.join(m[k1][r1] + m[k2][r2] + ['0'])+'\n')
			
if __name__ == '__main__':
	main()