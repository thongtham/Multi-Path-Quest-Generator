:- module(testIfElse,[
		
	]).

	
%testIfOrElse(1,B).	
testIfOrElse(A,B)
:-
	(A < 3 -> A2 is A+1, testIfOrElse(A2,B)
	;
	B = A
	).
	
%testIfOrElse3(1,1,1,X,Y,Z).	
%tempStep(1,X,Y,Z).	

tempStep(A,X,Y,Z)
:-
	testIfOrElse3(A,A,A,X,Y,Z).


testIfOrElse3(A,B,C,X,Y,Z)
:-
	( doMath(A,A2,B,B2,C,C2) -> emptyStep(A2,B2,C2,X,Y,Z)
	;
	X = A,
	Y = B,
	Z = C
	).	
	

doMath(A,A2,B,B2,C,C2)
:-
	A < 3,	
	A2 is A + 1,
	B2 is B + 2,
	C2 is C + 3.	


emptyStep(A,B,C,X,Y,Z)
:-
	testIfOrElse3(A,B,C,X,Y,Z).
	