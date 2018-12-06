


:- dynamic character/5.

testwrite(character(KillerName,KillerLvl,KillerStatus,KillerHealth)) :- write(KillerLvl).

testwrite2(Name) :- 	write(Name),
						character(Name,X,Y,Z),
						write(X).
						
testOpenWriteToFile :-
    open('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt',write, Stream),
    (   man(Man), write(Stream, Man), fail
    ;   true
    ),
    close(Stream).						

testSee :-
	see('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/fileSEE.txt'),
	%read(Input),
	%write(Input),
	seen.

testTold :-	
	tell('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/fileTold.txt'),
	write(abc),
	told.
	
	
						
testProtocolWriteToFile :-
    protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt'),
    getfirst([jack,15,alive,weak,market],ActorName),
    write(ActorName),
	getfirst([],ActorName2),
    noprotocol.							
						
						
						
						
						

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
						
						
						
check(Name) :- 	character(Name,X,Y,Z,L),
				write(Name),
				write(","),
				write(X),
				write(","),
				write(Y),
				write(","),
				write(Z),
				write(","),
				write(L).

attack(KillerName,TargetName) :- 	
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth,KillerLocation),
	TargetStatus == alive,
	KillerHealth == healthy,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,dead,TargetHealth,TargetLocation)),
	write(KillerName),write(" kill "),write(TargetName).




heal(Healer,TargetName) :- 
	healer(HealerName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),	
	assert(character(TargetName,TargetLvl,TargetStatus,healthy,TargetLocation)).

poisoneasy(KillerName,TargetName) :-
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth,KillerLocation),
	TargetStatus == alive,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,TargetStatus,poison,TargetLocation)),
	write(KillerName),write(" poison "),write(TargetName).
	
	
sickdiecheck(TargetName) :- 
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	TargetHealth == poison,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,dead,TargetHealth,TargetLocation)).


checkitem(Owner,ItemName) :-
	item(ItemName,Owner,Amount),
	write(ItemName),write(" owned by "), write(Owner),write(": amount = "), write(Amount).
	

checksamelocation(NPC1Name,NPC2Name) :-
	character(NPC1Name,NPC1Lvl,NPC1Status,NPC1Health,NPC1Location),
	character(NPC2Name,NPC2Lvl,NPC2Status,NPC2Health,NPC2Location),
	NPC1Location == NPC2Location.
	

