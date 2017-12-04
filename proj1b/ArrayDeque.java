public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(Item x){
        if (items.length == size) {
            resizeUp(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (items.length == size) {
            resizeUp(items.length * 2);
        }
        items[nextLast] = x;
        nextLast = addOne(nextLast);
        size++;
    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public Item removeFirst() {
        Item item = getFirst();
        items[addOne(nextFirst)] = null;
        if (items.length >= size * 4) {
            int newSize = (items.length / 2);
            Item[] temp = (Item[]) new Object[newSize];
            int tempMiddle = newSize / 2;
            int j = addOne(addOne(nextFirst));
            for(int i = tempMiddle; i < tempMiddle + size; j=addOne(j), i++){
                temp[i] = items[j];
            }
            items = temp;
            nextFirst = minusOne(minusOne(tempMiddle));
            nextLast = items.length - 1;
        }
        nextFirst = addOne(nextFirst);
        size--;
        return item;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        Item item = getLast();
        items[minusOne(nextLast)] = null;
        if (items.length >= size * 4) {
            int newSize = (items.length / 2);
            Item[] temp = (Item[]) new Object[newSize];
            int tempMiddle = newSize / 2;
            int j = addOne(nextFirst);
            for(int i = tempMiddle; i < tempMiddle + size; j=addOne(j), i++){
                temp[i] = items[j];
            }
            items = temp;
            nextFirst = minusOne(tempMiddle);
            nextLast = addOne(items.length - 1);
        }
        nextLast = minusOne(nextLast);
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = 0; i < size; i++)
            System.out.println(this.get(i));
    }

    /** Returns the item from the back of the list. */
    public Item getFirst() { return items[addOne(nextFirst)]; }

    /** Returns the item from the back of the list. */
    public Item getLast() { return items[minusOne(nextLast)]; }

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        int index = addOne(i + nextFirst);
        if (index >= items.length) {
            return items[index-items.length];
        } else {
            return items[index];
        }
    }


    /** Minus one from index by circular and return index */
    private int minusOne(int index) {
        if (index == 0) {index = items.length - 1;}
        else index--;
        return index;
    }

    /** Add one to index by circular and return index */
    private int addOne(int index) {
        if (index == items.length - 1) {index = 0;}
        else index++;
        return index;
    }

    private void resizeUp(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        //System.arraycopy(items, 0, temp, 0, size);
        int tempMiddle = capacity / 2;
        int j = addOne(nextFirst);
        for(int i = tempMiddle; i < tempMiddle + size; j=addOne(j), i++) {
            temp[i] = items[j];
        }
        items = temp;
        nextFirst = minusOne(tempMiddle);
        nextLast = addOne(tempMiddle + size - 1);
    }
}
