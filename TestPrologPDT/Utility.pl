
% This is to get the string infront of the CHAR
split_string_atFirstChar(InputString,CHAR,OutputString) 
:-
	split_string(InputString,CHAR,"",[OutputString|Tail]).
		
		
			
%last([[test,a],[test,b]],X).
%last([abc,def,ght],X).

last([X],X).
last([_|Z],X) :- last(Z,X).
	
%secondLast([goto,market,true,player],X).		
%secondLast([goto,market,city,player],X).	
secondLast([X,_], X).
secondLast([_|T], X) :- secondLast(T, X).

%thirdLast([goto,market,city,player],X).	
thirdLast([X,_,_],X).
thirdLast([_|T],X) :- thirdLast(T, X).

%forthLast([goto,market,city,player],X).
forthLast([X,_,_,_],X).
forthLast([_|T],X) :- forthLast(T, X).
	
	
testTrueAsElement(X)
:-
	X = true.

	
%MUST TRUE
%memberlist([[a,b]],[[a,b],[a,c],[a,d]]).
%memberlist([[a,b],[a,c]],[[a,b],[a,c],[a,d]]).
	
%MUST FALSE
%memberlist([[a,b],[d,d]],[[a,b],[a,c],[a,d]]).
%memberlist([d,d],[[a,b],[a,c],[a,d]]).
%member([d,d],[[a,b],[a,c],[a,d]]).


elementAtN([H|_],0,H) :-
    !. 
elementAtN([_|T],N,H) :-
    N > 0, %add for loop prevention
    N1 is N-1,
    elementAtN(T,N1,H).


memberlist([],_).
memberlist([XH|XT],LIST)
:-
	member(XH,LIST),
	memberlist(XT,LIST).


%lengthList([a,b,c,d],A).
lengthList([],0).
lengthList([H|T],N)
:-
	% N is N2+1,      %***** IF this line is here, this will not work ******
	lengthList(T,N2),
	N is N2+1.
	
	
%sublist([a,b,c],[a,d,b,c,k]).	
sublist( [], _ ).
sublist( [X|XS], [X|XSS] ) :- sublist( XS, XSS ).
sublist( [X|XS], [_|XSS] ) :- sublist( [X|XS], XSS ).


	
starts_with_ac(Word):-
	atom_chars(Word,ATM),
    ATM = [a|ATM2],
    ATM2 = [c|ATM3],
    ATM3 = ['_'|_].
    
%   ATM = [First|ATM2],
%   ATM2 = [Second|ATM3],
%   ATM3 = [Third|_],
%   write(First),
%   write(Second),
%   write(Third).
	
	
	
%seperateActionPath([ac_bribe,test1,test2,ac_kill,jackOLanturn,ac_test],ANS).
%
seperateActionPath([],[]).
seperateActionPath([Word|P],[Word|P_ac])
:-
	starts_with_ac(Word),
	seperateActionPath(P,P_ac).

seperateActionPath([Word|P],P_ac)
:-
	\+starts_with_ac(Word),
	seperateActionPath(P,P_ac).



	
%seperateHalfFront([ac_bribe,test1,test2,ac_kill,jackOLanturn,ac_test],ANS).
%seperateHalfFront([ac_bribe,test1,test2,ac_kill,jackOLanturn,ac_test,a,b,ac_final],ANS).
seperateHalfFront(P,ANS)
:-
	length(P,P_length),
	P_length_double = P_length*2,
	seperateHalfFront_a(P_length_double,P_length,P,ANS).

seperateHalfFront_a(_,_,[],[]).
seperateHalfFront_a(P_length_double,Current_Position,[H|T],[H|ANS])
:-
	Current_Position2 = Current_Position+2,
	Current_Position < P_length_double,
	seperateHalfFront_a(P_length_double,Current_Position2,T,ANS).
	
seperateHalfFront_a(P_length_double,Current_Position,[H|T],ANS)
:-
	\+Current_Position < P_length_double,
	seperateHalfFront_a(P_length_double,Current_Position,T,ANS).
	
%---
%seperateHalfBack([ac_bribe,test1,test2,ac_kill,jackOLanturn,ac_test],ANS).
%seperateHalfBack([ac_bribe,test1,test2,ac_kill,jackOLanturn,ac_test,a,b,ac_final],ANS).
seperateHalfBack(P,ANS)
:-
	length(P,P_length),
	P_length_double = P_length*2,
	seperateHalfBack_a(P_length_double,P_length,P,ANS).

seperateHalfBack_a(_,_,[],[]).
seperateHalfBack_a(P_length_double,Current_Position,[H|T],[H|ANS])
:-
	\+Current_Position > P_length_double,
	Current_Position2 = Current_Position+2,
	seperateHalfBack_a(P_length_double,Current_Position2,T,ANS).
	