changelocation(MoveName,NewLocation) :-
	passkeymovecheck(MoveName,NewLocation),
	retract(character(MoveName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(MoveName,TargetLvl,TargetStatus,TargetHealth,NewLocation)).
	

passkeymovecheck(MoveName,NewLocation) :-	
	character(MoveName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	passkeycheck(NewLocation,TargetLocation,dungeonkey).
	
	

passkeycheck(XLocation,YLocation,Item) :- passkey(XLocation,YLocation,dungeonkey).
passkeycheck(YLocation,XLocation,Item) :- passkey(XLocation,YLocation,dungeonkey).	 


getnumber(item(X)) :- write(X).
getnumbername(item(Owner,X)) :- write(Owner),write(X).



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
useitem(Owner,ItemName) :-
	item(ItemName,Owner,Amount),
	Amount >= 0,
	newAmount is Amount-1,
	assert(item(ItemName,Owner,newAmount)),
	retract(item(ItemName,Owner,Amount)).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% attack
% poison
% trap
% fire smoke
% Motor Vehicle Accident
% heart attack		
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%change all of tragetStatus == to this %%%%%%%%%%%%%%

checkstatusexist(WantToCheck,[Check|Rest]) :-
		WantToCheck \= Check,
		checkstatus(WantToCheck,[Check|Rest]).

checkstatus(WantToCheck,[]).		
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	
	

checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current == location(X),
	location(ANY) \= Current,
	checkpathrepeat(ANY,RestOfPath).
	
checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current \= location(X).
	

%%%%%%%%%%%%%%%%%%%%
%%%%PASS IT WORK%%%%

testlocationcheck(ANY,Current) :-
	location(X),
	Current == location(X),
	location(ANY) == location(X).
	
%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%
%%%%PASS IT WORK%%%%

testlocationchecklight(ANY,Current) :-
	location(ANY) == Current.
	
%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	

	
	


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

changelisttoquest(List) :-
	getfirst(List,ActorName),
	getsecond(List,TargetName),
	getthird(List,Goal),
	getforth(List,A).
	

	

getfirst([ActorName|RestOfList],ActorName).
getsecond([_,TargetName|RestOfList],TargetName).
getthird([_,_,E|_], E).
getforth([_,_,_,E|_], E).


getsecond([Goal|RestOfList],Return).	


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



	
	
trykill(KillerName,TargetName,[Solution])	:-
	character(TargetName,TargetLvl,dead,TargetHealth),
	trykill(KillerName,TargetName,[Solution]).
	
	
	
	
	
testdead(_,_,dead).

testdead(KillerName,TargetName,TargetStatus) :-
	attack(KillerName,TargetName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	testdead(KillerName,TargetName,TargetStatus).
	
	Path is ["attack"|Rest].
	testdead(KillerName,TargetName,TargetStatus).
	
testdead(KillerName,TargetName,TargetStatus) :-	
	poisoneasy(KillerName,TargetName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	testdead(KillerName,TargetName,TargetStatus).	
	

	
	

	
	
	
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
















:- dynamic character/5.

testwrite(character(KillerName,KillerLvl,KillerStatus,KillerHealth)) :- write(KillerLvl).

testwrite2(Name) :- 	write(Name),
						character(Name,X,Y,Z),
						write(X).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
						
						
						
check(Name) :- 	character(Name,X,Y,Z,L),
				write(Name),
				write(","),
				write(X),
				write(","),
				write(Y),
				write(","),
				write(Z),
				write(","),
				write(L).

attack(KillerName,TargetName) :- 	
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth,KillerLocation),
	TargetStatus == alive,
	KillerHealth == healthy,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,dead,TargetHealth,TargetLocation)),
	write(KillerName),write(" kill "),write(TargetName).




heal(Healer,TargetName) :- 
	healer(HealerName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),	
	assert(character(TargetName,TargetLvl,TargetStatus,healthy,TargetLocation)).

poisoneasy(KillerName,TargetName) :-
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth,KillerLocation),
	TargetStatus == alive,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,TargetStatus,poison,TargetLocation)),
	write(KillerName),write(" poison "),write(TargetName).
	
	
sickdiecheck(TargetName) :- 
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	TargetHealth == poison,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,dead,TargetHealth,TargetLocation)).


checkitem(Owner,ItemName) :-
	item(ItemName,Owner,Amount),
	write(ItemName),write(" owned by "), write(Owner),write(": amount = "), write(Amount).
	

checksamelocation(NPC1Name,NPC2Name) :-
	character(NPC1Name,NPC1Lvl,NPC1Status,NPC1Health,NPC1Location),
	character(NPC2Name,NPC2Lvl,NPC2Status,NPC2Health,NPC2Location),
	NPC1Location == NPC2Location.
	

changelocation(MoveName,NewLocation) :-
	passkeymovecheck(MoveName,NewLocation),
	retract(character(MoveName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(MoveName,TargetLvl,TargetStatus,TargetHealth,NewLocation)).
	

passkeymovecheck(MoveName,NewLocation) :-	
	character(MoveName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	passkeycheck(NewLocation,TargetLocation,dungeonkey).
	
	

passkeycheck(XLocation,YLocation,Item) :- passkey(XLocation,YLocation,dungeonkey).
passkeycheck(YLocation,XLocation,Item) :- passkey(XLocation,YLocation,dungeonkey).	 


getnumber(item(X)) :- write(X).
getnumbername(item(Owner,X)) :- write(Owner),write(X).



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
useitem(Owner,ItemName) :-
	item(ItemName,Owner,Amount),
	Amount >= 0,
	newAmount is Amount-1,
	assert(item(ItemName,Owner,newAmount)),
	retract(item(ItemName,Owner,Amount)).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



% attack
% poison
% trap
% fire smoke
% Motor Vehicle Accident
% heart attack		
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


checkstatusexist(WantToCheck,[Check|Rest]) :-
		WantToCheck \= Check,
		checkstatus(WantToCheck,[Check|Rest]).

checkstatus(WantToCheck,[]).		
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	
	


checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current == location(X),
	location(ANY) \= Current,
	checkpathrepeat(ANY,RestOfPath).
	
checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current \= location(X).
	

%%%%%%%%%%%%%%%%%%%%
%%%%PASS IT WORK%%%%

testlocationcheck(ANY,Current) :-
	location(X),
	Current == location(X),
	location(ANY) == location(X).
	
%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%
%%%%PASS IT WORK%%%%

testlocationchecklight(ANY,Current) :-
	location(ANY) == Current.
	
%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

changelisttoquest(List) :-
	getfirst(List,ActorName),
	getsecond(List,TargetName),
	getthird(List,Goal),
	getforth(List,A).
	

	

getfirst([ActorName|RestOfList],ActorName).
getsecond([_,TargetName|RestOfList],TargetName).
getthird([_,_,E|_], E).
getforth([_,_,_,E|_], E).


getsecond([Goal|RestOfList],Return).	


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		


testQuest(TypeOfQuest,Actor,Target,Goal,Path) :-

	character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
	character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),

	(TypeOfQuest == kill -> testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
											character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
											Goal,
											[killQuest,testRest])
	;TypeOfQuest == heal -> testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
											character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
											Goal,
											Path)
	;TypeOfQuest == give -> testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
											character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
											Goal,
											Path)
	).

testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
				character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
				Goal,
				[Latest|RestOfPath]) :-
	
	\+(Latest == attack),
	ActorHealth == healthy,
	Goal == dead,
	testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
				character(Target,TargetLVL,dead,TargetHealth,TargetLocation),
				Goal,
				[attack,Latest|RestOfPath]).

testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
				character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
				Goal,
				Path) :-
	TargetStatus == Goal,
	write(Path+'kill1'),
	testQuestKill2(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
					character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
					Goal,
					[donetest|Path]).
	
testQuestKill2(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
				character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
				Goal,
				Path) :-
				
					protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/filePath.txt'),
					sleep(0.5),
					write(Path+'kill2'),
					noprotocol.
	
	
	
%%%%%%testQuest(kill,john,jill,dead,Path).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		
	
testString(X) :-
	X == abc.
	
	
						
testProtocolJava(SomeQuery) :-
    protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt'),
	
	%%%% Sleep will freeze Eclispe Java too. When Eclipse Java query prolog, it will wait until prolog finish before continuing. %%%% 
	sleep(1),
	%%%%%%%
	
    getfirst(SomeQuery,ActorName),
    write(ActorName),
    noprotocol.			
	
	
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Game State Definition  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



%** This will return a list of all fact in item/2 **	
gameStateItem(AllItem) :- findall(item(X,Y), (item(X,Y)), AllItem).

gameStateCharacter(AllCharacter) :-	 findall(character(Name,Level,Status,Attribute,Location), (character(Name,Level,Status,Attribute,Location)), AllCharacter).
	
gameStateRelation(AllRelation) :-	 findall(relationship(Name1,Name2,Relationship), (relationship(Name1,Name2,Relationship)), AllRelation).
	
gameStateAttribute(AllAttribute) :-	 findall(attribute(Name1,Attribute1), (attribute(Name1,Attribute1)), AllAttribute).
	
	

gameState([ListOfLocation]). 				%Location
gameState([ListOfItem]). 					%Item

gameState([ListOfFactionRelation]). 		%Ability
gameState([ListOfItem]). 					%Item

gameState(K).
gameState2([[K]]).


%%%%%%%%%%%%%%%%%%%%%%%%%%%% TESTING  %%%%%%%%%%%%%%%%%%%%%%%%%	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%**% CONFIRM WORKING%**%
testDeleteAdd :-
	gameStateItem(AllItem),
	write(AllItem),
	delete(AllItem,item(provision,food),AllItemNew),   %% NOTE: delete/3 will delete ALL match instants
	nl,
	nl,
	write(item(provision,food)),
	nl,
	nl,
	write(AllItemNew),
	append(AllItemNew, [item(ttttt,aaaa)], AllItemNew2),
	nl,
	nl,
	write(AllItemNew2).

testcrime :-
	A = [relationship(test,test2,friend),relationship(test2,test23,enemy)],
	add_crime(A,steal,character(player,1,a,b,c),New),
	add_crime(New,murder,character(player,1,a,b,c),New2),
	member(relationship(nation,player,major_crime),New2).

	
