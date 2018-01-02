import socket
import sys
from copy import deepcopy


class Netcat:
    """ Python 'netcat like' module """

    def __init__(self, ip, port):
        self.buff = ""
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.connect((ip, port))

    def read(self, length = 1024):
        """ Read 1024 bytes off the socket """

        return self.socket.recv(length)
 
    def read_until(self, data):
        """ Read data into the buffer until we have data """

        while not data in self.buff:
            self.buff += self.socket.recv(1024)
            #print self.buff
            if "HV" in self.buff:
                print self.buff
                sys.exit()

        pos = self.buff.find(data)
        rval = self.buff[:pos + len(data)]
        self.buff = self.buff[pos + len(data):]
        return rval
 
    def write(self, data):
        self.socket.send(data)
    
    def close(self):
        self.socket.close()


def parseField(field):
    board = [['*' for x in range(3)] for y in range(3)]
    lines = field.split('\n')
    lc = 0
    for line in lines:
        stripped = line.strip()
        if stripped.startswith('|'):
            cells = stripped.split('|')
            cc = 0
            for cell in cells:
                if cell != '':
                    board[lc % 3][cc % 3] = cell.strip()
                    cc += 1
            lc += 1
    return board

def isWinner(board, player):
    return ((board[0][0] == player and board[0][1] == player and board[0][2] == player)
        or (board[1][0] == player and board[1][1] == player and board[1][2] == player)
        or (board[2][0] == player and board[2][1] == player and board[2][2] == player)
        or (board[0][0] == player and board[1][0] == player and board[2][0] == player)
        or (board[0][1] == player and board[1][1] == player and board[2][1] == player)
        or (board[0][2] == player and board[1][2] == player and board[2][2] == player)
        or (board[0][0] == player and board[1][1] == player and board[2][2] == player)
        or (board[0][2] == player and board[1][1] == player and board[2][0] == player))

def isFree(board, x, y):
    return board[x][y] == '*'

def boardToValue(x, y):
    return x * 3 + y + 1

def makeMove(board):
    if isFree(board, 0, 0):
        return 0, 0
    
    for x in range(3):
        for y in range(3):
            if isFree(board, x, y):
                temp = deepcopy(board)
                temp[x][y] = 'O'
                if isWinner(temp, 'O'):
                    return x, y
                temp[x][y] = 'X'
                if isWinner(temp, 'X'):
                    return x, y

    if board[2][2] == '*':
        return 2, 2
    else:
        # board[2][2] already occupied
        if isFree(board, 2, 0):
            return 2, 0
        if isFree(board, 0, 2):
            return 0, 2

        if board[2][0] == 'X':
            if isFree(board, 2, 1):
                return 2, 1
            if isFree(board, 1, 0):
                return 1, 0

        if board[0][2]:
            if isFree(board, 0, 1):
                return 0, 1
            if isFree(board, 1, 2):
                return 1, 2

    # Otherwise make any move
    for x in range(3):
        for y in range(3):
            if isFree(board, x, y):
                return x, y

def noMoreMoves(board):
    if isWinner(board, 'X'):
        return True

    for x in range(3):
        for y in range(3):
            if isFree(board, x, y):
                return False
    
    return True


nc = Netcat(sys.argv[1], int(sys.argv[2]))
nc.read_until("game")
print "Starting..."
nc.write('\n')

wins = 0
while wins < 100:
    while True:
        field = nc.read_until("Field:")
        board = parseField(field)
        print board
        
        x, y = makeMove(board)
        board[x][y] = 'X'
        move = boardToValue(x, y)
        print "Move: {0}, {1} => {2}".format(x, y, move)
        nc.write("{0:d}\n".format(move))

        if noMoreMoves(board):
            if isWinner(board, 'X'):
                wins += 1
                print "WIN"
            else:
                print "draw..."

            break
    nc.read_until("again")
    nc.write('\n')

print nc.read()

nc.close()
