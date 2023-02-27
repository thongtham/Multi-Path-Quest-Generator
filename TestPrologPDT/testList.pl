:- module(testList,[
		testPast/2,
		testNum/2,
		testAP/2
	]).

	kill(AA) :- member(AA,[a]).
	


%
/**
*

What happen when query the following??

?- testPath(1,[a],A).
	[4:Path,3:Path,2:Path,1:Path,a]
	A = ["final", "4:Path", "3:Path", "2:Path", "1:Path", a] 

It can be seem that the "Final" isn't being written, even the line that ADD "final" 
is after the WRITE line.

*
*/
%
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
	
	
	
	
	
	
	
testIf(A,B,C,D)
:-
	((A == B, B == C) -> D = 5
	; D = 10 
	).


characterTest([],CharacterName,CharacterAbility)
:-
	fail.
	%CharacterAbility = "k".
	
characterTest([[CharacterName,Value]|TailMain],CharacterNameAsk,CharacterAbility)
:-
	(CharacterNameAsk == CharacterName -> CharacterAbility = Value
	;character(TailMain,CharacterNameAsk,CharacterAbility) -> write("next")).

%------------EXAMPLE FOR CHARACTER----------------------------------------
%characterTest([[king,am],[korn,b],[jack,j]],jack,A).	
	
%[1, 2, 3] = [First, Second| Tail].
%[[1,2,3],[4,5,6]]= [[First, Second| Tail]|TailB].
%-------------------------------------------------------------------------	
	

testAppendText(N,NAME2)
:-
	atom_concat('file',N,NAME),
	atom_concat(NAME,'.txt',NAME2).	
	
	
queryToRelation(friend(A,B))
:-.

queryTR([RelationHead|RelationTail])
:-.

	
	
	
  

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
[player:currentlocation:market:Z:ZZ:ZZZ]	
[jack:currentlocation:market:Z:ZZ:ZZZ]	

[player:currentlocation2:jack:Z:ZZ:ZZZ]	

[[player,jack]]

[jack:currentlocation:market:Z:ZZ:ZZZ]	
[player:currentlocation:market:Z:ZZ:ZZZ]

/-
member(GS,CS)

memberSameLocation(GsameLocation,GL)



%---------------------------- OPTION 1 == create new format of goal [[player,jack]] and check if char of that name is at same location when check if goal reach ---------------------------------
%---------------------------- Pro: easier to code, light weight
%---------------------------- Con: must code on Java site >> reliability 
%---------------------------- Con: the [jack:currentlocation:market:Z:ZZ:ZZZ]	is nolonger generalized 

%memberSameLocation([[player,jack]], ALL_CHAR_LOCATION_LIST)
memberSameLocation([[Char1Lo,Char2Lo]|Tail],GL)
:-
	getCharCurrentLocationFromList(GL,Char1Lo,B),
	getCharCurrentLocationFromList(GL,Char2Lo,B).




%----------------------------OPTION 2 == when goto(CharName,newLocation) > create all pair of [CharName:currentLocation:Char_at_newLocation] as below ---------------------------------
%---------------------------- Pro: self-contain in Prolog
%---------------------------- Con: Performance
%---------------------------- Con: coding in Prolog = hard 


%-check who's at market
%-
%player:currentlocation:jack
%player:currentlocation:jill
%player:currentlocation:adam
%
%jack:currentlocation:jill