seperateHalfBack_a(P_length_double,Current_Position,[H|T],ANS)
:-
	Current_Position > P_length_double,
	seperateHalfBack_a(P_length_double,Current_Position,T,ANS).
	

	
	
	
seperateHalf_ac_only([],_,_,[]).
seperateHalf_ac_only(P,Old_Path,Size,ANS)
:-
	seperateActionPath(P,P_ac_only),
	seperateHalf_ac_only_1(P_ac_only,Old_Path,Size,ANS).
	
seperateHalf_ac_only_1(P_ac,Old_Path,Size,ANS)
:-
	superSlice(P_ac,Size,1,List_P_ac),
	seperateHalf_ac_only_2(P_ac,Old_Path,Size,ANS)
	
	
seperateHalf_ac_only_1(P_ac,Old_Path,Size,ANS)
	
isIn([a,b],[a,c,b],1).


%-------------------  Untility to seperate big list into smaller list   ------------------------
%------------------- EX: [a,b,c,d] >>> into [[a,b],[b,c],[c,d]] ---------------------

%superSlice( [a,b,c,d],2,1,ANS).
superSlice([],_,_,[]).
superSlice(L1,Size,StartPos,[A1|ANS])
:-
	E is StartPos + Size,
	EndPos is E-1,
	slice(L1,StartPos,EndPos,A1),
	NewStart is StartPos +1,
	superSlice(L1,Size,NewStart,ANS).

superSlice(L1,Size,StartPos,[])
:-
	length(L1,L1_L),
	L1_L2 is StartPos + Size,
	L1_L3 is L1_L2 -1,
	L1_L3 > L1_L.
	

%slice([3, 4, 5, 6, 7, 8], 1, 4, S2).
slice([X|_],1,1,[X]). 
slice([X|Xs],1,K,[X|Ys]) 
:- 
	K > 1, 
	K1 is K - 1,
	slice(Xs,1,K1,Ys). 
	
slice([_|Xs],I,K,Ys) 
:- 
	I > 1, 
	I1 is I - 1,
 	K1 is K - 1,
 	slice(Xs,I1,K1,Ys).



%isIn([a,b],[a,c,b],1).
%isIn([a,b],[a,b,d,c],1).
%isIn([a,c],[a,d,a,c],1).
isIn(List1,List2,Start)
:-
	length(List1,L),
	El is Start+L,
	EndPos is El -1,
	slice(List2,Start,EndPos,Ans),
	Ans == List1.

isIn(List1,List2,Start)
:-
	length(List1,L),
	El is Start+L,
	EndPos is El -1,
	slice(List2,Start,EndPos,Ans), 
	Ans \== List1,
	S2 is Start +1,
	isIn(List1,List2,S2).

%------------------------  End untility   -------------------------------
	
	
	
	
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
		

	
	
	
	
	
%------------------------------------------------------------------------------------						
% add_tail(+List,+Element,-List)
% Add the given element to the end of the list, without using the "append" predicate.
add_tail([],X,[X]).
add_tail([H|T],X,[H|L]):-add_tail(T,X,L).						
					
	
	
	
%------------------------------------------------------------------------------------							
%getFileName('c:/Users/user/Desktop/testWriteTo',1,OUT).
getFileName(NAME,N,OUT) % This *** WORK ***
:-
	(
	atom_concat(NAME,N,NAME2),
	atom_concat(NAME2,'.txt',NAME3),
	\+exists_file(NAME3) ->
	OUT = NAME3
	;
	N2 is N+1,
	getFileName(NAME,N2,OUT)
	).


make_directory_with_check(DI2)
:-
	(exists_directory(DI2) -> true
	;
	make_directory(DI2)
	).









%--------------------Start Quest's gamecondition related procedure-------------------------------------------------							



%//1st return 	case ("isAlive"):
%//2st return 	case ("currentLocation"):	
%//2st return 	case ("level"):	
%//2st return 	case ("listOccupation"):
%//2st return 	case ("listStatus")	:
%//2st return	case ("listSkill"):		
%------------------------------------------------------------------------------------------							
getCharacterFromList([[Name,A,B,C,D,E]|T],Name,[Name,A,B,C,D,E]).
getCharacterFromList([[Name,A,B,C,D,E]|T],Name2, Ans):- 
	%Name \== Name2, 
	getCharacterFromList(T,Name2,Ans).

%----TEST = TRUE ------	
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[kane,k1,k2,k3,k4,5]],kane,[A,B,C,D,E,F]).
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[kane,currentLocation,k2,k3,k4,k5]],kane,[A,currentLocation,C,D,E,F]).
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[kane,currentLocation,k2,k3,k4,k5]],T,[A,currentLocation,C,D,E,F]).


