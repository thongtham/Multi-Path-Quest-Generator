:- module(QuestGeneratorMain,[
							
							testQuestGameState/3,
							character/3
							
							]).

% *** LEGEND ***

%COMPONENT		= CO
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



%BASE CASE >>>> If correct from start = quest is done when initiated
startQuestPath(CO,GC,GL,AC,AL,AR,LA,P,PF)
:-
	member(GC,AC),
	member(GL,AL).

startQuestPath(CO,GC,GL,AC,AL,AR,LA,P,PF)
:-
	LoopCounter = 1,
	append([start_quest],[P],P2),
	questPathMainLoop(GC,GL,AC,AL,AR,LA,P2,LoopCounter,PF).
	
%Main loop to check if after 1 action and follow by resolve, the quest 
%goal is reached yet or not.	
questPathMainLoop(GC,GL,AC,AL,AR,LA,P,LC,PF)	 
:-
	( 	questPathMainLoop_Done(GC,GL,AC,AL,AR,LA,P,LC,PF) -> writeToFile(GC,GL,AC,AL,AR,LA,P,LC,PF)
	;	questPathMainLoop_Continue(GC,GL,AC,AL,AR,LA,P,LC,PF)
	).
	
questPathMainLoop_Done(GC,GL,AC,AL,AR,LA,P,LC,PF) 
:-
	
	memberlist(GC,AC),
	memberlist(GL,AL),
	append([quest_done],P,PF).
	
%This will write all condition to file
writeToFile(GC,GL,AC,AL,AR,LA,P,LC,PF)	
:-	
    open('file.txt',write, Stream),
    (  	writeln(PF),
    	writeln(Stream,AC), 
		writeln(Stream,AL),
		writeln(AR)
    ;   true
    ),
    close(Stream).

%writeToFile([],[],[a,b,c],[kkk],[],[],[],[]).	


writeToFile(GC,GL,AC,AL,AR,LA,P,LC,PF)	
:-	
    open('file.txt',write, Stream),
    (  	writeln(PF),
    	writeln(Stream,AC), 
		writeln(Stream,AL),
		writeln(AR)
    ;   true
    ),
    close(Stream).
	
%
questPathMainLoop_Continue(GC,GL,AC,AL,AR,LA,P,LC,PF)
:-
	% Loop possible player action (player can only act 1 time, then wait until all NPC react to it and react to other NPC react too)
	questPathMainLoop_PlayerAction(GC,GL,AC,AL,AR,LA,P,LC,PF,ACReturn,ALReturn,ARReturn,LAReturn,P2,LC2),
	% Loop resolve
	questPathMainLoop_Resolve(GC,GL,ACReturn,ALReturn,ARReturn,LAReturn,P2,LC2,ACRS,ALRS,ARRS,LA3,P3,LC3),
	% Loop NPC-reaction 
	questPathMainLoop_Reaction(GC,GL,ACRS,ALRS,ARRS,LA3,P3,LC3,PF,ACLR,ALLR,ARLR,LA4,P4,LC4).
	% set back to main loop
	questPathMainLoop(GC,GL,ACLR,ALLR,ARLR,LA4,P4,LC4,PF).



questPathMainLoop_Resolve(GC,GL,AC,AL,AR,LA,P,LC,ACRS,ALRS,ARRS,LARS,PRS,LCRS)
:-
	%If it's possible to resolve, loop until no longer possible to resolve
	(resolving(GC,GL,AC,AL,AR,LA,P,LC,ACRS_2,ALRS_2,ARRS_2,LA_2,P_2,LC_2) -> questPathMainLoop_Resolve(GC,GL,ACRS_2,ALRS_2,ARRS_2,LA_2,P_2,LC_2,ACRS_B,ALRS_B,ARRS_B,PRS_B,LCRS_B)
	;ACRS = AC,
	ALRS = AL,
	ARRS = AR,
	LARS = LA,
	PRS = P,
	LCRS = LC
	).
	

questPathMainLoop_Reaction(GC,GL,ACRS,ALRS,ARRS,LA3,P3,LC3,PF,ACLR,ALLR,ARLR,LA4,P4,LC4)
:-
	.

