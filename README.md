# CSX42: Assignment 3
**Name: Onkar Kulkarni**</br>
**Email: okulkar4@binghamton.edu**</br>
**Please note that I am using NOT using any slack day.**</br>

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [decoratorsystem/src](./decoratorsystem/src/) folder.

## Instruction to clean:

```commandline
    ant -buildfile decoratorsystem/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
    ant -buildfile decoratorsystem/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
    ant -buildfile decoratorsystem/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="debug.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Onkar Kulkarni</br>
Date: 08/03/2020