%%%%%%%%%%%%%%%%%%%%%%% Utility Function %%%%%%%%%%%%%%%%%%%%%%	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	


forall(character(D),A).


% Check if "all of" element in A ([H|T] below) exist in List
subset([ ],_).
subset([H|T],List) :-
    member(H,List),
    subset(T,List).


%** BreakDown Goal into [] for easier check
%TEST getGoalGiveTest([character(jack,11,dead,weak,market),potion],A,B).

getGoalGive([character(A,B,C,D,E),Item],character(A,B,C,D,E),Item).

getGoalGiveTest(Goal,character(A,B,C,D,E),Item) :-
	getGoalGive(Goal,character(A,B,C,D,E),Item).


%** get a character from ListCharacter
getCharacterFromList([character(Name,A,B,C,D)|T],Name,character(Name,A,B,C,D)).
getCharacterFromList([character(Name,A,B,C,D)|T],Name2, Ans):- 
	Name \== Name2, 
	getCharacterFromList(T,Name2,Ans).
	
	


	

checkName1([],Name,Char) :-
	write(Char).
checkName1([H|T],Name,Char) :-
	H = character(K,A,B,C,D),
	
	%write(H),
	%write(A),
	%write(B),
	%write(C),
	%write(D),
	%Char2 = H,
	
	(K == Name  -> 	Char2 = H
	;K \= Name  ->	Char2 = Char
	),
	
	checkName1(T,Name,Char2).		
	
	
	
		
getCharacter(Name,Char) :-
	character(Name,A,B,C,D),
	Char = character(Name,A,B,C,D).

%%**	
=(X, Y, R) :- X == Y,    !, R = true.
=(X, Y, R) :- ?=(X, Y),  !, R = false. % syntactically different
=(X, Y, R) :- X \= Y,    !, R = false. % semantically different
=(X, Y, R) :- R == true, !, X = Y.
=(X, X, true).
=(X, Y, false) :-
   dif(X, Y).

if_(C_1, Then_0, Else_0) :-
   call(C_1, Truth),
   functor(Truth,_,0),  % safety check
   ( Truth == true -> Then_0 
   ; Truth == false -> Else_0 ).

list_item_subtracted([],_,[]).
list_item_subtracted([A|As],E,Bs1) :-
    if_(A = E, Bs = Bs1, Bs1 = [A|Bs]),
    list_item_subtracted(As,E,Bs).	
	

list_item_asserted(Bs,E,[E|Bs]).

	
%list_item_subtracted([a,b,c],a,K).
%list_item_asserted([a,b,c],a,K).
	
%%**
	
	
%** remove old, append new
direct_attack_record(ListOld,character(ActorName,ActorLevel,ActorStatus,ActorAttribute,ActorLocation),character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew2) :-
	write(oldlist),
	nl,
	write(ListOld),
	nl,
	list_item_asserted(ListOld,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew),   
	write(newList),
	nl,
	write(ListNew),
	nl,
	list_item_asserted(ListNew,character(TargetName,TargetLevel,dead,TargetAttribute,TargetLocation), ListNew2),
	write(newlist2),
	nl,
	write(ListNew2),
	nl.
	
add_crime(ListRelation,TypeOfCrime,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew2) :-
	(TypeOfCrime == steal  -> Crime = minor_crime
	;TypeOfCrime == murder -> Crime = major_crime
	),
	list_item_asserted(ListRelation,relationship(nation,TargetName,Crime), ListNew2),
	write(ListNew2),
	nl.


check_character(character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),name,TargetName).
check_character(character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),level,TargetLevel).
check_character(character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),status,TargetStatus).
check_character(character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),attribute,TargetAttribute).
check_character(character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),location,TargetLocation).



%%%%%%%%%%%%%%%%%%%%%%%%**
%testFKKK(a,a,b,c).

testKKK1(A,B,C) :-
	testKKK(A,B,C).

	
	
testFKKK(Q,A,B,C) :-
	(Q == a -> testKKK(A,B,C)
	;Q == b -> testKKK(A,B,C)
	;Q == c -> testKKK(A,B,C)
	).
	
	
testKKK(A,B,C) :-
	write(A),
	fail.
testKKK(A,B,C) :-
	write(B),
	fail.
