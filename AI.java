import java.util.Arrays;

class AI
{
    public AI()
    {
        
    }

    private char[] getLine(char[][] map, String dir, int[] pos)
    {
        //dir : ver || hor || dial(d left) || diar(d right)
        //pos : x, y
        switch(dir)
        {
            case "ver":
                return new char[] {map[0][pos[0]], map[1][pos[0]], map[2][pos[0]]};
            case "hor":
                return new char[] {map[pos[1]][0], map[pos[1]][1], map[pos[1]][2]};
            case "diar":
                if ((pos[0] == 1 && pos[1] == 0) || (pos[0] == 0 && pos[1] == 1) ||
                    (pos[0] == 2 && pos[1] == 1) || (pos[0] == 1 && pos[1] == 2) ||
                    (pos[0] == 2 && pos[1] == 0) || (pos[0] == 0 && pos[1] == 2))
                    return null;

                return new char[] {map[0][0], map[1][1], map[2][2]};
            case "dial":
                if ((pos[0] == 1 && pos[1] == 0) || (pos[0] == 0 && pos[1] == 1) ||
                    (pos[0] == 2 && pos[1] == 1) || (pos[0] == 1 && pos[1] == 2) ||
                    (pos[0] == 0 && pos[1] == 0) || (pos[0] == 2 && pos[1] == 2))
                    return null;

                return new char[] {map[0][2], map[1][1], map[2][0]};   
        }

        return new char[3];
    }

    private int getMoveScore(char[] line, int oldScore)
    {
        int x = 0;int o = 0;int space = 0;

        for (char c : line)
        {
            switch (c)
            {
                case 'X': x++;break;
                case 'O': o++;break;
                case ' ':space++;
            }
        }
        if (x == 2 && space == 1 && oldScore < 4)
            return 4;
        else if (o == 1 && space == 2 && oldScore < 3)
            return 3;
        else if (o == 2 && space == 1 && oldScore < 9)
            return 9;
        else if (oldScore > 0)
            return oldScore;
        return 0;
    }

    public Board move(Board board)
    {
        int [][] heatMap = new int [3][3];
        int[] movePos = {0,0};

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                if (board.map[y][x] == ' ')
                {
                    char [] l = getLine(board.map, "hor", new int[]{x, y});
                    // System.out.println("!!!" + Arrays.toString(l));
                    heatMap[y][x] = getMoveScore(l, heatMap[y][x]);          

                    l = getLine(board.map, "ver", new int[]{x, y});
                    heatMap[y][x] = getMoveScore(l, heatMap[y][x]);          
                    
                    l = getLine(board.map, "diar", new int[]{x, y});
                    if (l != null)
                        heatMap[y][x] = getMoveScore(l, heatMap[y][x]);                  

                    l = getLine(board.map, "dial", new int[]{x, y});
                    if (l != null)
                        heatMap[y][x] = getMoveScore(l, heatMap[y][x]);
                }
                else if (board.map[y][x] == 'X' || board.map[y][x] == 'O')
                    heatMap[y][x] = -1;
            }
        }

        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                if (heatMap[y][x] >  heatMap[movePos[1]][movePos[0]])
                    movePos = new int [] {x, y};

        System.out.print("--heatmap--\n");
        for (int [] y : heatMap)
        {
            for (int x : y)
            {
                if ((x + "").length() == 2)
                    System.out.print(x + "-");
                else{
                    System.out.print(" " + x + "-");
                }
            }
            System.out.print("\n");
        }
        System.out.print("Move chosen: " + movePos[0] + " " + movePos[1]);
        board.addMove(movePos[0], movePos[1], 'O');
        return board;
    }

    public Board firstMove(Board board)
    {
        if (board.map[1][1] == 'X')
        {
            board.addMove(0, 0, 'O');
            return board;
        }
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
                if (board.map[y][x] == 'X')
                {
                    board.addMove((x-2)*-1, (y-2)*-1, 'O');
                    return board;
                }
        }

        return board;
    }
}