package phonebook;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;

public class Main {

	public static void main(String[] args) throws IOException {
		PhoneBook phoneBook = new PhoneBook("D:\\Downloads fom web\\directory.txt", "D:\\Downloads fom web\\find.txt");

		Result linearSearch = phoneBook.linearSearch();
		System.out.println(linearSearch.getTimeResult());
		System.out.println();

		Result jumpSearch = phoneBook.jumpSearch(linearSearch);
		System.out.println(jumpSearch.getTimeResult());
		System.out.println(jumpSearch.getResultOfSorting());
		System.out.println(jumpSearch.getResultOfSearching());
		System.out.println();

		Result binarySearch = phoneBook.binarySearch();
		System.out.println(binarySearch.getTimeResult());
		System.out.println(binarySearch.getResultOfSorting());
		System.out.println(binarySearch.getResultOfSearching());
		System.out.println();

		Result hashTable = phoneBook.hashTable();
		System.out.println(hashTable.getTimeResult());
		System.out.println(hashTable.getResultOfCreating());
		System.out.println(hashTable.getResultOfSearching());
	}
}
