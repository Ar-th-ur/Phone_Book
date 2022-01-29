package phonebook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashTable extends SearchSystem {
	private HashMap<String, String> hashMap;

	public HashTable(ArrayList<Entry> entries, List<String> targets) {
		super("hash table", entries, targets);
	}

	@Override
	public Result search() {
		timer.start();
		toHashTable(entries);
		Duration toHashTableTime = timer.getPassedTime();

		int founded = 0;
		timer.start();
		for (String target : targets) {
			if (hashMap.containsKey(target)) {
				founded++;
			}
		}

		return new Result(targets.size(), founded, timer.getPassedTime(), toHashTableTime, false);
	}

	private void toHashTable(ArrayList<Entry> entries) {
		hashMap = entries.stream()
				.collect(Collectors.toMap(Entry::getName, Entry::getNumber, (f, s) -> f, HashMap::new));
	}
}
