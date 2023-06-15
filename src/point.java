public class point implements Comparable<point>
{
    int x, y, val;

    public point(int a, int b, int c)
    {
        x = a;
        y = b;
        val = c;
    }

    @Override
    public int compareTo(point o1)
    {
        return o1.val - val;
    }
}
