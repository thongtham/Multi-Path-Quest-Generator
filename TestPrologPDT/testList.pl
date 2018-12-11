:- module(testList,[
		testNum/2,
		testAP/2
	]).

	kill(AA) :- member(AA,[a]).
	

testNum(10).	
testNum(X)
:-
	write(X),
	nl,
	Y is X+1,
	testNum(Y).
	
	
	
testAP(Path,4)
:-
	write(Path).

testAP(Path,X)
:-
	Y is X+1,
	append([kill_quest],Path,Path2),
	append(X,Path2,Path3),
	testAP(Path3,Y).


	%
	%
	%
	%
	%member([a,b],[[a,b],[a,c],[a,d]]).					= true
	%
	%memberchk([a,b],[[a,b],[a,c],[a,d]]).        		= true
	%
	%member([[a,b],[a,c]],[[a,b],[a,c],[a,d]]).   		= false
	%
	%memberchk([[a,b],[a,c]],[[a,b],[a,c],[a,d]]).   	= false