testKKK(A,B,C) :-
	write(C).

	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Input Include Game State for rollback  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%testQuestGameState(kill,character(jack,15,alive,weak,market),test).
%checkCharacterFromList([character(jack,11,dead,weak,market),character(player,1,alive,strong,market),character(jill,12,alive,strong,lab)],player,Char).
%check2CharacterFromList([character(jack,11,dead,weak,market),character(player,1,alive,strong,market),character(jill,12,alive,strong,lab)],player,Char).
%check3CharacterFromList([character(jack,11,dead,weak,market),character(player,1,alive,strong,market),character(jill,12,alive,strong,lab)],Char).
%checkName1([character(jack,11,dead,weak,market),character(player,1,alive,strong,market),character(jill,12,alive,strong,lab)],player,Char).






% TYPE OF GOAL INPUT
% Kill == Goal = character(a,b,c,d,e)				= ATOM = that is the target must be dead
% Heal == Goal = character(a,b,c,d,e) 				= ATOM = that is the target must be heal
% Give == Goal = [character(a,b,c,d,e),Item]		= LIST = Item must be given to character



testQuestGameState(kill,Goal,Path) :-
	gameStateItem(AllItem),
	gameStateCharacter(AllCharacter),
	gameStateRelation(AllRelation),
	gameStateAttribute(AllAttribute),
	append([kill_quest],[Path],Path2),
	member(Goal,AllCharacter),
	testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path2).	

testQuestGameState(heal,Goal,Path) :-
	gameStateItem(AllItem),
	gameStateCharacter(AllCharacter),
	gameStateRelation(AllRelation),
	gameStateAttribute(AllAttribute),
	append([heal_quest],[Path],Path2),
	member(Goal,AllCharacter),
	testGameStateHeal(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path2).
	
testQuestGameState(give,Goal,Path) :-
	gameStateItem(AllItem),
	gameStateCharacter(AllCharacter),
	gameStateRelation(AllRelation),
	gameStateAttribute(AllAttribute),
	append([give_quest],[Path],Path2),
	getGoalGiveTestQuest(Goal,character(A,B,C,D,E),Item),
	member(character(A,B,C,D,E),AllCharacter),
	member(Item,AllItem),
	testGameStateGiveQuest(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path2).
	
	


	
%testQuestGameState(TypeOfQuest,Goal,Path) :-
%	gameStateItem(AllItem),
%	gameStateCharacter(AllCharacter),
%	gameStateRelation(AllRelation),
%	gameStateAttribute(AllAttribute),
%	append([kill_quest],Path,Path2),
%	append([kkk_],Path2,Path3),
%
%	
%	(TypeOfQuest == kill -> member(Goal,AllCharacter),
%							testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path3)
%	;TypeOfQuest == heal -> member(Goal,AllCharacter),
%							testGameStateHeal(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path)
%	;TypeOfQuest == give -> getGoalGiveTestQuest(Goal,character(A,B,C,D,E),Item),
%							member(character(A,B,C,D,E),AllCharacter),
%							member(Item,AllItem),
%							testGameStateGiveQuest(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path)
%	).

	
testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path) :-
	( 	testGameStateKillQuest1(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path) -> true
	;	testGameStateKillQuest2(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path)

	).


%%%% Kill == Goal = character(a,b,c,d,e) = ATOM = that is the target must be dead

%%%% BASE PREDICATE, TRUE WHEN QUEST GOAL STATE REACH
testGameStateKillQuest1(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path) :-
	getCharacterFromList(AllCharacter,Name,Char),
	check_character(Char,status,TargetStatus),
	nl,
	write(test2),
	TargetStatus == dead,
	nl,
	write(complete_one_path),
	nl,
	write(Path).
	
	
%%%% THESE FOLLOWING PREDICATE WILL CHECK FOR CONDITION OF ACTION, BEFORE TRIGGER ACTION
%Direct_Attack
testGameStateKillQuest2(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path) :-
	write(test3),
	nl,
	getCharacterFromList(AllCharacter,player,PlayerChar),
	check_character(PlayerChar,name,PlayerName),
	\+member(attribute(PlayerName,weak),AllAttribute),
	write(test4),
	nl,
	append([direct_attack],Path,Path2),
	write(Path2),
	nl,
	killQuest_Direct_Attack(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path2).
	
	
