import copy

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

def is_valid(b, row, col, num):
    for j in range(9):
        if b[row][j] == num:
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

b = copy.deepcopy(board)
if solve(b):
    print("Solution found:")
    for row in b:
        print(row)
else:
    print("No solution!")
