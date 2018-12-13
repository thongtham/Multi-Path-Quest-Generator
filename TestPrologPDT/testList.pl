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
	



testAP(Path,PathANS,0).
testAP(_,PathANS,4)
:-
	write(PathANS).

testAP(Path,PathANS,X)
:-
	Y is X+1,
	string_concat(X,":Path",K),
	append([K],Path,Path2),
	PathANS2 = Path2,
	%write(PathANS2),
	testAP(PathANS2,PathANS2,Y).


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