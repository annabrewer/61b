public class LinkedListDeque<Item> {
    private int size;
    private StuffNode sentinel;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        size = 0;
    }
    
    private class StuffNode {
        private Item item;
        private StuffNode next;
        private StuffNode prev;

        private StuffNode(StuffNode p, Item i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }

        public StuffNode getPrev() {
        	return prev;
        }

        public Item getItem() {
        	return item;
        }

        public StuffNode getNext() {
        	return next;
        }

        public void setPrev(StuffNode i) {
        	prev = i;
        }

        public void setItem(Item i) {
        	item = i;
        }

        public void setNext(StuffNode i) {
        	next = i;
        }
    }

    public void addFirst(Item x) { 
        sentinel.setNext(new StuffNode(sentinel, x, sentinel.next));
        size += 1;
    }

    public void addLast(Item x) {
        sentinel.setPrev(new StuffNode(sentinel.prev, x, sentinel));
        size += 1;
        /*StuffNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new StuffNode(x, null);*/
        //make it double ended!!
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentinel;
        //idk why this works
        while (p.getNext() != null) {
            System.out.println(p.item);
            p = p.getNext();
        }
    }

    public Item removeFirst() {
        Item temp = sentinel.getNext().getItem();
        sentinel.setNext(sentinel.getNext().getNext());
        size -= 1;
        return temp;
    }

    public Item removeLast() {
        Item temp = sentinel.getPrev().getItem();
        sentinel.setPrev(sentinel.getPrev().getPrev());
        size -= 1;
        return temp;
        /*StuffNode p = sentinel;
        int counter = 2;
        while (counter < size) {
            p = p.next;
            counter += 1;
        }
        StuffNode temp = p.next;
        p.next = sentinel;
        size -= 1;
        return temp.item;*/
    }

    public Item get(int index) {
        StuffNode p = sentinel;
        while (index > 0) {
            p = p.getNext();
            index -= 1;
        }
        return p.item;
    }

    public Item getRecursive(int index) {
        StuffNode p = sentinel;
        return getRecursiveHelper(p, index);
    }

    private Item getRecursiveHelper(StuffNode p, int index) {
        if (index == 0) {
            return p.getItem();
        }
        else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }
}
