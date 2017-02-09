/**
 * Created by Anna on 2/8/17.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        ArrayDequeSolution<Character> deque = new ArrayDequeSolution<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public static boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        else if (word.charAt(word.length()-1) == word.charAt(0)) {
            return isPalindrome(word.substring(1, word.length()-2));
        }
        else {
            return false;
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        else if (cc.equalChars(word.charAt(word.length()-1), word.charAt(0))) {
            return isPalindrome(word.substring(1, word.length()-2));
        }
        else {
            return false;
        }
    }
}
