:- module(testAppend,[
		
	]).

%testAppend([[testChar,listStatus],[temp,temp2],[omg]],A).
testAppend(AC,AC2)
:-
	append(AC,[testChar,listStatus,captured],AC2).
	%    A = [[testChar, listStatus], [temp, temp2], [omg], testChar, listStatus, captured].

	
%testAppend1([[testChar,listStatus],[temp,temp2],[omg]],A).	
testAppend1(AC,AC2)
:-
	append(AC,[[testChar,listStatus,captured]],AC2).
	%    A = [[testChar, listStatus], [temp, temp2], [omg], [testChar, listStatus, captured]].
	
	
	
	
%testAppend1B([[testChar,listStatus],[temp,temp2],[omg]],A).	
testAppend1B(AC,AC2)
:-
	append([[testChar,listStatus,captured]],AC,AC2).
	%     A = [[testChar, listStatus, captured], [testChar, listStatus], [temp, temp2], [omg]].




%testAppend2([testChar,listStatus,temp,temp2,omg],A).		
testAppend2(AC,AC2)
:-
	append(AC,[testChar,listStatus,captured],AC2).
	%    A = [testChar, listStatus, temp, temp2, omg, testChar, listStatus, captured].






	
	