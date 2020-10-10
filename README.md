# Decorator Design Pattern

-----------------------------------------------------------------------
## Description:
- Use Decorator Design Pattern to decorate the input with following decorators:
    - most frequent word
    - most frequent letter
    - sentence start
    - sentence end
    - paragraph start
    - paragraph end
- DataStructure used: List -> space complexity = O(n)
    - to Append : time complexity = O(1), 
    - to Search : time complexity = O(n) where n is size
    - to remove : time complexity = O(1)


-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
Note: build.xml is present in [decoratorsystem/src](./decoratorsystem/src/) folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
    ant -buildfile decoratorsystem/src/build.xml clean
```
Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instructions to compile:

```commandline
    ant -buildfile decoratorsystem/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instructions to run:

```commandline
    ant -buildfile decoratorsystem/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="debug.txt"
```
Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
-----------------------------------------------------------------------
