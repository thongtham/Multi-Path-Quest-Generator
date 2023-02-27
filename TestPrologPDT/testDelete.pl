:- module(testDelete,[
		
	]).

	
	
	
%testDelete([[testChar,listStatus,captured,z,zz,zzz],[temp,temp2],[omg]],A).
testDelete(AC,AC2) %***** WORKING ******
:-
	delete(AC,[testChar,listStatus,captured,z,zz,zzz],AC2).     
	%remove_list(AC,[[testChar,listStatus,captured,z,zz,zzz]],AC2).



%testDeleteANY([[testChar,listStatus,captured,z,zz,zzz],[testChar,listStatus,captured,z,zz,1],[temp,temp2],[omg]],A).
testDeleteANY(AC,AC3) %***** WORKING ******
:-
	remove_list(AC,[[testChar,listStatus,captured,_,_,_]],AC2),		%  A = [[testChar, listStatus, captured, z, zz, 1], [temp, temp2], [omg]].
	remove_list(AC2,[[testChar,listStatus,captured,_,_,_]],AC3).	%  A = [[temp, temp2], [omg]].




%testDel([a(a),b,c],A)	
testDel(AC,AC2)%***** NOT WORKING ******
:-
	delete(AC,a,AC2).

	
	
	
	
	
	
	
remove_list([], _, []).
remove_list([X|Tail], L2, Result)
:- 
	member(X, L2),
	!, 
	remove_list(Tail, L2, Result). 
remove_list([X|Tail], L2, [X|Result])
:- 
	remove_list(Tail, L2, Result).		
	