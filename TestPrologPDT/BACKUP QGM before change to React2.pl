:- module(BACKUP QGM before change to React2,[
		
	]).

	
	
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

% GA here = all Goal condition that isn't seperated yet.
startQuestPath(GA,AC,AR,LA,P,PF)
:-
	seperateGoalType(AC,GA,GC,GL),
	startQuestPath(GC,GL,AC,AR,LA,P,PF).


%BASE CASE >>>> If correct from start = quest is done when initiated
startQuestPath(GC,GL,AC,AR,LA,P,PF)
:-
	memberlist(GC,AC),
	checkSpecialCondition(GL,AC),
	writeToFile(GC,GL,AC,AR,LA,P,0,PF),
	PF = P.


startQuestPath(GC,GL,AC,AR,LA,P,PF)
:-
	LoopCounter = 1,
	append([start_quest],[P],P2),
	questPathMainLoop(GC,GL,AC,AR,LA,P2,LoopCounter,PF),
	PF = P.
	
%Main loop to check if after 1 action and follow by resolve, the quest 
%goal is reached yet or not.	
questPathMainLoop(GC,GL,AC,AR,LA,P,LC,PF)	 
:-
	( 	questPathMainLoop_Done(GC,GL,AC,AR,LA,P,LC,PF) -> writeToFile(GC,GL,AC,AR,LA,P,LC,PF)
	;	questPathMainLoop_Continue(GC,GL,AC,AR,LA,P,LC,PF)
	).
	
questPathMainLoop_Done(GC,GL,AC,AR,LA,P,LC,PF) 
:-
	%memberlist(GC,AC),  // Nolonger working, obsolete
	%memberlist(GL,AL),  // Nolonger working, obsolete
	memberlist(GC,AC),
	checkSpecialCondition(GL,AC).
	

questPathMainLoop_Continue(GC,GL,AC,AR,LA,P,LC,PF)
:-
	LC < 20, % player should do less than 20 action to pass a component of quest, this also exist to force backtrack and make the Prolog try new action more than infinity [attack] loop
	
	% Loop possible player action (player can only act 1 time, then wait until all NPC react to it and react to other NPC react too)
	questPathMainLoop_PlayerAction(GC,GL,AC,AR,LA,P,LC,PF,ACReturn,ARReturn,LAReturn,P2,LC2),
	% Loop resolve
	questPathMainLoop_Resolve(GC,GL,ACReturn,ARReturn,LAReturn,P2,LC2,ACRS,ARRS,LA3,P3,LC3),
	% set back to main loop
	questPathMainLoop(GC,GL,ACRS,ARRS,LA3,P3,LC3,PF).



