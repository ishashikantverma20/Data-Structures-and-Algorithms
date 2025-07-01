import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		int i = 0, j = 0;
		int length = s.length();
		Set<Character> set = new HashSet();
		int longSubString = 0;


		// running loop to slide the window using i and j and checking if element present in set
		// then shrinking window size
		while (j < length) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j));
				longSubString = Math.max(longSubString, j - i + 1);
				j++;
			} else {
				set.remove(s.charAt(i));
				//shrinking window size
				i++;
			}
		}

		return longSubString;
	}
}
