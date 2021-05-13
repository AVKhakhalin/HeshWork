package HeshWork;

public class HashTableDoubleHashing
{
    private Item[] hashArr; // Это сама хеш-таблица
    private int arrSize;    // Размер нашего массива
    private Item nonItem;   // Обозначение: элемента не существует. Т.е. замена существующего элемента в массиве на данный элемент, что эквивалентно удалению элемента из массива

    public HashTableDoubleHashing(int size)
    {
        this.arrSize = size * 2;          // Задаём размер нашей хеш-таблицы
        hashArr = new Item[arrSize];  // Создаём саму хеш-таблицу нужного размера
        nonItem = new Item(-1);       // Создаём элемент, которого нет, чтобы его вставлять в хеш-таблицу на место удаляемого элемента
    }

    public void show() // Метод отображения полной хеш-таблицы
    {
        System.out.print("[");
        for (int i = 0; i < arrSize; i++)
        {
            if (hashArr[i] != null)
            {
                System.out.print((i <= arrSize - 2) ? (hashArr[i].getKey() + ", ") : hashArr[i].getKey());
            }
            else
            {
                System.out.print((i <= arrSize - 2) ? "*, " : "*");
            }
        }
        System.out.println("]");
    }

    public int hashFunc(int key) // Первая хеш-функция
    {
        return key % arrSize; // Масштабирует хеш по размеру нашего массива arrSize
    }

    public int hashFuncDouble(int key) // Вторая хеш-функция
    {
        return 5 - key % 5; // В выражении 5 - key % 5 первое число 5 это константа смещения.
    }

    public boolean insert(Item item) // Вставка нового элемента в хеш-таблицу. На вход подаётся переменная, из которой берётся ключ (data). Данный метод похож на поиск элемента в хеш-таблице, но он ищет пустую ячейку (null) или удалённый элемент (-1)
    {
        int key = item.getKey();     // Создаём ключ
        int hashVal = hashFunc(key); // Создаём переменную, которая будет содержать в себе хеш
        int stepSize = hashFuncDouble(key); // Переменная stepSize выячисляется с помощью двойной хеш-функции.
        while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) // Условие hashArr[hashVal].getKey() != -1 нужно для проверки достижения конца хеш таблицы
        {
            hashVal += stepSize; // Изменение смещения по нашей хеш-таблице. Это смещение отличное от линейного пробирования
            if (hashVal >= arrSize)
            {
                return false;
            }
        }
        hashArr[hashVal] = item;
        return true;
    }

    public Item delete(int key) // Удаление элемента в хеш-таблице
    {
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key); // Вычисляем смещение с помощью второй хеш-функции
        while (hashArr[hashVal] != null) // Пока у нас не наступил конец хеш-таблицы, мы что-то выполняем
        {
            if (hashArr[hashVal].getKey() == key)
            {
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp; // Возвращаем удалённый элемент
            }
            hashVal += stepSize;
            if (hashVal == arrSize)
            {
                return null;
            }
        }
        return null;
    }

    public Item find(int key) // Поиск элемента в хеш-таблице
    {
        int hashVal = hashFunc(key); // Хешируем ключ
        int stepSize = hashFuncDouble(key); // Переменная stepSize вычисляется с помощью второй хеш-функции
        while (hashArr[hashVal] != null)
        {
            if (hashArr[hashVal].getKey() == key)
            {
                return hashArr[hashVal];
            }
            hashVal += stepSize;
            if (hashVal == arrSize)
            {
                return null;
            }
        }
        return null;
    }

    private int getPrime(int min) // Основной метод, который будет вызываться для увеличения хеш-таблицы. min - Это то число, которое мы хотим увеличить
    {
        for (int i = min + 1; true; i++) // Здесь в условии for стоит true. Данное условие означает значит бесконечный цикл. Выход из него осуществляется только вместе с выходом из функции getPrime, когда isPrime(i) будет равно true.
        {
            if (isPrime(i))
            {
                return i;
            }
        }
    }

    private boolean isPrime(int n)
    {
        for (int j = 2; (j * j <= n); j++)
        {
            if (n % j == 0)
            {
                return false;
            }
        }
        return true;
    }
}
