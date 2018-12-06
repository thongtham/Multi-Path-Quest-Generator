testwrite11(Name) :- 	write(Name),
						character(Name,X,_,_),
						write(X).
					

healer(jack).

character(jack,15,alive,weak).
character(john,15,alive,healthy).					
						
happy(jack).
happy(jack,16).
happy(jack,16,alive).
happy(jack,16,alive,healthy).