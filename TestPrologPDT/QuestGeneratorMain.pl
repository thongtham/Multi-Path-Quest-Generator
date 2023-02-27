
% *** LEGEND ***

%COMPONENT		= CO  //DELETED 
%GoalCharacter	= GC
%GoalLocation	= GL
%AllCharacter	= AC
%AllLocation	= AL
%AllRelation	= AR
%LeftAction		= LA
%Path			= P
%PathFinal		= PF

%LoopCounter 	= LC


% *** CODE STRUCTURE ***

%	1st = main loop that check if quest goal is met
%
%	2nd = the loop of NPC react to player
%
%	3rd = all methods / possible action of player (called by 1st)
%
%	4th = the loop of NPC react to player 
%
%	5th = all methods / possible action of character (if certain condition met, activate it),  (called by 4th)
%
%
%
%

%'c:/Users/user/Desktop/Prolog Test/
%:- consult(Utility).


:- reconsult('Utility.pl').
:- dynamic counter/1.
:- dynamic pathExist/1.
:- dynamic conditionExist/1.
:- dynamic pathExist_ac/1.



% GA here = all Goal condition that isn't seperated yet.
startQuestPath(GA,AC,AR,LA,P,PF)
:-
	retractall(pathExist(ANYTHING)),
	retractall(conditionExist(ANYTHING)),
	retractall(counter(ANYTHING)),
	retractall(pathExist_ac(ANYTHING)),
	assert(counter(0)),
	\+pathExist(ANYTHING),
	seperateGoalType(AC,GA,GC,GL),!,
	startQuestPath(GC,GL,AC,AR,LA,P,PF).


%BASE CASE >>>> If correct from start = quest is done when initiated
startQuestPath(GC,GL,AC,AR,LA,P,PF)
:-
	memberlist(GC,AC),
	checkSpecialCondition(GL,AC),
	append(P,[start_new_component],P2),
	append(P2,[no_action_need],P3),!,
	writeToFileFirst(GC,GL,AC,AR,LA,P3,0,PF),!.
	%PF = P.


startQuestPath(GC,GL,AC,AR,LA,P,PF)
:-
	LoopCounter = 1,
	append(P,[start_new_component],P2),
	questPathMainLoop(GC,GL,AC,AR,LA,P2,LoopCounter,PF).
	%PF = P2.
	
startQuestPath(GC,GL,AC,AR,LA,P,PF)
:-
	pathExist(ANYTHING),
	PF = 'found'.	
	
	
	
	
	
	
	
	
%Main loop to check if after 1 action and follow by resolve, the quest 
%goal is reached yet or not.	
questPathMainLoop(GC,GL,AC,AR,LA,P,LC,PF)	 
:-
	( 	questPathMainLoop_Done(GC,GL,AC,AR,LA,P,LC,PF) -> writeToFile(GC,GL,AC,AR,LA,P,LC,PF)
	;	questPathMainLoop_Continue(GC,GL,AC,AR,LA,P,LC,PF)
	).


%FOR DEBUG TO SPECIFIC PATH	
%questPathMainLoop_Done(GC,GL,AC,AR,LA,P,LC,PF)
%:-	
%	P == [c,sa1,start_new_component,no_action_need,start_new_component,move_from_to,city,jail,player,capture,thief1,player,criminal_lockpick_escape_jail,thief1,hire_to_attack,player,thief1,soldier1,char_die,soldier1],
%	getCharIsAliveFromList(AC,player,true),
%	fail.
	
	
questPathMainLoop_Done(GC,GL,AC,AR,LA,P,LC,PF) 
:-
	%memberlist(GC,AC),  // Nolonger working, obsolete
	%memberlist(GL,AL),  // Nolonger working, obsolete
	
	getCharIsAliveFromList(AC,player,true),
	memberlist(GC,AC),
	checkSpecialCondition(GL,AC),
	
	(	%If there already exist complete path
	pathExist(ANYTHING)
	->
		\+checkSubList(P),
		\+checkSubAction(P),
		\+checkSubListFront(P),
		\+checkSubListBack(P)
	;	%Else if no path exist, this is the first path
		true
	).



checkSubAction(P)
:-
	pathExist_ac(OLD_P_ac),
	seperateActionPath(P,P_ac),
	(
	
	sublist(OLD_P_ac,P_ac) -> true
	;
	sublist(P_ac,OLD_P_ac) -> true
	).

checkSubListFront(P)
:-
	seperateHalfFront(P,P_FR),
	pathExist(OLD_P),
	(
	
	sublist(OLD_P,P_FR) -> true
	;
	sublist(P_FR,OLD_P) -> true
	).
	
checkSubListBack(P)
:-
	seperateHalfBack(P,P_BA),
	pathExist(OLD_P),
	(
	
	sublist(OLD_P,P_BA) -> true
	;
	sublist(P_BA,OLD_P) -> true
	).







checkSubList(Path)
:-
	pathExist(OLD_PATH),
	(
	sublist(OLD_PATH,Path) -> true
	;
	sublist(Path,OLD_PATH) -> true
	).
	
	
%	(
%	conditionExist(ANY),
%	length(ANY,ExistSize),
%	length(AC,ExistSize2),
%	ExistSize = ExistSize2,
%	memberlist(AC,ANY),
%	memberlist(ANY,AC)
%	->
%		fail
%	;
%	memberlist(GC,AC),
%	checkSpecialCondition(GL,AC)
%	).


	

questPathMainLoop_Continue(GC,GL,AC,AR,LA,P,LC,PF)
:-
	LC < 4, % player should do less than LC's number action to pass a component of quest, this also exist to force backtrack and make the Prolog try new action more than infinity [attack] loop
	
	%Before doing anything, make sure that player is alive
	getCharIsAliveFromList(AC,player,true),
	
	% Loop possible player action (player can only act 1 time, then wait until all NPC react to it and react to other NPC react too)
	questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2),
	
	% Loop resolve
	%Count = 1, % This is to count number of reaction, to avoid forever reaction loop
	questPathMainLoop_Resolve(1,ACReturn,ARReturn,LAReturn,P2,LC2,ACRS,ARRS,LA3,P3,LC3),
	
	% set back to main loop
	questPathMainLoop(GC,GL,ACRS,ARRS,LA3,P3,LC3,PF).

