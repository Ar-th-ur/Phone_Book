package phonebook;

public class Entry {
	private final String number;
	private final String name;

	public Entry(String line) {
		String[] tokens = line.split(" ", 2);
		this.number = tokens[0];
		this.name = tokens[1];
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
}
