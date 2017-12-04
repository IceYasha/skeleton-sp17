import javax.sound.midi.Track;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private void resizeUp(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        //System.arraycopy(items, 0, temp, 0, size);
        int tempMiddle = temp.length / 2;
        int j = items.length / 2 + 1;
        for(int i = tempMiddle; i < tempMiddle + size; j=addOne(j), i++) {
            temp[i] = items[j];
        }
        items = temp;
        nextFirst = minusOne(tempMiddle);
        nextLast = addOne(tempMiddle + size - 1);
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

    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = 0; i < items.length; i++)
            System.out.println(items[i]);
    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public Item removeFirst() {
        Item item = getFirst();
        items[addOne(nextFirst)] = null;
        if (items.length >= size * 4) {
            int newSize = (items.length / 2);
            Item[] temp = (Item[]) new Object[newSize];
            int tempMiddle = temp.length / 2;
            int j = addOne(addOne(nextFirst));
            for(int i = tempMiddle; i < tempMiddle + size; j=addOne(j), i++){
                temp[i] = items[j];
            }
            items = temp;
            nextFirst = minusOne(tempMiddle);
            nextLast = addOne(tempMiddle + size - 1);
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
            int tempMiddle = temp.length / 2;
            int j = addOne(nextFirst);
            for(int i = tempMiddle; i < tempMiddle + size; j=addOne(j), i++){
                temp[i] = items[j];
            }
            items = temp;
            nextFirst = (minusOne(tempMiddle));
            nextLast = addOne(tempMiddle + size - 1);
        }
        nextLast = minusOne(nextLast);
        size--;
        return item;
    }

    /** Returns the item from the back of the list. */
    public Item getFirst() { return items[addOne(nextFirst)]; }

    /** Returns the item from the back of the list. */
    public Item getLast() { return items[minusOne(nextLast)]; }

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

}
