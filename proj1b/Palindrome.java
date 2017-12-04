public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /** Return True if word is Off by Comparator cc, else return False. */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);

        return isPalindromeRecursive(deque, cc);
    }

    /** Return True if word is palindrome, else return False. */
    public static boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);

        return isPalindromeRecursive(deque);
    }


    private static boolean isPalindromeRecursive(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() > 1) {
            if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return isPalindromeRecursive(deque, cc);
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindromeRecursive(Deque<Character> deque) {
        if (deque.size() > 1) {
            if (deque.removeFirst() == deque.removeLast()) {
                return isPalindromeRecursive(deque);
            } else {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "howAreYou";
        Deque<Character> deque = wordToDeque(s);
        deque.printDeque();
    }
}