%Hire_to_Attack
testGameStateKillQuest2(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	write(test5),
	nl,
	getCharacterFromList(AllCharacter,_,AssassinChar),
	check_character(AssassinChar,name,AssassinName),
	\+member(attribute(AssassinName,weak),AllAttribute),
	write(test6),
	nl,
	killQuest_Hire_to_Attack(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),AssassinName,[hire_to_attack|Path]).


%Bribe_accusation
testGameStateKillQuest2(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	write(test7),
	getCharacterFromList(AllCharacter,_,GuardChar),
	check_character(GuardChar,name,GuardName),
	\+member(attribute(GuardName,just),AllAttribute),
	write(test8),
	killQuest_Bribe_Accusation(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),GuardName,Path)


	
	
	
	
%%%% 
%%%% 
%%%% %%%% This is all combination of RESOLVE
%%%% 
%%%% 
killQuest_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	killQuest_Resolve_loop(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path),
	testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).

	
	
%%%% %%%% %%%% %%%% %%%% %%%% %%%% %%%% %%%% %%%% 
%%%% 
%%%% 
%%%% %%%% RESOLVE should read AllRelation and see what current relation is 'pending'
%
% AssassinContract
% Arrest_attempt for people with 'crime' relation
% character seek healing (if poison)
% character seek redemption (if crime)
% character seek crime-immunity (if crime+criminal gank)
% Fire from faction (state, guide, etc) if condition nolonger met (crime for state, weak for warrior guide)
% Other quest specific condition (pre-determined)
% etc....

%This_one_AssassinContract
killQuest_Resolve_loop(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	killQuest_Hire_to_Attack_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path),
	
	
	killQuest_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
	
	
	
killQuest_Hire_to_Attack_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	direct_attack_record(AllCharacter,AssassinChar,TargetChar,AllCharacterNew),
	write(hire_to_attack_record), 
	nl,
	add_crime(AllRelation,murder,AssassinChar,AllRelationNew),
	write(hire_to_attack_addcrime), 
	nl,
	testGameStateKillQuest(AllItem,AllCharacterNew,AllRelationNew,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
	
	
	
	
	
	
%%%% %%%% %%%% %%%% %%%% %%%% %%%% %%%% %%%% %%%% 	
	
	
	
	
	
	
	
	
	
	
%%%% 
%%%% 
%%%%  Direct_Attack(Player,Target)
%%%%  Actor must be "Alive" , not "weak"
%%%%  Actor must be at same location of target
%%%%  
%%%%  EFFECT  %%%%
%%%%  Target Die
%%%%  Target friend will "Hate" Player
%%%%  Target get relationship(StateName,crime_major) 
%%%%  

killQuest_Direct_Attack(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	write(direct_Attack),
	nl,
	getCharacterFromList(AllCharacter,player,PlayerChar),
	getCharacterFromList(AllCharacter,TargetName,TargetChar),
	write(PlayerChar),
	nl,
	write(TargetChar),
	nl,	
	write(direct_attack_record),
	nl,
	direct_attack_record(AllCharacter,PlayerChar,TargetChar,AllCharacterNew),
	write(direct_attack_record),
	nl,
	write(AllCharacterNew),
	nl,
	add_crime(AllRelation,murder,PlayerChar,AllRelationNew),
	write(direct_attack_add_crime),
	nl,
	write(AllRelationNew),
	nl,	
	killQuest_Resolve(AllItem,AllCharacterNew,AllRelationNew,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
	
	
%%%%  Hire_to_attack(Player,Actor,Target)
%%%%  Actor must be "Alive" , not "weak"
%%%%  Actor must not be "friend" with Target
%%%%  Player must be able to "pay" Actor
%%%%  Actor must be able to reach Target
%%%%  
%%%%  EFFECT  %%%%
%%%%  Target die
%%%%  Target friend will "Hate" Target
%%%%  
%%%%  
killQuest_Hire_to_Attack(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),AssassinName,Path) :-
	getCharacterFromList(AllCharacter,player,PlayerChar),
	getCharacterFromList(AllCharacter,AssassinName,AssassinChar),
	getCharacterFromList(AllCharacter,TargetName,TargetChar),
	write(PlayerChar), 
	nl,
	write(AssassinChar), 
	nl,
	write(TargetChar), 
	nl,
	killQuest_Hire_to_Attack_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path,PlayerChar,AssassinChar,TargetChar).

	
%% SUB 
%% RESOLVE HIRE TO ATTACK
%%
killQuest_Hire_to_Attack_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path,PlayerChar,AssassinChar,TargetChar) :-
	direct_attack_record(AllCharacter,AssassinChar,TargetChar,AllCharacterNew),
	write(hire_to_attack_record), 
	nl,
	add_crime(AllRelation,murder,AssassinChar,AllRelationNew),
	write(hire_to_attack_addcrime), 
	nl,
	killQuest_Resolve(AllItem,AllCharacterNew,AllRelationNew,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
	
	
%%%%  Bribe_accusation(Soldier,Target)
%%%%  Soldier must be "Alive" , not "weak"
%%%%  Soldier must not be "friend" with Target
%%%%  Soldier must not be "rightous"
%%%%  Soldier must have occupation "Soldier"
%%%%
%%%%  EFFECT  %%%%
%%%%  Target will have relationship(StateName,crime_major) 
%%%%  f
%%%%  f
%%%%  f
killQuest_Bribe_Accusation(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),CorruptGuardName,Path) :-
	getCharacterFromList(AllCharacter,player,PlayerChar),
	getCharacterFromList(AllCharacter,CorruptGuardName,CorruptGuardChar),
	getCharacterFromList(AllCharacter,TargetName,TargetChar),
	killQuest_Bribe_Accusation_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path,PlayerChar,CorruptGuardChar,TargetChar).