%
%
%questPathMainLoop_Resolve(ACReturn,ARReturn,LAReturn,P2,LC2,ACRS,ARRS,LA3,P3,LC3)
%:-
%	P2 == [c,sa1,start_new_component,no_action_need,start_new_component,move_from_to,city,palace,player],
%	getCharIsAliveFromList(AC,player,true),
%	fail.


questPathMainLoop_Resolve(Count,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	%If it's possible to resolve, loop until no longer possible to resolve
	(
	resolving(AC,AR,LA,P,LC,AC2,AR2,LA2,P2,LC2) -> 
		questPathMainLoop_React(Count,AC2,AR2,LA2,P2,LC2,ACRS,ARRS,LARS,PRS,LCRS)
	;
	ACRS = AC,
	ARRS = AR,
	LARS = LA,
	PRS = P,
	LCRS = LC
	).
	
	
	
questPathMainLoop_React(Count,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	Count2 = (Count+1),
	%If it's possible to resolve, loop until no longer possible to resolve
	(	
	Count2 =< 10,	% Reaction must not loop forever, if after 10 still loop, cut it and pretend that player do another action while NPC reacting chain is going on.
	reacting(AC,AR,LA,P,LC,AC1,AR1,LA1,P1,LC1) 
	-> questPathMainLoop_React(Count2,AC1,AR1,LA1,P1,LC1,ACRS,ARRS,LARS,PRS,LCRS)
	;
	questPathMainLoop_Resolve(Count2,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
	).







% START of Possible player action
% --------------------------------------------------------------------------------------------------------------
%




% Goto, can only goto adjecent location
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,PRE,LCRE)
:-
	%player must not be captured
	\+existCharListStatusFromList(AC,player,captured),
	
	%can only goto adjecent location
	getCharCurrentLocationFromList(AC,player,FROM),
	existLocationListConnectLocationList(AC,FROM,DesLo),

	%Must not go back to where the player just came from (if react happen cause player move, the latest element in 'path' won't be 'move_from_to')
	(
	%secondLast([......,move_from_to,From,DesLo,player],X).	EXAMPLE
	thirdLast(P,Previous_From),
	Previous_From = DesLo,
	forthLast(P,Previous_Action),
	Previous_Action = move_from_to
	->
		fail	
	;

	append([relationship(goto,player,FROM,DesLo)],LA,LAReturn),
	
	ACReturn = AC,
	ARReturn = AR,
	PRE = P,
	LCRE is LC+1
	).
	



%Direct Attack
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	getCharCurrentLocationFromList(AC,player,LO), 
	getCharCurrentLocationFromList(AC,TarName,LO),
	
	TarName \= player, %player shouldn't be able to self-harm
	getCharIsAliveFromList(AC,TarName,true),	% Target must be alive
	

%	getCharNameFromList(AC,_,TarName), %select target (all character)
%	%getCharNameFromList(AC,_,TarName2), % ?????? Why this don't get a char, but the above does???
%
%	%TarName \= TarName2,
%	TarName \= player, %player shouldn't be able to self-harm
%	
%	getCharIsAliveFromList(AC,TarName,true),	% Target must be alive
%	
%	%Check if same place
%	getCharCurrentLocationFromList(AC,player,LO), 
%	getCharCurrentLocationFromList(AC,TarName,LO),

	append([relationship(direct_attack,player,TarName)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.

	
	
%Hire to Attack
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action

%	getACharName(AC,TarName), %select target (all character)
	getACharName(AC,Hitman),  %select attacker (all character)  
%	TarName \= Hitman,
	Hitman \= player,
	
	getCharIsAliveFromList(AC,Hitman,true),	% Hitman must be alive
	
	% Hitman must not be 'rich' or hole high position in society
	\+existCharListStatusFromList(AC,Hitman,rich),
	\+existCharListOccupationFromList(AC,Hitman,noble), 
	
	%Check if player and Hitman at sameplace
	getCharCurrentLocationFromList(AC,player,LO), 
	getCharCurrentLocationFromList(AC,Hitman,LO),
	
	getACharName(AC,TarName),
	TarName \= Hitman,
	
	getCharIsAliveFromList(AC,TarName,true), 	% Target must be alive
	
	% Both tarket and hitman must NOT be friend
	\+existRelationship(AR,friend,TarName,Hitman), 
	
	append([relationship(hire_to_attack,player,Hitman,TarName)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.
	
	
% Pickup / gather item from location (non holded by NPC)
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	
	
	%--------- START:*** Select random item on ground from player location *********-------------
	
	%getCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name,B).
	getCharCurrentLocationFromList(AC,player,PLYLO),
	%existItemInLocationWithName([[L,A,B,itemName,NAME,Z]|T],L,NAME).
	existItemInLocationWithID(AC,PLYLO,ItemID),
	%getItemIsOnGroundUsingID([[ANY,A,Name,isOnGround,D,Z]|T],Z,D).
	getItemIsOnGroundUsingID(AC,ItemID,true),

	%--------- END:***** Select random item on ground from player location *********-------------

	append([relationship(pickup,ItemID,player,PLYLO)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.

	% CURRENT RELATIONSHIP
	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
	


	
%(Simplify complex chain action in react to this) poisoned food = just give the target poisoned (stealth action)	
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	%Does player have the poisoned?
	
	existItemListPropertyUsingID(AC,ItemID,poisoned),
	getItemCurrentHOLDERUsingID(AC,ItemID,player),
	
	getCharNameFromList(AC,_,TarName), %select target (all character)
	
	%Target must not be already poisoneded
	\+existCharListStatusFromList(AC,TarName,poisoned),
	
	%Check if same place
	getCharCurrentLocationFromList(AC,player,LO), 
	getCharCurrentLocationFromList(AC,TarName,LO),


	% 2nd call the proper related action
	append([relationship(poisoned,TarName,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.

	% CURRENT RELATIONSHIP
	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),


% Take (from corpse)
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	getCharCurrentLocationFromList(AC,player,PLYLO), 
	getCharCurrentLocationFromList(AC,TarName,PLYLO),
	TarName \= player,
	getCharIsAliveFromList(AC,TarName,false),
	
	%See the item in that 'character' inventory
	getItemCurrentHOLDERUsingID(AC,ItemID,TarName),
	
	append([relationship(take_corpse,ItemID,player,TarName)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.


% damage item
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	getItemCurrentHOLDERUsingID(AC,ItemID,player),
	\+existItemListPropertyUsingID(AC,ItemID,damaged),
	
	append([relationship(damage,ItemID,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.

% damage NPC
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	getCharCurrentLocationFromList(AC,player,LO), 
	getCharCurrentLocationFromList(AC,TarName,LO),
	\+existCharListStatusFromList(AC,TarName,damaged),
	
	
	append([relationship(damage,TarName,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.


% Give
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	getCharCurrentLocationFromList(AC,player,PLYLO), 
	getCharCurrentLocationFromList(AC,TarName,PLYLO),
	TarName \= player,
	getCharIsAliveFromList(AC,TarName,true),
	
	%See the item in that 'character' inventory
	getItemCurrentHOLDERUsingID(AC,ItemID,player),
	
	append([relationship(give_item,ItemID,TarName,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.




% Capture 
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	% 2nd call the proper related action

	getCharCurrentLocationFromList(AC,player,LO), 
	getCharCurrentLocationFromList(AC,Target,LO),
	Target \= player,
	
	getCharIsAliveFromList(AC,Target,true),	% Target must be alive
	
	% Target must not be 'rich' or hole high position in society
	\+existCharListStatusFromList(AC,Target,rich),
	\+existCharListOccupationFromList(AC,Target,noble), 
	
	
	append([relationship(capture,player,Target)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.


% Free
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	% 2nd call the proper related action

	getCharCurrentLocationFromList(AC,player,LO), 
	getCharCurrentLocationFromList(AC,Target,LO),
	Target \= player,
	
	\+existCharListStatusFromList(AC,Target,captured), %Target must be captured
	getCharIsAliveFromList(AC,Target,true),	% Target must be alive
	
	
	append([relationship(free,player,Target)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.







% Fix (item Name?)
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	getItemCurrentHOLDERUsingID(AC,ItemID,player),
	existItemListPropertyUsingID(AC,ItemID,damaged),
	
	append([relationship(fix,ItemID,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.





% Bribe (add crime)
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	getCharCurrentLocationFromList(AC,player,city), % Player must be at city
	
	getACharName(AC,TarName),								% Get Target
	existCharListStatusFromList(AC,TarName,crime),			% Target Must not already have crime
	\+existCharListOccupationFromList(AC,TarName,noble), 	% Target must not be noble
	getCharIsAliveFromList(AC,TarName,true), 				% Target must be alive
	
	
	append([relationship(bribe_add_crime,TarName,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.
	
	

% Bribe (remove crime)
questPathMainLoop_PlayerAction(AC,AR,LA,P,LC,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	getCharCurrentLocationFromList(AC,player,city), % Player must be at city
	
	existCharListStatusFromList(AC,TarName,crime),			% Get Target
	\+existCharListOccupationFromList(AC,TarName,noble), 	% Target must not be noble
	getCharIsAliveFromList(AC,TarName,true), 				% Target must be alive
	
	
	append([relationship(bribe_remove_crime,TarName,player)],LA,LAReturn),
	ACReturn = AC,
	ARReturn = AR,
	P2 = P,
	LC2 is LC+1.



%---------- STEALTH ACTION ---------------------------
% Pickpocket (is stealth)
% Putpocket (reverse-pickpocket, is stealth)

% Sneak attack ()

% Sneak 

%-----------------------------------------------------


% SHOULD DO SPELL INTERACTION TOO, ex, water spell stop fire????
% EACH HAS 2 VERSION, target self or target other
% Spell (heal)
% Spell (fire)

% SHOULD DO SPELL INTERACTION TOO, ex, water spell stop fire????
% EACH HAS 2 VERSION, target self or target other
% Use item(potion)
% Use item(poisoned)
% Use item(berry)

% Learn (Skill Name?)
% Craft/Build (item Name?)





% OTHER ACTION THAT IS NOT USED (too complexed, is simplified already in other parts)

% Equip Item

% 

	
	
	

% END of Possible player action
% --------------------------------------------------------------------------------------------------------------





% Start ACTUAL action by player
% --------------------------------------------------------------------------------------------------------------

% player_Direct_to_Attack(TEST)
% :-
%   	.
	
	
% END ACTUAL action by player
% --------------------------------------------------------------------------------------------------------------




% Start Resolve LOOP
% --------------------------------------------------------------------------------------------------------------

%GoalCharacter		= GC
%GoalLocation		= GL
%AllCharacter		= AC
%AllLocation		= AL
%AllRelation		= AR
%AllRelationHead 	= ARH
%AllRelationTail	= ART
%Path				= P
%LoopCounter 		= LC

%AllCharacterResolveReturn	= ACRS
%AllLocationResolveReturn	= ALRS
%AllRelationResolveReturn	= ARRS
%PathResolveReturn			= PRS
%LoopCounterResolveReturn 	= LCRS


%IF EMPTY	== no more to resolve
% 			Return false so it kick back to the loop

%resolving(AC,AR,[],P,LC,ACRS,ARRS,[],PRS,LCRS)
%:-
%	ACRS = AC,
%	ARRS = AR,
%	PRS = P,
%	LCRS = LC.

resolving(AC,AR,[relationship(direct_attack,AtkName,TarName)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_direct_attack],P2),
	append(P2,[AtkName],P3),
	append(P3,[TarName],P4),
	
	%EXAMPLE ON THE PATH 
	%[player,jack,direct_attack,quest_start] - before reverse
	%[quest_start,direct_attack,jack,player] - after reverse 
	
	action_attack(AC,AR,AtkName,TarName,P4,ACNew2,AR2,P5),


	ACRS = ACNew2,
	ARRS = AR2,
	PRS = P5,
	LCRS = LC.
	
resolving(AC,AR,[relationship(hire_to_attack,HirerName,Hitman,TarName)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_hire_to_attack],P2),
	append(P2,[HirerName],P3),
	append(P3,[Hitman],P4),
	append(P4,[TarName],P5),

	action_hire_to_attack(AC,AR,HirerName,Hitman,TarName,P5,ACNew2,AR2,P6),


	ACRS = ACNew2,
	ARRS = AR2,
	PRS = P6,
	LCRS = LC.
	

	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
	
	
%__________________________________________________________________________		

resolving(AC,AR,[relationship(pickup,ItmID,PICKER,PLYLO)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_pickup],P2),
	append(P2,[ItmID],P3),
	append(P3,[PICKER],P4),
	append(P4,[PLYLO],P5),   % [{what's pick}, {by who}, {at where}]
	
	action_pick_up(AC,AR,ItmID,PICKER,P5,ACRE,ARRE,PRE), %Coding

	ACRS = ACRE,
	ARRS = ARRE,
	PRS = PRE,
	LCRS = LC.


resolving(AC,AR,[relationship(take_corpse,ItemID,PICKER,TarName)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-

	getItemNameUsingID(AC,ItemID,ItemNAME),
	append(P,[ac_take_corpse],P2),
	append(P2,[ItemNAME],P3),
	append(P3,[TarName],P4),
	append(P4,[player],P5),  % [{what is taken},{from who},{to who}]
	
	
	action_pick_up(AC,AR,ItemID,PICKER,P5,ACRE,ARRE,PRE), %Coding

	ACRS = ACRE,
	ARRS = ARRE,
	PRS = PRE,
	LCRS = LC.
%__________________________________________________________________________		



resolving(AC,AR,[relationship(poisoned,TarName,Actor)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_poisoned],P2),
	append(P2,[TarName],P3),
	append(P3,[Actor],P4),  % [{what as poisoneded}, {by who}]
	
	action_poisoned(AC,TarName,Actor,ACRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = P4,
	LCRS = LC.
	



resolving(AC,AR,[relationship(goto,Actor,From,DesLo)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-

	append(P,[ac_move_from_to],P2),
	append(P2,[From],P3),
	append(P3,[DesLo],P4),
	append(P4,[Actor],P5), 

	action_moveChar(AC,P5,Actor,From,DesLo,ACRE,PRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = PRE,
	LCRS = LC.


resolving(AC,AR,[relationship(damage,ItemID,Owner)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	getItemNameUsingID(AC,ItemID,ItemNAME),
	append(P,[ac_damage_item],P2),
	append(P2,[ItemNAME],P3),
	
	action_damage(AC,P3,ItemID,ACRE,PRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = PRE,
	LCRS = LC.

	
resolving(AC,AR,[relationship(give_item,ItemID,Receiver,Giver)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	getItemNameUsingID(AC,ItemID,ItemNAME),
	append(P,[ac_give_item],P2),
	append(P2,[ItemNAME],P3),
	append(P3,[Receiver],P4),
	append(P4,[Giver],P5),
	
	action_give_item(AC,P5,ItemID,Receiver,Giver,ACRE,PRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = PRE,
	LCRS = LC.



resolving(AC,AR,[relationship(capture,Capturer,Target)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-

	append(P,[ac_capture],P2),
	append(P2,[Target],P3),
	append(P3,[Capturer],P4),

	action_capture(AC,AR,P4,Capturer,Target,ACRE,ARRE,PRE),

	ACRS = ACRE,
	ARRS = ARRE,
	PRS = PRE,
	LCRS = LC.


resolving(AC,AR,[relationship(free,Helper,Target)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_free],P2),
	append(P2,[Target],P3),
	append(P3,[Helper],P4),
		
	action_free(AC,AR,P4,Helper,Target,ACRE,ARRE,PRE),

	ACRS = ACRE,
	ARRS = ARRE,
	PRS = PRE,
	LCRS = LC.




resolving(AC,AR,[relationship(fix,ItemID,Fixer)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	getItemNameUsingID(AC,ItemID,ItemNAME),
	append(P,[ac_fix_item],P2),
	append(P2,[ItemNAME],P3),
	append(P3,[Fixer],P4),
	
	action_fix(AC,P4,ItemID,Fixer,ACRE,PRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = PRE,
	LCRS = LC.



%_____________________________________________________________________

resolving(AC,AR,[relationship(bribe_add_crime,TarName,Actor)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_bribe_add_crime],P2),
	append(P2,[TarName],P3),
	append(P3,[Actor],P4),
	
	action_bribe_add_crime(AC,P4,TarName,Actor,ACRE,PRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = PRE,
	LCRS = LC.

resolving(AC,AR,[relationship(bribe_remove_crime,TarName,Actor)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append(P,[ac_bribe_remove_crime],P2),
	append(P2,[TarName],P3),
	append(P3,[Actor],P4),
	
	action_bribe_remove_crime(AC,P4,TarName,Actor,ACRE,PRE),

	ACRS = ACRE,
	ARRS = AR,
	PRS = PRE,
	LCRS = LC.

%_____________________________________________________________________











% START reacting LOOP
% --------------------------------------------------------------------------------------------------------------


reacting(AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
%	getCharPairINI(AC,Pair_list),
	getCharList(AC,Single_list),
	reacting_Pair(Single_list,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS).


%Ada แนะนำให้เอา react ทั้งหมดมาใส่ใน react2 แท่น แล้วใข้ react(1) เอาไว้แค่เรียก pair character แทน
%ปัญหาคือ 
%
%	getCharNameFromList(AC,_,CharA), %any charA
%	getCharNameFromList(AC,_,CharB), %any charB
%
%จะได้แค่ตัวละครเดียวกันเท่านั้น เพราะ prolog จะจับคู่กับตัวละครที่เป็น variable เท่านั้น


% IF NO PAIR OR SINGLE LEFT, this one must fail, or it will loop forever

%reacting_Pair([],[],AC,AR,LA,P,LC,AC,AR,LA,P,LC)
%:-
%	!,fail.
%	fail,		% IF NO PAIR OR SINGLE LEFT, this one must fail, or it will loop forever
%	ACRE = AC,
%	ARRE = AR,
%	LARE = LA,
%	PRE = P,
%	LCRE = LC.
	



%Testing if it really get B as new char
%
%
%
%reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) 
%:-
%	getCharIsAliveFromList(AC,A,true), %The character must be alive
%	getCharIsAliveFromList(AC,B,true), %The character must be alive
%	A \== B,
%	fail.

	

%Friend cure Friend poisoned
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	%getCharNameFromList(AC,_,A), %any charA
	%getCharNameFromList(AC,_,B), %any charB
	
	existCharListStatusFromList(AC,A,poisoned), %is poisoned?
	
	existRelationship(AR,friend,A,B), 
	existCharListSkillFromList(AC,B,heal), %can char2 cure poisoned?
	
	getCharIsAliveFromList(AC,A,true), %The character must be alive
	getCharIsAliveFromList(AC,B,true), %The character must be alive
	
	
	delete(AC,[A,listStatus,poisoned,z,zz,zzz],ACRE),
	
	append(P,[friend_heal_poisoned],P2),
	append(P2,[A],P3),
	append(P3,[B],PRE),
	
	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.
	
%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).



%Soldier capture criminal at same place (unless noble or itself)
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) 
:-
	
	existCharListStatusFromList(AC,A,crime), %Character must be criminal
	\+existCharListStatusFromList(AC,A,captured), % Must not be already captured,
	getCharIsAliveFromList(AC,A,true),  %The character must be alive
	getCharCurrentLocationFromList(AC,A,LO),
	
	existCharListOccupationFromList(AC,B,soldier),	
	A \== B,
	getCharCurrentLocationFromList(AC,B,LO), % start checking for character at same place
	getCharIsAliveFromList(AC,B,true), %The character must be alive


	
%	********** Let Soldier able to arrest noble as 1st setting (since player can't directly capture nobel)	*********** 
%	********** 
%	********** READ ABOVE
%	********** READ ABOVE
%	********** READ ABOVE

%	\+existCharListOccupationFromList(AC,B,noble),	% Must not be noble


	append(AC,[[A,listStatus,captured,z,zz,zzz]],ACRE),
	
	append(P,[soldier_capture_criminal],P2),
	append(P2,[A],P3),
	append(P3,[B],PRE),
	
	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.
	
%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS).






%Soldier kill NPC criminal who resist (call it game mechanic, NPC resist if lvl higher than 15)
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B
	
	existCharListStatusFromList(AC,A,crime), %Character must be criminal
	\+existCharListStatusFromList(AC,A,captured), % Must not be already captured,
	getCharIsAliveFromList(AC,A,true),  %The character must be alive
	getCharCurrentLocationFromList(AC,A,LO),
	
	existCharListOccupationFromList(AC,B,soldier),	
	A \== B,
	getCharCurrentLocationFromList(AC,B,LO), % start checking for character at same place
	getCharIsAliveFromList(AC,B,true), %The character must be alive

	
	
	
	getCharLevelFromList(AC,A,LVLK),
	LVLK > 15,

	delete(AC,[A,isAlive,true,z,zz,zzz],AC2),
	append(AC2,[[A,isAlive,false,z,zz,zzz]],ACRE),

	append(P,[soldier_kill_resist],P2),
	append(P2,[A],P3),
	append(P3,[B],PRE),
	
	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.



%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).





%Doctor will always cure poisoned people at same place
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B
	
	existCharListStatusFromList(AC,A,poisoned), % Must be poisoneded
	getCharIsAliveFromList(AC,A,true), %The character must be alive
	getCharCurrentLocationFromList(AC,A,LO),
	
	
	existCharListOccupationFromList(AC,B,doctor),	
	getCharCurrentLocationFromList(AC,B,LO), % start checking for character at same place
	getCharIsAliveFromList(AC,B,true), %The character must be alive

		

	delete(AC,[A,listStatus,poisoned,z,zz,zzz],ACRE), 
	
	append(P,[doctor_heal_poisoned],P2),
	append(P2,[A],P3),
	append(P3,[B],PRE),


	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.
	
%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).



%Doctor will always cure damaged people at same place
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B
	
	existCharListStatusFromList(AC,A,damaged), % Must be poisoneded
	getCharIsAliveFromList(AC,A,true), %The character must be alive
	getCharCurrentLocationFromList(AC,A,LO),
	
	
	existCharListOccupationFromList(AC,B,doctor),	
	getCharCurrentLocationFromList(AC,B,LO), % start checking for character at same place
	getCharIsAliveFromList(AC,B,true), %The character must be alive

	delete(AC,[A,listStatus,damaged,z,zz,zzz],ACRE), 
	
	append(P,[doctor_heal_damaged],P2),
	append(P2,[A],P3),
	append(P3,[B],PRE),
	
	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.

%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).










% ----------------------------------------------------------------------------------------
% ******** MOVEMENT-Type of react are at the lowest so the other react react 1st ********
% ******** MOVEMENT-Type of react are at the lowest so the other react react 1st ********


%soldier walk out of jail to city


%Soldier will go to crime scene (where someone die?)



%Soldier bring captured criminal to jail (only way to go to jail?)
%This also remove crime from that character when reach Jail
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	
	existCharListStatusFromList(AC,A,crime), % Must be criminal	
	existCharListStatusFromList(AC,A,captured), % Must be already captured,	
	getCharIsAliveFromList(AC,A,true), %The character must be alive
	getCharCurrentLocationFromList(AC,A,LO),
		
	existCharListOccupationFromList(AC,B,soldier),	
	A \== B,
	getCharCurrentLocationFromList(AC,B,LO), % start checking for character at same place
	getCharIsAliveFromList(AC,B,true), %The character must be alive


	moveChar(AC,A,jail,AC2),
	moveChar(AC2,B,jail,AC3),
	
	delete(AC3,[A,listStatus,crime,z,zz,zzz],ACRE), %When moved to jail, lose crime status?

	append(P,[criminal_escort_to_jail_also_lose_crime],P2),
	append(P2,[A],P3),
	append(P3,[B],PRE),

	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.

%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).





% ----------------------------------------------------------------------------------------
% ******** Timer-Type / Single-Type of react are at the lower so the pair react react 1st ********
% ******** Timer-Type / Single-Type of react are at the lower so the pair react react 1st ********



%jailbreak (undo capture status) if has lockpick in inventory
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B
	
	getCharCurrentLocationFromList(AC,A,jail), %Must be at jail
	existCharListStatusFromList(AC,A,captured), % Must be already captured,
	getCharIsAliveFromList(AC,A,true), %The character must be alive
	
	getItemIDUsingName(AC,lockpick,LockPick_ID),
	getItemOwnerNameUsingID(AC,LockPick_ID,A),
	

	append(P,[criminal_lockpick_escape_jail],P2),
	append(P2,[A],PRE),
	
	delete(AC,[A,listStatus,captured,z,zz,zzz],AC2),
	moveChar(AC2,A,city,ACRE), %escape to city


	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.
	
%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).



%Character drink antidote to cure poisoned
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B

	existCharListStatusFromList(AC,A,poisoned),	
	getItemIDUsingName(AC,antidote,ATD_ID),
	getItemOwnerNameUsingID(AC,ATD_ID,A),
	
	getCharIsAliveFromList(AC,A,true), 		%The character must be alive

	
	append(P,[char_drink_antidote_cure_poisoned],P2),
	append(P2,[A],PRE),
	
	delete(AC,[A,listStatus,poisoned,z,zz,zzz],ACRE),

	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.

	
%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).




%Character die from poisoned
reacting_Pair([A|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B

	existCharListStatusFromList(AC,A,poisoned),	
	getCharIsAliveFromList(AC,A,true), %The character must be alive

	append(P,[die_from_poison],P2),
	append(P2,[A],PRE),
	
	delete(AC,[A,listStatus,poisoned,z,zz,zzz],AC2),
	delete(AC2,[A,isAlive,true,z,zz,zzz],AC3),
	append(AC3,[A,isAlive,false,z,zz,zzz],ACRE),

	ACRS = ACRE,
	ARRS = AR,
	LARS = LA,
	PRS = PRE,
	LCRS = LC.


%	reacting_Pair(P_FULL,S_FULL,ACRE,AR,LA,PRE,LC,ACRS,ARRS,LARS,PRS,LCRS).



%Character in Palace without being soldier / Noble are captured to jail


reacting_Pair([H|T],AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) 
:-
	reacting_Pair(T,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS).


% END reacting LOOP
% --------------------------------------------------------------------------------------------------------------












% Start  Condition Checking if character can perform certain action (character Ability, Relationship, etc.)
% --------------------------------------------------------------------------------------------------------------
%



action_attack(AC,AR,KillerName,TargetName,P,ACRE,ARRE,PRE) :- 	
%	getCharacterFromList(AC,KillerName,[NameKiller,A1Killer,V1Killer,A2Killer,V2Killer,ID_KILLER]),
%	getCharacterFromList(AC,TargetName,[NameTarget,A1Target,V1Target,A2Target,V2Target,ID_TARGET]),
	
	%1st the target and killer must be at the same location
	getCharCurrentLocationFromList(AC,KillerName,SameLo),
	getCharCurrentLocationFromList(AC,TargetName,SameLo),
	
	getCharIsAliveFromList(AC,TargetName,true),
	(
	%if there friend at same place, and friend level higher than attack
	
%	existRelationship(AR,friend,TargetName,FRIEND),
%	write(FRIEND),
%	write(AR),
%	nonvar(FRIEND),
	
	existRelationship(AR,friend,TargetName,FRIEND),
	getCharCurrentLocationFromList(AC,TargetName,B),
	getCharCurrentLocationFromList(AC,FRIEND,B),
	getCharLevelFromList(AC,FRIEND,LVLD),
	getCharLevelFromList(AC,KillerName,LVLK),
	LVLK < LVLD		%If there's friend with higher lvl than attacker, the attack fail and nothing happen
	->	
		(
		\+existCharListStatusFromList(AC,FRIEND,damaged)	% The friend must not be damaged
		->
			append(P,[friend_block],P2),
			append(P2,[FRIEND],PRE),
			deleteRelationship(AR,friend,KillerName,TargetName,AR2),
			deleteRelationship(AR2,friend,KillerName,FRIEND,AR3),
			append(AR3,[[enemy,KillerName,TargetName]],AR4),
			append(AR4,[[enemy,KillerName,FRIEND]],ARRE),
			append(AC,[[KillerName,listStatus,crime,z,zz,zzz]],ACRE)
		;
		%Else If friend is damaged, he cannot protect the friend and die instead
		append(P,[friend_block_fail_and_die],P2),
		append(P2,[FRIEND],PRE),
		deleteRelationship(AR,friend,KillerName,TargetName,AR2),
		deleteRelationship(AR2,friend,KillerName,FRIEND,AR3),
		append(AR3,[[enemy,KillerName,TargetName]],AR4),
		append(AR4,[[enemy,KillerName,FRIEND]],ARRE),
		append(AC,[[KillerName,listStatus,crime,z,zz,zzz]],AC2),
		delete(AC2,[FRIEND,isAlive,true,z,zz,zzz],AC3),
		append(AC3,[[FRIEND,isAlive,false,z,zz,zzz]],ACRE)
		)
	;
	%else, kill the target
	append(P,[char_die],P2),
	append(P2,[TargetName],PRE),
	delete(AC,[TargetName,isAlive,true,z,zz,zzz],ACNew),
	append(ACNew,[[TargetName,isAlive,false,z,zz,zzz]],ACNew2),
	append(ACNew2,[[KillerName,listStatus,crime,z,zz,zzz]],ACRE),
	deleteRelationship(AR,friend,KillerName,TargetName,AR2),
	append(AR2,[[enemy,KillerName,TargetName]],ARRE)
	).


test_if_else(A,B,ANS) :- 	

	(A = a 
	-> 
		(
		B = b
		->
		ANS = 1
		;
		ANS = 2
		)	
	;
	ANS = 4
	).









action_hire_to_attack(AC,AR,HirerName,Hitman,TarName,P,ACNew2,ARRE,PRE)
:-
	getCharCurrentLocationFromList(AC,Hitman,H_lo),
	getCharCurrentLocationFromList(AC,TarName,T_lo),
	action_moveChar(AC,P,Hitman,H_lo,T_lo,AC2,P2),
	
	action_attack(AC2,AR,Hitman,TarName,P2,ACNew2,ARRE,PRE).
	
	


action_pick_up(AC,AR,ItemID,PICKER,P,ACRE,ARRE,PRE)
:-
%	getCharacterFromList(AC,PICKER,[PICKER,A1PICKER,V1PICKER,A2PICKER,V2PICKER,ID]),
	
	getItemNameUsingID(AC,ItemID,ItemName),
	getItemCurrentLocationUsingID(AC,ItemID,SOURCE), %SOURCE = location name if on ground, NPC_name if not on ground
	(
	\+getItemIsOnGroundUsingID(AC,ItemID,true) % Not on ground = on NPC
	->
		changeItemIsOnGroundUsingID(AC,ItemID,no,AC2),
		precedure_moveItem(AC2,ItemID,SOURCE,PICKER,ACRE)
	;
	changeItemIsOnGroundUsingID(AC,ItemID,no,AC2),
	precedure_moveItem(AC2,ItemID,SOURCE,PICKER,ACRE)
	),

	append(P,[pick_up],P2),
	append(P2,[ItemName],P3),
	append(P3,[PICKER],P4),
	append(P4,[SOURCE],P5),
	ARRE = AR,
	PRE = P5.


%This assume the poisoned is used in food, which are simplified and skip from coding
action_poisoned(AC,TarName,Actor,ACRE)
:-	

%	getCharacterFromList(AC,TarName,[TarName,A1T,V1T,A2T,V2T,IDT]),
%	getCharacterFromList(AC,poisonederName,[poisonederName,A1P,V1P,A2P,V2P,IDP]),
	
	append(AC,[TarName,listStatus,poisoned,z,zz,zzz],ACRE).
	




action_moveChar(AC,P,Char,From,DesLo,ACRE,PRE)
:-
	(
	\+existCharListStatusFromList(AC,Char,captured)
	->
	delete(AC,[Char,currentLocation,From,z,zz,zzz],AC2),
	append(AC2,[[Char,currentLocation,DesLo,z,zz,zzz]],AC3),
	
	append(P,[move_from_to],P2),
	append(P2,[From],P3),
	append(P3,[DesLo],P4),
	append(P4,[Char],P5), 
	
	ACRE = AC3,
	PRE = P5
	;
	ACRE = AC,
	append(P,[cannot_move_cause_captured],P_1),
	append(P_1,[Char],PRE)
	).	
	
	
	

%action_damage	

action_damage(AC,P,TargetID_or_Name,ACRE,PRE)
:-
	(
	getItemNameUsingID(AC,TargetID_or_Name,ItemName),
	\+existItemListPropertyUsingID(AC,TargetID_or_Name,damaged) % The target must not already be damaged

	->
		getItemCurrentHOLDERUsingID(AC,TargetID_or_Name,OwnerName),
		append(P,[damage_item],P2),
		append(P2,[ItemName],PRE),
		append(AC,[[OwnerName,itemList,ItemName,listProperty,damaged,TargetID_or_Name]],ACRE)
	;
	getCharIsAliveFromList(AC,TargetID_or_Name,true),  %Target to damage must be alive
	\+existCharListStatusFromList(AC,TargetID_or_Name,damaged)	% The target must not already be damaged
	->
		append(P,[damage_character],P2),
		append(P2,[TargetID_or_Name],PRE),
		append(AC,[[TargetID_or_Name,listStatus,damaged,z,zz,zzz]],ACRE)
	;
	append(P,[damage_fail_no_condition_met],P2),
	ACRE = AC,
	PRE = P2
	).



%action_fix
action_fix(AC,P,TargetID_or_Name,Fixer,ACRE,PRE)
:-
	
	getItemNameUsingID(AC,TargetID_or_Name,ItemName),
	existItemListPropertyUsingID(AC,TargetID_or_Name,damaged), % The target must be damaged
	getItemCurrentHOLDERUsingID(AC,TargetID_or_Name,OwnerName),
	
	delete(AC,[OwnerName,itemList,ItemName,listProperty,damaged,TargetID_or_Name],ACRE),
	append(P,[fix_item],P2),
	append(P2,[ItemName],P3),
	append(P3,[Fixer],PRE).




%action_give_item
action_give_item(AC,P,ItemID,Receiver,Giver,ACRE,PRE)
:-
	getItemNameUsingID(AC,ItemID,ItemName),
	precedure_moveItem(AC,ItemID,Giver,Receiver,ACRE),
	
	append(P,[give_item],P2),
	append(P2,[ItemName],P3),
	append(P3,[Receiver],P4),
	append(P4,[Giver],P5),
	
	PRE = P5.


%action_capture
action_capture(AC,AR,P,Capturer,Target,ACRE,ARRE,PRE)
:-
	(
	\+existCharListStatusFromList(AC,Target,captured)
		->
		append(AC,[[Target,listStatus,captured,z,zz,zzz]],ACRE),
		deleteRelationship(AR,friend,Capturer,Target,AR2),
		append(AR2,[[capture_me,Capturer,Target]],ARRE),
		append(P,[capture],P2),
		append(P2,[Target],P3),
		append(P3,[Capturer],PRE)
	;
	append(P,[already_captured],PRE),
	ARRE = AR,
	ACRE = AC
	).


%action_free
%action_free(AC,AR,P,Helper,Target,ACRE,ARRE,PRE),

action_free(AC,AR,P,Helper,Target,ACRE,ARRE,PRE)
:-

	existCharListStatusFromList(AC,Target,captured),
	delete(AC,[Target,listStatus,captured,z,zz,zzz],ACRE),
	(
	existRelationship(AR,capture_me,Helper,Target)
		->
		
		ARRE = AR,
		append(P,[free],P2),
		append(P2,[Target],P3),
		append(P3,[Helper],PRE)
	;
	append(AR,[[friend,Helper,Target]],ARRE),
	
	append(P,[free],P2),
	append(P2,[Target],P3),
	append(P3,[Helper],PRE)
	).
		


%action_bribe_add_crime
action_bribe_add_crime(AC,P,TarName,Actor,ACRE,PRE)
:-
	append(AC,[[TarName,listStatus,crime,z,zz,zzz]],ACRE),
	
	append(P,[bribe_add_crime],P2),
	append(P2,[TarName],P3),
	append(P3,[Actor],PRE).


%action_bribe_remove_crime
action_bribe_remove_crime(AC,P,TarName,Actor,ACRE,PRE)
:-
	delete(AC,[TarName,listStatus,crime,z,zz,zzz],ACRE),
	
	append(P,[bribe_remove_crime],P2),
	append(P2,[TarName],P3),
	append(P3,[Actor],PRE).





%action_steal_item


%action_bribe_accusation


% only work on non-noble / king
%action_hire_to_move_to_location



%action_curse_to_low_level



%action_buff_to_higher_level


%action_craft








	





















action_heal(Healer,TargetName) :- 
	healer(HealerName),
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),	
	assert(character(TargetName,TargetLvl,TargetStatus,healthy,TargetLocation)).

poisonedeasy(KillerName,TargetName) :-
	character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	character(KillerName,KillerLvl,KillerStatus,KillerHealth,KillerLocation),
	TargetStatus == alive,
	retract(character(TargetName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(TargetName,TargetLvl,TargetStatus,poisoned,TargetLocation)),
	write(KillerName),write(" poisoned "),write(TargetName).
	

% END  Condition Checking if character can perform certain action (character Ability, Relationship, etc.)
% --------------------------------------------------------------------------------------------------------------
%





% START  effect of each action
% --------------------------------------------------------------------------------------------------------------
%


direct_attack_record(ListOld,character(ActorName,ActorLevel,ActorStatus,ActorAttribute,ActorLocation),character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew2) 
:-
	list_item_subtracted(ListOld,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew),   
	list_item_asserted(ListNew,character(TargetName,TargetLevel,dead,TargetAttribute,TargetLocation), ListNew2).

	
	
	
add_crime(ListRelation,TypeOfCrime,character(TargetName,TargetLevel,TargetStatus,TargetAttribute,TargetLocation),ListNew2) :-
	(TypeOfCrime == steal  -> Crime = minor_crime
	;TypeOfCrime == murder -> Crime = major_crime
	),
	list_item_asserted(ListRelation,relationship(nation,TargetName,Crime), ListNew2),
%	write(ListNew2),
	nl.
	
% END  effect of each action
% --------------------------------------------------------------------------------------------------------------
%	




relationship(attack_direct,AttackerName,TargetName).
relationship(hire_to_attack,ClientCharName,AssassinCharName,TargetCharName).
relationship(bribe_addCrime,ClientCharName,PoliceName,TargetCharName).


%Direct Attack

%Hire to Attack

% Pickup / gather

% Give

% Take (from corpse)

% Goto

% Bribe 

% Capture 

% Free

% Craft/Build (item Name?)

% Fix (item Name?)

% Learn (Skill Name?)

%





% -------------------------- Write to file -----------------------------


%---This one didn't use 'counter(0)' and use loopCounter(lc) instead
%---This will write all condition to file

%
%writeToFile(GC,GL,AC,AR,P,LC,PF)	
%:-	
%	
%	append([quest_done],P,PF),
%	
%	% -------------------------- Write to file -----------------------------
%	atom_concat('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file',LC,NAME),
%	getFileName(NAME,OUT),
%	
%    open(OUT,write, Stream),
%    (  	writeln(Stream,PF),
%    	writeln(Stream,AC), 
%		writeln(Stream,AR)
%    ;   true
%    ),
%    close(Stream).
%	% -------------------------- END Write to file --------------------------
%


%writeToFile([],[],[a,b,c],[kkk],[],[],[],[]).	
%--- Using counter instead of LoopCounter to write fileNum ---
counter(0).
pathExist(0).
pathExist_ac(0).
conditionExist(0).

writeToFile(GC,GL,AC,AR,LA,P,LC,PF)	
:-	
	assert(conditionExist(AC)),
	assert(pathExist(P)),
	
	seperateActionPath(P,P_ac),
	assert(pathExist_ac(P_ac)),
	% -------------------------- Create Folder --------------------------
	
	elementAtN(P,0,FolderName),
	atom_concat('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/',FolderName,DI2),
	make_directory_with_check(DI2),

	
	% -------------------------- Create file name --------------------------
	
	counter(N),
	elementAtN(P,1,FileName),
	%retract(counter(N)), % no need to delete, the line about will retrive the most top counter anyway.
	N1 is N+1,
	asserta(counter(N1)),	
	atom_concat(DI2,'/file',NAME2),
	atom_concat(NAME2,FileName,NAME3),
	atom_concat(NAME3,FolderName,NAME4),
	getFileName(NAME4,N1,OUT),
	
	% -------------------------- Write to file --------------------------
	
	
    open(OUT,write, Stream),
    (  	writeln(Stream,P),
    	writeln(Stream,AC), 
		writeln(Stream,AR),
		writeln(Stream,LA)
    ;   true
    ),
    !,
    close(Stream),
    PF = P,
    write(P),
    fail.
	% -------------------------- END Write to file --------------------------



writeToFileFirst(GC,GL,AC,AR,LA,P,LC,PF)	
:-	
	assert(conditionExist(AC)),
	assert(pathExist(P)),
	% -------------------------- Create Folder --------------------------
	
	elementAtN(P,0,FolderName),
	atom_concat('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/',FolderName,DI2),
	make_directory_with_check(DI2),

	
	% -------------------------- Create file name --------------------------
	
	counter(N),
	elementAtN(P,1,FileName),
	%retract(counter(N)), % no need to delete, the line about will retrive the most top counter anyway.
	N1 is N+1,
	asserta(counter(N1)),	
	atom_concat(DI2,'/file',NAME2),
	atom_concat(NAME2,FileName,NAME3),
	atom_concat(NAME3,FolderName,NAME4),
	getFileName(NAME4,N1,OUT),
	
	% -------------------------- Write to file --------------------------
	
	
    open(OUT,write, Stream),
    (  	writeln(Stream,P),
    	writeln(Stream,AC), 
		writeln(Stream,AR),
		writeln(Stream,LA)
    ;   true
    ),
    !,
    close(Stream),
    PF = P. 
    
    %write(P).







	

testJavaConnect(A,B)
:-
	B = A.





% ----------- COPY FROM UTILITY (until consult actually work) -------------

% ----------- DO NOT UPDATE CODE HERE -------------------
% ----------- UDATE AT UTILITY AND COPY HERE ------------
% ----------- DO NOT UPDATE CODE HERE -------------------





% ----------- DO NOT UPDATE CODE HERE -------------------
% ----------- UDATE AT UTILITY AND COPY HERE ------------
% ----------- DO NOT UPDATE CODE HERE -------------------


	