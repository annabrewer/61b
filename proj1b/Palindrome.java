/**
 * Created by Anna on 2/8/17.
 */
public class Palindrome {

    /*public static void main (String[] args) {
        OffByOne obo = new OffByOne();
        //System.out.println(isPalindrome("detrude", obo));
        //System.out.println(isPalindrome("toohottohoot"));
    }*/

    public static Deque<Character> wordToDeque(String word) {
        ArrayDequeSolution<Character> deque = new ArrayDequeSolution<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public static boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            //System.out.println(word);
            return true;
        } else if (word.charAt(word.length() - 1) == word.charAt(0)) {
            //System.out.println(word);
            return isPalindrome(word.substring(1, word.length() - 1));
        } else {
            //System.out.println(word);
            return false;
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            //System.out.println(word);
            return true;
        } else if (cc.equalChars(word.charAt(word.length() - 1), word.charAt(0))) {
            //System.out.println(word);
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        } else {
            //System.out.println(word);
            return false;
        }
    }
}
