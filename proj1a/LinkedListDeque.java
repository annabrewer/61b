public class LinkedListDeque<Item>{
	private int size;
	private StuffNode sentinel;

	public LinkedListDeque() {
		sentinel = new StuffNode (null, null, null);
		size = 0;
	}
	
	private class StuffNode {
		public Item item;
		public StuffNode next;
		public StuffNode prev;

		public StuffNode(StuffNode p, Item i, StuffNode n) {
			prev = p;
			item = i;
			next = n;
		}
	}

	public void addFirst(Item x) { 
		sentinel.next = new StuffNode(sentinel, x, sentinel.next);
		size += 1;
	}

	public void addLast(Item x) {
		sentinel.prev = new StuffNode(sentinel.prev, x, sentinel);
		size += 1;
		/*StuffNode p = sentinel;
		while (p.next != null) {
			p = p.next;
		}
		p.next = new StuffNode(x, null);*/
		//make it double ended!!
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		StuffNode p = sentinel;
		//idk why this works
		while (p.next != null) {
			System.out.println(p.item);
			p = p.next;
		}
	}

	public Item removeFirst() {
		Item temp = sentinel.next.item;
		sentinel.next = sentinel.next.next;
		size -= 1;
		return temp;
	}

	public Item removeLast() {
		Item temp = sentinel.prev.item;
		sentinel.prev = sentinel.prev.prev;
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
			p = p.next;
			index -= 1;
		}
		return p.item;
	}

	public Item getRecursive(int index) {
		StuffNode p = sentinel;
		return getRecursiveHelper(p, index);
	}

	public Item getRecursiveHelper (StuffNode p, int index) {
		if (index == 0) {
			return p.item;
		}
		else {
			return getRecursiveHelper(p.next, index-1);
		}
	}
}

