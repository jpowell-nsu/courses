package items;

import heap.KeyedItem;

// a job waiting to be done; the key is its priority
public class Task extends KeyedItem<Integer> {

	private String description;

	public Task(Integer priority, String description) {
		super(priority);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description + " (priority " + getKey() + ")";
	}

} // end Task
