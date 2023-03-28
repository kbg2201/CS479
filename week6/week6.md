# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
---
#### CrackMe #1 - ezcrackme1 Summary
Initially this file would not run and gave an error for any password that I inputted. To find the password I ran strings on the file and discovered the phrase "picklecucumberl337" right above the output of the error message: 
"Please insert the password:
First Password: 
Your password <%s> was incorrect. Time for some more RE...
You cracked me! Now make a keygen!"
Because this appeared obvious I input "picklecucumberl337" as the password and it passed successfully.

#### CrackMe #2 - ezcrackme2 Summary
When running this file with a random password the error 
"Please insert the password:
Your password <> was incorrect. Time for some more RE..." occured. 
Then I ran strings and found the phrase above the error that was "artificialtree" which was the successful key. 

#### CrackMe #3 - ezcrackme3 Summary

When I ran a random password I recieved the error message so once again I tried to run string and found:
"strawberry
kiwi
Please insert the password:
Password: 
You cracked me! 
Your password <%s> was incorrect. Time for some more RE...
Now make a keygen!"

I tried both strawberry and kiwi seperately and it was not successful. then when I used uftrace I discovered:

strcat("", "strawberry") = "strawberry";
strcat("strawberry", "kiwi") = "strawberrykiwi";

Then I tried the phrase strawberrykiwi and it was successful.

#### CrackMe #4 Summary
#####Valid keys:
#####Code:
package keygenerator;

/**
 *
 * @author kbgon
 */
import java.util.Random;

public class KeyGenerator {
  public static void main(String[] args) {
    String key = generateKey();
    System.out.println(key);
  }

  public static String generateKey() {
    Random rand = new Random();
    StringBuilder key = new StringBuilder();
    key.append('A');
    key.append('6');
    for (int i = 2; i < 3; i++) {
      key.append((char) (rand.nextInt(26) + 'A'));
    }
    key.append('2');
    for (int i = 4; i < 7; i++) {
      key.append((char) (rand.nextInt(26) + 'A'));
    }
    key.append('%');
    for (int i = 8; i < 15; i++) {
      key.append((char) (rand.nextInt(26) + 'A'));
    }
    key.append('*');
    return key.toString();
  }
}

#### CrackMe #5 Summary