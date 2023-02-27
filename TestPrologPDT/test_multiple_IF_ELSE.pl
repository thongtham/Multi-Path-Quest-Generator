
%test_if_else(a,b,ANS).
test_if_else(A,B,ANS) :- 	

	(A = a 
	-> 
		(
		B = b
		->
		ANS = 1
		;
		ANS = 2
		)	
	;
	ANS = 4
	).
	
aa(a).
aa(b).	