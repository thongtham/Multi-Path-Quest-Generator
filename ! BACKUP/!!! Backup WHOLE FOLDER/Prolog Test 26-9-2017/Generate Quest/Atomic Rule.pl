


%Knowledge Deliver item for study 			<get> <goto> give
%Spy 										<spy>
%Interview NPC 								<goto> listen <goto> report
%Use an item in the eld 					<get> <goto> use <goto> <give>
%Comfort Obtain luxuries 					<get> <goto> <give>
%Kill pests <goto> damage 					<goto> report
%Reputation Obtain rare items 				<get> <goto> <give>
%Kill enemies								<goto> <kill> <goto> report
%Visit a dangerous place 					<goto> <goto> report
%Serenity Revenge, Justice 					<goto> damage
%Capture Criminal(1) 						<get> <goto> use <goto> <give>
%Capture Criminal(2) 						<get> <goto> use capture <goto> <give>
%Check on NPC(1) 							<goto> listen <goto> report
%Check on NPC(2) 							<goto> take <goto> give
%Recover lost//stolen item 					<get> <goto> <give>
%Rescue captured NPC 						<goto> damage escort <goto> report
%Protection Attack threatening entities 	<goto> damage <goto> report
%Treat or repair (1) 						<get> <goto> use
%Treat or repair (2) 						<goto> repair
%Create Diversion 							<get> <goto> use
%Create Diversion 							<goto> damage
%Assemble fortication 					<goto> repair
%Guard Entity 								<goto> defend
%Conquest Attack enemy 						<goto> damage
%Steal stu 								<goto> <steal> <goto> give
%Wealth Gather raw materials 				<goto> <get>
%Steal valuables for resale 				<goto> <steal>
%Make valuables for resale 					repair
%Ability Assemble tool for new skill 		repair use
%Obtain training materials 					<get> use
%Use existing tools 						use
%Practice combat 							damage
%Practice skill 							use
%Research a skill(1) 						<get> use
%Research a skill(2) 						<get> experiment
%Equipment Assemble 						repair
%Deliver supplies 							<get> <goto> <give>
%Steal supplies 							<steal>
%Trade for supplies 						<goto> exchange



%1. <subquest> ::= <goto> 											Subquest could be just to go someplace.
%2. <subquest> ::= <goto> <QUEST> goto 								Go perform a quest and return.
%3. <goto> ::= + 													You are already there.
%4. <goto> ::= explore 												Just wander around and look.
%5. <goto> ::= <learn> goto 										Find out where to go and go there.
%6. <learn> ::= + 													You already know it.
%7. <learn> ::= <goto> <subquest> listen 							Go someplace, perform subquest, get info from NPC.
%8. <learn> ::= <goto> <get> read 									Go someplace, get something, and read what is written on it.
%9. <learn> ::= <get> <subquest> give listen 						Get something, perform subquest, give to NPC in return for info.
%10. <get> ::= + 													You already have it.
%11. <get> ::= <steal> 												Steal it from somebody.
%12. <get> ::= <goto> gather 										Go someplace and pick something up that's lying around there.
%13. <get> ::= <goto> <get> <goto> <subquest> exchange				Go someplace, get something, do a subquest for somebody, return and exchange.
%14. <steal> ::= <goto> stealth take 								Go someplace, sneak up on somebody, and take something.
%15. <steal> ::= <goto> <kill> take 								Go someplace, kill somebody and take something.
%16. <spy> ::= <goto> spy <goto> report 							Go someplace, spy on somebody, return and report.
%17. <capture> ::= <get> <goto> capture 							Get something, go someplace and use it to capture somebody.
%18. <kill> ::= <goto> kill 										Go someplace and kill somebody.


%	[Knowledge: Deliver item for study]
%	<get> <goto> give


%3. <goto> ::= + 													
%4. <goto> ::= explore 												
%5. <goto> ::= <learn> goto 


test(X) :- X == 

generate([NewRule,ListToBeGenerate],Generated) :- 	getAtomic(NewRule,ReturnAtom),
													
													
													
													generate(ListToBeGenerate,[,Generated]).
										
										
										
getAtomic(NewRule,ReturnAtom) :-	(NewRule == goto	
									;
									;
									).
									
									
									
									
									
									
									

















