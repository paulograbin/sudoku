import copy

# Let me double-check the board definition from BoardRepository.java
# makeHardBoard():
#                {0, 0, 2, 0, 0, 0, 0, 0, 0}, // 0
#                {0, 9, 8, 1, 0, 0, 7, 3, 4}, // 1
#                {0, 0, 0, 5, 0, 0, 2, 0, 9}, // 2
#                {0, 0, 0, 0, 0, 7, 3, 4, 0}, // 3
#                {0, 2, 7, 4, 1, 0, 5, 0, 0}, // 4
#                {0, 8, 0, 0, 0, 4, 0, 0, 0}, // 5
#                {0, 7, 0, 3, 4, 5, 0, 2, 0}, // 6
#                {0, 4, 9, 7, 0, 6, 0, 0, 1}, // 7
#                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8

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

# First let's check if the initial board is valid (no contradictions in given values)
def check_initial(b):
    for i in range(9):
        for j in range(9):
            if b[i][j] != 0:
                val = b[i][j]
                b[i][j] = 0
                # Check row
                if val in b[i]:
                    print(f"Duplicate {val} in row {i}")
                # Check col
                col = [b[r][j] for r in range(9)]
                if val in col:
                    print(f"Duplicate {val} in col {j}")
                # Check block
                br, bc = 3*(i//3), 3*(j//3)
                block = []
                for r in range(br, br+3):
                    for c in range(bc, bc+3):
                        block.append(b[r][c])
                if val in block:
                    print(f"Duplicate {val} in block ({br},{bc}) at cell ({i},{j})")
                b[i][j] = val

check_initial(board)
print("Initial board check complete")

# Try to solve with better backtracking
def is_valid(b, row, col, num):
    # Check row
    if num in b[row]:
        return False
    # Check col
    for i in range(9):
        if b[i][col] == num:
            return False
    # Check block
    br, bc = 3*(row//3), 3*(col//3)
    for i in range(br, br+3):
        for j in range(bc, bc+3):
            if b[i][j] == num:
                return False
    return True

def solve(b):
    for i in range(9):
        for j in range(9):
            if b[i][j] == 0:
                for num in range(1, 10):
                    if is_valid(b, i, j, num):
                        b[i][j] = num
                        if solve(b):
                            return True
                        b[i][j] = 0
                return False
    return True

b = [row[:] for row in board]
if solve(b):
    print("Solution found:")
    for i, row in enumerate(b):
        print(f"Row {i}: {row}")
else:
    print("No solution exists for this puzzle!")
