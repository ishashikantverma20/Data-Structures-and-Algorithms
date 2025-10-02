/**
 * Goal - Merge account if they share common email addresses. Return merged accounts sorted by email,
 *        with user's name first
 *
 * Example:
 *
 * accounts = [
 *   ["John","johnsmith@mail.com","john_newyork@mail.com"],
 *   ["John","johnsmith@mail.com","john00@mail.com"],
 *   ["Mary","mary@mail.com"],
 *   ["John","johnnybravo@mail.com"]
 * ]
 *
 *
 *
 * Core Idea:
 *  1. Treat each email as a node
 *  2. If 2 email are in same account, they are connected(Union).
 *  3. After all unions, connected emails are part of the same set.
 *  4. Group emails by their root parent, then prepend the owner name.
 *
 *
 *  for (List<String> a : acts) {
 *     for (int i = 1; i < a.size(); i++) {
 *         parents.put(a.get(i), a.get(i));
 *         owner.put(a.get(i), a.get(0));
 *     }
 * }
 *
 *  parents = {
 *   "johnsmith@mail.com" -> "johnsmith@mail.com",
 *   "john_newyork@mail.com" -> "john_newyork@mail.com",
 *   "john00@mail.com" -> "john00@mail.com",
 *   "mary@mail.com" -> "mary@mail.com",
 *   "johnnybravo@mail.com" -> "johnnybravo@mail.com"
 * }
 *
 * owner = {
 *   "johnsmith@mail.com" -> "John",
 *   "john_newyork@mail.com" -> "John",
 *   "john00@mail.com" -> "John",
 *   "mary@mail.com" -> "Mary",
 *   "johnnybravo@mail.com" -> "John"
 * }
 *
 * Step 2: Unions emails for same Account
 *
 * for (List<String> a : acts) {
 *     String p = find(a.get(1), parents); // parent of first email
 *     for (int i = 2; i < a.size(); i++) {
 *         parents.put(find(a.get(i), parents), p); // union
 *     }
 * }
 *
 * First Account
 *
 * ["John","johnsmith@mail.com","john_newyork@mail.com"]
 *
 * Union "john_newyork@mail.com" with "johnsmith@mail.com" -> set "john_newyork@mail.com" Parent to "johnsmith@mail.com"
 *
 * Second Account
 *
 * ["John","johnsmith@mail.com","john00@mail.com"]
 *
 * Union "john00@mail.com" with "johnsmith@mail.com" -> set "john00@mail.com" Parent to "johnsmith@mail.com"
 *
 * Now all 3 John emails are connected: "johnsmith@mail.com" is Parent of all.
 *
 * Third & Fourth Account
 *
 * only 1 email - no union required
 *
 * Step 3:  Group Emails by Parent root
 *
 * Map<String, TreeSet<String>> unions = new HashMap<>();
 *
 * for(List<String> a : acts) {
 *     String p = find(a.get(1), parents); // root parent
 *     if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
 *     for (int i = 1; i < a.size(); i++)
 *         unions.get(p).add(a.get(i));
 * }
 *
 * {
 *   "johnsmith@mail.com" -> [ "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com" ],
 *   "mary@mail.com" -> [ "mary@mail.com" ],
 *   "johnnybravo@mail.com" -> [ "johnnybravo@mail.com" ]
 * }
 *
 * Step 4: Format output
 *
 * List<List<String>> res = new ArrayList<>();
 * for (String p : unions.keySet()) {
 *     List<String> emails = new ArrayList(unions.get(p));
 *     emails.add(0, owner.get(p)); // add owner name
 *     res.add(emails);
 * }
 *
 * List<List<String>> res = new ArrayList<>();
 * for (String p : unions.keySet()) {
 *     List<String> emails = new ArrayList(unions.get(p));
 *     emails.add(0, owner.get(p)); // add owner name
 *     res.add(emails);
 * }
 *
 * Final result:
 *
 * [
 *   ["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],
 *   ["Mary", "mary@mail.com"],
 *   ["John", "johnnybravo@mail.com"]
 * ]
 *
 * TC - O(N*a(N)) where N - total no. of emails.
 * SC - O(N) for maps and set
 * **/
public class AccountMerge {
	class Solution {
		public List<List<String>> accountsMerge(List<List<String>> acts) {
			Map<String, String> owner = new HashMap<>();
			Map<String, String> parents = new HashMap<>();
			Map<String, TreeSet<String>> unions = new HashMap<>();


			for (List<String> a : acts) {
				for (int i = 1; i < a.size(); i++) {
					parents.put(a.get(i), a.get(i));
					owner.put(a.get(i), a.get(0));
				}
			}


			for (List<String> a : acts) {
				String p = find(a.get(1), parents);
				for (int i = 2; i < a.size(); i++)
					parents.put(find(a.get(i), parents), p);
			}


			for(List<String> a : acts) {
				String p = find(a.get(1), parents);
				if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
				for (int i = 1; i < a.size(); i++)
					unions.get(p).add(a.get(i));
			}

			// Result
			List<List<String>> res = new ArrayList<>();

			for (String p : unions.keySet()) {
				List<String> emails = new ArrayList(unions.get(p));
				emails.add(0, owner.get(p));
				res.add(emails);
			}
			return res;
		}

		// Find the root parent of a node(email)
		private String find(String s, Map<String, String> p) {
			return p.get(s) == s ? s : find(p.get(s), p);
		}
	}
}