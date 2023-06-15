import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.DeflaterInputStream;

public class chess
{
    int[][] mmp = new int[21][21];
    long[][][] ha = new long[21][21][3];
    int[][] f = new int[21][21];
    int[] px = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] py = {0, 1, 1, 1, 0, -1, -1, -1};

    public chess()
    {
        int[][] mmp = new int[16][16];
        for (int i = 1; i <= 15; i++)
            for (int j = 1; j <= 15; j++)
            {
                mmp[i][j] = 0;
                ha[i][j][1] = ThreadLocalRandom.current().nextLong();
                ha[i][j][2] = ThreadLocalRandom.current().nextLong();
            }
    }

    public void setRecord(int l, int r, point[] qwq)
    {
        for(int i = l; i <= r; i ++)
            f[qwq[i].x][qwq[i].y] = 1;
    }

    public int ask(int x, int y, int col, int fl)
    {
        int su = 0;
        int tmpx = x - 5 * px[fl];
        int tmpy = y - 5 * py[fl];
        int[] tmpcon = new int[11];
        point[] sta = new point[11];
        for (int i = 0; i < 9; i ++)
        {
            tmpx += px[fl];
            tmpy += py[fl];
            sta[i] = new point(tmpx, tmpy, 0);
            if (isLegal(tmpx, tmpy) == 0)
                tmpcon[i] = 3 - col;
            else
                tmpcon[i] = mmp[tmpx][tmpy];
        }
        int lidx = 4, ridx = 4;
        while (ridx < 8)
        {
            if (tmpcon[ridx + 1] != col)
                break;
            ridx ++;
        }
        while (lidx > 0)
        {
            if (tmpcon[lidx - 1] != col)
                break;
            lidx --;
        }
        int lrange = lidx, rrange = ridx;
        while(rrange < 8)
        {
            if(tmpcon[rrange + 1] == 3 - col)
                break;
            rrange ++;
        }
        while(lrange > 0)
        {
            if(tmpcon[lrange - 1] == 3 - col)
                break;
            lrange --;
        }
        int chess_range = rrange - lrange + 1;
        if(chess_range < 5)
        {
            setRecord(lrange, rrange, sta);
            return 0;
        }

        setRecord(lidx, ridx, sta);

        int m_range = ridx - lidx + 1;

        /*
        五子棋主要棋形：连五，活四，冲四，活三，眠三，活二，眠二
        连五既是胜利
        活n就表示有n个连在一起且两端均没有敌人堵死的
        其余两端必有一段被堵死

        连五5000000分
        活四100000分
        冲四50000分
        活三8000分
        眠三500分
        活二50分
        眠二10分
        */
        if(m_range == 5)
            su += 5000000;

        if(m_range == 4)
        {
            boolean lempty = false, rempty = false;
            if(tmpcon[lidx - 1] == 0)
                lempty = true;
            if(tmpcon[ridx + 1] == 0)
                rempty = true;
            if(lempty && rempty)
                su += 100000;
            else if(lempty || rempty)
                su += 50000;
        }


        if(m_range == 3)
        {
            boolean lempty = false, rempty = false;
            boolean lfour = false, rfour = false;
            if(tmpcon[lidx - 1] == 0)
            {
                if(tmpcon[lidx - 2] == col)
                {
                    setRecord(lidx - 2, lidx - 1, sta);
                    su += 50010;
                    lfour = true;
                }
                lempty = true;
            }
            if(tmpcon[ridx + 1] == 0)
            {
                if(tmpcon[ridx + 2] == col)
                {
                    setRecord(ridx + 1, ridx + 2, sta);
                    su += 90000;
                    rfour = true;
                }
                rempty = true;
            }


            if(lfour || rfour)
            {

            }
            else if(lempty && rempty)
            {
                if(chess_range > 5)
                    su += 8000;
                else
                    su += 500;
            }
            else if(lempty || rempty)
                su += 500;
        }


        if(m_range == 2)
        {
            boolean lempty = false, rempty = false;
            boolean lthree = false, rthree = false;
            if(tmpcon[lidx - 1] == 0)
            {
                if(tmpcon[lidx - 2] == col)
                {
                    setRecord(lidx - 2, lidx - 1, sta);
                    if(tmpcon[lidx - 3] == 0)
                    {
                        if(tmpcon[ridx + 1] == 0)
                            su += 8000;
                        else
                            su += 500;
                        lthree = true;
                    }

                    else if(tmpcon[lidx - 3] == 3 - col)
                    {
                        if(tmpcon[ridx + 1] == 0)
                            su += 500;
                        lthree = true;
                    }

                }
                lempty = true;
            }


            if(tmpcon[ridx + 1] == 0)
            {
                if(tmpcon[ridx + 2] == col)
                {
                    if(tmpcon[ridx + 3] == col)
                    {
                        setRecord(ridx + 1, ridx + 2, sta);
                        su += 50000;
                        rthree = true;
                    }

                    else if(tmpcon[ ridx + 3] == 0)
                    {
                        //setRecord(self, x, y, ridx + 1, ridx + 2, dir_index, dir)
                        if(lempty)
                            su += 8000;
                        else
                            su += 500;
                        rthree = true;
                    }

                    else if(lempty)
                        su += 500;
                    rthree = true;
                }
                rempty = true;
            }


            if(lthree || rthree)
            {

            }
            else if(lempty && rempty)
                su += 50;
            else if(lempty || rempty)
                su += 10;
        }


        if(m_range == 1)
        {
            boolean lempty = false, rempty = false;
            if(tmpcon[lidx - 1] == 0)
            {
                if(tmpcon[lidx - 2] == col)
                    if(tmpcon[lidx - 3] == 0)
                        if(tmpcon[ridx + 1] == 3 - col)
                            su += 10;
                lempty = true;
            }


            if(tmpcon[ridx + 1] == 0)
                if(tmpcon[ridx + 2] == col)
                    if(tmpcon[ridx + 3] == 0)
                        if(lempty)
                            su += 50;
                            //setRecord(self, x, y, lidx, ridx + 2, dir_index, dir)
                        else
                            su += 10;
                    else if(tmpcon[ ridx + 2] == 0)
                        if(tmpcon[ridx + 3] == col && tmpcon[ ridx + 4] == 0)
                            su += 50;
        }
        return su;
    }

    public int cal_poi(int x, int y, int col, int fl)
    {
        if (fl == -1)
        {
            int su = 0;
            for (int l = 3; l < 7; l ++)
                su += ask(x, y, col, l);
            return su;
        }
        else
        {
            if(f[x][y] > 0)
                return 0;
            return ask(x, y, col, fl);
        }
    }

    public int cal(int fl)
    {
        int su = 0;
        for (int l = 3; l < 7; l++)
        {
            for (int i = 1; i <= 15; i++)
                for (int j = 1; j <= 15; j++)
                    f[i][j] = 0;
            for (int i = 1; i <= 15; i++)
                for (int j = 1; j <= 15; j++)
                    if (mmp[i][j] == fl)
                        su += cal_poi(i, j, fl, l);
        }
        return su;
    }

    public void print()
    {
        System.out.print("   ");
        for(int i = 1; i <= 15; i ++)
        {
            if(i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
        }
        System.out.print("\n");
        for(int i = 1; i <= 15; i ++)
        {
            if(i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
            for(int j = 1; j <= 15; j ++)
                System.out.print(mmp[i][j] + "  ");
            System.out.print("\n");
        }
        System.out.println("----------------------------------------------------");
    }

    public int isLegal(int x, int y)
    {
        if (x < 1 || x > 15 || y < 1 || y > 15)
            return 0;
        return 1;
    }

    public int asknum()
    {
        int rt = 0;
        for(int i = 1; i <= 15; i ++)
            for(int j = 1; j <= 15; j ++)
                rt += (mmp[i][j] != 0) ? 1 : 0;
        return rt;
    }


    public int ans(int fff)
    {
        int ss = 0;
        for(int i = 1; i <= 15; i ++)
            for(int j = 1; j <= 15; j ++)
                ss += (mmp[i][j] != 0) ? 1 : 0;
        if(ss == 15 * 15)
            return -1;
        for(int i = 1; i <= 15; i ++)
            for(int j = 1; j <= 15; j ++)
                if(mmp[i][j] != 0)
                    for(int k = 3; k < 7; k ++)
                    {
                        int s = 1;
                        for(int l = 1; l < 5; l ++)
                        {
                            int tmpx = i + px[k] * l;
                            int tmpy = j + py[k] * l;
                            if(isLegal(tmpx, tmpy) == 0 || mmp[tmpx][tmpy] != mmp[i][j])
                                break;
                            s ++;
                        }
                        if(s >= 5)
                        {
                            if(fff == 1)
                                System.out.println(i + " " + j);
                            int rt = mmp[i][j];
                            if(rt == -1)
                                rt = 2;
                            return rt;
                        }
                    }
        return 0;
    }

    public static double MysigMoid(double value)
    {
        //Math.E=e;Math.Pow(a,b)=a^b
        double ey = Math.pow(Math.E, -value);
        double result = 1 / (1 + ey);
        return result;
    }

    public int check()
    {
        int cnt1 = 0, cnt2 = 0;
        for(int i = 1; i <= 15; i ++)
            for(int j = 1; j <= 15; j ++)
            {
                if(mmp[i][j] == 1)
                    cnt1 ++;
                else if(mmp[i][j] == 2)
                    cnt2 ++;
            }
        return cnt1 - cnt2;
    }


    public ArrayList<point> generate(int fl, int lx, int ly)
    {
        ArrayList<point> tmp = new ArrayList<>();
        for (int i = 1; i <= 15; i++)
            for (int j = 1; j <= 15; j++)
                f[i][j] = 0;
        for (int i = 1; i <= 15; i ++)
            for (int j = 1; j <= 15; j ++)
                if (mmp[i][j] > 0)
                {
                    for (int k = 0; k < 8; k ++)
                    {
                        for (int l = 1; l <= 2; l ++)
                        {
                            if (isLegal(i + l * px[k], j + l * py[k]) < 1)
                                break;
                            if(mmp[i + l * px[k]][j + l * py[k]] > 0 || f[i + l * px[k]][j + l * py[k]] > 0)
                                continue;
                            mmp[i + l * px[k]][j + l * py[k]] = fl;
                            int tmpp = cal_poi(i + l * px[k], j + l * py[k], fl, -1);
                            mmp[i + l * px[k]][j + l * py[k]] = 3 - fl;
                            int tmppp = cal_poi(i + l * px[k], j + l * py[k], 3 - fl, -1) * 3;
                            tmpp += tmppp;
                            tmp.add(new point(i + l * px[k], j + l * py[k], tmpp));
                            mmp[i + l * px[k]][j + l * py[k]] = 0;
                            f[i + l * px[k]][j + l * py[k]] = 1;
                        }
                    }
                }
        Collections.sort(tmp);
        //System.out.println(tmp.get(0).val + " " + tmp.get(tmp.size() - 1).val);
        return tmp;
    }
}