%% SUB 
%% RESOLVE Accusation
%%
	killQuest_Bribe_Accusation_Resolve(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path,PlayerChar,CorruptGuardChar,TargetChar) :-
	%
	%
	%
	%
	testGameStateKillQuest(AllItem,AllCharacterNew,AllRelationNew,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
		
	
	
	
%%%%  Bribe_redemption(Soldier,Target)
%%%%  Soldier must be "Alive" , not "weak"
%%%%  Soldier must not be "enemy" with Target
%%%%  Soldier must not be "rightous"
%%%%  Soldier must have occupation "Soldier"
%%%%  
%%%%  EFFECT  %%%%
%%%%  Target will lose relationship(StateName,crime_minor) 
%%%%  Target will lose relationship(StateName,crime_major) 
%%%%  f
%%%%  f
	

	
%%%%  arrest(Soldier,Target)
%%%%  Soldier must be "Alive" , not "weak"
%%%%  Soldier must not be "friend" with Target (unless "rightous")
%%%%  Soldier must have occupation "Soldier"
%%%%  Target must have relationship(StateName,crime_minor) 
%%%%  
%%%%  EFFECT  %%%%
%%%%  f
%%%%  f
%%%%  f
%%%%  f

%%%%  execute(Soldier,Target)
%%%%  Soldier must be "Alive" , not "weak"
%%%%  Soldier must not be "friend" with Target (unless "rightous")
%%%%  Soldier must have occupation "Soldier"
%%%%  Target must have relationship(StateName,crime_major) 
%%%%  
%%%%  EFFECT  %%%%
%%%%  f
%%%%  f
%%%%  f
%%%%  f	

	

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%





startQuest(AtomAction1State,AtomItem1) :-
testWholeAtomQuest([AtomAction1State,AtomItem1]).


%%%%[AtomAction1State,AtomItem1,AtomAction2State,AtomItem2,.....]
testWholeAtomQuest([]).
testWholeAtomQuest([AtomAction1State,AtomItem1,AtomAction2State,AtomItem2|RestOfAtomList]):-
	compareAtomState(AtomAction1State,AtomAction2State),
	belongToSameItemType(AtomItem1,AtomItem2),
	testWholeAtomQuest(RestOfAtomList]).





%% ALL atomState must be in similar order %%
compareAtomState([],[]).
compareAtomState([State1|T1],[State2,T2]) :- 
	State1 = State2,
	compareAtomState([T1],[T2]).


%% PASS TEST %%
belongToSameItemType(AtomItem1,AtomItem2) :-
item(X,AtomItem1),
item(X,AtomItem2).







%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Type of Item  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%Provision
itemType(provision,food).
itemType(provision,water).

%Equipment
itemType(equipment,weapon).
itemType(equipment,armor).

