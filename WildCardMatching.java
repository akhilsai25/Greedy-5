// This solution uses a DP with memoization approach to iterate over the pattern and matching the given string
// For ? we always match any char, for * we have two branches to either match or ignore and skip as it is 0 or more
// For non equal chars, always return false and for equal chars always move forward
// With above rules we iterate over and see if we can reach end of the strings and return true
// Since there is good chance of visiting same i and j points in string and pattern respectively we can maintain memo array for memoization and reuse
class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        memo=new Boolean[s.length()][p.length()];
        return checkMatch(s, p, 0, 0);
    }

    private boolean checkMatch(String s, String p, int i, int j) {

        if(i==s.length()) {
            if(j==p.length()) return true;
            while(j<p.length() && p.charAt(j)=='*') {j++;}
            return j==p.length();
        }

        if(j==p.length()) {
            return false;
        }

        if(memo[i][j]!=null) return memo[i][j];

        boolean result = false;
        if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='?') {
            result = checkMatch(s, p, i+1, j+1);
        } else if(p.charAt(j)=='*') {
            result = checkMatch(s, p, i, j+1) || checkMatch(s, p, i+1, j);
        } 
        memo[i][j] = result;
        return result;
    }
}
