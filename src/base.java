import java.util.Random;

public class base
{
    static AI B = new AI();
    static AI W = new AI();
    static chess m = new chess();

    public static void main(String[] args)
    {
        //m.mmp = Game.gam(2, cnt);

        /*for(int i = 1, cnt = 1; m.ans(0) == 0; i = 3 - i, cnt ++)
        {
            if(cnt == 1)
            {
                Random rnd = new Random();
                int px = 7 + rnd.nextInt() % 3;
                int py = 7 + rnd.nextInt() % 3;
                m.mmp[px][py] = 1;
            }
            else
            {
                //if(cnt == 6)
                //    System.out.println(233);
                int[] rt;
                if(i == 1)
                    rt = B.ask(m, i);
                else
                    rt = W.ask(m, i);
                m.mmp[rt[0]][rt[1]] = i;
            }
            System.out.println(cnt + ":" + i);
            m.print();
        }
        System.out.println(m.ans(1));*/
    }
}

