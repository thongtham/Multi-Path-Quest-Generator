way(a,b).
way(a,j).
way(b,a).
way(b,f).

way(c,d).
way(c,e).

way(d,c).
way(d,g).

way(e,f).
way(e,c).

way(f,b).
way(f,e).
way(f,j).

way(g,d).
way(g,i).
way(g,h).

way(h,k).
way(h,g).
way(h,i).

way(i,g).
way(i,h).

way(j,a).
way(j,f).
way(j,k).

way(k,j).
way(k,h).





member(X,[X|T]).
member(X,[H|T]):- not(X==H), member(X,T).

route(X,X,[],Avoid,0).
route(X,Y,[],Avoid,1):- X\== Y, way(X,Y).
route(X,Y,[H|T],Avoid,N):-	way(X,H), 
							\+(member(H,Avoid)), 
							route(H,Y,T,[H|Avoid],N2), 
							N is N2+1.


shortestRoute(X,Y,L1,N):-  route(X,Y,L1,[X],N),\+(route(X,Y,L2,[X],N2), L2\== L1, N2<N). 








state(0, 0, right).


% solution(CurrentState, Visited, Path)

solution(state(0, 0, right), _, []).
solution(CurrentState, Visited, [Move | RestOfMoves]) :-
	newstate(CurrentState, NextState),
	not(member(NextState, Visited)),
	make_move(CurrentState, NextState, Move),
	solution(NextState, [NextState | Visited], RestOfMoves).






make_move(state(M1, C1, left), state(M2, C2, right), move(M, C, right)) :-
	M is M1 - M2,
	C is C1 - C2.
make_move(state(M1, C1, right), state(M2, C2, left), move(M, C, left)) :-
	M is M2 - M1,
	C is C2 - C1.




legal(X, X).
legal(3, X).
legal(0, X).

newstate(state(M1, C1, left), state(M2, C2, right)) :-
	carry(M, C),
	M <= M1,
	C <= C1,
	M2 is M1 - M,
	C2 is C1 - C,
	legal(M2, C2).
newstate(state(M1, C1, right), state(M2, C2, left)) :-
	carry(M, C),
	M2 is M1 + M,
	C2 is C1 + C,
	M2 <= 3,
	C2 <= 3,
	legal(M2, C2).








