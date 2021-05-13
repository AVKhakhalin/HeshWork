package HeshWork;

import java.util.Random;

public class RandArray
{
    Random random = new Random();
    int[] newArray_int_400;

    public RandArray(int sizeArray)
    {
        newArray_int_400 = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++)
        {
            newArray_int_400[i] = random.nextInt(sizeArray);
        }
    }

    public int[] getNewArray_int_400()
    {
        return newArray_int_400;
    }
}
