:- module(testFile,[
		
	]).

	
	
	
%------------------------------------------------------------------------------------							
testProtocolJava(SomeQuery) :-
    protocol('C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt'),
	
	%%%% Sleep will freeze Eclispe Java too. When Eclipse Java query prolog, it will wait until prolog finish before continuing. %%%% 
	sleep(1),
	%%%%%%%
	
    getfirst(SomeQuery,ActorName),
    write(ActorName),
    noprotocol.		
    
    
       
   
   
    
    
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


writeFile(GC,GL,AC,AL,AR,LA,P,LC,PF)	
:-	
    open('file.txt',write, Stream),
    (  	writeln(PF),
    	writeln(Stream,AC), 
		writeln(Stream,AL),
		writeln(AR)
    ;   true
    ),
    close(Stream).  
 
%WORKING 
%writeFile([],[],[a,b,c],[kkk],[],[],[],[],[]).	    
 
 

writeFile(A,B)
:- 
	open('file.txt',write, Stream),
    (  	
    	writeln(Stream,A), 
		writeln(Stream,B)
    ;   true
    ),
    close(Stream).  

%writeFile([a,b,c],[kkk]).
%("writeFile","[a,b,c]","[kkk]");	
	
	
%testWriteToHere('c:/Users/user/Desktop/testWriteTo.txt').
testWriteToHere(A) % This *** WORK ***
:-
	open(A,write, Stream),
    (  	writeln(Stream,"write success with \"\" "),
    	writeln(Stream,'write sucess with \'\' ')
    ;   true
    ),
    close(Stream).

%testWriteToHere2('c:/Users/user/Desktop/testWriteTo.txt').
testWriteToHere2(A)
:-
	protocol(A),
	writeln(Stream,"write success with \"\" "),
    writeln(Stream,'write sucess with \'\' '),
	noprotocol.







%testFileExist('c:/Users/user/Desktop/testWriteTo.txt.').

testFileExist(A) % This *** WORK ***
:- exists_file(A).		

