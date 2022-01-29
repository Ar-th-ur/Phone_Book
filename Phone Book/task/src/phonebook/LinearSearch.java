package phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LinearSearch extends SearchSystem {

	public LinearSearch(ArrayList<Entry> entries, List<String> targets) {
		super("linear search", entries, targets);
	}

	@Override
	public Result search() {
		timer.start();

		int founded = 0;
		for (String target : targets) {
			for (Entry entry : entries) {
				if (Objects.equals(entry.getName(), target)) {
					founded++;
					break;
				}
			}
		}

		return new Result(targets.size(), founded, timer.getPassedTime());
	}
}
