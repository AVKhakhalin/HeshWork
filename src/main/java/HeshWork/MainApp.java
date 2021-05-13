package HeshWork;

import java.util.Arrays;

public class MainApp
{
    public static int NUMBER_ELEMENTS = 400;

    public static void main(String[] args)
    {
        System.out.println("\nДомашнее задание №8 студента университета GeekBrains Хахалина Андрея Владимировича\n");

        //region Решение задания 8.1
        System.out.println("\nРешение задания 8.1\n");
        /* Задание 8.1
        Приведите пример использование хеш-таблиц. */
        System.out.println("Примеры использования хеш-таблиц:\n" +
                "   1. В базах данных для быстрого поиска информации по запросам применяются хеш-таблицы для хранения тематической информации.\n" +
                "   2. В системах авторизации пользователей хеш-таблицы используются для быстрого сопоставления вводимой ими информации с их профилем.\n");

        //endregion-------------------------------------------------

        //region Решение задания 8.2
        System.out.println("\nРешение задания 8.2\n");
        /* Задание 8.2
        Приведите примеры ключей и коллизий. */
        System.out.println("Примеры ключей:\n" +
                "   1. \"Победа\".\n" +
                "   2. 45.\n" +
                "   3. [1, 2, 3, 4, 5].\n");
        System.out.println("\nПримеры коллизий:\n" +
                "   1. Ключу \"Победа\" могут соответствовать одинаковые индексы в хеш-таблице, соответствующие написанию данного слова с большой и маленькой букв.\n" +
                "   2. Ключу 45 могут соответствовать одинаковые индексы в хеш-таблице, являющиеся его простыми множителями: 1, 5, 9, 45.\n" +
                "   3. Ключу [1, 2, 3, 4, 5] могут соответствовать одинаковые индексы в хеш-таблице, являющиеся элементами массива ключа: 1, 2, 3, 4, 5.");
        //endregion-------------------------------------------------

        //region Решение задания 8.3
        System.out.println("\nРешение задания 8.3\n");
        /* Задание 8.3
        Приведите примеры популярных и эффективных хеш-функций. */
        System.out.println("Популярными хеш-функциями являются:\n" +
                "   1. CRC16/32\n" +
                "   2. MD2/4/5/6\n" +
                "   3. Линейка алогоритмов SHA: SHA224, SHA256, SHA384, SHA512\n" +
                "   4. Российский стандарт ГОСТ 34.11-94.\n" +
                "   5. Метод линейного пробирования.\n" +
                "   6. Методы на основе следующих функций: \"mod\" или \"%\", деления и умножения.");
        System.out.println("\nЭффективными хеш-функциями являются:\n" +
                "   1. SHA3.\n" +
                "   2. BLAKE.\n" +
                "   3. Алгоритм двойного хеширования.");
        //endregion-------------------------------------------------

        //region Решение задания 8.4
        System.out.println("\nРешение задания 8.4\n");
        /* Задание 8.4
        На основе данных массива из задания 2.3 реализуйте хеш-таблицу с помощью открытой адресации, а конкретнее метода линейного пробирования */

        RandArray randArray = new RandArray(NUMBER_ELEMENTS);
        System.out.println("Исходный массив:\n" + Arrays.toString(randArray.getNewArray_int_400()));

        HashTableLineProbe hashTableLineProbe = new HashTableLineProbe(NUMBER_ELEMENTS);
        for (int i = 0; i < NUMBER_ELEMENTS; i++)
        {
            hashTableLineProbe.insert(new Item(randArray.getNewArray_int_400()[i]));
        }
        System.out.println("Хеш-таблица с исходным массивом после применения класса с линейным пробированием HashTableLineProbe:");
        hashTableLineProbe.show();

        HashTable_LineProbe_Optim hashTable_lineProbeOptim = new HashTable_LineProbe_Optim(3);
        for (int i = 0; i < NUMBER_ELEMENTS; i++)
        {
            hashTable_lineProbeOptim.insert(new NewDate(randArray.getNewArray_int_400()[i]));
        }
        System.out.println("Хеш-таблица с исходным массивом после применения метода с линейным пробированием HashTable_LineProbe_Optim:");
        hashTable_lineProbeOptim.show();
        //endregion-------------------------------------------------

        //region Решение задания 8.5
        System.out.println("\nРешение задания 8.5\n");
        /* Задание 8.5
        Перестройте программный код задания 8.4 из алгоритма линейного пробирования в алгоритм двойного хеширования.
        Сравните отличительные черты двух алгоритмов. */

        System.out.println("Исходный массив:\n" + Arrays.toString(randArray.getNewArray_int_400()));

        HashTableDoubleHashing hashTableDoubleHashing = new HashTableDoubleHashing(NUMBER_ELEMENTS);
        for (int i = 0; i < NUMBER_ELEMENTS; i++)
        {
            hashTableDoubleHashing.insert(new Item(randArray.getNewArray_int_400()[i]));
        }
        System.out.println("Хеш-таблица с исходным массивом после применения класса с двойным хешированием HashTableLineProbe:");
        hashTableDoubleHashing.show();

        HashTable_DoubleHashing_Optim hashTable_doubleHashing_optim = new HashTable_DoubleHashing_Optim(NUMBER_ELEMENTS);
        for (int i = 0; i < NUMBER_ELEMENTS; i++)
        {
            hashTable_doubleHashing_optim.insert(new NewDate(randArray.getNewArray_int_400()[i]));
        }
        System.out.println("Хеш-таблица с исходным массивом после применения класса с двойным хешированием HashTable_DoubleHashing_Optim:");
        hashTable_doubleHashing_optim.show();

        System.out.println("\nОТЛИЧИТЕЛЬНЫЕ ЧЕРТЫ ДВУХ АЛГОРИТМОВ:\n" +
                "методом двойного хеширования хеш-таблица заполняется более равномерно, чем методом линейного пробирования.");
        //endregion-------------------------------------------------
    }
}
