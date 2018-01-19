int fun(int n) {
	int a = 1;
	int b = 2;
	int c = 3;
	to:
		a++;
	while (a) {
		a++
		// b++;
		// a--;
		b--;
	}
	goto to;
	c++;
	return n + a + b + c;
	c += 2;
}