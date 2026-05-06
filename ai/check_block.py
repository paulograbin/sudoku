board = [
    [0, 0, 2, 0, 0, 0, 0, 0, 0],
    [0, 9, 8, 1, 0, 0, 7, 3, 4],
    [0, 0, 0, 5, 0, 0, 2, 0, 9],
    [0, 0, 0, 0, 0, 7, 3, 4, 0],
    [0, 2, 7, 4, 1, 0, 5, 0, 0],
    [0, 8, 0, 0, 0, 4, 0, 0, 0],
    [0, 7, 0, 3, 4, 5, 0, 2, 0],
    [0, 4, 9, 7, 0, 6, 0, 0, 1],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
]

# Block (3,3) covers rows 3-5, cols 3-5
print("Block rows 3-5, cols 3-5:")
for r in range(3, 6):
    for c in range(3, 6):
        print(f"  ({r},{c}) = {board[r][c]}")

# The values are: 0, 0, 7, 4, 1, 0, 0, 0, 4
# Two 4s! At (3,7)... wait that's col 7.
# Let me recheck. The block (3,3) is rows 3-5, cols 3-5
# (3,3)=0, (3,4)=0, (3,5)=7
# (4,3)=4, (4,4)=1, (4,5)=0
# (5,3)=0, (5,4)=0, (5,5)=4
print("\nValues in block: ", [board[r][c] for r in range(3,6) for c in range(3,6)])
print("Two 4s: at (4,3) and (5,5)")
