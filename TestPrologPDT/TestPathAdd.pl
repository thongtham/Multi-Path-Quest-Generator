:- module(TestPathAdd,[
		
	]).

	
	
%-------------------------

testPath(5,Path,Path2)
:-
	testPath2(Path,Path2).	
	
	
testPath(X,Path,Path2)
:-
	Y is X+1,
	string_concat(X,":Path",K),
	append([K],Path,PathB),
	testPath(Y,PathB,Path2).


testPath2(Path,Path2)
:- 
	append(["final"],Path,Path2),
	write(Path).
	
%-------------------------

testNum(10).	
testNum(X)
:-
	write(X),
	nl,
	Y is X+1,
	testNum(Y).
	


%-------------------------
testAP(Path,PathANS,0).
testAP(_,PathANS,4)
:-
	write(PathANS).

testAP(_,PathANS,Y)

testAP(Path,PathANS,X)
:-
	Y is X+1,
	string_concat(X,":Path",K),
	append([K],Path,Path2),
	PathANS2 = Path2,
	testAP(PathANS2,PathANS2,Y).
%-------------------------






%--------------------Test --------------------

%testPathSSS([a],A,0).
testPathSSS(Path,PathANS,A)
:-
	testPathQG(Path,PathANS,A).
	
	
testPathQG(Path,PathANS,A)
:-
	(A is 4 -> PathANS = Path, testPathFinal(Path,PathANS,A)
	; 
	%append(A,Path,PathB),    // This like cause bug, because [A is a number] and append must be list.
	append([A],Path,PathB), % // That's why we put [A] there instead of just A
	A2 is A+1, 				% // We put [A] above too cause if we put [0] as input from testPathQG(x,Y,[0]) this will just create 1 which is not list == [1]
	testPathQG(PathB,PathANS,A2)).


testPathFinal(Path,PathANS,A)
:-
	write(A).

%-----------------------------------------------






path(dungeon,forest).
path(city,forest).
path(city,jail).
path(city,palace).
path(forest,city).
path(forest,dungeon).
path(jail,city).
path(palace,city).




existLocationListConnectLocationList([[Name,listConnectLocation,B,C,D,Z]|T],Name,B).
existLocationListConnectLocationList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	existLocationListConnectLocationList(T,Name2,Ans).	


route(AC,X,X,[],Avoid,0).
%route(AC,X,Y,[],Avoid,1):- X\== Y, existLocationListConnectLocationList(AC,X,Y).
route(AC,X,Y,[H|T],Avoid,N)
:-	
	existLocationListConnectLocationList(AC,X,H),
	%way(X,H), 
	\+(member(H,Avoid)), 
	route(AC,H,Y,T,[H|Avoid],N2), 
	N is N2+1.




%route([
%[dungeon,listConnectLocation,forest,z,zz,zzz],
%[city,listConnectLocation,forest,z,zz,zzz],
%[city,listConnectLocation,jail,z,zz,zzz],
%[city,listConnectLocation,palace,z,zz,zzz],
%[forest,listConnectLocation,city,z,zz,zzz],
%[forest,listConnectLocation,dungeon,z,zz,zzz],
%[jail,listConnectLocation,city,z,zz,zzz],
%[palace,listConnectLocation,city,z,zz,zzz]
%],forest,palace,P,[forest],N).
	