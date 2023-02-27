:- module(Utility,[
		split_string_atChar/3,
		memberlist/2,
		remove_list/3
	]).

% This is to get the string infront of the CHAR
split_string_atFirstChar(InputString,CHAR,OutputString) 
:-
	split_string(InputString,CHAR,"",[OutputString|Tail]).
		
		
			
	
	
	%MUST TRUE
	%memberlist([[a,b]],[[a,b],[a,c],[a,d]]).
	%memberlist([[a,b],[a,c]],[[a,b],[a,c],[a,d]]).
	
	%MUST FALSE
	%memberlist([[a,b],[d,d]],[[a,b],[a,c],[a,d]]).
	%memberlist([d,d],[[a,b],[a,c],[a,d]]).
	%member([d,d],[[a,b],[a,c],[a,d]]).

memberlist([],_).
memberlist([XH|XT],LIST)
:-
	member(XH,LIST),
	memberlist(XT,LIST).
	
	
	
%----------------- OBSOLETE ------------------------------
memberlistInlist([],_).
memberlistInlist(X,[[HeadInside|TailInside]|Tail])	
:-
	(X == HeadInside -> true
	;memberlistInlist(X,[TailInside|Tail])	
	).
	
	
memberlistInlist(X,[Head|Tail])
:-
	member(X,Head).	
%----------------- OBSOLETE END ----------------------------	
	
	
	
	
%----------------- Just get some info ----------------------------		
getfirst([ActorName|RestOfList],ActorName).
getsecond([_,TargetName|RestOfList],TargetName).
getthird([_,_,E|_], E).
getforth([_,_,_,E|_], E).
	
	
	
	
%---------------------Write to file and read file----------------------------			
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
						
		
		
		
		
		
%-----------------------------Remove all element in a list from a list---------------------------------	
remove_list([], _, []).
remove_list([X|Tail], L2, Result)
:- 
	member(X, L2),
	!, 
	remove_list(Tail, L2, Result). 
remove_list([X|Tail], L2, [X|Result])
:- 
	remove_list(Tail, L2, Result).		
		

delete(AllItem,item(provision,food),AllItemNew).   %% NOTE: delete/3 will delete ALL match instants
	
	
	
	
	
%------------------------------------------------------------------------------------						
% add_tail(+List,+Element,-List)
% Add the given element to the end of the list, without using the "append" predicate.
add_tail([],X,[X]).
add_tail([H|T],X,[H|L]):-add_tail(T,X,L).						
					
	
	
	

%//1st return 	case ("isAlive"):
%//2st return 	case ("currentLocation"):	
%//2st return 	case ("level"):	
%//2st return 	case ("listOccupation"):
%//2st return 	case ("listStatus")	:
%//2st return	case ("listSkill"):		
%------------------------------------------------------------------------------------							
getCharacterFromList([[Name,A,B,C,D,E]|T],Name,[Name,A,B,C,D,E]).
getCharacterFromList([[Name,A,B,C,D,E]|T],Name2, Ans):- 
	Name \== Name2, 
	getCharacterFromList(T,Name2,Ans).

%----TEST = TRUE ------	
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[kane,k1,k2,k3,k4,5]],kane,[A,B,C,D,E,F]).
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[kane,currentLocation,k2,k3,k4,k5]],kane,[A,currentLocation,C,D,E,F]).
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[kane,currentLocation,k2,k3,k4,k5]],T,[A,currentLocation,C,D,E,F]).


%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5]],_,[A,B,C,D,E]).	
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[abel,x,y,z,a,b]],_,[A,B,C,D,E,F]).	
%getCharacterFromList(AC,TargetName,[NameTarget,"isAlive","true",A2Target,V2Target,ID])


getCharNameFromList([[Name,A,B,C,D,E]|T],Name,Name).
getCharNameFromList([[Name,A,B,C,D,E]|T],Name2,Ans)
:-
	Name \== Name2,
	getCharNameFromList(T,Name2,Ans).

%----TEST: below line = get all possible name
%getCharNameFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5]],_,A).


