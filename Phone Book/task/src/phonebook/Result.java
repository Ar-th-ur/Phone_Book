package phonebook;

import java.time.Duration;

public class Result {
	private final int founded;
	private final int total;
	private final Duration passedTime;
	private final Duration searchingTime;
	private final Duration sortingTime;
	private boolean isLongSorting;

	public Result(int total, int founded, Duration searchingTime, Duration sortingTime, boolean isLongSorting) {
		this.founded = founded;
		this.total = total;
		this.searchingTime = searchingTime;
		this.sortingTime = sortingTime;
		this.isLongSorting = isLongSorting;
		passedTime = searchingTime.plus(sortingTime);
	}

	public Result(int total, int founded, Duration searchingTime) {
		this.founded = founded;
		this.total = total;
		this.searchingTime = searchingTime;
		this.sortingTime = Duration.ofSeconds(0);
		passedTime = searchingTime;
	}

	public String getResultOfSearching() {
		return String.format("Searching time: %d min. %d sec. %d ms.", searchingTime.toMinutesPart(),
				searchingTime.toSecondsPart(), searchingTime.toMillisPart());
	}

	public String getResultOfSorting() {
		return String.format("Sorting time: %d min. %d sec. %d ms.%s", sortingTime.toMinutesPart(),
				sortingTime.toSecondsPart(), sortingTime.toMillisPart(),
				isLongSorting ? " - STOPPED, moved to linear search" : "");
	}

	public String getResultOfCreating() {
		return String.format("Creating time: %d min. %d sec. %d ms.", sortingTime.toMinutesPart(),
				sortingTime.toSecondsPart(), sortingTime.toMillisPart());
	}

	public String getTimeResult() {
		return String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.", founded, total,
				passedTime.toMinutesPart(), passedTime.toSecondsPart(), passedTime.toMillisPart());
	}

	public int getFounded() {
		return founded;
	}

	public Duration getSearchingTime() {
		return searchingTime;
	}

	public Duration getSortingTime() {
		return sortingTime;
	}
}
