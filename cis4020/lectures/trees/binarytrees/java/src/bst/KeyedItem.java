package bst;

public abstract class KeyedItem<KT extends Comparable<? super KT>> {
  
	private KT key;

	public KeyedItem(KT key) {
		this.key = key;
	}

	public KT getKey() {
		return this.key;
	}
	
}