%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5]],_,[A,B,C,D,E]).	
%getCharacterFromList([[jack,a,b,c,d,e],[kane,1,2,3,4,5],[abel,x,y,z,a,b]],_,[A,B,C,D,E,F]).	
%getCharacterFromList(AC,TargetName,[NameTarget,"isAlive","true",A2Target,V2Target,ID])


getCharNameFromList([[Name,isAlive,B,C,D,E]|T],Name,Name).
getCharNameFromList([[Name,ELSE,B,C,D,E]|T],Name2,Ans)
:-
	%Name \== Name2, 
	getCharNameFromList(T,Name2,Ans).

%----TEST: below line = get all possible name
%getCharNameFromList([[jack,isAlive,12,z,zz,zzz],[ken,isAlive,b,c,d,e],[kane,isAlive,2,3,4,5]],_,A).

%----TEST: below line = get all possible name
%getACharName([[jack,isAlive,b,c,d,e],[kane,isAlive,2,3,4,5]],A).
getACharName(AC,A)
:-
	getCharNameFromList(AC,_,A).




getCharIsAliveFromList([[Name,isAlive,B,C,D,Z]|T],Name,B).
getCharIsAliveFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	%Name \== Name2, 
	getCharIsAliveFromList(T,Name2,Ans).		


getCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name,B).
getCharCurrentLocationFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	%Name \== Name2, 
	getCharCurrentLocationFromList(T,Name2,Ans).	



%getNonPlayerCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name,B)
%:-
%	Name \= player.
%getNonPlayerCharCurrentLocationFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
%:-
%	getNonPlayerCharCurrentLocationFromList(T,Name2,Ans).







getCharLevelFromList([[Name,level,B,C,D,Z]|T],Name,B).
getCharLevelFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	%Name \== Name2, 
	getCharLevelFromList(T,Name2,Ans).	

%getCharLevelFromList([[jack,level,10,c,d,e],[kane,1,2,3,4,5],[max,level,12,c,d,e],[kane,k1,k2,k3,k4,5]],jack,B).

% GET EVERY LEVEL
%getCharLevelFromList([[jack,level,10,c,d,e],[kane,1,2,3,4,5],[max,level,12,c,d,e],[kane,k1,k2,k3,k4,5]],_,B).
	
existCharListOccupationFromList([[Name,listOccupation,B,C,D,Z]|T],Name,B).
existCharListOccupationFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	%Name \== Name2, 
	existCharListOccupationFromList(T,Name2,Ans).	

%existCharListOccupationFromList([[jack,listOccupation,soldier,c,d,e],[kane,listOccupation,lord,3,4,5],[kane,k1,k2,k3,k4,5]],jack,soldier).
%existCharListOccupationFromList([[jack,listOccupation,soldier,c,d,e],[kane,listOccupation,lord,3,4,5],[kane,k1,k2,k3,k4,5]],kaane,lord).
	
%____existCharListStatusFromList(AC,player,poisoned).
	
existCharListStatusFromList([[Name,listStatus,B,C,D,Z]|T],Name,B).
existCharListStatusFromList([_|T],Name2,Ans)
:-
	%Name \== Name2, 
	existCharListStatusFromList(T,Name2,Ans).	
	
	
existCharListSkillFromList([[Name,listSkill,B,C,D,Z]|T],Name,B).
existCharListSkillFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	%Name \== Name2, 
	existCharListSkillFromList(T,Name2,Ans).		
	
	
	
getItemIDUsingName([[ANY,A,B,itemName,Name,Z]|T],Name,Z).
getItemIDUsingName([[ANY,A,B,C,Name,Z]|T],Name2,Ans)
:-
	%Name \== Name2, 
	getItemIDUsingName(T,Name2,Ans).	
	
%getItemIDUsingName([[a,b,c,itemName,berry,003],[a,b,c,itemName,berry,002],[a,b,c,item,berry,001],[a,b,c,itemName,axe,101]],berry,Z).	
getItemNameUsingID([[ANY,A,B,itemName,Name,Z]|T],Z,B).
getItemNameUsingID([[ANY,A,B,C,Name,Z]|T],Z2,Ans)
:-
	getItemNameUsingID(T,Z2,Ans).	

%getItemSpecificUsingID([[jack,a,b,isOnGround,berry,11],[john,a,b,itemName,berry,12]],isOnGround,11,A).
getItemSpecificUsingID([[ANY,A,B,SPEC,Name,Z]|T],SPEC,Z,[ANY,A,B,SPEC,Name,Z]).
getItemSpecificUsingID([[ANY,A,B,C,Name,Z]|T],SPEC,Z2,Ans)
:-
	getItemSpecificUsingID(T,SPEC,Z2,Ans).	

