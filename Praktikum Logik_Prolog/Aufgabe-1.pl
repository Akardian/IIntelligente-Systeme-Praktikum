% Autor:
% Datum: 17.11.2017

% Eltern
eltern(X, Vater, Mutter) :- elternteile(X, Mutter), weiblich(Mutter),   % Sucht weibliche Mutter
                            elternteile(X, Vater), maennlich(Vater)     % Sucht mannlichen Vater
                            ,Vater \== Mutter.                          % Vater darf nicht Mutter sein
% Kinder
kinder(X, Kinder) :- elternteile(Kinder, X).

% Geschwister (Gleicher Vater und Mutter)
geschwister(X, Geschwister) :- eltern(X, Vater, Mutter),              % Findet Eltern
                               kinder(Vater, Geschwister),            % Sucht Kinder vom Vater
                               eltern(Geschwister, Vater, Mutter),    % Pruft ob beide Elternteile ubereinstimmen.
                               X \== Geschwister.                     % Ist nicht Geschwister mit sich selbst
                               
% Bruder
bruder(X, Bruder) :- geschwister(X, Bruder), maennlich(Bruder). % Pruft ob geschwister mannich sind.

% Schwester
schwester(X, Schwester) :- geschwister(X, Schwester), weiblich(Schwester). % Pruft ob geschwister weiblich ist.

% Halbgeschwister
halbgeschwister(X, Geschwister) :- eltern(X, Vater, Mutter),                 % Finder Eltern
                                   (halbkinder(Vater, Mutter, Geschwister);  % Geschwister mit anderer Mutter
                                   halbkinder(Mutter, Vater, Geschwister)).  % Geschwister mit andererm Vater
% Stiefkinder
halbkinder(Elternteil, NichtElternteil, Geschwister) :- kinder(Elternteil, Geschwister),                   % Suche Geschwister vom Elternteil
                                                        \+elternteile(Geschwister, NichtElternteil).       % Nur Geschwister mit einem gleichem Elternteil
                                                        
%Halbschwester
halbschwester(X, Schwester) :- halbgeschwister(X, Schwester), weiblich(Schwester).

%Halbbruder
halbbruder(X, Bruder) :- halbgeschwister(X, Bruder), maennlich(Bruder).

alleGeschwister(X, Geschwister) :- geschwister(X, Geschwister),  halbgeschwister(X, Geschwister).

% Nichten und Neffen (Nicht von Halbgeschwistern)
nichtenNeffen(X, N) :- geschwister(X, Geschwister), kinder(Geschwister,N). % Sucht Kinder von geschwistern

nichten(X, Nichten) :- nichtenNeffen(X, Nichten), weiblich(Nichten).
neffen(X, Neffen) :-  nichtenNeffen(X, Neffen), maennlich(Neffen).

%verheiratet
verheiratetMit(X, Partner) :- verheiratet(X, Partner) ; verheiratet(Partner, X).  % Wenn X verheitatet ist mit P ist auch P mit X verheiratet

%nicht verheitatet
nichtVerheiratet(X, Partner) :- \+verheiratetMit(X,Partner).
