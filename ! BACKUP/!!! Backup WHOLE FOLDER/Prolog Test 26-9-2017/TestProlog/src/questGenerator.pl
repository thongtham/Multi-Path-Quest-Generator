


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


item(gun,jack,1).
item(potion,jack,5).
item(carrot,john,10).
item(poison,john,1).






friendcheck(X,Y) :- friend(X,Y).
friendcheck(Y,X) :- friend(X,Y).



character(jack,15,alive,weak,market).
character(john,15,alive,healthy,market).
character(jill,15,alive,healthy,market).
character(bob,20,alive,healthy,capital).


healer(jack).
healer(jill).

technician(bob).

friend(john,bob).



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


testQuest(TypeOfQuest,Actor,Target,Goal) :-

	character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
	character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),

	(TypeOfQuest==kill -> testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
											character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
											Goal,
											Path)
	;TypeOfQuest==heal -> testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
											character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
											Goal,
											Path)
	;TypeOfQuest==give -> testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
											character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
											Goal,
											Path)
	).

testQuestKill(character(Actor,ActorLVL,ActorStatus,ActorHealth,ActorLocation),
				character(Target,TargetLVL,TargetStatus,TargetHealth,TargetLocation),
				Goal,
				Path) :-
	ActorHealth == healthy,
	Goal == dead,
	write("dead").
	
	
	
	
	
%%%%%%testQuest(kill,john,jill,d)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		
	
testString(X) :-
	X == abc.
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


item(gun,jack,1).
item(potion,jack,5).
item(carrot,john,10).
item(poison,john,1).






friendcheck(X,Y) :- friend(X,Y).
friendcheck(Y,X) :- friend(X,Y).



character(jack,15,alive,weak,market).
character(john,15,alive,healthy,market).
character(jill,15,alive,healthy,market).
character(bob,20,alive,healthy,capital).


healer(jack).
healer(jill).

technician(bob).

friend(john,bob).



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






















