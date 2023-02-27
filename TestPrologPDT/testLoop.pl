
%-------------------------------From [Clause and Effect] book P.25 ----------------

a(g,h).
a(g,d).
a(e,d).
a(h,f).
a(e,f).
a(a,e).
a(a,b).
a(b,f).
a(b,c).
a(f,c).
%a(d,a).


path(X,X).
path(X,Y) :- a(X,Z), path(Z,Y).

%path(a,c,[]).
path(X,X,X).
path(X,Y,T) :- a(X,Z), legal(Z,T), path(Z,Y,[Z|T]).

legal(Z,[]).
legal(Z,[H|T]) :- Z \== H, legal(Z,T).

%--------------------------Start my modification --------------------------


%path that also return the step taken (p.22)
pathRE(X,X,[X|T]).
pathRE(X,Y,[X|A]) :- a(X,Z), legal(Z,A),pathRE(Z,Y,A).
%pathRE(a,c,A).

%A = [a, e, f, c] ;
%A = [a, e, f, c, _4508114] ;
%A = [a, e, d, a, b, c] ;
%A = [a, e, f, c, _4508114, _4508120] ;



%https://stackoverflow.com/questions/13170401/find-all-possible-paths-without-revisiting
% BELOW = also printout path
% *** WORKING ***
routing(X, Y, [X, Y]) :- 
  a(X, Y).

% *** WORKING ***
routing(X, Y, [X|A]) :- 
  a(X, Z),
  routing(Z, Y, A).

%A = [a, e, f, c] ;
%A = [a, e, d, a, b, c] ;
%A = [a, e, d, a, e, f, c] ;
%A = [a, e, d, a, b, f, c] ;
%A = [a, e, d, a, e, d, a, b, c] 

%---------------------------------------------------------------------------------------




%lengthList([a,b,c,d],A).
lengthList([],0).
lengthList([H|T],N)
:-
	% N is N2+1,      %***** IF this line is here, this will not work ******
	lengthList(T,N2),
	N is N2+1.





%--------------------------Test on how force fail backtrack for new path work-------------------------------------------------------------


%---------------------------------------------
%questPathMainLoop([],P).
questPathMainLoop(A,P)	 
:-
	% Below = loop action, but never go to P5
	%
	questPathMainLoop_Done(A,P),
	writeToFile(A,P).

questPathMainLoop(A,P)
:-
	lengthList(A,N),
	N < 5,
	questPathMainLoop_Continue(A,P).
%---------------------------------------------	
	
	
%---------------------------------------------	
%questPathMainLoop(A,P)
%:-	
%	(questPathMainLoop_Done(A,P) -> writeToFile(A,P)
%	;questPathMainLoop_Continue(A,P)
%	).
%---------------------------------------------	
	
	

writeToFile(P,P).


questPathMainLoop_Done(P,P)
:-
	lengthList(P,3).

questPathMainLoop_Done(P,P)
:-
	lengthList(P,4).


%attack template
questPathMainLoop_Continue(A,P)
:-
	append(A,[attack],A2),
	questPathMainLoop(A2,P).

%Hire template	
questPathMainLoop_Continue(A,P)
:-
	append(A,[hire],A2),
	questPathMainLoop(A2,P).

%Heal template	
questPathMainLoop_Continue(A,P)
:-
	append(A,[heal],A2),
	questPathMainLoop(A2,P).	




%----------------------Test new theory THIS WORK---------------------------

%questPathMainLoop2([],P).
questPathMainLoop2(A,P)	 
:-
	% Below = loop action, but never go to P5
	%
	( 	questPathMainLoop_DoneSPC(A,P) -> writeToFile2(A,P)
	;	questPathMainLoop_Continue2(A,P)
	).
	
	%( 	questPathMainLoop_Done(A,P)
	%;	questPathMainLoop_Continue(A,P)
	%).
	

writeToFile2(P,P).

questPathMainLoop_DoneSPC([attack,attack,heal],P).

questPathMainLoop_Done2(P,P)
:-
	lengthList(P,2).

questPathMainLoop_Done2(P,P)
:-
	lengthList(P,3).


questPathMainLoop_Continue2(A,P)
:-
	% Loop possible player action (player can only act 1 time, then wait until all NPC react to it and react to other NPC react too)
	lengthList(A,L),
	L < 4,
	questPathMainLoop_PlayerAction2(A,P,A2,P2),
	questPathMainLoop2(A2,P2).

%attack template
questPathMainLoop_PlayerAction2(A,P,A2,P)
:-
	append(A,[attack],A2).

%Hire template	
questPathMainLoop_PlayerAction2(A,P,A2,P)
:-
	append(A,[hire],A2).

%Heal template	
questPathMainLoop_PlayerAction2(A,P,A2,P)
:-
	append(A,[heal],A2).
