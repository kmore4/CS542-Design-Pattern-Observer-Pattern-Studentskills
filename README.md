# CSX42: Assignment 3
Name:  Kasturi Tarachand More

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

Assuming you are at studentskills/ folder.

```commandline
 ant -buildfile src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

Assuming you are at studentskills/ folder.

```commandline
 ant -buildfile src/build.xml all 
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dout1="outa.txt" -Dout2="outb.txt"-Dout3="outpc.txt" -Derror="errorF.txt" -Ddebug=3
```
Note: Arguments accept the absolute path of the files.


## Description:

Slack Days Used: 1/5

Tree Structure Used: Binary Search Tree

Time Complexities:

For Insertion: Worst case Time complexity: O(n) where n = total number of nodes
			   Average case Time Complexity: O(logn) where n = total number of nodes
			   
For Search: Worst case Time complexity: O(n) where n = total number of nodes
			Average case Time Complexity: O(logn) where n = total number of nodes
			   
For Delete: Worst case Time complexity: O(n) where n = total number of nodes
			Average case Time Complexity: O(logn) where n = total number of nodes
			
As Binary Search Tree is a special form of Binary Tree where the nodes with values lesser than root/current node are stored as left child node and the nodes with values greater than root/current node are stored as right child node.
Because of this, insertion, search and deletion average time complexities are O(logn).


## References:

Binary Search Tree:

https://www.geeksforgeeks.org/insert-a-node-in-binary-search-tree-iteratively/
https://www.baeldung.com/java-binary-tree


## Assumptions and check conditions:

1) Each studentRecord will have only 10 skills. 
   If insert tries to add more than 10 skills, program will skip skills after 10 skill records.
   
2) File is empty, File not found, Line is empty, input line format is wrong -
   Program throws an appropriate exception and exits the program execution.
   
3) BNumber is not of length 4, replica number mentioned does not exist -
   Program throws an appropriate exception and exits the program execution.


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [07/11/2019]



