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
					
	
	
	
	
%------------------------------------------------------------------------------------							
getCharacterFromList([[Name,A,B,C,D,E]|T],Name,[Name,A,B,C,D,E]).
getCharacterFromList([[Name,A,B,C,D,E]|T],Name2, Ans):- 
	Name \== Name2, 
	getCharacterFromList(T,Name2,Ans).

%----TEST = TRUE ------	
%getCharacterFromList([[jack,a,b,c,d],[kane,1,2,3,4]],kane,[A,B,C,D,E]).
%getCharacterFromList([[jack,a,b,c,d],[kane,1,2,3,4]],_,[A,B,C,D,E]).	
%getCharacterFromList([[jack,a,b,c,d],[kane,1,2,3,4],[abel,x,y,z,a]],_,[A,B,C,D,E]).	


getNameFromList([[Name,A,B,C,D,E]|T],Name,Name).
getNameFromList([[Name,A,B,C,D,E]|T],Name2,Ans)
:-
	Name \== Name2,
	getNameFromList(T,Name2,Ans).

%----TEST: below line = get all possible name
%getNameFromList([[jack,a,b,c,d],[kane,1,2,3,4]],_,A).
		
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
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,a,a,z]],[bob,_,_,_,_,_]).

% ---- FALSE
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,a,a,_]],[bob,isAlive,true,_,_,_]).
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,_,_,_]],[bob,isAlive,true,_,_,_]).
%characterCheck([[jack,isAlive,true,_,_,_],[bob,isAlive,true,_,_,_]],[bob,isAlive,true,a,a,a]).

%------------------------------------------------------------------------------------							
	
	
	
	
	
	
	
	
	
%------------------------------------------------------------------------------------							
testProtocolJava(SomeQuery) :-
    protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt'),
	
	%%%% Sleep will freeze Eclispe Java too. When Eclipse Java query prolog, it will wait until prolog finish before continuing. %%%% 
	sleep(1),
	%%%%%%%
	
    getfirst(SomeQuery,ActorName),
    write(ActorName),
    noprotocol.		
    
    
    
    
    
    
    
    
    
    
checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current == location(X),
	location(ANY) \= Current,
	checkpathrepeat(ANY,RestOfPath).
	
checknotpathrepeat(ANY,[Current|RestOfPath]) :-
	Current \= location(X).
	   
    
    
    
    
writeTest()
:-
	reconsult('b.txt'),
	tell('a.txt'),
	listing(test/1),
	listing(friend/1),
	listing(enemy/2),
	told.   
    
friend(a).
friend(k).
enemy(a,b).    
    