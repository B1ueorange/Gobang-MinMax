import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AI
{
    static chess s;
    static int tx, ty;
    static int cnt, ABcnt;
    static int las_ans, las_ans1, las_ans2;
    static Map Ha = new HashMap();

    public AI()
    {
        s = new chess();
        Ha.clear();
        las_ans = 0;
    }

    public static int[] ask(chess x, int fl)
    {
        Ha.clear();
        int lx = 0, ly = 0;
        for(int i = 1; i <= 15; i ++)
        {
            Boolean bb = false;
            for(int j = 1; j <= 15; j ++)
                if(x.mmp[i][j] != s.mmp[i][j])
                {
                    bb = true;
                    lx = i;
                    ly = j;
                    break;
                }
            if(bb)
                break;
        }
        s = x;
        //int t1 = s.cal(1) - s.cal(2);
        cnt = ABcnt = 0;
        int tmp = 0;
        if(fl == 1)
            tmp = max(6, 1000000009, -1000000009, fl, 0, 0, lx, ly);
        else
            tmp = min(6, 1000000009, -1000000009, fl, 0, 0, lx, ly);
        int[] rt =  {tx, ty};
        System.out.println(cnt + " " + ABcnt);
        System.out.println(tmp);
        las_ans = tmp;
        return rt;
    }

    /*private static int dfs(int de, int alpha, int beta, int fl)
    {
        if(de <= 0)
            return s.cal(fl) - s.cal(3 - fl);
        ArrayList<point>qwq = s.generate(fl);
        int rt = -1000000009;
        for(int i = 0; i < qwq.size(); i ++)
        {
            point t1 = qwq.get(i);
            s.mmp[t1.x][t1.y] = fl;
            int tmp = dfs(de - 1, -beta, -alpha, 3 - fl);
            s.mmp[t1.x][t1.y] = 0;
            tmp *= -1;
            if(tmp > rt)
            {
                rt = tmp;
                if(de == 6)
                {
                    tx = t1.x;
                    ty = t1.y;
                }
            }
            if(rt != -1000000009 && tmp < alpha)
                return rt;
            else
                alpha = rt;
        }
        return rt;
    }*/
    private static int max(int de, int alpha, int beta, int fl, int fl1, long hh, int lx, int ly)
    {
        cnt++;
        if(Ha.containsKey(hh))
            return (int)Ha.get(hh);
        int rt = s.cal(1) - s.cal(2);
        if (de <= 0 || s.ans(0) != 0)
        {
            Ha.put(hh, rt);
            return rt;
        }
        rt = -1000000009;
        ArrayList<point> qwq = s.generate(fl, lx, ly);
        ArrayList<point> owo = new ArrayList<>();
        int tt = 0;
        int tll = qwq.size();
        for (int i = 0; i < tll; i++)
        {
            point t1 = qwq.get(i);
            if (i != 0 && t1.val <= 0)
                break;
            //if(de % 2 == 0 && rt >= 24000 && las_ans > 0 && (double)(rt - las_ans) / (double)las_ans > 1.0 && Math.max(Math.abs(t1.x - lx), Math.abs(t1.y - ly)) > 3 && Math.abs(t1.x - lx) != Math.abs(t1.y - ly) && Math.abs(t1.x - lx) != 0 && Math.abs(t1.y - ly) != 0)
            //    continue;
            //else if(rt >= 24000 && Math.max(Math.abs(t1.x - lx), Math.abs(t1.y - ly)) > 3)
            //    continue;
            s.mmp[t1.x][t1.y] = fl;
            int tmp = 0;
            long nhh = hh ^ s.ha[t1.x][t1.y][fl];
            //if(de == 6 && i < 2 && fl1 <= 0)
            //    tmp = min(de - 1, alpha, rt > beta ? rt : beta, 3 - fl, 1, nhh, t1.x, t1.y);
            //else
            tmp = min(de - 1, alpha, rt > beta ? rt : beta, 3 - fl, fl1, nhh, t1.x, t1.y);
            s.mmp[t1.x][t1.y] = 0;
            tt++;
            //tmp *= -1;
            if (tmp > rt)
            {
                tt = 0;
                rt = tmp;
                if (de == 6 && fl1 <= 0)
                {
                    owo.clear();
                    owo.add(t1);
                    //tx = t1.x;
                    //ty = t1.y;
                }
            }
            if (tmp == rt && de == 6 && fl1 <= 0)
                owo.add(t1);
            if (tt > Math.max(100, qwq.size() / 4))
                break;
            if (tmp > alpha)
            {
                ABcnt++;
                break;
            }
        }
        if(de == 6 && fl1 <= 0)
        {
            Random rnd = new Random();
            int pos = rnd.nextInt() % owo.size();
            pos = Math.abs(pos);
            point t1 = owo.get(pos);
            tx = t1.x;
            ty = t1.y;
        }
        Ha.put(hh, rt);
        return rt;
    }

    private static int min(int de, int alpha, int beta, int fl, int fl1, long hh, int lx, int ly)
    {
        cnt ++;
        if(Ha.containsKey(hh))
            return (int)Ha.get(hh);
        int rt = s.cal(1) - s.cal(2);
        if(de <= 0 || s.ans(0) != 0)
        {
            Ha.put(hh, rt);
            return rt;
        }
        rt = 1000000009;
        ArrayList<point>qwq = s.generate(fl, lx, ly);
        ArrayList<point>owo = new ArrayList<>();
        int tt = 0;
        int tll = qwq.size();
        for (int i = 0; i < tll; i++)
        {
            point t1 = qwq.get(i);
            if(i != 0 && t1.val <= 0)
                break;
            //if(de % 2 == 0 && rt <= -24000 && las_ans < 0 && (double)(las_ans - rt) / (double)Math.abs(las_ans) > 1.0 && Math.max(Math.abs(t1.x - lx), Math.abs(t1.y - ly)) > 3 && Math.abs(t1.x - lx) != Math.abs(t1.y - ly) && Math.abs(t1.x - lx) != 0 && Math.abs(t1.y - ly) != 0)
            //    continue;
            //else if(rt <= -24000 && Math.max(Math.abs(t1.x - lx), Math.abs(t1.y - ly)) > 3)
            //    continue;
            s.mmp[t1.x][t1.y] = fl;
            int tmp = 0;
            long nhh = hh ^ s.ha[t1.x][t1.y][fl];
            //if(de == 6 && i < 2 && fl1 <= 0)
            //    tmp = max(de - 1, rt < alpha ? rt : alpha, beta, 3 - fl, 1, nhh, t1.x, t1.y);
            //else
            tmp = max(de - 1, rt < alpha ? rt : alpha, beta, 3 - fl, fl1, nhh, t1.x, t1.y);
            s.mmp[t1.x][t1.y] = 0;
            //tmp *= -1;
            tt ++;
            if(tmp < rt)
            {
                tt = 0;
                rt = tmp;
                if(de == 6 && fl1 <= 0)
                {
                    owo.clear();
                    owo.add(t1);
                    //tx = t1.x;
                    //ty = t1.y;
                }
            }
            if(tmp == rt && de == 6 && fl1 <= 0)
                owo.add(t1);
            if(tt > Math.max(100, qwq.size() / 4))
                break;
            if(tmp < beta)
            {
                ABcnt ++;
                break;
            }
        }
        if(de == 6 && fl1 <= 0)
        {
            Random rnd = new Random();
            int pos = rnd.nextInt() % owo.size();
            pos = Math.abs(pos);
            point t1 = owo.get(pos);
            tx = t1.x;
            ty = t1.y;
        }
        Ha.put(hh, rt);
        return rt;
    }
}

