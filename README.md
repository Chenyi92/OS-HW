# Sudoku solution validator
## Inroduction_the Goal
The task is to design a multithreaded application that determines whether a Sudoku puzzle solution is valid. This means that given a 9x9 grid of numbers that represent a solved Sudoku puzzle, the program should check if the solution is valid according to the rules of Sudoku.
## Usage
Compile the program using the following command:
```javascript
javac SudokuValidator.java
```
Run the program using the following command:
```javascript
java SudokuValidator
```
## Output
when the program show "Please enter the 9*9 Sudoku solution:",
the user shold enter the 9*9 Sudoku solution with row by row and using space between numbers.
Then the output will show whether the sudoku solution above is right in below pattern

Below is an example intput and output for reference:
```
Input:
8 3 5 4 1 6 9 2 7
2 9 6 8 5 7 4 3 1
4 1 7 2 9 3 6 5 8
5 6 9 1 3 4 7 8 2
1 2 3 6 7 8 5 4 9
7 4 8 5 2 9 1 6 3
6 5 2 7 8 1 3 9 4
9 8 1 3 4 5 2 7 6
3 7 4 9 6 2 8 1 5
Output:
The Sudoku solution is valid.

Input:
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9
Output:
The Sudoku solution is invalid.

```


