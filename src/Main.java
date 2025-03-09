import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
	private static Instant startTime;

	public static void main(String[] args) {
		String key = inputKey();

		Monkey monkey = new Monkey();
		BackgroundThread statusThread = new BackgroundThread(monkey);

		startTime = Instant.now();
		monkey.findWord(key);
		Instant finishTime = Instant.now();

		statusThread.shutdown();
		Duration duration = Duration.between(startTime, finishTime);

		printResults(key, monkey.getAttempts(), duration);
	}

	private static String inputKey() {
		Scanner input = new Scanner(System.in);
		String key;

		System.out.println("Type word to match: ");
		key = input.nextLine();
		input.close();

		System.out.println("\"" + key + "\" has " + key.length() + " characters.");
		System.out.println("The chances of randomly typing this word are 1 on " + Math.pow(Monkey.getNumberOfCharacters(), key.length() + 1));
		System.out.println("Let's go!");

		return key;
	}

	private static void printResults(String word, long attempts, Duration duration) {
		System.out.println("\nWord: " + word);

		printTimeElapsed(duration);

		System.out.println("Total attempts: " + attempts);
		if (duration.toSeconds() != 0) {
			System.out.println("Average attempts per second: " + (attempts / duration.toSeconds()));
		}
	}

	public static void printTimeElapsed(Duration duration) {
		String days = duration.toDays() > 0 ? (duration.toDays() + (duration.toDays() > 1 ? " days, " : " day, ")) : "";
		String hours = duration.toHours() % 24 > 0 ? (duration.toHours() % 24 + (duration.toHours() % 24 > 1 ? " hours, " : " hour, ")) : "";
		String minutes = duration.toMinutes() % 60 > 0 ? (duration.toMinutes() % 60 + (duration.toMinutes() % 60 > 1 ? " minutes, " : " minute, ")) : "";
		String seconds = duration.toSeconds() % 60 > 0 ? (duration.toSeconds() % 60 + (duration.toSeconds() % 60 > 1 ? " seconds, " : " second, ")) : "";
		String milliseconds = duration.toMillis() % 1000 > 0 ? (duration.toMillis() % 1000 + (duration.toMillis() % 1000 > 1 ? " milliseconds" : " millisecond")) : "";

		System.out.println("Time elapsed: " + days + " " + hours + " " + minutes + " " + seconds + " " + milliseconds);
	}

	public static Instant getStartTime() {
		return startTime;
	}
}
