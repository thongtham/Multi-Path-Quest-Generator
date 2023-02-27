

%testFolder([a,b,c,d],A).
testFolder(P,OUT)
:-
	counter(N),
	%retract(counter(N)), % no need to delete, the line about will retrive the most top counter anyway.
	N1 is N+1,
	asserta(counter(N1)),
	last(P,FileDepo),
	atom_concat('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file',FileDepo,NAME),
	getFileName(NAME,N1,OUT).
	
	
	
% -------------------------- Write to file -----------------------------
% DI = Directory
%testWriteFolder('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/',[a,b,a,c]).
%testWriteFolder('c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/',[[a],[b],[a]]).
testWriteFolder(DI,PATH)
:-
	last(PATH,X),
	write(X),
	atomic_list_concat(X,',',X2), % This will turn X ([a]) into X2 (a).
	write(X2),
	atom_concat(DI,X2,DI2),
	make_directory(DI2).

	