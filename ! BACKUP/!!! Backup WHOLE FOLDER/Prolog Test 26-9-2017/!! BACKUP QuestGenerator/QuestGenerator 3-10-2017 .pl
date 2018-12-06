


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
getCharacterFromList([ ],Name,Character).
getCharacterFromList([H|T],Name,Character) :-
	character(Name,A,B,C,D),
    H = character(Name,A,B,C,D),
    getCharacterFromList([T],Name,Character).	

getCharacter(Name,Char) :-
	character(Name,A,B,C,D),
	Char = character(Name,A,B,C,D).
	
	
%** remove old, append new
direct_attack_record(ListOld,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),character(ActorName,ActorLevel,ActorStatus,ActorAttribute,ActorLocation),ListNew2) :-

	character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),
	delete(ListOld,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew),   
	append(ListNew,[character(TargetName,TargetLevel,dead,TargetAttribute,TargetLocation)], ListNew2),
	write(ListOld),
	nl,
	nl,
	write(ListNew2).
	
add_crime(ListRelation,TypeOfCrime,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew2) :-
	(TypeOfCrime == steal  -> Crime = minor_crime
	;TypeOfCrime == murder -> Crime = major_crime
	),
	append(ListRelation,[relationship(nation,TargetName,Crime)], ListNew2).


check_character(character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),A,B) :-
	(A == name  -> B = TargetName
	;A == level -> B = TargetLevel
	;A == status -> B = TargetStatus
	;A == attribute -> B = TargetAttribute
	;A == location -> B = TargetLocation
	).
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Input Include Game State for rollback  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%testQuestGameState(kill,character(jack,15,alive,weak,market),test).


% TYPE OF GOAL INPUT
% Kill == Goal = character(a,b,c,d,e)				= ATOM = that is the target must be dead
% Heal == Goal = character(a,b,c,d,e) 				= ATOM = that is the target must be heal
% Give == Goal = [character(a,b,c,d,e),Item]		= LIST = Item must be given to character


testQuestGameState(TypeOfQuest,Goal,Path) :-
	gameStateItem(AllItem),
	gameStateCharacter(AllCharacter),
	gameStateRelation(AllRelation),
	gameStateAttribute(AllAttribute),
	(TypeOfQuest == kill -> member(Goal,AllCharacter),
							testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path)
	;TypeOfQuest == heal -> member(Goal,AllCharacter),
							testGameStateHeal(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path)
	;TypeOfQuest == give -> getGoalGiveTestQuest(Goal,character(A,B,C,D,E),Item),
							member(character(A,B,C,D,E),AllCharacter),
							member(Item,AllItem),
							testGameStateGiveQuest(AllItem,AllCharacter,AllRelation,AllAttribute,Goal,Path)
	).




%%%% Kill == Goal = character(a,b,c,d,e) = ATOM = that is the target must be dead

%%%% BASE PREDICATE, TRUE WHEN QUEST GOAL STATE REACH
testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,character(Name,Level,Status,Attribute,Location),Path) :-
	getCharacterFromList(TargAllCharacter,etName,TargetChar),
	check_character(TargetChar,status,TargetStatus),
	TargetStatus = dead,
	write(Path).
	
	
%%%% THESE FOLLOWING PREDICATE WILL CHECK FOR CONDITION OF ACTION, BEFORE TRIGGER ACTION
%Direct_Attack
testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	getCharacter(player,PlayerChar),
	PlayerChar(PlayerName,PlayerLevel,PlayerStatus,PlayerAttribute,PlayerLocation),
	member(attribute(PlayerName,weak),AllAttribute),
	KillQuest_Direct_Attack(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),[direct_attack|Path]).

	
%Hire_to_Attack
testGameStateKillQuest(AllItem,AllCharacter,AllRelation,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	getCharacter(Assassin,AssassinChar),
	AssassinChar(AssassinName,AssassinLevel,AssassinStatus,AssassinAttribute,AssassinLocation),
	member(attribute(AssassinName,weak),AllAttribute),
	KillQuest_Hire_to_Attack(AllItem,AllCharacter,AllRelation,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),[hire_to_attack|Path]).






%%%%  Direct_Attack(Player,Target)
%%%%  Actor must be "Alive" , not "weak"
%%%%  Actor must be at same location of target
%%%%  
%%%%  EFFECT  %%%%
%%%%  Target Die
%%%%  Target friend will "Hate" Player
%%%%  Target get relationship(StateName,crime_major) 
%%%%  

KillQuest_Direct_Attack(AllItem,AllCharacter,AllRelation,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	getCharacter(player,PlayerChar),
	PlayerChar(PlayerName,PlayerLevel,PlayerStatus,PlayerAttribute,PlayerLocation),
	getCharacter(TargetName,TargetChar),
	TargetChar(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),
	direct_attack_record(AllCharacter,PlayerChar,TargetChar,AllCharacterNew),
	add_crime(AllRelation,murder,PlayerChar,AllRelationNew)
	testGameStateKillQuest(AllItem,AllCharacter,AllRelationNew,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
	

	
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
KillQuest_Hire_to_Attack(AllItem,AllCharacter,AllRelation,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path) :-
	getCharacter(player,PlayerChar),
	PlayerChar(PlayerName,PlayerLevel,PlayerStatus,PlayerAttribute,PlayerLocation),
	getCharacter(TargetName,TargetChar),
	TargetChar(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),
	KillQuest_Hire_to_Attack_Resolve().

	
%% SUB 
%% RESOLVE HIRE TO ATTACK
%%
%%
%%
KillQuest_Hire_to_Attack_Resolve() :-
	direct_attack_record(AllCharacter,PlayerChar,TargetChar,AllCharacterNew),
	add_crime(AllRelation,murder,PlayerChar,AllRelationNew)
	testGameStateKillQuest(AllItem,AllCharacter,AllRelationNew,AllAttribute,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),Path).
	
	
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
TestWholeAtomQuest([AtomAction1State,AtomItem1]).


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






















