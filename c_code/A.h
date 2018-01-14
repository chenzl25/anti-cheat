// class A {
//  public:
//  	int n_;
//  	A(int n);
//  	int foo();
//  private:
//  	int _foo(int n);
// };

// A::A(int n) {
// 	n_ = n;
// }

// int A::foo() {
// 	return _foo(n_);
// }

// int A::_foo(int n) {
// 	if (n <= 1)
// 		return 1;
// 	else
// 		return _foo(n-1) * n;
// }