questPathMainLoop_Resolve(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	%If it's possible to resolve, loop until no longer possible to resolve
	(resolving(GC,GL,AC,AR,LA,P,LC,ACRS_2,ARRS_2,LA_2,P_2,LC_2) -> questPathMainLoop_React(GC,GL,ACRS_2,ARRS_2,LA_2,P_2,LC_2,ACRS_B,ARRS_B,LARS_B,PRS_B,LCRS_B)
	;ACRS = AC,
	ARRS = AR,
	LARS = LA,
	PRS = P,
	LCRS = LC
	).
	
questPathMainLoop_React(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	%If it's possible to resolve, loop until no longer possible to resolve
	(reacting(GC,GL,AC,AR,LA,P,LC,ACRS_2,ARRS_2,LA_2,P_2,LC_2) -> questPathMainLoop_React(GC,GL,ACRS_2,ARRS_2,LA_2,P_2,LC_2,ACRS_B,ARRS_B,LARS_B,PRS_B,LCRS_B)
	;questPathMainLoop_Resolve(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
	).







% START of Possible player action
% --------------------------------------------------------------------------------------------------------------
%

%Direct Attack
questPathMainLoop_PlayerAction(GC,GL,AC,AR,LA,P,LC,PF,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action

	getCharNameFromList(AC,_,TarName), %select target (all character)
	append([relationship(direct_attack,player,TarName)],LA,LAReturn),
	ACReturn = AC,
	P2 = P,
	LC2 is LC+1.
	
	
%Hire to Attack
questPathMainLoop_PlayerAction(GC,GL,AC,AR,LA,P,LC,PF,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action

	getACharName(AC,TarName), %select target (all character)
	getACharName(AC,Hitman),  %select attacker (all character)
	
	% Both tarket and hitman must NOT be friend
	existRelationship(AR,friend,TarName,Hitman),
	
	% Hitman must not be 'rich' or hole high position in society
	\+existCharListStatusFromList([[Name,listStatus,B,C,D,Z]|T],Hitman,rich),
	\+existCharListOccupationFromList([[Name,listOccupation,B,C,D,Z]|T],Hitman,noble), 
	
	\+existRelationship(AR,friend,TarName,Hitman), %they must not be friend with each other
	append([relationship(hire_to_attack,player,Hitman,TarName)],LA,LAReturn),
	ACReturn = AC,
	P2 = P,
	LC2 is LC+1.
	
	
% Pickup / gather item from location (non holded by NPC)
questPathMainLoop_PlayerAction(GC,GL,AC,AR,LA,P,LC,PF,ACReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	
	
	%--------- START:*** Select random item on ground from player location *********-------------
	
	%getCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name,B).
	getCharCurrentLocationFromList(AC,player,PLYLO),
	%existItemInLocationWithName([[L,A,B,itemName,NAME,Z]|T],L,NAME).
	existItemInLocationWithID(AC,PLYLO,ItmID),
	%getItemIsOnGroundUsingID([[ANY,A,Name,isOnGround,D,Z]|T],Z,D).
	getItemIsOnGroundUsingID(AC,ItmID,ItmName),

	%--------- END:***** Select random item on ground from player location *********-------------

	append([relationship(pickup,ItmID,player,PLYLO)],LA,LAReturn),
	ACReturn = AC,
	P2 = P,
	LC2 is LC+1.

	% CURRENT RELATIONSHIP
	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
	
	
% (Simplify complex chain action in react to this) poison food = just give the target poison (stealth action)	
	
% Give

% Take (from corpse)

% Goto

% Capture 

% Free

% Craft/Build (item Name?)

% Fix (item Name?)

% Learn (Skill Name?)


% Bribe (add crime)
% Bribe (remove crime)



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
% Use item(poison)
% Use item(berry)






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

resolving(GC,GL,AC,AR,[],P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	false.


resolving(GC,GL,AC,AR,[relationship(direct_attack,AtkName,TarName)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append([direct_attack],P,P2),
	append([AtkName],P2,P3),
	append([TarName],P3,P4),
	
	%EXAMPLE ON THE PATH 
	%[player,jack,direct_attack,quest_start] - before reverse
	%[quest_start,direct_attack,jack,player] - after reverse 
	
	action_attack(AC,AR,AtkName,TarName,P4,ACNew2,AR2,P5),


	ACRS = ACNew2,
	ARRS = AR2,
	LARS = LAT,
	PRS = P5,
	LCRS = LC.
	
resolving(GC,GL,AC,AR,[relationship(hire_to_attack,HirerName,Hitman,TarName)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append([hire_to_attack],P,P2),
	append([HirerName],P2,P3),
	append([Hitman],P3,P4),
	append([TarName],P4,P5),

	action_hire_to_attack(AC,AR,HirerName,Hitman,TarName,P5,ACNew2,AR2,P6),


	ACRS = ACNew2,
	ARRS = AR2,
	LARS = LAT,
	PRS = P6,
	LCRS = LC.
	

	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
	
	
resolving(GC,GL,AC,AR,[relationship(pickup,ItmID,PICKER,PLYLO)|LAT],P,LC,ACRS,ARRS,LAT,PRS,LCRS)
:-
	append([pickup],P,P2),
	append([ItmID],P2,P3),
	append([PICKER],P3,P4),
	append([PLYLO],P3,P5),   % [{what's pick}, {by who}, {at where}]
	
	action_pick_up(AC,AR,ItmID,PICKER,P5,ACNew2,AR2,P6),


	ACRS = ACNew2,
	ARRS = AR2,
	LARS = LAT,
	PRS = P6,
	LCRS = LC.
	

	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
















	
% *********************	FOR TESTING	*****************************
resolving1([])
:- write("not working").

resolving1([relationship(direct_attack,X,Y)])
:-
	write(X),
	nl,
	write(Y).

% RETURN TRUE == WORKING
%resolving1([relationship(direct_attack,player,jack)]).

resolving1([ARH|ART])
:-
	write("ABC"),
	ARH == relationship(_,_,_).

% RETURN FALSE
%resolving1([relationship(direct_attack,player,jack),relationship(direct_attack,player,john)]).
	

resolving1([ARH|ART])
:-
	(ARH == relationship(direct_attack,PLAYER,TarName) -> write("test"), write(TarName)
	;resolving1(ART)).

% RETURN FALSE
%resolving1([relationship(direct_attack,player,jack),relationship(direct_attack,player,john)]).


% END Resolve LOOP
% --------------------------------------------------------------------------------------------------------------






% START reacting LOOP
% --------------------------------------------------------------------------------------------------------------

%Friend cure Friend poison
reacting(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	
	getCharNameFromList(AC,_,CharA), %any charA
	getCharNameFromList(AC,_,CharB), %any charB

	
	getCharIsAliveFromList(AC,CharA,true), %The character must be alive
	getCharIsAliveFromList(AC,CharB,true), %The character must be alive
	
	existCharListStatusFromList(AC,CharA,poison), %is poison?
	existCharListSkillFromList(AC,CharB,heal), %can char2 cure poison?
	
	delete(AC,[CharA,listStatus,poison,_,_,_],AC2),
	
	append(P,[friend_heal_poison],P2),
	append(P2,[CharA],P3),
	append(P3,[CharB],P4),
	
	ACRS = AC2,
	ARRS = AR,
	LARS = LA,
	PRS = P4,
	LCRS = LC.
	
	
	
	
	
%Soldier capture criminal at same place (unless noble)	
reacting(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	getCharPair(AC,AC,ANS),
	reacting2(ANS,GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS).

reacting2([],GC,GL,AC,AR,LA,P,LC,AC,AR,LA,P,LC).
reacting2([(A, B)|T],GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
	% Do something on A and B
	
	getACharName(AC,_,A), %any charA
	getCharIsAliveFromList(AC,A,true), %The character must be alive
	existCharListOccupationFromList(AC,A,soldier),	
	
	getCharCurrentLocationFromList(AC,A,LO),
	getCharCurrentLocationFromList(AC,B,LO), % start checking for character at same place
	
	getCharIsAliveFromList(AC,B,true), %The character must be alive
	\+existCharListOccupationFromList(AC,B,noble),	
	existCharListStatusFromList(AC,B,crime),
	\+existCharListStatusFromList(AC,B,captured), % Must not be already captured,
	
	append(AC,[[B,listStatus,captured,z,zz,zzz]],AC2),
	reacting2(T,GC,GL,AC,AR2,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS).

reacting2([(A, B)|T],GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS) :-
		reacting2(T,GC,GL,AC,AR2,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS).
	

Ada แนะนำให้เอา react ทั้งหมดมาใส่ใน react2 แท่น แล้วใข้ react(1) เอาไว้แค่เรียก pair character แทน
ปัญหาคือ 

	getCharNameFromList(AC,_,CharA), %any charA
	getCharNameFromList(AC,_,CharB), %any charB

จะได้แค่ตัวละครเดียวกันเท่านั้น เพราะ prolog จะจับคู่กับตัวละครที่เป็น variable เท่านั้น


%Soldier capture criminal at same place (unless noble)
reacting(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	getACharName(AC,_,CharA), %any charA
	getCharIsAliveFromList(AC,CharA,true), %The character must be alive
	existCharListOccupationFromList(AC,CharAName,soldier),	
	
	getCharCurrentLocationFromList(AC,CharA,LO),
	getCharCurrentLocationFromList(AC,CharB,LO), % start checking for character at same place
	
	getCharIsAliveFromList(AC,CharB,true), %The character must be alive
	\+existCharListOccupationFromList(AC,CharB,noble),	
	existCharListStatusFromList(AC,CharB,crime),
	\+existCharListStatusFromList(AC,CharB,captured), % Must not be already captured,
	
	append(AC,[[CharB,listStatus,captured,z,zz,zzz]],AC2),
	ACRS = AC2,
	ARRS = AR,
	LARS = LA,
	PRS = P,
	LCRS = LC.


%Criminal resist capture if lvl higher than 15


%Soldier kill NPC criminal who resist (call it game mechanic)

%Soldier auto warp criminal player to jail (no resist possible?)

%jailbreak (undo capture status) if has lockpick in inventory

%none soldier walk out of jail

%Character with non-owned item in inventory = got crime, the item is then removed and 'warped' to the true owner

%Doctor will always heal people at same place



% ----------------------------------------------------------------------------------------
% ******** MOVEMENT-Type of react are at the lowest so the other react react 1st ********
% ******** MOVEMENT-Type of react are at the lowest so the other react react 1st ********


%Soldier will go to crime scene (where someone die?)

%Soldier bring captured criminal to jail (only way to go to jail?)
%This also remove crime from that character when reach Jail

reacting_NOT_CODED_YET(GC,GL,AC,AR,LA,P,LC,ACRS,ARRS,LARS,PRS,LCRS)
:-
	
	
	ACRS = AC2,
	ARRS = AR,
	LARS = LA,
	PRS = P,
	LCRS = LC.

% END reacting LOOP
% --------------------------------------------------------------------------------------------------------------












% Start  Condition Checking if character can perform certain action (character Ability, Relationship, etc.)
% --------------------------------------------------------------------------------------------------------------
%



action_attack(AC,AR,KillerName,TargetName,P,ACNew2,ARRE,PRE) :- 	
	getCharacterFromList(AC,KillerName,[NameKiller,A1Killer,V1Killer,A2Killer,V2Killer,ID_KILLER]),
	getCharacterFromList(AC,TargetName,[NameTarget,isAlive,true,A2Target,V2Target,ID_TARGET]),
	
	(
	%if there friend at same place, and friend level higher than attack
	existRelationship(AR,friend,NameTarket,ANYCHAR),
	getCharCurrentLocationFromList(AC,NameTarget,B),
	getCharCurrentLocationFromList(AC,ANYCHAR,B),
	getCharLevelFromList(AC,ANYCHAR,LVLD),
	getCharLevelFromList(AC,KillerName,LVLK),
	LVLK < LVLD
	->
	%If there's friend with higher lvl than attacker, the attack fail and nothing happen
	append([friend_block],P,P2),
	append([ANYCHAR],P2,PRE),
	deleteRelationship(AR,friend,KillerName,TargetName,AR2),
	deleteRelationship(AR2,friend,KillerName,ANYCHAR,ARRE),
	append(AC,[[NameKiller,listStatus,crime,A2Target,V2Target,ID_KILLER]],ACRE)
	;
	%else, kill the target
	append([char_die],P,P2),
	append([TargetName],P2,PRE),
	delete(AC,[NameTarget,isAlive,true,A2Target,V2Target,ID_TARGET],ACNew),
	append(ACNew,[[NameTarget,isAlive,false,A2Target,V2Target,ID_TARGET]],ACNew2),
	append(ACNew2,[[NameKiller,listStatus,crime,A2Target,V2Target,ID_KILLER]],ACNew3),
	append(ACNew3,[[NameKiller,listStatus,crime,A2Target,V2Target,ID_KILLER]],ACRE),
	deleteRelationship(AR,friend,KillerName,TargetName,ARRE)
	).


action_hire_to_attack(AC,AR,HirerName,Hitman,TarName,P,ACNew2,ARRE,PRE)
:-
	getCharacterFromList(AC,HirerName,[HirerName,A1Killer,V1Killer,A2Killer,V2Killer,ID]),
	getCharacterFromList(AC,Hitman,[Hitman,isAlive,true,A2Target,V2Target,ID]),
	getCharacterFromList(AC,TarName,[TarName,isAlive,true,A2Target,V2Target,ID]),
	
	action_attack(AC,AR,Hitman,TarName,P,ACNew2,ARRE,PRE).
	
	


action_pick_up(AC,AR,ItmID,PICKER,P,ACRE,ARRE,PRE)
:-
	getCharacterFromList(AC,PICKER,[PICKER,A1PICKER,V1PICKER,A2PICKER,V2PICKER,ID]),
	getItemCurrentLocationUsingID(AC,ItmID,SOURCE), %CHANGE TO MAKE IT PICK UP BOTH FROM NPC & LOCATION
	getItemIsOnGroundUsingID(AC,ItmID,ItmName),
	
	precedure_moveItem(AC,ItmID,SOURCE,PICKER,AC2),
	changeItemIsOnGroundUsingID(AC2,ItmID,no,AC3),
	
	append([pick_up],P,P2),
	append([ItmID],P2,P3),
	append([PICKER],P3,P4),
	append([SOURCE],P4,P5),
	
	ACRE = AC3,
	ARRE = AR,
	PRE = P5.






action_heal(Healer,TargetName) :- 
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
writeToFile(GC,GL,AC,AR,LA,P,LC,PF)	
:-	
	counter(N),
	%retract(counter(N)), % no need to delete, the line about will retrive the most top counter anyway.
	N1 is N+1,
	asserta(counter(N1)),
	
	
	% -------------------------- Write to file --------------------------
	
	last(P,FileDepo),
	atom_concat('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file',FileDepo,NAME),
	getFileName(NAME,N1,OUT),
	
    open(OUT,write, Stream),
    (  	writeln(Stream,P),
    	writeln(Stream,AC), 
		writeln(Stream,AR),
		writeln(Stream,LA)
    ;   true
    ),
    !,
    close(Stream).
	% -------------------------- END Write to file --------------------------

	







% ----------- COPY FROM UTILITY (until consult actually work) -------------

% ----------- DO NOT UPDATE CODE HERE -------------------
% ----------- UDATE AT UTILITY AND COPY HERE ------------
% ----------- DO NOT UPDATE CODE HERE -------------------




%	getCharPair(
%	[[jack,isAlive,true,z,zz,zzz],[jack,level,true,z,zz,zzz],[ken,isAlive,true,z,zz,zzz],[ryu,isAlive,true,z,zz,zzz]],
%	[[jack,isAlive,true,z,zz,zzz],[jack,level,true,z,zz,zzz],[ken,isAlive,true,z,zz,zzz],[ryu,isAlive,true,z,zz,zzz]],
%	ANS).

	getCharPair([], _, []).
	getCharPair(_, [], []).
	getCharPair([H|T], AC, Ans) :-
		pair(H, AC, Ans1),
		getCharPair(T, AC, Ans2),
		append(Ans1, Ans2, Ans).
	
	pair(_, [], []).
	pair(A, [B|T], [(A,B)|Ans]) :-
		isAlive(A),
		isAlive(B),
		pair([A,isAlive,_,_,_,_], T, Ans).
	pair([A,StatusA,_,_,_,_], [_|T], []):-
		StatusA \== isAlive.	
	pair(A,[[B,StatusB,_,_,_,_]|T],Ans):-
		StatusB \== isAlive,
		pair(A,T,Ans).
	
	isAlive([_, isAlive, _, _, _, _]).
		
		
		
% NOT WORKING BELOW		
		
%	getCharPair(AC,[],[],[]).
%	getCharPair(AC,[H|T],[],Ans)
%	:-
%		getCharPair(AC,T,AC,Ans).
%	getCharPair([AC,[A,isAlive,_,_,_,_]|T],[[B,isAlive,_,_,_,_]|N],[(A,B)|Ans])
%	:-
%		getCharPair(AC,[[A,isAlive,_,_,_,_]|T],N,Ans).
%		
		



% ----------- DO NOT UPDATE CODE HERE -------------------
% ----------- UDATE AT UTILITY AND COPY HERE ------------
% ----------- DO NOT UPDATE CODE HERE -------------------


	