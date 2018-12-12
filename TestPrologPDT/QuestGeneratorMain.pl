:- module(QuestGeneratorMain,[
							
							testQuestGameState/3
							
							
							
							
							]).


startQuestPath(COMPONENT,GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path) 
:-
	%member(Goal,AllCharacter),
	LoopCounter = 1,
	append([kill_quest],[Path],Path2),
	
	protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/filePath.txt'),
	write('[Start Quest Analyzing Test]'),
	noprotocol,
	
	questPathMainLoop(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path2,LoopCounter).	
	
	
questPathMainLoop(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter)	 
:-
	( 	questPathMainLoop_Done(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter) -> true
	;	questPathMainLoop_Continue(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter)
	).
	
%questPathMainLoop_Done([GoalCharHead|GoalCharTail],[GoalLoHead|GoalLoTail],AllCharacter,AllLocation,AllRelation,Path2,LoopCounter) 
questPathMainLoop_Done(GoalCharacterList,GoalLocationList,AllCharacter,AllLocation,AllRelation,Path2,LoopCounter) 
:-
	memberlist(GoalCharacterList,AllCharacter),
	memberlist(GoalLocationList,AllLocation),
	append("QUEST DONE",Path,Path2).
	

	
questPathMainLoop_Continue(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter)	
:-
	questPathMainLoop_PlayerAction(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter).


% START of Possible player action
% --------------------------------------------------------------------------------------------------------------
%



%Direct Attack
questPathMainLoop_PlayerAction(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	player_Direct_to_Attack(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter).
	
	
%Hire to Attack
questPathMainLoop_PlayerAction(GoalCharacter,GoalLocation,AllCharacter,AllLocation,AllRelation,Path,LoopCounter)
:-
	% 1st check if player can perform the action
	
	% 2nd call the proper related action
	player_Hire_to_Attack().	
	
	
% Pickup / gather

% Give

% Take (from corpse)

% Pickpocket (is stealth)
% Putpocket (reverse-pickpocket, is stealth)

% Goto

% SHOULD DO SPELL INTERACTION TOO, ex, water spell stop fire????
% EACH HAS 2 VERSION, target self or target other
% Spell (heal)
% Spell (fire)
%

% SHOULD DO SPELL INTERACTION TOO, ex, water spell stop fire????
% EACH HAS 2 VERSION, target self or target other
% Use item(potion)
% Use item(poison)
% Use item(berry)
%
%
% 
%
%

	
	
	
	
	
	
	
	

% END of Possible player action
% --------------------------------------------------------------------------------------------------------------
%









	
		