getItemNameFromList([[ANY,A,B,itemName,Name,Z]|T],Name,Name).
getItemNameFromList([[ANY,A,B,C,Name,Z]|T],Name2,Ans)
:-
	getItemNameFromList(T,Name2,Ans).	
	
%----TEST: below line = get all possible name
%getItemNameFromList([[jack,a,b,itemName,d,e],[kane,1,2,itemName,4,e]],_,A).



getItemCurrentHOLDERUsingID([[ANY,listItem,ItemName,ownerName,ANY,ItemID]|T],ItemID,ANY).
getItemCurrentHOLDERUsingID([[ANY,A,B,C,D,ItemID]|T],Z2,Ans)
:-
	getItemCurrentHOLDERUsingID(T,Z2,Ans).	


getItemOwnerNameUsingID([[ANY,listItem,ItemName,ownerName,D,Z]|T],Z,D).
getItemOwnerNameUsingID([[ANY,A,Name,C,D,Z]|T],Z2,Ans)
:-
	getItemOwnerNameUsingID(T,Z2,Ans).	


getItemIsOnGroundUsingID([[ANY,listItem,ItemName,isOnGround,D,Z]|T],Z,D).
getItemIsOnGroundUsingID([[ANY,A,Name,C,D,Z]|T],Z2,Ans)
:-
	getItemIsOnGroundUsingID(T,Z2,Ans).	





%changeItemIsOnGroundUsingID([[jack,a,b,isOnGround,no,11],[john,a,b,isOnGround,yes,12]],11,yes,A).
changeItemIsOnGroundUsingID(AC,ID,STATUS,ACRE)
:-
	getItemSpecificUsingID(AC,isOnGround,ID,[ANY,A,B,C,D,ID]),
	delete(AC,[ANY,A,B,isOnGround,D,ID],AC2),
	append(AC2,[[ANY,A,B,isOnGround,STATUS,ID]],ACRE).

	
	
	
	
existItemTypeOfItemUsingID([[ANY,A,Name,typeOfItem,D,Z]|T],Z,D).
existItemTypeOfItemUsingID([[ANY,A,Name,C,D,Z]|T],Z2,Ans)
:-
	existItemTypeOfItemUsingID(T,Z2,Ans).			
		

existItemTypeOfFunctionUsingID([[ANY,A,Name,typeOfFunction,D,Z]|T],Z,D).
existItemTypeOfFunctionUsingID([[ANY,A,Name,C,D,Z]|T],Z2,Ans)
:-
	existItemTypeOfFunctionUsingID(T,Z2,Ans).	


existItemListPropertyUsingID([[ANY,A,Name,listProperty,D,Z]|T],Z,D).
existItemListPropertyUsingID([[ANY,A,Name,C,D,Z]|T],Z2,Ans)
:-
	existItemListPropertyUsingID(T,Z2,Ans).	
 
 
 
existItemInLocationWithName([[L,A,B,itemName,NAME,Z]|T],L,NAME).
existItemInLocationWithName([[L,A,B,C,D,Z]|T],L2,Ans)	
:-
	existItemInLocationWithName(T,L2,Ans).	

existItemInLocationWithID([[L,A,B,itemName,NAME,ID]|T],L,ID).
existItemInLocationWithID([[L,A,B,C,D,Z]|T],L2,Ans)	
:-
	existItemInLocationWithID(T,L2,Ans). 			




getLocationNameFromList([[Name,locationName,B,C,D,Z]|T],Name,B).
getLocationNameFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	getLocationNameFromList(T,Name2,Ans).	

getLocationTypeFromList([[Name,locationType,B,C,D,Z]|T],Name,B).
getLocationTypeFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	getLocationTypeFromList(T,Name2,Ans).	
		
		
getLocationEnvironmentList([[Name,locationEnvironment,B,C,D,Z]|T],Name,B).
getLocationEnvironmentList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	getLocationEnvironmentList(T,Name2,Ans).	

		
		
	
		
existLocationListConnectLocationList([[Name,listConnectLocation,B,C,D,Z]|T],Name,B).
existLocationListConnectLocationList([[Name,A,B,C,D,Z]|T],Name2,Ans)
:-
	existLocationListConnectLocationList(T,Name2,Ans).	
	
	
	
	
	
%CHANGE TO MAKE IT PICK UP BOTH FROM NPC & LOCATION
	
getItemCurrentLocationUsingID(AC,ItmID,SOURCE) 
:-
	(
	getItemIsOnGroundUsingID(AC,ItmID,yes) -> 
		existItemInLocationWithID(AC,SOURCE,ItmID)
	;
	getItemCurrentHOLDERUsingID(AC,ItmID,HOLDER),
	SOURCE = HOLDER
	).


	
	
				
%------------------------------------------------------------------------------------							








%------------------------------------------------------------------------------------							

