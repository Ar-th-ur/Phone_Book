package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {
	private final ArrayList<Entry> entries;
	private final List<String> targets;

	public PhoneBook(String entriesSrc, String targetsSrc) throws IOException {
		entries = convertToEntries(download(entriesSrc));
		targets = download(targetsSrc);
	}

	public Result hashTable() {
		SearchSystem hashTable = new HashTable(entries, targets);
		startMessage(hashTable.getName());
		return hashTable.search();
	}

	public Result binarySearch() {
		SearchSystem binarySearch = new BinarySearch(entries, targets);
		startMessage(binarySearch.getName());
		return binarySearch.search();
	}

	public Result jumpSearch(Result toCompare) {
		SearchSystem jumpSearch = new JumpSearch(entries, targets, toCompare);
		startMessage(jumpSearch.getName());
		return jumpSearch.search();
	}

	public Result linearSearch() {
		SearchSystem linearSearch = new LinearSearch(entries, targets);
		startMessage(linearSearch.getName());
		return linearSearch.search();
	}

	private void startMessage(String sortingAlgorithm) {
		System.out.printf("Start searching (%s)...%n", sortingAlgorithm);
	}

	private List<String> download(String src) throws IOException {
		return Files.readAllLines(Path.of(src));
	}

	private ArrayList<Entry> convertToEntries(List<String> entries) {
		return entries.stream().map(Entry::new).collect(Collectors.toCollection(ArrayList::new));
	}
}
