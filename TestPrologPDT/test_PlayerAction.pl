
	
questPathMainLoop_PlayerAction1(AC,AR,LA,P,LC,LA2)
:-
	%player must not be captured
	\+existCharListStatusFromList(AC,player,captured),
	
	%can only goto adjecent location
	existLocationListConnectLocationList(AC,FROM,DesLo),
	getCharCurrentLocationFromList(AC,player,FROM),
%	write(TO),
%	getFromToPair(AC,player,FROM,DesLo),

	append([relationship(goto,player,FROM,DesLo)],LA,LA2).

	


validMovePath(AC,LA,LA2)
:-
	%player must not be captured
	\+existCharListStatusFromList(AC,player,captured),
	
	%can only goto adjecent location
	getCharCurrentLocationFromList(AC,player,FROM),
	existLocationListConnectLocationList(AC,FROM,DesLo),

	append([relationship(goto,player,FROM,DesLo)],LA,LA2).	
	