%existRelationship([[enemy,ken,ryu],[friend,john,jack],[friend,rock,ada]],friend,jack,john).
%existRelationship([[enemy,ken,ryu],[enemy,john,jack],[enemy,rock,ada]],friend,jack,john).
%existRelationship([[]],friend,jack,john).

%existRelationship(LIST,Relationship,A,B) 
existRelationship([[X,Y,Z]|T],Relationship,A,B) % *** WORKING ***
:-
	existRelationship_(T,[X,Y,Z],Relationship,A,B).

existRelationship_(_,[Relationship,A,B],Relationship,A,B).
existRelationship_(_,[Relationship,B,A],Relationship,A,B).
existRelationship_([H|T],[Relationship2,A2,B2],Relationship,A,B)
:-
	existRelationship_(T,H,Relationship,A,B).
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
		
	
	
%------------------------------------------------------------------------------------							

% *** EXAMPLE WORKING ***
%deleteRelationship([[kill,jack,jill],[kill,jill,jack],[friend,tony,hack],[enemy,ken,ron]],kill,jack,jill,RE).

deleteRelationship([],Relation,Char1,Char2,[]). 		% *** WORKING ***
deleteRelationship([[R,A,B]|T],Relation,Char1,Char2,Re)	% *** WORKING ***
:-
	A = Char1,
	B = Char2,
	R = Relation,
	deleteRelationship(T,Relation,Char1,Char2,Re).		
	
deleteRelationship([[R,A,B]|T],Relation,Char1,Char2,Re)	% *** WORKING ***
:-
	A = Char2,
	B = Char1,
	R = Relation,
	deleteRelationship(T,Relation,Char1,Char2,Re).
	
deleteRelationship([H|T],Relation,Char1,Char2,[H|Re])	% *** WORKING ***
:-deleteRelationship(T,Relation,Char1,Char2,Re).
	
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


%checkSpecialCondition([[jack,sameLocation,jill,z,zz,zzz]],[[jack,currentLocation,market,z,zz,zzz],[jill,currentLocation,market,z,zz,zzz]]).
%checkSpecialCondition([[jack,sameLocation,jill,z,zz,zzz]],[[jill,currentLocation,market,z,zz,zzz],[jack,currentLocation,market,z,zz,zzz]]).

%checkSpecialCondition([],[[jack,currentLocation,market,z,zz,zzz],[jill,currentLocation,market,z,zz,zzz]]).

checkSpecialCondition([],AC).
checkSpecialCondition([H|T],AC)
:-
	checkSpecialConditionIndividual(H,AC),
	checkSpecialCondition(T,AC).

checkSpecialConditionIndividual([Name,sameLocation,Name2,_,_,_],AC)
:-
	getCharCurrentLocationFromList(AC,Name,B),
	getCharCurrentLocationFromList(AC,Name2,B).	

checkSpecialConditionIndividual([Name,listStatusNOT,C,D,E,F],AC)
:-
	\+existCharListStatusFromList(AC,Name,C).



%itemNameNOT
checkSpecialConditionIndividual([Name,B,ItemName,itemNameNOT,E,ItemID],AC)
:-
	\+getItemCurrentHOLDERUsingID_Check(AC,[Name,B,ItemName,itemNameNOT,E,ItemID]).
	
%listPropertyNOT
checkSpecialConditionIndividual([Name,B,ItemName,listPropertyNOT,E,ItemID],AC)
:-
	\+existItemListPropertyUsingID_Check(AC,[Name,B,ItemName,listPropertyNOT,E,ItemID]).


%------------ Special condition check -------------------
getItemCurrentHOLDERUsingID_Check([[Name,B,ItemName,itemNameNOT,E,ItemID]|T],[Name,B,ItemName,itemNameNOT,E,ItemID]).
getItemCurrentHOLDERUsingID_Check([[ANY,A,B,C,D,ItemID]|T],Z2)
:-
	getItemCurrentHOLDERUsingID_Check(T,Z2).	
	
existItemListPropertyUsingID_Check([[Name,B,ItemName,listPropertyNOT,E,ItemID]|T],[Name,B,ItemName,listPropertyNOT,E,ItemID]).
existItemListPropertyUsingID_Check([[ANY,A,Name,C,D,Z]|T],Z2)
:-
	existItemListPropertyUsingID_Check(T,Z2).	
%------------ END Special condition check -------------------	




%getCharCurrentLocationFromList([[Name,currentLocation,B,C,D,Z]|T],Name,B).
%getCharCurrentLocationFromList([[Name,A,B,C,D,Z]|T],Name2,Ans)

%-------------------------------------	    
    
%-------------------------------------------------------------------------------------    	
%-------------------------END Checking if something exist in list------------------------------------    
%-------------------------------------------------------------------------------------       
       
   






