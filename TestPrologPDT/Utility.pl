:- module(Utility,[
		split_string_atChar/3
		memberlist/2
	]).

	% This is to get the string infront of the CHAR
	split_string_atFirstChar(InputString,CHAR,OutputString) 
		:-
			split_string(InputString,CHAR,"",[OutputString|Tail])
			.
		
		
			
	%member([a,b],[[a,b],[a,c],[a,d]]).					= true
	%
	%memberchk([a,b],[[a,b],[a,c],[a,d]]).        		= true
	%
	%member([[a,b],[a,c]],[[a,b],[a,c],[a,d]]).   		= false
	%
	%memberchk([[a,b],[a,c]],[[a,b],[a,c],[a,d]]).   	= false
	
	
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
	write(XH),
	nl,
	member(XH,LIST),
	memberlist(XT,LIST).
	

memberlistInlist([],_).
memberlistInlist(X,[[HeadInside|TailInside]|Tail])	
:-
	X == HeadInside.
	
memberlistInlist(X,[Head|Tail])
:-
	member(X,Head).	