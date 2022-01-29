package phonebook;

import java.time.Duration;

public class MyTimer {
	private long time;

	public void start() {
		time = System.currentTimeMillis();
	}

	public Duration getPassedTime() {
		return Duration.ofMillis(System.currentTimeMillis() - time);
	}
}
