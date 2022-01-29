package phonebook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JumpSearch extends SearchSystem {
	private final int step;
	private final Duration toCompare;
	private boolean isLongSorting;

	public JumpSearch(ArrayList<Entry> entries, List<String> targets, Result toCompare) {
		super("bubble sort + jump search", entries, targets);
		this.toCompare = toCompare.getSearchingTime();
		step = (int) Math.floor(Math.sqrt(entries.size()));
		isLongSorting = false;
	}

	@Override
	public Result search() {
		// sorting
		Duration sortingTime = bubbleSort(entries);

		// if too long sorting
		if (isLongSorting) {
			Result result = new LinearSearch(entries, targets).search();
			return new Result(targets.size(), result.getFounded(), result.getSearchingTime(), sortingTime, true);
		}

		int founded = 0;
		// searching
		timer.start();
		for (String target : targets) {
			if (jump(target) >= 0) {
				founded++;
			}
		}
		return new Result(targets.size(), founded, timer.getPassedTime(), sortingTime, false);
	}

	private int jump(String value) {
		int curr = 0;
		while (curr < entries.size()) {
			if (entries.get(curr).getName().equals(value)) {
				return curr;
			} else if (value.compareTo(entries.get(curr).getName()) > 0) {
				int index = curr - 1;
				while (index > curr - step && index >= 0) {
					if (entries.get(index).getName().equals(value)) {
						return index;
					}
					index--;
				}
				return -1;
			}
			curr += step;
		}

		int index = entries.size() - 1;
		while (index > curr - step) {
			if (entries.get(index).getName().equals(value)) {
				return index;
			}
			index--;
		}
		return -1;
	}

	private Duration bubbleSort(ArrayList<Entry> entries) {
		timer.start();
		int max = entries.size() - 1;
		for (; max != 0; max--) {
			for (int i = 0; i < max; i++) {
				if (entries.get(i).getName().compareTo(entries.get(i + 1).getName()) < 0) {
					Collections.swap(entries, i, i + 1);
				}

				if (timer.getPassedTime().toSeconds() > toCompare.toSeconds() * 2) {
					isLongSorting = true;
					return timer.getPassedTime();
				}
			}
		}
		return timer.getPassedTime();
	}
}