%-------------------------------------------------------------------------------------    	
%-------------------------START list related Utility------------------------------------    
%-------------------------------------------------------------------------------------       

	
%This will try to remove all gameCondition with matched [ID,OriginOwner] 
%Then add new gameCondition that replace the deleted gameCondition with [NewOwner] as 'owner'

%precedure_moveItem([[jack,a,b,c,zzz,11],[jack,a,b,c,xxx,11],[kan,a,b,c,yyy,12]],11,jack,john,ANS).

%precedure_moveItem([
%[forest,listItem,poison_plant,itemName,poison_plant,101000],
%[forest,listItem,poison_plant,ownerName,null,101000],
%[forest,listItem,poison_plant,isOnGround,true,101000],
%[forest,listItem,poison_plant,currentLocation,forest,101000],
%[forest,listItem,poison_plant,typeOfItem,supply,101000],
%[forest,listItem,poison_plant,typeOfFunction,consumable,101000],
%[forest,listItem,poison_plant,itemName,poison_plant,101001],
%[forest,listItem,poison_plant,ownerName,null,101001],
%[forest,listItem,poison_plant,isOnGround,true,101001],
%[forest,listItem,poison_plant,currentLocation,forest,101001],
%[forest,listItem,poison_plant,typeOfItem,supply,101001],
%[forest,listItem,poison_plant,typeOfFunction,consumable,101001]]
%,101000,forest,jack,ANS).


precedure_moveItem([],ID,OriginOwner,NewOwner,[]). %*** WORKING ***

% This one is to make sure that the ownerName is changed to NewOwner
precedure_moveItem([[OriginOwner,listItem,C,ownerName,E,ID]|T],ID,OriginOwner,NewOwner,[[NewOwner,listItem,C,ownerName,NewOwner,ID]|T2])
:-	precedure_moveItem(T,ID,OriginOwner,NewOwner,T2).
	
%This is changing all Original Owner to NewOwner	
precedure_moveItem([[OriginOwner,B,C,D,E,ID]|T],ID,OriginOwner,NewOwner,[[NewOwner,B,C,D,E,ID]|T2])
:-	precedure_moveItem(T,ID,OriginOwner,NewOwner,T2).
	
	
precedure_moveItem([[ANY,B,C,D,E,ID2]|T],ID,OriginOwner,NewOwner,[[ANY,B,C,D,E,ID2]|T2])
:-	ID2 \= ID,
	precedure_moveItem(T,ID,OriginOwner,NewOwner,T2).	
	
	
precedure_moveItem([[ANY,B,C,D,E,ID]|T],ID,OriginOwner,NewOwner,[[ANY,B,C,D,E,ID]|T2])
:-	ANY \= OriginOwner,
	precedure_moveItem(T,ID,OriginOwner,NewOwner,T2).	
	
	
%-------------------------------------------------------------------------------------    	
%-------------------------START list related Utility------------------------------------    
%-------------------------------------------------------------------------------------       
  	
	
	

 
 
 
%-------------------------------------------------------------------------------------    	
%-------------------------START path related procedure------------------------------------    
%-------------------------------------------------------------------------------------       
   
% Get path from location X to Y.
    
route(AC,X,X,[],Avoid,0).
route(AC,X,Y,[H|T],Avoid,N)
:-	
	existLocationListConnectLocationList(AC,X,H),
	\+(member(H,Avoid)), 
	route(AC,H,Y,T,[H|Avoid],N2), 
	N is N2+1.







%-------------------------------------------------------------------------------------    	
%-------------------------START untility action------------------------------------    
%-------------------------------------------------------------------------------------       
    


 
 
%-------------------------------------------------------------------------------------    	
%-------------------------END untility action------------------------------------    
%-------------------------------------------------------------------------------------       
   
%seperateGoalType(
%[[player,isAlive,true,z,zz,zzz],[player,currentLocation,City,z,zz,zzz],[player,level,1,z,zz,zzz],[jack,isAlive,true,z,zz,zzz],[jack,currentLocation,City,z,zz,zzz],[jack,level,1,z,zz,zzz],[john,isAlive,true,z,zz,zzz],[john,currentLocation,City,z,zz,zzz],[john,level,1,z,zz,zzz],[mob_npc_1,isAlive,true,z,zz,zzz],[mob_npc_1,currentLocation,Jail,z,zz,zzz],[mob_npc_1,level,15,z,zz,zzz],[mob_npc_2,isAlive,true,z,zz,zzz],[mob_npc_2,currentLocation,City,z,zz,zzz],[mob_npc_2,level,15,z,zz,zzz],[city,locationName,city,z,zz,zzz],[city,locationEnvironment,sunny,z,zz,zzz],[city,listConnectLocation,jail,z,zz,zzz],[jail,locationName,jail,z,zz,zzz],[jail,listConnectLocation,city,z,zz,zzz]],
%[[player,sameLocation,jack,z,zz,zzz],[player,currentLocation,market,z,zz,zzz],[jack,sameLocation,john,z,zz,zzz]],
%A,B).

