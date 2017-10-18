
public class LinkedListDeque<Item> {
    private class Node {
        public Item item;
        public Node pre;
        public Node next;

        public Node(Item i, Node preNode, Node nextNode) {
            item = i;
            pre = preNode;
            next = nextNode;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }

    public void addFirst(Item i) {
        sentinel.next = new Node(i, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size += 1;
    }

    public void addLast(Item i) {
        sentinel.pre = new Node(i, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node currentNode = sentinel.next;
        while (currentNode.next != sentinel) {
            System.out.println(currentNode.item);
            currentNode = currentNode.next;
        }
        System.out.println(currentNode.item);
    }

    public Item removeFirst() {
        Node firstNode;
        if (sentinel.next == sentinel) {
            return null;
        } else {
            firstNode = sentinel.next;

            sentinel.next.next.pre = firstNode.next;
            sentinel.next = firstNode.next;
            firstNode.next = null;
            firstNode.pre = null;
            size -= 1;

            return firstNode.item;
        }
    }

    public Item removeLast() {
        Node lastNode;
        if (sentinel.next == sentinel) {
            return null;
        } else {
            lastNode = sentinel.pre;

            sentinel.pre.pre.next = lastNode.pre;
            sentinel.pre = lastNode.pre;
            lastNode.next = null;
            lastNode.pre = null;
            size -= 1;

            return lastNode.item;
        }
    }

    public Item get(int index) {
        Node currentNode = sentinel.next;

        while (index != 0) {
            if (currentNode.next == sentinel) return null;
            currentNode = currentNode.next;
            index -= 1;
        }
        return currentNode.item;
    }

    public Item getRecursive(int index, Node currentNode) {
        if (index == 0 && currentNode.next != sentinel) {
            return currentNode.item;
        } else if (currentNode.next == sentinel) {
            return null;
        } else {
            return getRecursive(index-1, currentNode.next);
        }
    }

    public Item getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }
}
