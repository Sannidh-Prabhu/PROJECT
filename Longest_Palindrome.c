#include <string.h>

char* longestPalindrome(char* s) {
    int start = 0, maxLen = 1;
    int n = strlen(s);

    for (int i = 0; i < n; i++) {
        // Odd length palindrome
        int l = i, r = i;
        while (l >= 0 && r < n && s[l] == s[r]) {
            if (r - l + 1 > maxLen) {
                start = l;
                maxLen = r - l + 1;
            }
            l--;
            r++;
        }

        // Even length palindrome
        l = i;
        r = i + 1;
        while (l >= 0 && r < n && s[l] == s[r]) {
            if (r - l + 1 > maxLen) {
                start = l;
                maxLen = r - l + 1;
            }
            l--;
            r++;
        }
    }

    static char result[1001];
    strncpy(result, s + start, maxLen);
    result[maxLen] = '\0';
    return result;
}