getCharIsAliveFromList([[Name,isAlive,B,C,D,Z]|T],Name,B).
getCharIsAliveFromList([[Name,isAlive,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getCharIsAliveFromList(T,Name2,Ans).		

getCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name,B).
getCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getCharCurrentLocationFromList(T,Name2,Ans).	


getCharLevelFromList([[Name,level,B,C,D,Z]|T],Name,B).
getCharLevelFromList([[Name,level,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getCharCurrentLocationFromList(T,Name2,Ans).	

%getCharLevelFromList([[jack,level,10,c,d,e],[kane,1,2,3,4,5],[kane,k1,k2,k3,k4,5]],jack,B).

	
existCharListOccupationFromList([[Name,listOccupation,B,C,D,Z]|T],Name,B).
existCharListOccupationFromList([[Name,listOccupation,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	existCharListOccupationFromList(T,Name2,Ans).	

%existCharListOccupationFromList([[jack,listOccupation,soldier,c,d,e],[kane,listOccupation,lord,3,4,5],[kane,k1,k2,k3,k4,5]],jack,soldier).
%existCharListOccupationFromList([[jack,listOccupation,soldier,c,d,e],[kane,listOccupation,lord,3,4,5],[kane,k1,k2,k3,k4,5]],kane,lord).
	
	
existCharListStatusFromList([[Name,listStatus,B,C,D,Z]|T],Name,B).
existCharListStatusFromList([[Name,listStatus,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	existCharListStatusFromList(T,Name2,Ans).	
	
	
existCharListSkillFromList([[Name,listSkill,B,C,D,Z]|T],Name,B).
existCharListSkillFromList([[Name,listSkill,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	existCharListSkillFromList(T,Name2,Ans).		
	
	
	
	
	

getItemNameUsingID([[ANY,A,B,itemName,Name,Z]|T],Z,B).
getItemNameUsingID([[ANY,A,B,itemName,Name,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	getItemNameUsingID(T,Z2,Ans).	
	
	
getItemNameFromList([[ANY,A,B,itemName,Name,Z]|T],Name,Name).
getItemNameFromList([[ANY,A,B,itemName,Name,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getItemNameFromList(T,Name2,Ans).	
	
%----TEST: below line = get all possible name
%getItemNameFromList([[jack,a,b,itemName,d,e],[kane,1,2,itemName,4,e]],_,A).
	


getItemCurrentLocationUsingID([[ANY,A,Name,currentLocation,D,Z]|T],Z,D).
getItemCurrentLocationUsingID([[ANY,A,Name,currentLocation,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	getItemCurrentLocationUsingID(T,Z2,Ans).	


getItemOwnerNameUsingID([[ANY,A,Name,ownerName,D,Z]|T],Z,D).
getItemOwnerNameUsingID([[ANY,A,Name,ownerName,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	getItemOwnerNameUsingID(T,Z2,Ans).	


getItemIsOnGroundUsingID([[ANY,A,Name,isOnGround,D,Z]|T],Z,D).
getItemIsOnGroundUsingID([[ANY,A,Name,isOnGround,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	getItemIsOnGroundUsingID(T,Z2,Ans).	


getItemIsOnGroundUsingID([[ANY,A,Name,isOnGround,D,Z]|T],Z,D).
getItemIsOnGroundUsingID([[ANY,A,Name,isOnGround,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	getItemIsOnGroundUsingID(T,Z2,Ans).	



existItemTypeOfItemUsingID([[ANY,A,Name,typeOfItem,D,Z]|T],Z,D).
existItemTypeOfItemUsingID([[ANY,A,Name,typeOfItem,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	existItemTypeOfItemUsingID(T,Z2,Ans).			
		

existItemTypeOfFunctionUsingID([[ANY,A,Name,typeOfFunction,D,Z]|T],Z,D).
existItemTypeOfFunctionUsingID([[ANY,A,Name,typeOfFunction,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	existItemTypeOfFunctionUsingID(T,Z2,Ans).	


existItemListPropertyUsingID([[ANY,A,Name,listProperty,D,Z]|T],Z,D).
existItemListPropertyUsingID([[ANY,A,Name,listProperty,D,Z]|T],Z2,Ans)
:-
	Z \== Z2,
	existItemListPropertyUsingID(T,Z2,Ans).	
 			


getLocationNameFromList([[Name,locationName,B,C,D,Z]|T],Name,B).
getLocationNameFromList([[Name,locationName,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getLocationNameFromList(T,Name2,Ans).	

getLocationTypeFromList([[Name,locationType,B,C,D,Z]|T],Name,B).
getLocationTypeFromList([[Name,locationType,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getLocationTypeFromList(T,Name2,Ans).	
		
		
getLocationEnvironmentList([[Name,locationEnvironment,B,C,D,Z]|T],Name,B).
getLocationEnvironmentList([[Name,locationEnvironment,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	getLocationEnvironmentList(T,Name2,Ans).	

		
		
		
existLocationListConnectLocationList([[Name,listConnectLocation,B,C,D,Z]|T],Name,B).
existLocationListConnectLocationList([[Name,listConnectLocation,B,C,D,Z]|T],Name2,Ans)
:-
	Name \== Name2,
	existLocationListConnectLocationList(T,Name2,Ans).	
				

		
		
%------------------------------------------------------------------------------------							
	
	
	
	
%------------------------------------------------------------------------------------							
	
% characterCheck ใช้เป็น List in List เพื่อที่จะได้แยก "Jack:isAlive:true" ได้ง่ายขิ้นไม่ต้องเขียน prolog ให้แยก string ที่จุด ":" 
% โดยจะต้องเขียนให้ต้อง query จาก Java นั้นแปลงข้อมูลจาก  "Jack:isAlive:true" ให้เป็น [Jack,isAlive,true,z,z] ด้วย
characterCheck([[CharacterName,Attribute1,Value1,Attribute2,Value2,ID]|TailMain],
				[NameAsk,Attribute1Ask,Value1Ask,Attribute2Ask,Value2Ask,IDAsk])
:-
	%write(CharacterName), write(NameAsk),nl,
	%write(Attribute1), write(Attribute1Ask),nl,
	%write(Value1), write(Value1Ask),nl,
	%write(Attribute2), write(Attribute2Ask),nl,
	%write(Value2), write(Value2Ask),nl,
	((CharacterName == NameAsk,
	Attribute1 == Attribute1Ask,
	Value1 == Value1Ask,
	Attribute2 == Attribute2Ask,
	Value2 == Value2Ask,
	ID == IDAsk) -> true
	;characterCheck(TailMain,[NameAsk,Attribute1Ask,Value1Ask,Attribute2Ask,Attribute2Ask,IDAsk])
	).

% ---- TRUE
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,a,a,z]],[bob,isAlive,true,a,a,z]).

% ---- FALSE
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,a,a,_]],[bob,isAlive,true,_,_,_]).
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,_,_,_]],[bob,isAlive,true,_,_,_]).
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,_,_,_]],[bob,isAlive,true,a,a,a]).

%------------------------------------------------------------------------------------							
	
	
	
	
%------------------------------------------------------------------------------------							
		
checksamelocation(AC,NPC1Name,NPC2Name) 
:-
	getCharacterFromList(AC,NPC1Name,[NPC1Name,currentLocation,B1,C1,D1,E1]),
	getCharacterFromList(AC,NPC2Name,[NPC2Name,currentLocation,B2,C2,D2,E2]),
	NPC1Location == NPC2Location.
%------------------------------------------------------------------------------------							

		
%------------------------------------------------------------------------------------							
changelocation(MoveName,NewLocation) :-
	passkeymovecheck(MoveName,NewLocation),
	retract(character(MoveName,TargetLvl,TargetStatus,TargetHealth,TargetLocation)),
	assert(character(MoveName,TargetLvl,TargetStatus,TargetHealth,NewLocation)).
%------------------------------------------------------------------------------------							
		

%------------------------------------------------------------------------------------							
passkeymovecheck(MoveName,NewLocation) :-	
	character(MoveName,TargetLvl,TargetStatus,TargetHealth,TargetLocation),
	passkeycheck(NewLocation,TargetLocation,dungeonkey).
%------------------------------------------------------------------------------------							
		
	
	
	
	
	
%-------------------------------------------------------------------------------------    	
%-------------------------Checking if something exist in list------------------------------------    
%-------------------------------------------------------------------------------------       
       
    
%-------------------------------------    
checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current == location(X),
	location(ANY) \= Current,
	checkpathrepeat(ANY,RestOfPath).
	
checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current \= location(X).
%-------------------------------------

	   
	   
	   
	   
%-------------------------------------
checkSpecialConditionIndividual([Name,sameLocation,Name2,_,_,_],AC)
:-
	getCharCurrentLocationFromList(AC,Name,B),
	getCharCurrentLocationFromList(AC,Name2,B).	

checkSpecialCondition([],AC).
% INPUT example: checkSameLocation(List_of_charPair,AC)
checkSpecialCondition([H|T],AC)
:-
	checkSpecialConditionIndividual(H,AC),
	checkSpecialCondition(T,AC).
%-------------------------------------	    
    
%-------------------------------------------------------------------------------------    	
%-------------------------END Checking if something exist in list------------------------------------    
%-------------------------------------------------------------------------------------       
       
   
 
 
 
 
 
 
 
 
 
 
 
 