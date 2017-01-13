To build the project we use a script we made that creates the Bfck executable (Launcher.sh)

**Function and Procedure** 

**Declaration :**  
(Func name_of_function  
instructions  
)

(Proc name_of_proc    
instructions  
)

**use function or procedure :**  
name_of_function[]

parameters between '[]' and separated by comma


**example :**  
(Func my_function  
INCR  
+++++  
DECR  
)  
my_function[5,5,5]

Function with three parameters
when the function is called the cell 5 of the memory will be duplicated 3 times at the beginning of the function buffer

**MACRO:**  
{ Macro_name  
instructions  
}

Do not forget the space between '{' and the name of the macro 

example 

{ My_macro  
+++  
INCR  
}

**Arguments :** 

-p + the brainfuck file you want to interpret,   
-i + the input file you want to use for in,   
-o + the output file you want to use for out,   
--rewrite to print to the standard output your program in short syntax,  
--translate to get an image representation of you program,   
--check to only check if the program is well formed   
--showMetrics to print metrics of the program   
--trace to create log  
--code (java, c) [optimize] to translate the brainfuck code in the appropriate language  
and with optimization if you specify the option  

Launcher.sh used to build project before usage of maven 