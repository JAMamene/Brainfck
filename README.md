To build the project we use a script we made that creates the Bfck executable (Launcher.sh)

Function and Procedure 

Declaration : 
(Func name_of_function
instructions
)

(Proc name_of_proc
instructions
)

use function or procedure : 
name_of_function[]

parameters between '[]' and separated by comma


exemple : 
(Func my_function
INCR
+++++
DECR
)
my_function[5,5,5]

Fonction with three parameters
when the function is called the cell 5 of the memory will be duplicated 3 times at the beginning of the function buffer

MACRO
{ Macro_name
instructions
}

Do not forget the space between '{' and the name of the macro 

exemple 

{ My_macro
+++
INCR
}
