package phonebook;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchSystem {
	final MyTimer timer = new MyTimer();
	final String name;
	ArrayList<Entry> entries;
	List<String> targets;

	public SearchSystem(String name, ArrayList<Entry> entries, List<String> targets) {
		this.name = name;
		this.entries = entries;
		this.targets = targets;
	}

	public String getName() {
		return name;
	}

	abstract public Result search();
}
