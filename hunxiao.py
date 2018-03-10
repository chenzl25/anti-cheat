import random

import argparse



def main(input_file, output_file):
	f = open(input_file, 'r')
	lines = f.readlines()
	stack = []
	line_no = 0
	for line in lines:
		line_no += 1
		for i in range(len(line)):
			if line[i] == '{' or line[i] == '}':
				stack.append({'ch':line[i], 'line':line_no})
	indent = 0
	s = '  '
	tmp_stk = []
	q = []
	for i in range(len(stack)):
		ch = stack[i]['ch']
		line = stack[i]['line']
		if ch == '{':
			# print indent * s + ch + ':' + str(line)
			stack[i]['indent'] = indent
			tmp_stk.append(stack[i])
			indent += 1
		else:
			indent -= 1
			item = tmp_stk.pop()
			item['another'] = stack[i]
			q.append(item)
			# print indent * s + ch + ':' + str(line)

	q.sort(lambda a,b: 1 if a['line'] > b['line'] else -1)

	func_no = 0

	func_q = []

	for item in q:
		if item['indent'] == 0:
			item['func_no'] = func_no
			func_q.append(item)
			func_no += 1
		else:
			f_item = func_q[-1]
			if 'inside_block' in f_item:
				f_item['inside_block'].append(item)
			else:
				f_item['inside_block'] = [item]

	# choose a function block merge to func 1
	if len(func_q) > 0:
		merge_func_no = random.randint(1,len(func_q)-1)
		merge_func = func_q[merge_func_no]
		merge_block = merge_func
		delete_merge_func = False
		if random.randint(0,1) == 0:
			delete_merge_func = True
		if random.randint(0,1) == 0:
			if 'inside_block' in merge_func:
				merge_block_no = random.randint(0,len(merge_func['inside_block'])-1)
				merge_block = merge_func['inside_block'][merge_block_no]
				delete_merge_func = False
				
		print merge_block
		print delete_merge_func

		print merge_block['line'], merge_block['another']['line']
		lines = [''] + lines
		out_lines = lines[:q[0]['line']+1]  + lines[merge_block['line']+1:merge_block['another']['line']]
		if delete_merge_func:
			out_lines +=  lines[q[0]['line']+1:merge_func['line']] + lines[merge_func['another']['line']+1:]
		else:
			if random.randint(0,1) == 0:
				out_lines +=  lines[q[0]['line']+1:merge_block['line']+1] + lines[merge_block['another']['line']:]
			else:
				# duplicate the block
				print 'duplicate'
				out_lines +=  lines[q[0]['line']+1:]
		out_file = open(output_file, 'w')
		out_file.writelines(out_lines)




if __name__ == '__main__':
	parser = argparse.ArgumentParser(description='Process some integers.')
	# parser.add_argument('input', metavar='N', type=int, nargs='+',
	#                     help='an integer for the accumulator')
	# parser.add_argument('--sum', dest='accumulate', action='store_const',
	#                     const=sum, default=max,
	#                     help='sum the integers (default: find the max)')
	parser.add_argument('-i', dest='input')
	parser.add_argument('-o', dest='output')
	args = parser.parse_args()
	main(args.input, args.output)
