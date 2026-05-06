def is_valid(b, row, col, num):
    if num in b[row]:
        return False
    for i in range(9):
        if b[i][col] == num:
            return False
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

# Option 1: remove 4 at (4,3)
b1 = [row[:] for row in board]
b1[4][3] = 0
if solve(b1):
    print("Option 1 works: remove 4 at (4,3)")
    for row in b1:
        print(row)
else:
    print("Option 1 fails")

print()

# Option 2: remove 4 at (5,5)
b2 = [row[:] for row in board]
b2[5][5] = 0
if solve(b2):
    print("Option 2 works: remove 4 at (5,5)")
    for row in b2:
        print(row)
else:
    print("Option 2 fails")
