import java.util.*;
import java.time.*;

public class Monkey {
	private static final String[] characters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," "};
	private long attempts = 0;



	public void findWord(String key) {
		String word;
		do {
			word = generateWord();
			attempts++;
		} while (!word.equals(key));
	}

	private String generateWord() {
		Random rand = new Random();
		StringBuilder word = new StringBuilder();
		int c = 0;

		while (c < 51) {
			String character = characters[rand.nextInt(characters.length)];
			if (character.equals(" ")) break;
			word.append(character);
			c++;
		}
		return word.toString();
	}

	public void resetAttempts() {
		attempts = 0;
	}

	public static int getNumberOfCharacters() {
		return characters.length;
	}

	public long getAttempts() {
		return attempts;
	}
}
