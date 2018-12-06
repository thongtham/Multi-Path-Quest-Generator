merge([],Ys,Zs) => Zs=Ys.
merge(Xs,[],Zs) => Zs=Xs.
merge([X|Xs],[Y|Ys],Zs),X<Y => Zs=[X|ZsT],merge(Xs,[Y|Ys],ZsT).
merge(Xs,[Y|Ys],Zs) => Zs=[Y|ZsT],merge(Xs,Ys,ZsT).


b(X,1):- X<0.
b(X,Y):- X=1, Y>0.
a(X,Y):- X>0, b(X,Y).