/*
private static int max(int de, int alpha, int beta, int fl, int fl1, long hh)
    {
        cnt++;
        if(Ha.containsKey(hh))
            return (int)Ha.get(hh);
        int rt = s.cal(1) - s.cal(2);
        if (de <= 0 || s.ans(0) != 0)
        {
            Ha.put(hh, rt);
            return rt;
        }
        rt = -1000000009;
        ArrayList<point> qwq = s.generate(fl);
        ArrayList<point> owo = new ArrayList<>();
        int tt = 0;
        int tll = qwq.size();
        for (int i = 0; i < tll; i++)
        {
            point t1 = qwq.get(i);
            if (i != 0 && t1.val <= 0)
                break;
            s.mmp[t1.x][t1.y] = fl;
            int tmp = 0;
            long nhh = hh ^ s.ha[t1.x][t1.y][fl];
            if(de == 6 && i < 2 && fl1 <= 0)
                tmp = min(de - 1, alpha, rt > beta ? rt : beta, 3 - fl, 1, nhh);
            else
                tmp = min(de - 1, alpha, rt > beta ? rt : beta, 3 - fl, fl1, nhh);
            s.mmp[t1.x][t1.y] = 0;
            tt++;
            //tmp *= -1;
            if (tmp > rt)
            {
                tt = 0;
                rt = tmp;
                if (de == 6 && fl1 <= 0)
                {
                    owo.clear();
                    owo.add(t1);
                    //tx = t1.x;
                    //ty = t1.y;
                }
            }
            if (tmp == rt && de == 6 && fl1 <= 0)
                owo.add(t1);
            if (tt > Math.max(100, qwq.size() / 4))
                break;
            if (tmp > alpha)
            {
                ABcnt++;
                break;
            }
        }
        if(de == 6 && fl1 <= 0)
        {
            Random rnd = new Random();
            int pos = rnd.nextInt() % owo.size();
            pos = Math.abs(pos);
            point t1 = owo.get(pos);
            tx = t1.x;
            ty = t1.y;
        }
        Ha.put(hh, rt);
        return rt;
    }

    private static int min(int de, int alpha, int beta, int fl, int fl1, long hh)
    {
        cnt ++;
        if(Ha.containsKey(hh))
            return (int)Ha.get(hh);
        int rt = s.cal(1) - s.cal(2);
        if(de <= 0 || s.ans(0) != 0)
        {
            Ha.put(hh, rt);
            return rt;
        }
        rt = 1000000009;
        ArrayList<point>qwq = s.generate(fl);
        ArrayList<point>owo = new ArrayList<>();
        int tt = 0;
        int tll = qwq.size();
        for (int i = 0; i < tll; i++)
        {
            point t1 = qwq.get(i);
            if(i != 0 && t1.val <= 0)
                break;
            s.mmp[t1.x][t1.y] = fl;
            int tmp = 0;
            long nhh = hh ^ s.ha[t1.x][t1.y][fl];
            if(de == 6 && i < 2 && fl1 <= 0)
                tmp = max(de - 1, rt < alpha ? rt : alpha, beta, 3 - fl, 1, nh  h);
            else
                tmp = max(de - 1, rt < alpha ? rt : alpha, beta, 3 - fl, fl1, nhh);
            s.mmp[t1.x][t1.y] = 0;
            //tmp *= -1;
            tt ++;
            if(tmp < rt)
            {
                tt = 0;
                rt = tmp;
                if(de == 6 && fl1 <= 0)
                {
                    owo.clear();
                    owo.add(t1);
                    //tx = t1.x;
                    //ty = t1.y;
                }
            }
            if(tmp == rt && de == 6 && fl1 <= 0)
                owo.add(t1);
            if(tt > Math.max(100, qwq.size() / 4))
                break;
            if(tmp < beta)
            {
                ABcnt ++;
                break;
            }
        }
        if(de == 6 && fl1 <= 0)
        {
            Random rnd = new Random();
            int pos = rnd.nextInt() % owo.size();
            pos = Math.abs(pos);
            point t1 = owo.get(pos);
            tx = t1.x;
            ty = t1.y;
        }
        Ha.put(hh, rt);
        return rt;
    }
 */