%seperateGoalType(GameWorld_Condition,Goal_Condition, Normal_Goal, Special_Goal).
seperateGoalType(_,[],[],[]).
seperateGoalType(AC,GC,NG,SG)
:-
	seperateGoalType1(AC,GC,NG),
	seperateGoalType2(AC,GC,SG).




%Normal Condition  (This will remove the special condition from the GC)
seperateGoalType1(_,[],[]).
seperateGoalType1(AC,[[ANYNAME,sameLocation,ANYNAME2,A,B,C]|AT],Ans)
:-
	seperateGoalType1(AC,AT,Ans).
seperateGoalType1(AC,[[ANYNAME,sameLevel,ANYNAME2,A,B,C]|AT],Ans)
:-
	seperateGoalType1(AC,AT,Ans).
seperateGoalType1(AC,[[ANYNAME,listStatusNOT,ANYNAME2,A,B,C]|AT],Ans)
:-
	seperateGoalType1(AC,AT,Ans).
seperateGoalType1(AC,[[ANYNAME,SOMETHING,ANYNAME2,itemNameNOT,B,C]|AT],Ans)
:-
	seperateGoalType1(AC,AT,Ans).	
seperateGoalType1(AC,[[ANYNAME,SOMETHING,ANYNAME2,listPropertyNOT,B,C]|AT],Ans)
:-
	seperateGoalType1(AC,AT,Ans).		

	
seperateGoalType1(AC,[H|AT],[H|Ans])
:-
	seperateGoalType1(AC,AT,Ans).


%Special Condition
seperateGoalType2(_,[],[]).
seperateGoalType2(AC,[[ANYNAME,sameLocation,ANYNAME2,A,B,C]|AT],[[ANYNAME,sameLocation,ANYNAME2,A,B,C]|Ans])
:-
	seperateGoalType2(AC,AT,Ans).

seperateGoalType2(AC,[[ANYNAME,sameLevel,ANYNAME2,A,B,C]|AT],[[ANYNAME,sameLevel,ANYNAME2,A,B,C]|Ans])
:-
	seperateGoalType2(AC,AT,Ans).


seperateGoalType2(AC,[[ANYNAME,listStatusNOT,ANYNAME2,A,B,C]|AT],[[ANYNAME,listStatusNOT,ANYNAME2,A,B,C]|Ans])
:-
	seperateGoalType2(AC,AT,Ans).
seperateGoalType2(AC,[[ANYNAME,SOMETHING,ANYNAME2,itemNameNOT,B,C]|AT],[[ANYNAME,SOMETHING,ANYNAME2,itemNameNOT,B,C]|Ans])
:-
	seperateGoalType2(AC,AT,Ans).
seperateGoalType2(AC,[[ANYNAME,SOMETHING,ANYNAME2,listPropertyNOT,B,C]|AT],[[ANYNAME,SOMETHING,ANYNAME2,listPropertyNOT,B,C]|Ans])
:-
	seperateGoalType2(AC,AT,Ans).	
seperateGoalType2(AC,[H|AT],Ans)
:-
	seperateGoalType2(AC,AT,Ans).
	



%seperateGoalType(AC,[AH|AT],[AH|GCT],GL)
%:-
%	seperateGoalType(AC,AT,GCT,GL).
%
%seperateGoalType(AC,[[ANYNAME,sameLocation,ANYNAME2,A,B,C]|AT],NG,[[ANYNAME,sameLocation,ANYNAME2,A,B,C]|GLT])
%:- 
% 	getCharNameFromList(AC,_,A),
%	getCharNameFromList(AC,ANYNAME2,B),
%	A \= B,
%	seperateGoalType(AC,AT,GC,GLT).
 


getOnlyIsAliveElement([],[]).
getOnlyIsAliveElement([[A,isAlive,C,D,E,F]|T],[[A,isAlive,C,D,E,F]|Return])
:-
	getOnlyIsAliveElement(T,Return).
getOnlyIsAliveElement([[A,B,C,D,E,F]|T],Return)
:-
	B \= isAlive,
	getOnlyIsAliveElement(T,Return).












 
 
 
 
 
 
%------------------------------------------------------------------------------------------		





%------------------------------------------------------------------------------------------		

%getCharList([[a,isAlive,c,d,e,f],[x,isAlive,c,d,e,f],[y,isAlive,c,d,e,f],[z,isAlive,c,d,e,f]],ANS).
getCharList([],[]).
getCharList([[A,isAlive,C,D,E,F]|T],[A|ANS])
:-
	getCharList(T,ANS).
