

testwrite(character(KillerName,KillerLvl,KillerStatus,KillerHealth)) :- write(KillerLvl).

testwrite11(Name) :- 	write(Name),
						character(Name,X,Y,Z),
						write(X).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
						
						
						
check(Name) :- 	character(Name,X,Y,Z),
				write(Name),
				write(","),
				write(X),
				write(","),
				write(Y),
				write(","),
				write(Z).

attack(KillerName,TargetName) :- 	
	write(KillerName),write(" kill "),write(TargetName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth),
	TargetStatus == alive,
	KillerHealth == healthy,
	assert(character(TargetName,TargetLvl,dead,TargetHealth)),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth)).




heal(Healer,TargetName) :- 
	healer(HealerName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth),
	assert(character(TargetName,TargetLvl,TargetStatus,healthy)),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth)).	

poisoneasy(KillerName,TargetName) :-
	write(KillerName),write(" poison "),write(TargetName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth),
	TargetStatus == alive,
	assert(character(TargetName,TargetLvl,TargetStatus,poison)),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth)).
	
		
sickdiecheck(TargetName) :- 
	character(TargetName,TargetLvl,TargetStatus,TargetHealth),
	TargetStatus == poison,
	assert(character(TargetName,TargetLvl,dead,TargetHealth)),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth)).

	
getnumber(item(X)) :- write(X).
getnumbername(item(Owner,X)) :- write(Owner),write(X).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	
trykill(KillerName,TargetName,[Solution])	:-
	character(TargetName,TargetLvl,dead,TargetHealth),
	trykill(KillerName,TargetName,[Solution]).
	
	
	
	
	
testdead(_,dead).

testdead(KillerName,TargetName,TargetStatus) :-
	attack(KillerName,TargetName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth),
	testdead(TargetName,TargetStatus).
	
testdead(KillerName,TargetName,TargetStatus) :-	
	poisoneasy(KillerName,TargetName),
	sickdiecheck(TargetName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth),
	testdead(TargetName,TargetStatus).	
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
%Result of 
%testdead(jack,john,X).
%
%
%jack kill johnjack poison john
%false.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


healer(jack).

character(jack,15,alive,weak).
character(john,15,alive,healthy).

happy(jack).
happy(jack,16).
happy(jack,16,alive).
happy(jack,16,alive,healthy).














