public class MyList<T> {
    private T[] array;
    private int size;
    private int capacity;

    public MyList() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public MyList(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Kapasite 0'dan büyük olmalıdır.");

        this.array = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(T data) {
        if (size == capacity) {
            resizeArray();
        }

        array[size++] = data;
    }

    private void resizeArray() {
        capacity *= 2;
        T[] newArray = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            return null;

        return array[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            return;

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null;
        size--;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size)
            return;

        array[index] = data;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(array[i]);

            if (i != size - 1)
                sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }

    public int indexOf(T data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data))
                return i;
        }

        return -1;
    }

    public int lastIndexOf(T data) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(data))
                return i;
        }

        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T[] toArray() {
        T[] newArray = (T[]) new Object[size];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        return newArray;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
        capacity = 10;
    }

    public MyList<T> subList(int start, int finish) {
        if (start < 0 || start >= size || finish < start || finish >= size)
            throw new IndexOutOfBoundsException();

        MyList<T> sublist = new MyList<>(finish - start + 1);

        for (int i = start; i <= finish; i++) {
            sublist.add(array[i]);
        }

        return sublist;
    }

    public boolean contains(T data) {
        return indexOf(data) != -1;
    }
}