getCharList([[A,B,C,D,E,F]|T],ANS)
:-
	B \= isAlive,
	getCharList(T,ANS).	





getCharPairINI(AC,ANS)
:-
	getOnlyIsAliveElement(AC,IL),
	getCharPair(IL,IL,ANS).
	
		
getCharPair([], _, []).
getCharPair(_, [], []).
getCharPair([H|T], AC, Ans) :-
	pair(H, AC, Ans1),
	getCharPair(T, AC, Ans2),
	append(Ans1, Ans2, Ans).
	
pair(_, [], []).
pair(A, [B|T], [(NameA,NameB)|Ans]) :-
	isAlive(A),
	isAlive(B),
	nameChar(A,NameA),
	nameChar(B,NameB),
	pair(A, T, Ans).	
	
pair(A, [_|T], Ans):-	
	\+isAlive(A),
	pair(A,T,Ans).	
pair(A,[B|T],Ans):-
	\+isAlive(B),
	isAlive(A),			%This line is added to avoid backtrack to above pair, then it jump here and create another answer from backtrack with identical answer
	pair(A,T,Ans).
	
	
isAlive([_, isAlive, _, _, _, _]).
nameChar([A,_,_,_,_,_],A).
		
	
	
		
		
% e.g. pairs([1,2,3,4,5],X)
%   gives X=[[1,2],[1,3],[1,4],[1,5],[2,3],[2,4],[2,5],[3,4],[3,5],[4,5]]
pairs(Xs, Ys):-pairs_1(Xs, Ys-[]).

pairs_1([], Ys-Ys).
pairs_1([X|Xs], Ys-As):-
  pairs_2(Xs, X, Ys-Bs),
  pairs_1(Xs, Bs-As).

pairs_2([], _, Zs-Zs).
pairs_2([X|Xs], Y, [[Y,X]|Zs]-As):-
  pairs_2(Xs, Y, Zs-As).		
		
% NOT WORKING BELOW		
		
%	getCharPair(AC,[],[],[]).
%	getCharPair(AC,[H|T],[],Ans)
%	:-
%		getCharPair(AC,T,AC,Ans).
%	getCharPair([AC,[A,isAlive,_,_,_,_]|T],[[B,isAlive,_,_,_,_]|N],[(A,B)|Ans])
%	:-
%		getCharPair(AC,[[A,isAlive,_,_,_,_]|T],N,Ans).
%		
%-------------------------------------------------------------------------------------		





%-------------------------------------------------------------------------------------		 
moveChar(AC,Char,DesLo,ACRE)
:-
	getCharCurrentLocationFromList(AC,CharA,LO),
%	existLocationListConnectLocationList(AC,LO,DesLo),  	% comment this out, so that this will teleport the char there directly. (for performance, isntead of looping until they reach the destination)

	delete(AC,[CharA,currentLocation,LO,z,zz,zzz],AC2),
	append(AC2,[[CharA,currentLocation,DesLo,z,zz,zzz]],AC3),
	
	ACRE = AC3. 
%-------------------------------------------------------------------------------------		




 
 
 
%-------------------------------------------------------------------------------------		
 
%getFromToPair([], _, []).
%getFromToPair(_, [], []).
getFromToPair(AC,Char,FROM,TO) :-
	getFromToPairFROM(AC,Char,FROM),
	getFromToPairTO(AC,FROM,TO).


%------------------
getFromToPairFROM([],_,_):-
	false.
	
getFromToPairFROM([H|T],CharName,FROM)
:-
	currentLocation(H),
	currentLocationName(H,CharName),
	currentLocationGet(H,FROM).
	
getFromToPairFROM([H|T],CharName,FROM)
:-
	getFromToPairFROM(T,CharName,FROM).
%------------------
	
	
%------------------	
getFromToPairTO([],_,_):-
	false.
	
getFromToPairTO([H|T],FROM,TO)
:-
	connectedLocation(H),
	connectedLocationOrigin(H,FROM),
	connectedLocationDestiny(H,TO).
	
getFromToPairTO([H|T],FROM,TO)
:-
	getFromToPairTO(T,FROM,TO).
%------------------	
 


currentLocation([_, currentLocation, _, _, _, _]). 
currentLocationName([Name, currentLocation, _, _, _, _],Name). 
currentLocationGet([_, currentLocation, RE, _, _, _],RE). 
 
connectedLocation([_, listConnectLocation, _, _, _, _]). 
connectedLocationOrigin([LoName, listConnectLocation, _, _, _, _],LoName). 
connectedLocationDestiny([_, listConnectLocation, LoName, _, _, _],LoName). 
 
 
 
 
 
 
 
 
 
 
 
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
						
		
		
		