import java.util.*;
import java.time.*;

public class BackgroundThread {

	Timer statusTimer;
	private final int updateInterval = 5000;
	Monkey monkey;

	private final Thread t = new Thread(() -> {
		statusTimer = new Timer();
		statusTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				update();
			}
		}, 10, updateInterval);
	});

	BackgroundThread(Monkey monkey) {
		this.monkey = monkey;
		t.start();
	}

	private void update() {
		Instant startTime = Main.getStartTime() != null ? Main.getStartTime() : Instant.now();
		Instant now = Instant.now();
		Duration timeElapsed = Duration.between(startTime, now);

		printStatus(monkey.getAttempts(), timeElapsed);
	}

	private void printStatus(long attempts, Duration duration) {
		System.out.println("\nCurrent number of attempts: " + attempts);

		Main.printTimeElapsed(duration);
	}

	public void shutdown() {
		statusTimer.cancel();
		statusTimer.purge();
		t.interrupt();
	}
}
