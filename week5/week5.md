# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
---
#### System Setup
#### CrackMe #1 Summary

Here is the logic and control flow of this crackMe:

- Rock:
 This function requires that the key be 19 characters long and every character is greater that the value 0x2d. The if statement (If #1 = 0x2d, '-' < input[i] & '0'> input[i] )requires that the key must consist of numbers letters and the '-' character. Then returns to paper.
- Paper:
This function must pass the first if and fail the second. It has [8] ^[10] + 0x30 < 0x3a. the next if being [5]^[13]+ 0x30 < 0x3a (This would fail because = 0x30 and anything is not less than 0x30). The other if requires the calculations of [3] = [8]^[10] + 30 = [15] this must be true. this results in the 1st and 18th thing in the key are = B. Then it returns to scissors.
- Scissors:
- Cracker:
- Decraycray:

#### CrackMe #5 Summary