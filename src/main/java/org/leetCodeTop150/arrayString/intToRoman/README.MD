# 12. Integer to Roman

<span style="color: #FFFF00; font-weight: bold">Difficulty: Medium</span>

Seven different symbols represent Roman numerals with the following values:

| Symbol | Value |
|--------|-------|
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

Roman numerals are formed by appending the conversions of decimal place values from highest to lowest.
Converting a decimal place value into a Roman numeral has the following rules:

- If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
- If the value starts with 4 or 9, use the **subtractive form** representing one symbol subtracted from the following symbol. For example, 4 is 1 (`I`) less than 5 (`V`): `IV`, and 9 is 1 (`I`) less than 10 (`X`): `IX`. Only the following subtractive forms are used: `IV`, `IX`, `XL`, `XC`, `CD`, `CM`.
- Only powers of 10 (`I`, `X`, `C`, `M`) can be prepended. `V`, `L`, and `D` can never be prepended.

Given an integer, convert it to a Roman numeral.


## Example 1

Input: `num = 3749`  
Output: `"MMMDCCXLIX"`  
Explanation:
- 3000 = MMM  
- 700 = DCC  
- 40 = XL  
- 9 = IX  

Note: 49 is not 1 (I) less than 50 (L) because the conversion is based on decimal place value.


## Example 2

Input: `num = 58`  
Output: `"LVIII"`  
Explanation:
- 50 = L  
- 8 = VIII  


## Example 3

Input: `num = 1994`  
Output: `"MCMXCIV"`  
Explanation:
- 1000 = M  
- 900 = CM  
- 90 = XC  
- 4 = IV  


## Constraints

- `1 <= num <= 3999`


Original problem on LeetCode: https://leetcode.com/problems/integer-to-roman/

