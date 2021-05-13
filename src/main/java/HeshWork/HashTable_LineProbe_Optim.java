package HeshWork;

public class HashTable_LineProbe_Optim
{
    public NewDate[] hashTable;
    public int numberDeletedElements;
    private int arraySize;
    private NewDate deletedElement = new NewDate(-1);

    HashTable_LineProbe_Optim(int size)
    {
        hashTable = new NewDate[size];
        numberDeletedElements = 0;
        arraySize = size;
    }

    public int hashFunction(int key) // Хеш-функция
    {
        return key % arraySize;
    }

    public boolean insert(NewDate newDate) // Вставка элементов в хеш-таблицу
    {
        int index = hashFunction(newDate.getNewDate());
        while(hashTable[index] != null)
        {
            if (hashTable[index].getNewDate() == deletedElement.getNewDate())
            {
                numberDeletedElements--;
                break;
            }
            if (++index == arraySize)
            {
                resize();
                numberDeletedElements = 0;
                int index2 = hashFunction(newDate.getNewDate());
                while(hashTable[index2] != null)
                {
                    index2++;
                    if (index2 >= arraySize)
                    {
                        index2--;
                        break;
                    }
                }
                hashTable[index2] = newDate;
                return true;
            }
        }
        hashTable[index] = newDate;
        return true;
    }

    public void show() // Отображение хеш-таблицы
    {
        System.out.print("[");
        for (int i = 0; i < arraySize; i++)
        {
            if (hashTable[i] == null)
            {
                System.out.print((i <= arraySize - 2) ? "*, " : "*");
            }
            else
            {
                System.out.print((i <= arraySize - 2) ? (hashTable[i].getNewDate() + ", ") : hashTable[i].getNewDate());
            }
        }
        System.out.println("]");
    }

    public void resize() // Увеличение размера массива на следующее простое число
    {
        int newArraySize = getNewNumber(arraySize * 2);
        NewDate[] oldHashTable = new NewDate[arraySize];
        System.arraycopy(hashTable, 0, oldHashTable, 0, arraySize);
        hashTable = new NewDate[newArraySize];
        arraySize = newArraySize;
        for (int i = 0; i < oldHashTable.length; i++)
        {
            if (oldHashTable[i] == null)
            {
            }
            else if (oldHashTable[i].getNewDate() == deletedElement.getNewDate())
            {
            }
            else
            {
                int index = hashFunction(oldHashTable[i].getNewDate());
                while(hashTable[index] != null)
                {
                    if (++index >= arraySize)
                    {
                        index--;
                        break;
                    }
                }
                hashTable[index] = oldHashTable[i];
            }
        }
        oldHashTable = null;
    }

    public int getNewNumber(int initNumber) // Метод получение нового простого числа
    {
        int counter = initNumber;
        int newNumber = 0;
        boolean foundedNewNumber = false;
        while(foundedNewNumber == false)
        {
            foundedNewNumber = true;
            counter++;
            for (int i = 2; (i * i <= counter); i++)
            {
                if (counter % i == 0)
                {
                    foundedNewNumber = false;
                    break;
                }
            }
            if (foundedNewNumber == true)
            {
                newNumber = counter;
                break;
            }
        }
        return newNumber;
    }

    public boolean remove(NewDate newDate) // Удаление элементов из хеш-таблицы
    {
        int index = hashFunction(newDate.getNewDate());
        while(true)
        {
            if (hashTable[index] == null)
            {
            }
            else if (hashTable[index].getNewDate() == deletedElement.getNewDate())
            {
            }
            else if (hashTable[index].getNewDate() == newDate.getNewDate())
            {
                hashTable[index] = deletedElement;
                numberDeletedElements++;
                if (numberDeletedElements > arraySize / 2)
                {
                    rehashTable();
                }
                return true;
            }
            index++;
            if (index >= arraySize)
            {
                return false;
            }
        }
    }

    public void rehashTable() // Рехеширование таблицы
    {
        NewDate[] oldHashTable = new NewDate[arraySize];
        System.arraycopy(hashTable, 0, oldHashTable, 0, arraySize);
        hashTable = new NewDate[arraySize];
        for (int i = 0; i < oldHashTable.length; i++)
        {
            if (oldHashTable[i] == null)
            {
            }
            else if (oldHashTable[i].getNewDate() == deletedElement.getNewDate())
            {
                oldHashTable[i] = null;
            }
            else
            {
                insert(oldHashTable[i]);
            }
        }
        numberDeletedElements = 0;
        oldHashTable = null;
    }

    public NewDate find(int key) // Поиск элемента в хеш-таблице
    {
        int index = hashFunction(key);

        while(true)
        {
            if ((hashTable[index] != null) && (hashTable[index].getNewDate() == key))
            {
                return hashTable[index];
            }
            index++;
            if (index >= arraySize)
            {
                return null;
            }
        }
    }
}