package tests.graph;

@SuppressWarnings("unchecked")
class Heap<K extends Comparable<K>, T> {

	private class HeapItem implements Comparable<HeapItem> {

		private final K priority;
		private final T value;

		HeapItem(K priority, T value) {
			this.priority = priority;
			this.value = value;
		}

		public int compareTo(HeapItem o) {
			return priority.compareTo(o.priority);
		}

		boolean has(T value) {
			return this.value.equals(value);
		}

		public String toString() {
			return "(" + priority + ") -> " + value;
		}

	}

	private int ORDER;
	private Object[] data = new Object[200];
	private int size = 0;

	Heap(int order) {
		this.ORDER = order;
	}

	public boolean empty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	/** Complexity: time O(1) */
	public T peek() {
		return ((HeapItem) data[0]).value;
	}

	/** Complexity: time O(log n) */
	public void insert(K priority, T value) {
		data[size] = new HeapItem(priority, value);
		size++;
		heapifyUp(size - 1);
	}

	/** Complexity: time O(log n) */
	public T extract() {
		T result = ((HeapItem) data[0]).value;
		size--;
		data[0] = data[size];
		heapifyDown(0);
		return result;
	}

	private int indexOf(T value) {
		for (int i = 0; i < size; i++) {
			if (((HeapItem) data[i]).has(value)) {
				return i;
			}
		}
		return -1;
	}

	public K priority(T value) {
		int index = indexOf(value);
		if (index >= 0) {
			return ((HeapItem) data[index]).priority;
		} else {
			return null;
		}
	}

	public K remove(T value) {
		int index = indexOf(value);
		if (index >= 0) {
			K result = ((HeapItem) data[index]).priority;
			size--;
			data[index] = data[size];
			heapifyUp(index);
			heapifyDown(index);
			return result;
		} else {
			return null;
		}
	}

	private void heapifyUp(int index) {
		while (hasParent(index) && parent(index).compareTo(((HeapItem) data[index])) * ORDER > 0) {
			HeapItem tmp = ((HeapItem) data[index]);
			data[index] = parent(index);
			data[getParentIndex(index)] = tmp;
			index = getParentIndex(index);
		}
	}

	private void heapifyDown(int index) {
		while (hasLeftChild(index)) {
			int smallestChildIndex = getLeftChildIndex(index);
			if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) * ORDER < 0) {
				smallestChildIndex++;
			}
			if (((HeapItem) data[smallestChildIndex]).compareTo(((HeapItem) data[index])) * ORDER < 0) {
				HeapItem tmp = ((HeapItem) data[index]);
				data[index] = data[smallestChildIndex];
				data[smallestChildIndex] = tmp;
				index = smallestChildIndex;
			} else {
				break;
			}
		}
	}

	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	private HeapItem leftChild(int index) {
		return ((HeapItem) data[getLeftChildIndex(index)]);
	}

	private HeapItem rightChild(int index) {
		return ((HeapItem) data[getRightChildIndex(index)]);
	}

	private HeapItem parent(int index) {
		return ((HeapItem) data[getParentIndex(index)]);
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < size; i++) {
			res.append((T) data[i]);
			res.append('\n');
		}
		return res.toString();
	}

}