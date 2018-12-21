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
startQuestPath(CO,GC,GL,AC,AL,AR,P,PF)
:-
	member(GC,AC),
	member(GL,AL).

startQuestPath(CO,GC,GL,AC,AL,AR,P,PF)
:-
	reconsult('c.txt'),
	LoopCounter = 1,
	append([kill_quest],[P],P2),
	
	protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/filePath.txt'),
	write('[Start Quest Analyzing Test]'),
	noprotocol,
	
	questPathMainLoop(GC,GL,AC,AL,AR,P2,LoopCounter,PF).
	
%Main loop to check if after 1 action and follow by resolve, the quest 
%goal is reached yet or not.	
questPathMainLoop(GC,GL,AC,AL,AR,P,LC,PF)	 
:-
	( 	questPathMainLoop_Done(GC,GL,AC,AL,AR,P,LC,PF) -> writeToFile(GC,GL,AC,AL,AR,P,LC,PF)
	;	questPathMainLoop_Continue(GC,GL,AC,AL,AR,P,LC,PF)
	).
	
questPathMainLoop_Done(GC,GL,AC,AL,AR,P,LC,PF) 
:-
	
	memberlist(GC,AC),
	memberlist(GL,AL),
	append("QUEST DONE",P,PF).
	
%This will write all condition to file
writeToFile(GC,GL,AC,AL,AR,P,LC,PF)	
:-
	.

	
	
%
questPathMainLoop_Continue(GC,GL,AC,AL,AR,P,LC,PF)
:-
	% Loop possible player action (player can only act 1 time, then wait until all NPC react to it and react to other NPC react too)
	questPathMainLoop_PlayerAction(GC,GL,AC,AL,AR,P,LC,PF,ACReturn,ALReturn,ARReturn,P2,LC2),
	% Loop resolve
	questPathMainLoop_Resolve(GC,GL,ACReturn,ALReturn,ARReturn,P2,LC2,ACRS,ALRS,ARRS,P3,LC3),
	% set back to main loop
	questPathMainLoop(GC,GL,ACRS,ALRS,ARRS,P3,LC3,PF).



questPathMainLoop_Resolve(GC,GL,AC,AL,AR,P,LC,ACRS,ALRS,ARRS,PRS,LCRS)
:-
	%If it's possible to resolve, loop until no longer possible to resolve
	(resolving(GC,GL,AC,AL,AR,P,LC,ACRS_2,ALRS_2,ARRS_2,P_2,LC_2) -> questPathMainLoop_Resolve(GC,GL,ACRS_2,ALRS_2,ARRS_2,P_2,LC_2,ACRS_B,ALRS_B,ARRS_B,PRS_B,LCRS_B)
	;ACRS = AC,
	ALRS = AL,
	ARRS = AR,
	PRS = P,
	LCRS = LC
	).
	
food(jelly).

	
student(a,b).


% START of Possible player action
% --------------------------------------------------------------------------------------------------------------
%

%Direct Attack
questPathMainLoop_PlayerAction(GC,GL,AC,AL,AR,P,LC,PF,ACReturn,ALReturn,ARReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	player_Direct_to_Attack(GC,GL,AC,AL,AR,P,LC,PF,ACReturn,ALReturn,ARReturn,P2,LC2).

	
	
	delete()
	
	
%Hire to Attack
questPathMainLoop_PlayerAction(GC,GL,AC,AL,AR,P,LC,PF,ACReturn,ALReturn,ARReturn,P2,LC2)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	player_Hire_to_Attack(GC,GL,AC,AL,AR,P,LC,PF,ACReturn,ALReturn,ARReturn,P2,LC2).
	
	
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

player_Direct_to_Attack(GC,GL,AC,AL,AR,P,LC,
						ACReturn,ALReturn,ARReturn,P2,LC2
						)
:-
	
	.
	
	
	
% END ACTUAL action by player
% --------------------------------------------------------------------------------------------------------------




% Start Resolve LOOP
% --------------------------------------------------------------------------------------------------------------

%GoalCharacter	= GC
%GoalLocation	= GL
%AllCharacter	= AC
%AllLocation	= AL
%AllRelation	= AR
%Path			= P
%LoopCounter 	= LC

%AllCharacterResolveReturn	= ACRS
%AllLocationResolveReturn	= ALRS
%AllRelationResolveReturn	= ARRS
%PathResolveReturn			= P3
%LoopCounterResolveReturn 	= LC3



resolving





% END Resolve LOOP
% --------------------------------------------------------------------------------------------------------------











% Start  Condition Checking if character can perform certain action (character Ability, Relationship, etc.)
% --------------------------------------------------------------------------------------------------------------
%



attack(AC,KillerName,TargetName,ACNew2) :- 	
	getCharacterFromList(AC,KillerName,[NameKiller,A1Killer,V1Killer,A2Killer,V2Killer]),
	getCharacterFromList(AC,TargetName,[NameTarget,"isAlive","true",A2Target,V2Target]),
	
	delete(AC,[NameTarget,"isAlive","true",A2Target,V2Target],ACNew),
	append(ACNew,[NameTarget,"isAlive","false",A2Target,V2Target],ACNew2),

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












	