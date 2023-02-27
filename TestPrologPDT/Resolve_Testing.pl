:- module(Resolve_Testing,[
		
	]).

	
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
	