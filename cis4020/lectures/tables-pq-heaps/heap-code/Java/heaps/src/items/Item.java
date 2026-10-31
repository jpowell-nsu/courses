package items;

import heap.KeyedItem;

// the simplest item a heap can hold: just an integer key
public class Item extends KeyedItem<Integer> {

	public Item(Integer key) {
		super(key);
	}

	@Override
	public String toString() {
		return String.valueOf(getKey());
	}

} // end Item
