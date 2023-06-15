import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    public static int[][] gam(int col, int num)//col AI棋子颜色 num 目前第几手
    {
        wuziqi.tx = new JLabel("Waiting");
        wuziqi.jp.add(wuziqi.tx);
        wuziqi.gl.setgraph(wuziqi.g);
        //wuziqi.paint(wuziqi.g);
        File file1 = new File("input.txt");
        Scanner sc = null;
        try
        {
            sc = new Scanner(file1);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String[] s1 = null;
        int[][] mmm = new int[21][21];
        for(int i = 1; i <= 15; i ++)
        {
            s1 = sc.nextLine().split(" ");
            for(int j = 1; j <= 15; j ++)
            {
                mmm[i][j] = Integer.parseInt(s1[j - 1]);
                if(mmm[i][j] == -1)
                    mmm[i][j] = 2;
            }
        }
        int[][] mm1 = new int[21][21];
        for(int i = 1; i <= 15; i ++)
            for(int j = 1; j <= 15; j ++)
                mm1[j][i] = mmm[i][j];
        AI A = new AI();
        chess m = new chess();
        m.mmp = mm1;
        int[] rt = new int[2];
        if(m.ans(0) == 3 - col)
        {
            rt[0] = 15 + 3 - col;
            rt[1] = 15 + 3 - col;
        }
        else
        {
            if(m.ans(0) == col)
            {
                rt[0] = 15 + col;
                rt[1] = 15 + col;
            }
            if(num == 1)
            {
                Random rnd = new Random();
                int px = 7 + rnd.nextInt() % 3;
                int py = 7 + rnd.nextInt() % 3;
                m.mmp[px][py] = col;
                rt[0] = px;
                rt[1] = py;
                //return rt;
            }
            else
            {
                rt = A.ask(m, col);
                m.mmp[rt[0]][rt[1]] = col;
                //return rt;
            }
        }
        File file = new File("output.txt");
        file.delete();
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try
        {
            fw = new FileWriter("output.txt", true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.print(rt[1] + " ");
        pw.flush();
        pw.println(rt[0]);
        pw.flush();
        wuziqi.tx = new JLabel("");
        wuziqi.jp.add(wuziqi.tx);
        wuziqi.gl.setgraph(wuziqi.g);
        return mm1;
    }
}
