package phonebook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch extends SearchSystem {

	public BinarySearch(ArrayList<Entry> entries, List<String> targets) {
		super("quick sort + binary search", entries, targets);
	}

	@Override
	public Result search() {
		// sort
		timer.start();
		quickSort(0, entries.size() - 1);
		Duration sortTime = timer.getPassedTime();

		// search
		timer.start();
		int founded = 0;
		for (String target : targets) {
			if (binarySearch(target) >= 0) {
				founded++;
			}
		}
		return new Result(targets.size(), founded, timer.getPassedTime(), sortTime, false);
	}

	private int binarySearch(String value) {
		int firstIndex = 0;
		int lastIndex = entries.size();

		while (firstIndex <= lastIndex) {
			int middle = (lastIndex + firstIndex) / 2;

			if (entries.get(middle).getName().equals(value)) {
				return middle;
			}
			if (entries.get(middle).getName().compareTo(value) > 0) {
				lastIndex = middle - 1;
			} else if (entries.get(middle).getName().compareTo(value) < 0) {
				firstIndex = middle + 1;
			}
		}

		return -1;
	}

	private void quickSort(int start, int end) {
		if (start >= end) {
			return;
		}
		String pivot = entries.get(end).getName();
		int i = start - 1;
		int j = start;
		while (j < end) {
			if (entries.get(j).getName().compareTo(pivot) < 0) {
				i++;
				Collections.swap(entries, i, j);
			}
			j++;
		}
		Collections.swap(entries, i + 1, end);

		quickSort(start, i);
		quickSort(i + 1, end);
	}
}