%Medicine
itemType(medicine,potion).
itemType(medicine,bondage).
itemType(medicine,alcohol).
itemType(medicine,tonic).

o
%Luxury
itemType(luxury,jewel).
itemType(luxury,gem).
itemType(luxury,gold).
itemType(luxury,fancyWare).


%Information
itemType(information,book).
itemType(information,letter).
itemType(information,note).
itemType(information,document).
itemType(information,tape).

%%% item
%%% 
%%%
item(provision,food).
item(provision,water).
item(provision,food).

%Equipment
item(equipment,weapon).
item(equipment,armor).

%Medicine
item(medicine,potion).
item(medicine,bondage).
item(medicine,alcohol).
item(medicine,tonic).

o
%Luxury
item(luxury,jewel).
item(luxury,gem).
item(luxury,gold).
item(luxury,fancyWare).


%Information
item(information,book).
item(information,letter).
item(information,note).
item(information,document).
item(information,tape).



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



%%%

friendcheck(X,Y) :- friend(X,Y).
friendcheck(Y,X) :- friend(X,Y).

relationshipCheck(X,Y,Z) :- relationship(X,Y,Z).
relationshipCheck(Y,X,Z) :- relationship(X,Y,Z).



%%% RELATIONSHIP
%%% If relationship not exist; == neutral / (not know each other?)
%%%
relationship(jack,john,friend).
relationship(jack,john,friend).
relationship(jack,john,friend).
relationship(jack,john,friend).


%%% CHARACTER
%%% 
%%%

%**
character(player,1,alive,strong,market).
%**

character(jack,15,alive,weak,market).
character(john,15,alive,healthy,market).
character(jill,15,alive,healthy,market).
character(bob,20,alive,healthy,capital).

% Mob character
character(soldier1,20,alive,healthy,capital).
character(soldier2,20,alive,healthy,capital).
character(soldier3,20,alive,healthy,capital).
character(soldier4,20,alive,healthy,capital).

character(doctor1,20,alive,healthy,capital).

character(merchant1,20,alive,healthy,capital).

character(blacksmith1,20,alive,healthy,capital).

character(thief1,20,alive,healthy,capital).

character(messenger1,20,alive,healthy,capital).

character(scout1,20,alive,healthy,capital).

character(farmer1,20,alive,healthy,capital).

character(miner1,20,alive,healthy,capital).

character(lumberjack1,20,alive,healthy,capital).




%%% ABILITY
%%% 
%%%
ability(jack,heal).
ability(jill,heal).
ability(john,heal).
ability(bob,heal).

ability(doctor1,heal).

ability(merchant1,analysis).

ability(blacksmith1,craft).

ability(thief1,pickpocket).

ability(scout1,scout).

ability(farmer1,farm).

ability(miner1,mine).

ability(lumberjack1,lumberjack).



%%% OCCUPATION / FACTION
%%% 
%%%
Occupation(jack,farmer).



%%% Attribute
%%% 
%%%
attribute(player,player).
attribute(jack,weak).






healer(jack).
healer(jill).


technician(bob).


happy(jack).
happy(jack,16).
happy(jack,16,alive).
happy(jack,16,alive,healthy).


location(capital).
location(market).
location(farm).
location(river).
location(dungeon).
location(hill).

passkey(capital,dungeon,dungeonkey).

item(gun,jack,1).
item(potion,jack,5).
item(carrot,john,10).
item(poison,john,1).


friend(jack,jill).
friend(john,bob).


%%%%%% LIST OF RULE %%%%%%%%%

actorRule(jack).
actorRule(john).
actorRule(jill).
actorRule(bob).


targetRule(jack).
targetRule(john).
targetRule(jill).
targetRule(bob).


goalRule().



%%% kill quest
%[Actor, target]

% attack
% poison
% trap
% fire smoke
% Motor Vehicle Accident
% heart attack
%


%%% report quest
%[Actor, target, info]

% write message
% direct report
% 
% 
% 


%%%  quest
%[Actor, target, info]

% write message
% direct report
% 
% 



%%% escort quest
%[Actor, target, targetlocation]

% move
% teleport
% transportation
% 
% 

father_of(paul, peter).
father_of(john, paul).
father_of(john, ringo).
father_of(john, george).






















