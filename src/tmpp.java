public class tmpp
{
    static void swap(Integer i, Integer j)
    {
        Integer temp = i;
        i = j;
        j = temp;
    }

    public static void main(String args[])
    {
        Integer a = 10;
        Integer b = 1;
        swap(a, b);
        System.out.println(a);
        System.out.println(b);
    }

}
