# airplane-seating
An algorithm to seat an airplane given the arrangement of seats and the number of passengers to be seated.

The seats are allocated in this order:
1. Aisle seats
2. Window seats
3. Center seats

The aisle and window allocation starts from the top left and proceeds to the right. Center allocation may be of any order (but follows the same order).

If the number of passengers is more than the number of seats available, the algorithm seats as many passengers as it can and then displays the number of passengers left unseated.

The final result is a visual representation of the seating in the plane. **Unoccupied seats are represented by an X.**
