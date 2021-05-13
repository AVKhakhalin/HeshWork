package HeshWork;

public class HashTableLineProbe
{
    private Item[] hashArr; // Это сама хеш-таблица
    public int arrSize;    // Размер нашего массива
    private Item nonItem;   // Обозначение: элемента не существует. Т.е. замена существующего элемента в массиве на данный элемент, что эквивалентно удалению элемента из массива

    public HashTableLineProbe(int size)
    {
        this.arrSize = size * 2;      // Задаём размер нашей хеш-таблицы в два раза больший, чем исходный массив
        hashArr = new Item[arrSize];  // Создаём саму хеш-таблицу нужного размера
        nonItem = new Item(-1); // Создаём элемент, которого нет, чтобы его вставлять в хеш-таблицу на место удаляемого элемента
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

    public int hashFunc(int key) // Хеш-функция (Вариант 1)
    {
        return key % arrSize; // Масштабирует хеш по размеру нашего массива arrSize
    }

    public boolean insert(Item item) // Вставка нового элемента в хеш-таблицу. На вход подаётся переменная, из которой берётся ключ (data). Данный метод похож на поиск элемента в хеш-таблице, но он ищет пустую ячейку (null) или удалённый элемент (-1)
    {
        int key = item.getKey();     // Создаём ключ
        int hashVal = hashFunc(key); // Создаём переменную, которая будет содержать в себе хеш
        while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) // Условие hashArr[hashVal].getKey() != -1 нужно для проверки достижения конца хеш таблицы
        {
            ++hashVal;
//            hashVal %= arrSize; // Возвращение перебора к началу таблицы
                                  // ЗДЕСЬ ОШИБКА: Если хеш-таблица полностью заполнена, т.е. в хеш-таблице нет места, то цикл будет идти вечно
            if (hashVal == arrSize)
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
        while (hashArr[hashVal] != null) // Пока у нас не наступил конец хеш-таблицы, мы что-то выполняем
        {
            if (hashArr[hashVal].getKey() == key)
            {
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp; // Возвращаем удалённый элемент
            }
            ++hashVal;
//            hashVal %= arrSize; // ЗДЕСЬ ОШИБКА: если искать элемент по не существующему в хеш-таблице ключу, то программа зависнет
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
        while (hashArr[hashVal] != null)
        {
            if (hashArr[hashVal].getKey() == key)
            {
                return hashArr[hashVal];
            }
            ++hashVal;
//            hashVal %= arrSize; // Переход к началу таблицы
                                  // ЗДЕСЬ ОШИБКА: если искать элемент по не существующему в хеш-таблице ключу, то программа зависнет
            if (hashVal == arrSize)
            {
                return null;
            }
        }
        return null;
    }
}
