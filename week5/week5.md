# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
---
#### System Setup
#### CrackMe #1 Summary

Here is the logic and control flow of this crackMe:

- Rock:
 This function requires that the key be 19 characters long and every character is greater that the value 0x2d. The if statement (If #1: 0x2d, '-' < input[i] & '0'> input[i] )requires that the key must consist of numbers letters and the '-' character. Then returns to paper.
- Paper:
This function must pass the first if and fail the second. It has [8] ^[10] + 0x30 < 0x3a. the next if being [5]^[13]+ 0x30 < 0x3a (This would fail because = 0x30 and anything is not less than 0x30). The other if requires the calculations of [3] = [8]^[10] + 30 = [15] this must be true. Then it returns to scissors.
- Scissors:
In this function, thenfirst If goes over more arithmetic to pass defining that [2]+[1] >= 0xab and [17] + [16] >= 0xab. Both of these must fail to pass the function but it helps determine [2]+[1] = [17]+[16]. Then returns to Cracker.
- Cracker:
In this function, [2]+[1] != [17]+[16], [14]+[4]+[9] = 0x87. To determine the values I simply divided 0x87 by 3 and the result was 2d or '-' character. Then returns to DeCrayCray.
- Decraycray:Lead to the sink

 Here is the code that generated a successful key for the crackme in java:
 
import java.util.Random;


public class Main {

    // Generates a random character based condition
    public static char rand(Condition cond, String chars) {
        StringBuilder res = new StringBuilder();
        for (char c : chars.toCharArray()) {
            if (cond.test(c)) {
                res.append(c);
            }
        }
        return res.charAt(new Random().nextInt(res.length()));
    }

    // Functional for the condition 
    public interface Condition {
        boolean test(char c);
    }

    public static void main(String[] args) {

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";

        // Generate as a byte array
        byte[] validChars = chars.getBytes();

        // Generate a random key that is 19 characters long
        byte[] key = new byte[19];
        new Random().nextBytes(key);
        for (int i = 0; i < key.length; i++) {
            if (key[i] < 0) {
                key[i] = (byte) (-key[i]);
            }
            key[i] = validChars[key[i] % validChars.length];
        }

        // positions 4, 9, and 14 condition
        key[4] = 0x2d;
        key[9] = 0x2d;
        key[14] = 0x2d;

        // conditions of 8 and 5
        key[8] = (byte) rand(c -> ((byte) (c ^ key[10])) <= 9, chars);
        key[5] = (byte) rand(c -> ((byte) (c ^ key[13])) <= 9, chars);
        byte iVar1 = (byte) (key[10] ^ key[8] + 0x30);
        byte iVar2 = (byte) (key[13] ^ key[5] + 0x30);
        key[3] = iVar1;
        key[15] = iVar1;
        key[0] = iVar2;
        key[18] = iVar2;

        // conditions of 1 and 16
        key[1] = (byte) rand(c -> (c + key[2]) > 170, chars);
        key[16] = (byte) rand(c -> (c + key[17]) > 170 && key[1] + key[2] != c + key[17], chars);

        System.out.println(new String(key));
    }
}

This generated a correct key from one ide and not from another so I took it as working anyways.


#### CrackMe #4 Summary
From demonstration of this crackme we saw that the file woul delete itself so I had to change it to be immutable and would not delete.