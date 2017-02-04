public class ArrayDeque<Item> {
    private int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    public void addFirst(Item x) { 
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        items[nextFirst] = x;
        //System.out.println(x);
        //System.out.println(nextFirst);
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        //System.out.println(size);
    }

    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        items[nextLast] = x;
        //System.out.println(x);
        //System.out.println(nextLast);
        nextLast = (nextLast + 1) % items.length;
        /*if (nextLast == items.length - 1) {
            nextLast = 0;
        }
        else {
            nextLast += 1;
        }*/
        
        //System.out.println(size);
    }

    private void resize(int newSize) {
        //System.out.println(size);
        //System.out.println(nextFirst);
        //System.out.println(nextLast);
        //System.out.println("resize");
        Item[] a = (Item[]) new Object[newSize]; 
        int index = nextLast;
        int counter = 0;
        while (counter < size) {
            a[counter] = items[index];
            counter += 1;
            index += 1;
            if (index == size) {
                index = 0;
            }
        }
        //System.arraycopy(items, nextFirst+1, a, 0, items.length-nextFirst);
        //System.arraycopy(items, 0, a, items.length-nextFirst, nextFirst-nextLast);
        nextLast = size;
        nextFirst = a.length - 1;
        items = a;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Item i : items) {
            System.out.print(i + " ");
        }
        System.out.println(nextFirst);
        System.out.println(nextLast);
        /*int intndex = nextLast;
        while (index != nextFirst) {
            index += 1;
            if (index == size) {
                index = 0;
            }
            System.out.print(items[index]);
        }*/
    }

    public Item removeFirst() {
        Item x = get(0);
        size -= 1;
        manageStorage();
        return x;
    }

    public Item removeLast() {
        Item x = get(size - 1);
        size -= 1;
        manageStorage();
        return x;
        //do not need to actually set last item to 0
    }

    public Item get(int index) {
        return items[(nextFirst + index + 1) % items.length];
    }

    private void manageStorage() {
        if (items.length >= 16 && (size / items.length) < 0.25) {
            resize(items.length / 2);
            /*Item[] a = (Item[]) new Object[size/2]; 
            int index = nextLast;
            int counter = 0;
            while (counter < size) {
                a[counter] = items[index];
                counter += 1;
                index += 1;
                if (index == size) {
                    index = 0;
                }
            }
            //System.arraycopy(items, nextFirst+1, a, 0, items.length-nextFirst);
            //System.arraycopy(items, 0, a, items.length-nextFirst, nextFirst-nextLast);
            nextLast = size;
            nextFirst = a.length - 1;
            items = a;
        }*/
        }
    }
}
