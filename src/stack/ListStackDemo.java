package stack;

public class ListStackDemo {

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

}

class LinkedListStack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E pop() {
        return list.removeFirst();
    }

    public E peek() {
        return list.getFirst();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:top ");
        res.append(list);
        return res.toString();
    }

}

class LinkedList<E> {

    private Node head;
    private int size;

    public LinkedList() {
        head = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.");
        }

        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.getNext();
        }
        prev.setNext(new Node(e, prev.getNext()));
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed");
        }

        Node cur = head.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return (E) cur.getE();
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }


    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Update failer.");
        }

        Node cur = head.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        cur.setE(e);
    }

    public boolean contains(E e) {
        Node cur = head.getNext();
        while (cur != null) {
            if (cur.getE().equals(e)) {
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    //从链表中删除index位置的元素，返回删除的元素
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed");
        }

        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.getNext();
        }
        Node retNode = prev.getNext();
        prev.setNext(retNode.getNext());
        retNode.setNext(null);

        size--;

        return (E) retNode.getE();
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        Node prev = head;
        while (prev.getNext() != null) {
            if (prev.getNext().getE().equals(e)) {
                break;
            }
            prev = prev.getNext();
        }

        if (prev.getE() != null) {
            Node delnode = prev.getNext();
            prev.setNext(delnode.getNext());
            delnode.setNext(null);
            size--;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while (cur != null) {
            res.append(cur.getE() + "->");
            cur = cur.getNext();
        }
        res.append("null");
        return res.toString();
    }

}

class Node<E> {

    private E e;
    private Node next;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(E e, Node next) {
        this.e = e;
        this.next = next;
    }

    public Node(E e) {
        this(e, null);
    }

    public Node() {
        this(null, null);
    }

    public String toString() {
        return e.toString();
    }
}
