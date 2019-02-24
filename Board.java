class Board
{
    public char [][] map;
    public int nbMoves;

    public Board()
    {
        map =  new char [][] {{' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '}};
        nbMoves = 0;
    }

    public void addMove(int x, int y, char player)
    {
        nbMoves++;
        map[y][x] = player;
    }

    public int checkForWin()
    {
        //returns -1: lose 0: draw 1: game cont 2: win
        Boolean win = false;
        Boolean lose = false;
        for (int i = 0; i < 3; i++)
        {
            //hor
            if (map[i][0] == 'O' && map[i][1] == 'O' && map[i][2] == 'O')
                win = true;
            else if (map[i][0] == 'X' && map[i][1] == 'X' && map[i][2] == 'X')
                lose = true;
            
            //ver
            if (map[0][i] == 'O' && map[1][i] == 'O' && map[2][i] == 'O')
                win = true;
            else if (map[0][i] == 'X' && map[1][i] == 'X' && map[2][i] == 'X')
                lose = true;
        }
        //diar
        if ((map[0][0] == 'O' && map[1][1] == 'O' && map[2][2] == 'O') ||
            (map[0][2] == 'O' && map[1][1] == 'O' && map[2][0] == 'O'))
            win = true;
        //dial
        else if ((map[0][0] == 'X' && map[1][1] == 'X' && map[2][2] == 'X') ||
                (map[0][2] == 'X' && map[1][1] == 'X' && map[2][0] == 'X'))
            lose = true;

        if (!lose && !win)
        {
            int count = 0;
            for (char [] y : map)
                for (char x : y)
                    if (x != ' ')
                        count++;
            if (count == 9)
                return 0;
        }

        if (lose)
            return -1;
        else if (win)
            return 2;
        
            return 1;
    }  

    public void print()
    {
        System.out.print("\n");
        for (char [] y : map)
        {               
            for (char x : y)
                System.out.print(x+"|");
            System.out.print("\n");
        }
    }
}