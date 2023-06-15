import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Scanner;

class k1
{
    public static int a[][] = new int[21][21];
    public static int cnt;
    public static int xianshou = 0;
    public static int flaglost = 0;
    public static int ta = 0;
    public static point[] sta = new point[300];

    //int duizhan=0;
}

class jblis implements MouseListener
{
    Graphics g;

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    private wuziqi frame = null;

    public jblis(wuziqi frame)
    {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        for (int i = 0; i <= 20; i++)
            for (int j = 0; j <= 20; j++)
                k1.a[i][j] = 0;
        k1.cnt = 0;
        k1.xianshou = 1;
        k1.flaglost = 0;
        k1.ta = 0;
        frame.paint(g);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

class jblis1 implements MouseListener
{
    Graphics g;

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    private wuziqi frame = null;

    public jblis1(wuziqi frame)
    {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        for (int i = 0; i <= 20; i++)
            for (int j = 0; j <= 20; j++)
                k1.a[i][j] = 0;
        k1.cnt = 0;
        k1.xianshou = 2;
        k1.flaglost = 0;
        Image blackchess= new ImageIcon("black.png").getImage();   //这里不能用ImageIcon
        Image whitechess= new ImageIcon("white.png").getImage();   //这里不能用ImageIcon
        chess tmp1 = new chess();
        tmp1.mmp = k1.a;
        File f = new File("input.txt");//指定文件
        FileOutputStream fos = null;//创建输出流fos并以f为参数
        try
        {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);//创建字符输出流对象osw并以fos为参数
        BufferedWriter bw = new BufferedWriter(osw);//创建一个带缓冲的输出流对象bw，并以osw为参数
        for (int i = 1; i <= 15; i++)
        {
            for (int j = 1; j <= 15; j++)
            {
                if (k1.a[i][j] == -1)
                {
                    try
                    {
                        bw.write("-1 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (k1.a[i][j] == 0)
                {
                    try
                    {
                        bw.write("0 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (k1.a[i][j] == 1)
                {
                    try
                    {
                        bw.write("1 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            try
            {
                bw.write("\n");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        try
        {
            bw.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }


        Runtime run = Runtime.getRuntime();

        tmp1.mmp = k1.a;

        int numm = tmp1.asknum();

        /*this.frame.tx = new JLabel("Waiting");
        this.frame.tx.setBounds(650, 350, 150, 40);
        this.frame.jp.add(this.frame.tx);
        this.frame.gl.setgraph(this.frame.g);*/
        //this.frame.paint(this.frame.g);

        Game.gam(1, numm == 0 ? 1 : 3);

        /*this.frame.tx = new JLabel("");
        this.frame.tx.setBounds(650, 350, 150, 40);
        this.frame.jp.add(this.frame.tx);
        this.frame.gl.setgraph(this.frame.g);
        //this.frame.paint(this.frame.g);*/

        File f5 = new File("output.txt");//指定文件
        FileInputStream fis = null;//创建输入流fis并以f为参数
        try
        {
            fis = new FileInputStream(f5);
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);//创建字符输入流对象isr并以fis为参数
        BufferedReader br = new BufferedReader(isr);//创建一个带缓冲的输入流对象br，并以isr为参数
        try
        {
            String result = br.readLine();//使用bufferedreader读取一行文字并将读取值赋给字符串result。每执行一次br.readLine();,就会往下读取一行。
            String[] ss1 = result.split(" ");
            int f11, f12;
            f11 = Integer.parseInt(ss1[0]);
            f12 = Integer.parseInt(ss1[1]);
            System.out.printf("%d %d ", f11, f12);
            k1.cnt++;
            k1.a[f11][f12] = 1;
            k1.sta[++ k1.ta] = new point(f11, f12, 0);
            g.drawImage(blackchess, f11 * 39, f12 * 39, 39, 39,null);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        try
        {
            br.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        frame.paint(g);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

class jblis2 implements MouseListener
{
    Graphics g;

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    private wuziqi frame = null;

    public jblis2(wuziqi frame)
    {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        k1.flaglost = 1;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

class jblis3 implements MouseListener
{
    Graphics g;

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    private wuziqi frame = null;

    public jblis3(wuziqi frame)
    {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(k1.ta >= 2)
        {
            int tx = k1.sta[k1.ta].x;
            int ty = k1.sta[k1.ta].y;
            k1.ta --;
            k1.a[tx][ty] = 0;
            tx = k1.sta[k1.ta].x;
            ty = k1.sta[k1.ta].y;
            k1.ta --;
            k1.a[tx][ty] = 0;
            frame.paint(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

class golis implements MouseListener
{
    private wuziqi frame = null;

    public golis(wuziqi frame)
    {
        this.frame = frame;
    }

    Graphics g;
    int flag = 1;//1 黑 -1 白

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    int quzheng(int x)
    {
        // if(x%56<=23)
        return x / 39 * 39;
        //return x/56*56+56;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(k1.xianshou == 0)
        {
            JOptionPane.showConfirmDialog(null, "游戏未开始，请选择黑白！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        int x = e.getX();
        int y = e.getY();
        chess tmp1 = new chess();
        tmp1.mmp = k1.a;
        x = quzheng(x);
        y = quzheng(y);
        if ((x < 39) || (x >= 39 * 16))
            return;
        if ((y < 39) || (y >= 39 * 16))
            return;
        if (k1.a[x / 39][y / 39] != 0)
            return;
        if (k1.flaglost == 1)
        {
            JOptionPane.showConfirmDialog(null, "您已经认输，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == 1)
        {
            JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == 2)
        {
            JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == -1)
        {
            JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if(k1.xianshou == 1)
        {
            k1.cnt++;
            //  System.out.printf("%d ",k1.cnt);
            if ((k1.cnt & 1) == 1)
                flag = 1;
            else
                flag = -1;
            k1.a[x / 39][y / 39] = flag;
            k1.sta[++ k1.ta] = new point(x / 39, y / 39, 0);
            Image blackchess= new ImageIcon("black.png").getImage();   //这里不能用ImageIcon
            Image whitechess= new ImageIcon("white.png").getImage();   //这里不能用ImageIcon
            if(flag == 1)
                g.drawImage(blackchess, x, y, 39, 39,null);
            else
                g.drawImage(whitechess, x, y, 39, 39,null);
            //g.fillOval(x, y, 39, 39);
            //if (flag == 1)
            //    g.setColor(Color.white);
            //else
            //    g.setColor(Color.black);
            //flag=-flag;


            File f = new File("input.txt");//指定文件
            FileOutputStream fos = null;//创建输出流fos并以f为参数
            try
            {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos);//创建字符输出流对象osw并以fos为参数
            BufferedWriter bw = new BufferedWriter(osw);//创建一个带缓冲的输出流对象bw，并以osw为参数
            for (int i = 1; i <= 15; i++)
            {
                for (int j = 1; j <= 15; j++)
                {
                    if (k1.a[i][j] == -1)
                    {
                        try
                        {
                            bw.write("-1 ");
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    if (k1.a[i][j] == 0)
                    {
                        try
                        {
                            bw.write("0 ");
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    if (k1.a[i][j] == 1)
                    {
                        try
                        {
                            bw.write("1 ");
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
                try
                {
                    bw.write("\n");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            try
            {
                bw.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }


            Runtime run = Runtime.getRuntime();

            tmp1.mmp = k1.a;

            if (tmp1.ans(0) == 1)
            {
                JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == 2)
            {
                JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == -1)
            {
                JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }

            /*this.frame.tx = new JLabel("Waiting");
            this.frame.tx.setBounds(650, 350, 150, 40);
            this.frame.jp.add(this.frame.tx);
            this.frame.gl.setgraph(this.frame.g);
            //this.frame.paint(this.frame.g);*/

            //this.frame.tx.setText("Finished");
            //this.frame.repaint();

            Game.gam(2, 2);

            //this.frame.tx.setText("Finished");
            //this.frame.repaint();

            /*this.frame.tx = new JLabel("");
            this.frame.tx.setBounds(650, 350, 150, 40);
            this.frame.jp.add(this.frame.tx);
            this.frame.gl.setgraph(this.frame.g);*/
            //this.frame.paint(this.frame.g);

            File f5 = new File("output.txt");//指定文件
            FileInputStream fis = null;//创建输入流fis并以f为参数
            try
            {
                fis = new FileInputStream(f5);
            } catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            InputStreamReader isr = new InputStreamReader(fis);//创建字符输入流对象isr并以fis为参数
            BufferedReader br = new BufferedReader(isr);//创建一个带缓冲的输入流对象br，并以isr为参数
            try
            {
                String result = br.readLine();//使用bufferedreader读取一行文字并将读取值赋给字符串result。每执行一次br.readLine();,就会往下读取一行。
                String[] ss1 = result.split(" ");
                int f11, f12;
                f11 = Integer.parseInt(ss1[0]);
                f12 = Integer.parseInt(ss1[1]);
                System.out.printf("%d %d ", f11, f12);
                k1.cnt++;
                //  System.out.printf("%d ",k1.cnt);
                if ((k1.cnt & 1) == 1)
                    flag = 1;
                else
                    flag = -1;
                k1.a[f11][f12] = flag;
                k1.sta[++ k1.ta] = new point(f11, f12, 0);
                if(flag == 1)
                    g.drawImage(blackchess, f11 * 39, f12 * 39, 39, 39,null);
                else
                    g.drawImage(whitechess, f11 * 39, f12 * 39, 39, 39,null);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            try
            {
                br.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

            if (tmp1.ans(0) == 1)
            {
                JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == 2)
            {
                JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == -1)
            {
                JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
        }
        else if(k1.xianshou == 2)
        {
            Image blackchess= new ImageIcon("black.png").getImage();   //这里不能用ImageIcon
            Image whitechess= new ImageIcon("white.png").getImage();   //这里不能用ImageIcon

            k1.cnt ++;
            //  System.out.printf("%d ",k1.cnt);
            if ((k1.cnt & 1) == 1)
                flag = 1;
            else
                flag = -1;
            k1.a[x / 39][y / 39] = flag;
            k1.sta[++ k1.ta] = new point(x / 39, y / 39, 0);
            if(flag == 1)
                g.drawImage(blackchess, x, y, 39, 39,null);
            else
                g.drawImage(whitechess, x, y, 39, 39,null);

            File f = new File("input.txt");//指定文件
            FileOutputStream fos = null;//创建输出流fos并以f为参数
            try
            {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos);//创建字符输出流对象osw并以fos为参数
            BufferedWriter bw = new BufferedWriter(osw);//创建一个带缓冲的输出流对象bw，并以osw为参数
            for (int i = 1; i <= 15; i++)
            {
                for (int j = 1; j <= 15; j++)
                {
                    if (k1.a[i][j] == -1)
                    {
                        try
                        {
                            bw.write("-1 ");
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    if (k1.a[i][j] == 0)
                    {
                        try
                        {
                            bw.write("0 ");
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    if (k1.a[i][j] == 1)
                    {
                        try
                        {
                            bw.write("1 ");
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
                try
                {
                    bw.write("\n");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            try
            {
                bw.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }


            Runtime run = Runtime.getRuntime();

            tmp1.mmp = k1.a;

            if (tmp1.ans(0) == 1)
            {
                JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == 2)
            {
                JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == -1)
            {
                JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            /*this.frame.tx = new JLabel("Waiting");
            this.frame.tx.setBounds(650, 350, 150, 40);
            this.frame.jp.add(this.frame.tx);
            this.frame.gl.setgraph(this.frame.g);*/
            //this.frame.paint(this.frame.g);

            Game.gam(1, 3);

            /*this.frame.tx = new JLabel("");
            this.frame.tx.setBounds(650, 350, 150, 40);
            this.frame.jp.add(this.frame.tx);
            this.frame.gl.setgraph(this.frame.g);*/
            //this.frame.paint(this.frame.g);

            File f5 = new File("output.txt");//指定文件
            FileInputStream fis = null;//创建输入流fis并以f为参数
            try
            {
                fis = new FileInputStream(f5);
            } catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            InputStreamReader isr = new InputStreamReader(fis);//创建字符输入流对象isr并以fis为参数
            BufferedReader br = new BufferedReader(isr);//创建一个带缓冲的输入流对象br，并以isr为参数
            try
            {
                String result = br.readLine();//使用bufferedreader读取一行文字并将读取值赋给字符串result。每执行一次br.readLine();,就会往下读取一行。
                String[] ss1 = result.split(" ");
                int f11, f12;
                f11 = Integer.parseInt(ss1[0]);
                f12 = Integer.parseInt(ss1[1]);
                k1.sta[++ k1.ta] = new point(f11, f12, 0);
                System.out.printf("%d %d ", f11, f12);
                k1.cnt++;
                //  System.out.printf("%d ",k1.cnt);
                if ((k1.cnt & 1) == 1)
                    flag = 1;
                else
                    flag = -1;
                k1.a[f11][f12] = flag;
                if(flag == 1)
                    g.drawImage(blackchess, f11 * 39, f12 * 39, 39, 39,null);
                else
                    g.drawImage(whitechess, f11 * 39, f12 * 39, 39, 39,null);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            try
            {
                br.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

            if (tmp1.ans(0) == 1)
            {
                JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == 2)
            {
                JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
            if (tmp1.ans(0) == -1)
            {
                JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
                return;
            }
        }
        else
        {
            Image blackchess= new ImageIcon("black.png").getImage();   //这里不能用ImageIcon
            Image whitechess= new ImageIcon("white.png").getImage();   //这里不能用ImageIcon

            k1.cnt ++;
            //  System.out.printf("%d ",k1.cnt);
            if ((k1.cnt & 1) == 1)
                flag = 1;
            else
                flag = -1;
            k1.a[x / 39][y / 39] = flag;
            k1.sta[++ k1.ta] = new point(x / 39, y / 39, 0);
            if(flag == 1)
                g.drawImage(blackchess, x, y, 39, 39,null);
            else
                g.drawImage(whitechess, x, y, 39, 39,null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

class jblis4 implements MouseListener
{
    Graphics g;

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    private wuziqi frame = null;

    public jblis4(wuziqi frame)
    {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        for (int i = 0; i <= 20; i++)
            for (int j = 0; j <= 20; j++)
                k1.a[i][j] = 0;
        k1.cnt = 0;
        k1.xianshou = 3;
        k1.flaglost = 0;
        k1.ta = 0;
        frame.paint(g);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

class jblis5 implements MouseListener
{
    Graphics g;

    public void setgraph(Graphics g)
    {
        this.g = g;
    }

    private wuziqi frame = null;

    public jblis5(wuziqi frame)
    {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        chess tmp1 = new chess();
        tmp1.mmp = k1.a;
        if (k1.flaglost == 1)
        {
            JOptionPane.showConfirmDialog(null, "您已经认输，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == 1)
        {
            JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == 2)
        {
            JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == -1)
        {
            JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        Image blackchess= new ImageIcon("black.png").getImage();   //这里不能用ImageIcon
        Image whitechess= new ImageIcon("white.png").getImage();   //这里不能用ImageIcon
        int flag = 0;
        k1.cnt ++;
        //  System.out.printf("%d ",k1.cnt);
        if ((k1.cnt & 1) == 1)
            flag = 1;
        else
            flag = -1;
        File f = new File("input.txt");//指定文件
        FileOutputStream fos = null;//创建输出流fos并以f为参数
        try
        {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);//创建字符输出流对象osw并以fos为参数
        BufferedWriter bw = new BufferedWriter(osw);//创建一个带缓冲的输出流对象bw，并以osw为参数
        for (int i = 1; i <= 15; i++)
        {
            for (int j = 1; j <= 15; j++)
            {
                if (k1.a[i][j] == -1)
                {
                    try
                    {
                        bw.write("-1 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (k1.a[i][j] == 0)
                {
                    try
                    {
                        bw.write("0 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (k1.a[i][j] == 1)
                {
                    try
                    {
                        bw.write("1 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            try
            {
                bw.write("\n");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        try
        {
            bw.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        int tmpp = 2;
        if(flag == 1)
            tmpp = tmp1.asknum() > 0 ? 3 : 1;

        /*this.frame.tx = new JLabel("Waiting");
        this.frame.tx.setBounds(650, 350, 150, 40);
        this.frame.jp.add(this.frame.tx);
        this.frame.gl.setgraph(this.frame.g);*/
        //this.frame.paint(this.frame.g);

        Game.gam(flag == 1 ? 1 : 2, tmpp);

        /*this.frame.tx = new JLabel("");
        this.frame.tx.setBounds(650, 350, 150, 40);
        this.frame.jp.add(this.frame.tx);
        this.frame.gl.setgraph(this.frame.g);*/
        //this.frame.paint(this.frame.g);

        File f5 = new File("output.txt");//指定文件
        FileInputStream fis = null;//创建输入流fis并以f为参数
        try
        {
            fis = new FileInputStream(f5);
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);//创建字符输入流对象isr并以fis为参数
        BufferedReader br = new BufferedReader(isr);//创建一个带缓冲的输入流对象br，并以isr为参数
        try
        {
            String result = br.readLine();//使用bufferedreader读取一行文字并将读取值赋给字符串result。每执行一次br.readLine();,就会往下读取一行。
            String[] ss1 = result.split(" ");
            int f11, f12;
            f11 = Integer.parseInt(ss1[0]);
            f12 = Integer.parseInt(ss1[1]);
            k1.sta[++ k1.ta] = new point(f11, f12, 0);
            System.out.printf("%d %d ", f11, f12);
            k1.a[f11][f12] = flag;
            if(flag == 1)
                g.drawImage(blackchess, f11 * 39, f12 * 39, 39, 39,null);
            else
                g.drawImage(whitechess, f11 * 39, f12 * 39, 39, 39,null);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        try
        {
            br.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        if (tmp1.ans(0) == 1)
        {
            JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == 2)
        {
            JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == -1)
        {
            JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }

        flag = 0;
        k1.cnt ++;
        //  System.out.printf("%d ",k1.cnt);
        if ((k1.cnt & 1) == 1)
            flag = 1;
        else
            flag = -1;
        f = new File("input.txt");//指定文件
        fos = null;//创建输出流fos并以f为参数
        try
        {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        osw = new OutputStreamWriter(fos);//创建字符输出流对象osw并以fos为参数
        bw = new BufferedWriter(osw);//创建一个带缓冲的输出流对象bw，并以osw为参数
        for (int i = 1; i <= 15; i++)
        {
            for (int j = 1; j <= 15; j++)
            {
                if (k1.a[i][j] == -1)
                {
                    try
                    {
                        bw.write("-1 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (k1.a[i][j] == 0)
                {
                    try
                    {
                        bw.write("0 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (k1.a[i][j] == 1)
                {
                    try
                    {
                        bw.write("1 ");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            try
            {
                bw.write("\n");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        try
        {
            bw.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        tmpp = 2;
        if(flag == 1)
            tmpp = tmp1.asknum() > 0 ? 3 : 1;

        /*this.frame.tx = new JLabel("Waiting");
        this.frame.tx.setBounds(650, 350, 150, 40);
        this.frame.jp.add(this.frame.tx);
        this.frame.gl.setgraph(this.frame.g);*/
        //this.frame.paint(this.frame.g);

        Game.gam(flag == 1 ? 1 : 2, tmpp);

        /*this.frame.tx = new JLabel("");
        this.frame.tx.setBounds(650, 350, 150, 40);
        this.frame.jp.add(this.frame.tx);
        this.frame.gl.setgraph(this.frame.g);*/
        //this.frame.paint(this.frame.g);

        f5 = new File("output.txt");//指定文件
        fis = null;//创建输入流fis并以f为参数
        try
        {
            fis = new FileInputStream(f5);
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        isr = new InputStreamReader(fis);//创建字符输入流对象isr并以fis为参数
        br = new BufferedReader(isr);//创建一个带缓冲的输入流对象br，并以isr为参数
        try
        {
            String result = br.readLine();//使用bufferedreader读取一行文字并将读取值赋给字符串result。每执行一次br.readLine();,就会往下读取一行。
            String[] ss1 = result.split(" ");
            int f11, f12;
            f11 = Integer.parseInt(ss1[0]);
            f12 = Integer.parseInt(ss1[1]);
            k1.sta[++ k1.ta] = new point(f11, f12, 0);
            System.out.printf("%d %d ", f11, f12);
            k1.a[f11][f12] = flag;
            if(flag == 1)
                g.drawImage(blackchess, f11 * 39, f12 * 39, 39, 39,null);
            else
                g.drawImage(whitechess, f11 * 39, f12 * 39, 39, 39,null);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        try
        {
            br.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        if (tmp1.ans(0) == 1)
        {
            JOptionPane.showConfirmDialog(null, "黑方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == 2)
        {
            JOptionPane.showConfirmDialog(null, "白方已胜利，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
        if (tmp1.ans(0) == -1)
        {
            JOptionPane.showConfirmDialog(null, "双方已平局，请重启游戏！", "提示", JOptionPane.YES_NO_OPTION);
            return;
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}

public class wuziqi extends JFrame
{
    public static void main(String[] args)
    {
        wuziqi gogo = new wuziqi();
        gogo.init();
    }

    static JLabel tx = new JLabel();

    static JPanel jp = new JPanel();

    static golis gl;

    static Graphics g;

    public void init()
    {
        this.setTitle("alphago");
        this.setSize(850, 660);
        this.setDefaultCloseOperation(3);
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //this.setLayout(new FlowLayout());
        //this.setLayout(new BorderLayout());
        g = this.getGraphics();
        jp = new JPanel();
        //jp.setBackground(Color.white);
        jp.setLayout(null);
        this.add(jp);
        JButton jb = new JButton("人机对战（人执黑）");
        JButton jb1 = new JButton("人机对战（人执白）");
        JButton jb2 = new JButton("认输");
        JButton jb3 = new JButton("悔棋");
        JButton jb4 = new JButton("人人对战");
        JButton jb5 = new JButton("惊天妙手（求助一步）");
        //tx = new JLabel("ri");
        jb.setBounds(650, 50, 150, 40);
        jb1.setBounds(650, 100, 150, 40);
        jb2.setBounds(650, 150, 150, 40);
        jb3.setBounds(650, 200, 150, 40);
        jb4.setBounds(650, 250, 150, 40);
        jb5.setBounds(650, 300, 150, 40);
        tx.setBounds(650, 350, 150, 40);
        jblis t = new jblis(this);
        jblis1 t1 = new jblis1(this);
        jblis2 t2 = new jblis2(this);
        jblis3 t3 = new jblis3(this);
        jblis4 t4 = new jblis4(this);
        jblis5 t5 = new jblis5(this);
        t.setgraph(g);
        t1.setgraph(g);
        t2.setgraph(g);
        t3.setgraph(g);
        t4.setgraph(g);
        t5.setgraph(g);
        jb.addMouseListener(t);
        jb1.addMouseListener(t1);
        jb2.addMouseListener(t2);
        jb3.addMouseListener(t3);
        jb4.addMouseListener(t4);
        jb5.addMouseListener(t5);
        jp.add(jb);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        jp.add(jb5);
        //jp.add(tx);
        gl = new golis(this);
        gl.setgraph(g);
        this.addMouseListener(gl);
        //g.drawLine(100,300,300,300);
    }

    @Override
    public void paint(Graphics g)
    {
        //System.out.printf("#");
        super.paint(g);
        Image icon= new ImageIcon("chessboard.jpg").getImage();
        g.drawImage(icon, 32, 38, 600, 600, null);
        int f1 = 39;
        g.setColor(Color.black);
        //for (int i = 1; i <= 16; i++, f1 += 39)
        //    g.drawLine(39, f1, 39 * 16, f1);
        f1 = 39;
        //for (int i = 1; i <= 16; i++, f1 += 39)
        //    g.drawLine(f1, 39, f1, 39 * 16);
        int f2;
        f1 = f2 = 39;
        //System.out.printf("%d ",k1.cnt);
        Image blackchess= new ImageIcon("black.png").getImage();   //这里不能用ImageIcon
        Image whitechess= new ImageIcon("white.png").getImage();   //这里不能用ImageIcon
        for (int i = 1; i <= 16; i++, f1 += 39)
        {
            f2 = 39;
            for (int j = 1; j <= 16; j++, f2 += 39)
            {
                //  System.out.printf("%d %d\n",f1,f2);
                if (k1.a[i][j] == 0)
                    continue;
                g.setColor(Color.black);
                if (k1.a[i][j] == 1)
                {
                    //g.fillOval(f1, f2, 39, 39);
                    g.drawImage(blackchess, f1, f2, 39, 39,null);
                    //System.out.printf("#%d %d",f1,f2);
                }
                g.setColor(Color.white);
                if (k1.a[i][j] == -1)
                {
                    g.drawImage(whitechess, f1, f2, 39, 39,null);
                    //g.fillOval(f1, f2, 39, 39);
                    //System.out.printf("%%");
                }
            }
        }/**/
    }
}