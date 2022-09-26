# airplane-seating
An algorithm to seat an airplane given the arrangement of seats and the number of passengers to be seated.

The seats are allocated in this order:
1. Aisle seats
2. Window seats
3. Center seats

The aisle and window allocation starts from the top left and proceeds to the right. Center allocation may be of any order (but follows the same order).

If the number of passengers is more than the number of seats available, the algorithm seats as many passengers as it can and then displays the number of passengers left unseated.

The final result is a visual representation of the seating in the plane.
* **Unoccupied seats are represented by an X.**
* **Aisles are separated by "||"**
* **Each seat ends with a "|"**

Due to the representation specified in the input, it is not clear whether an input of [3, 2] represents **3 rows & 2 columns** or **2 rows & 3 columns**. So, both are supported. Please refer the invert method.