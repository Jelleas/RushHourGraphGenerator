Rush Hour Design Outline
==========

####Representations:

#####Line:
A line is a column or row in a Rush Hour board. It shows all vehicles that, if not blocked, can move on the line. For this I use a binary string of length 6. Each bit represents a position on the board. A 1 means the position is taken, a 0 that the position is not taken OR the end of a vehicle. For example 110100 means: 1 truck, 1 car, 1 empty space. The second meaning for the 0 is needed as otherwise the string 111111 could be interpreted as both 3 cars and 2 trucks. As there exist only 24 different lines in the 6x6 I am able to precompute all the accessibility relations between lines.

#####Board:
I represent a Rush Hour board as a collection of 12 lines, where there are 6 column lines and 6 row lines. This representation has several key advantages over a simple representation such as a 6x6 integer matrix:

* Moving a vehicle can be done by swapping just one line with another, which can be easily done by the accessibility relations between lines. When trying to perform a move, just one condition has to be checked for every move, meaning: if a move is performed on a row, then only one position in one column has to be checked for the move to be legal. For this to hold, I perform moves in succession from the original position of the vehicle I am moving, thus if a car cannot move 1 position it can certainly not move 2.
* Creating a copy of one board (important for memoization) can be done by just copying a maximum of 12 references. Could be as low as 7 if I decide to keep rows and columns in a structure, then I could copy the reference to the structure on which no move was executed.
* "Good" compression of the board that does not need decompression in order to perform moves on it. In short, the representation within the database is essentially the same as within my program, so there is very little translating that needs to happen.
