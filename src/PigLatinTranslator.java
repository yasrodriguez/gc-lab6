import java.util.Scanner;

/**
 * Prompt the user for some text, translate it to Pig Latin and display it on
 * the console. Pig latin rules: 1. If a word starts with a vowel, add "way" to
 * the end. 2. If a word starts with a consonant, move all consonants before the
 * first vowel to the end of the word, then add "ay" to the end.
 *
 * @author Yasmin
 *
 */
public class PigLatinTranslator {

	/**
	 * Prompts the user for text and calls a function to convert the text entered to
	 * PigLatin.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String prompt = "Translate another line? (y/n)";
		boolean keepGoing = true;
		String userText = "";
		String pigLatin = "";

		System.out.println("Welcome to the Pig Latin Translator!\n");

		while (keepGoing) {
			System.out.println("Enter a line to be translated:");
			userText = scan.nextLine().trim();
			if (userText.isEmpty()) {
				System.out.println("Sorry, you must enter some text.");
			} else {
				pigLatin = convertToPigLatin(userText);
				System.out.println(pigLatin);
			}

			keepGoing = wantToContinue(prompt, scan);
		}
		scan.close();
		System.out.println("Goodbye!");
	}

	/**
	 * Splits the text into words, then translates each word into Pig Latin.
	 * 
	 * @param text
	 *            text to be converted
	 * @return text translated to Pig Latin
	 */
	public static String convertToPigLatin(String text) {
		StringBuilder sb = new StringBuilder();
		String[] words = text.toLowerCase().split(" ");

		for (String word : words) {
			String firstLetter = word.substring(0, 1);

			if (isVowel(firstLetter)) {
				sb.append(convertVowelWord(word));
			} else {
				sb.append(convertConsonantWord(word));
			}
			sb.append(" ");
		}
		// Converts the first letter in the converted sentence to uppercase
		sb.replace(0, 1, sb.substring(0, 1).toUpperCase());

		return sb.toString();
	}

	/**
	 * Determines if a letter is a vowel.
	 * 
	 * @param letter
	 *            letter to check
	 * @return true if it's a vowel, false otherwise
	 */
	public static boolean isVowel(String letter) {
		return "aeiou".contains(letter);
	}

	/**
	 * Converts a word to Pig Latin using the rules for words that start with a
	 * vowel.
	 * 
	 * @param word
	 *            word to convert
	 * @return word converted to Pig Latin
	 */
	public static String convertVowelWord(String word) {
		return word + "way";
	}

	/**
	 * Converts a word to Pig Latin using the rules for words that start with a
	 * consonant.
	 * 
	 * @param word
	 *            word to be converted
	 * @return word converted to Pig Latin
	 */
	public static String convertConsonantWord(String word) {
		char[] letters = word.toCharArray();
		int indexOfFirstVowel = 0;
		boolean found = false;

		for (int i = 0; i < letters.length && !found; i++) {
			if (isVowel(String.valueOf(letters[i]))) {
				indexOfFirstVowel = i;
				found = true;
			}
		}

		String consonantsBeforeVowel = word.substring(0, indexOfFirstVowel);
		return word.substring(indexOfFirstVowel) + consonantsBeforeVowel + "ay";
	}

	/**
	 * Verifies if the user wants to continue.
	 * 
	 * @param prompt
	 *            the user prompt
	 * @param scan
	 *            the scanner to collect input from
	 * @return true if they enter y, false otherwise
	 */
	public static boolean wantToContinue(String prompt, Scanner scan) {
		String response = "";
		boolean valid = false;

		System.out.println(prompt);
		while (!valid) {
			response = scan.nextLine().toLowerCase();

			if (response.equals("y") || response.equals("n")) {
				valid = true;
			} else {
				System.out.println("You must enter y or n. Please try again.");
			}
		}
		return response.equals("y");
	}
}