% START of Possible player action
% --------------------------------------------------------------------------------------------------------------
%

%Direct Attack
questPathMainLoop_PlayerAction(GC,GL,AC,AL,AR,LA,P,LC,PF,ACReturn,ALReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action

	getCharNameFromList(AC,_,TarName), %select target (all character)
	append([relationship(direct_attack,player,TarName)],LA,LAReturn),
	ACReturn = AC,
	ALReturn = AL,
	P2 = P,
	LC2 = LC.
	
	
%Hire to Attack
questPathMainLoop_PlayerAction(GC,GL,AC,AL,AR,LA,P,LC,PF,ACReturn,ALReturn,ARReturn,LAReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action

	getCharNameFromList(AC,_,TarName), %select target (all character)
	getCharNameFromList(AC,_,AtName),  %select attacker (all character)
	append([relationship(hire_to_attack,player,AtName,TarName)],LA,LAReturn),
	ACReturn = AC,
	ALReturn = AL,
	P2 = P,
	LC2 = LC.
	
% Pickup / gather


	% CURRENT RELATIONSHIP
	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
	
	
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

player_Direct_to_Attack(TEST)
:-
	.
	
	
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

resolving(GC,GL,AC,AL,[],LA,P,LC,ACRS,ALRS,ARRS,LARS,PRS,LCRS)
:-
	false.


resolving(GC,GL,AC,AL,AR,[relationship(direct_attack,PLAYER,TarName)|LAT],P,LC,ACRS,ALRS,ARRS,LARS,PRS,LCRS)
:-
	append(direct_attack,TarName,P2),
	append(direct_attack,P2,P3),
	
	
	action_attack(AC,AL,AR,KillerName,TargetName,P,ACNew2),


	ACRS = AC,
	ALRS = AL,
	ARRS = ART,
	LARS = LAT,
	PRS = P3,
	LCRS = LC.
	
resolving(GC,GL,AC,AL,AR,[relationship(hire_to_attack,player,AtName,TarName)|LAT],P,LC,ACRS,ALRS,ARRS,LARS,PRS,LCRS)
:-
	append(direct_attack,TarName,P2)
	append(direct_attack,P2,P3)

	ACRS = AC,
	ALRS = AL,
	ARRS = ART,
	LARS = LAT,
	PRS = P3,
	LCRS = LC.
	

	%append([relationship(direct_attack,player,TarName)],AR,ARReturn),
	%append([relationship(hire_to_attack,player,AtName,TarName)],AR,ARReturn),
	
	
	
% *********************	FOR TESTING	*****************************
resolving([])
:- write("not working").

resolving([relationship(direct_attack,X,Y)])
:-
	write(X),
	nl,
	write(Y).

% RETURN TRUE == WORKING
%resolving([relationship(direct_attack,player,jack)]).

resolving([ARH|ART])
:-
	write("ABC"),
	ARH == relationship(_,_,_).

% RETURN FALSE
%resolving([relationship(direct_attack,player,jack),relationship(direct_attack,player,john)]).
	

resolving([ARH|ART])
:-
	(ARH == relationship(direct_attack,PLAYER,TarName) -> write("test"), write(TarName)
	;resolving(ART)).

% RETURN FALSE
%resolving([relationship(direct_attack,player,jack),relationship(direct_attack,player,john)]).


% END Resolve LOOP
% --------------------------------------------------------------------------------------------------------------











% Start  Condition Checking if character can perform certain action (character Ability, Relationship, etc.)
% --------------------------------------------------------------------------------------------------------------
%



action_attack(AC,AL,AR,KillerName,TargetName,P,ACNew2), :- 	
	getCharacterFromList(AC,KillerName,[NameKiller,A1Killer,V1Killer,A2Killer,V2Killer,ID]),
	getCharacterFromList(AC,TargetName,[NameTarget,isAlive,true,A2Target,V2Target,ID]),
	
	%if there friend at same place
	(
	;
	;
	;
	)
	delete(AC,[NameTarget,isAlive,true,A2Target,V2Target],ACNew),
	append(ACNew,[NameTarget,isAlive,false,A2Target,V2Target],ACNew2),

	write(KillerName),write(" kill "),write(TargetName).




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


	