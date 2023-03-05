# BroLang


DATA TYPES

1. num: A data type that stores all the integer and float point values*

2. String: A data type8 used to store text-based data, such as names, addresses, messages, and more. (basically int)

3. boolean: There are two values of the Boolean data type: Cap and NoCap, which represent "false" and "true" statements, respectively.

ARITHMETIC OPERATORS

1.  + : This represents addition operation (2+5=7)
2.  -  : This represents subtraction operation (2-5=-3)
*3.  *  : This represents multiplication operation (25 = 10)
4.  / :  This represents division operation (2/5 = 0.4)

*ASSIGNMENT OPERATOR

1. (:=) : This represents the equal to operation (num i := 5)

*STRING OPERATIONS** ![WhatsApp Image 2023-03-04 at 11 02 35 PM](https://user-images.githubusercontent.com/89576014/222973748-8aa42aec-8c43-48f7-a388-4a2bbade90d8.jpeg)

![WhatsApp Image 2023-03-04 at 11 02 35 PM](https://user-images.githubusercontent.com/89576014/222973762-95ac6ef4-f5cc-4ef9-89af-e7a41ab2e189.jpeg)

1.  watchaPakin (returns the number of character in the string, i.e its length)

DISPLAY OPERATIONS

1. goOff(): (prints the string/ number / boolean to the console )
2. goOffKing(): (println method to print onto new line)
3. getSauce(): used as a scanner class to get input from the user.

***EXAMPLE:**

Input: hey 

String strin1 = getSauce.next() ;
String output = " hello." ;
goOffKing(string1 + output);

Output:
hey hello.

LOOP CONDITION

1. loopin i : (initial,final)   (for loop - intitial to final)  
A loop which executes the code, written in the block, (final-initial) times

EXAMPLE: 

*num m := 0;
num n := 3;
loopin i : (m,n);
{
	goOff i;
	i++;
}* 
2. tillClapback([condition])   (while )
A loop which executes the code, written in the block, till the [condition] becomes Cap.

Example :

Num i =5;
tillClapback(i > 0)
{
	goOff i
	i := i -1;
}*
3. vibeCheck([condition])  (If): Executes the code if the [condition] is NoCap.

4.*bruhPlease([condition])** (else if ): executes this block of code if the vibecheck([condition]) doesnt pass.It executes if vibeCheck is not executed.*

5. okBoomer (else):Executes if neither vibeCheck nor bruhPlease get executed.

6. peaceOut:
End if statement
Ends the if loop.

***EXAMPLE:***

num i := 5;
vibeCheck(i == 1)
{
	goOff i;
}
bruhPlease(i == 3)
{
	goOff i ;
}
okBoomer
{
	goOff i;
}
peaceOut;
