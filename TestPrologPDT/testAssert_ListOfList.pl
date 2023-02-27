
counter111(0).

testAssertLOL(AC)
:-
%	counter111(AC),			% If this is use before assert, it will return false. without writing the (line).
	writeln(line),
	writeln(line),
	write(counter111(AC)),
	assert(counter111(AC)),
	assert(counter111(AC)),
	assert(counter111(AC)),
	writeln(line),
	writeln(line),
	retractall(counter111(ANYTHING)),
	counter111(AC).