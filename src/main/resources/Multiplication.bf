(Func Multiplication    #definition de la fonction multiplication
<<                                    #Reviens au debut
<[-]
<[-]
<[-]
<[-]
>>>>
[                                    #Tant que case 0 différent de 0
        >                                #Passe à 1
        [                                #Tant que 1 différent de 0
                <<+                          #Reviens à T / Incrémenter T
                <+                           #Reviens à R / Incrémenter R
                >>>-                         #Passe à M / Décrémenter M
        ]                                #Retour boucle
	<<[>>+<<-]		     #Reinitialise M / Détruit T
	>-			     #Décrémente O
]                                    #Retour boucle
>[-]				     #Retourne à M / M = 0
<				     #Retourne O déjà = 0
<[-]                                 #Retourne à T / T = 0
<                                    #Retourne à R
)
+++++ #case 0 : 5
>
++++++ #case 1 : 6
>
Multiplication[0,1]
