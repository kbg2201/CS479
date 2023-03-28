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
#####Analysis:

When first running the file I was told that the pasword was "uncool" as the first error so then I spammed a bunch of more random characters for a really long password and got the error "Error: Password lacked motivation.". Since the guessing method did not work I tried string since it was successful before. After running it through strings there was nothing that could have been a password. The few words that stuck out such as "lizard,paper,rock,scissor,cupcake!!!" were not the passsword. However since I recognized the paper rock scissors as functions to pass requirements I knew I would need to analyze the code so I opened it in ghidra. When analyzing in ghidra I first started in the main function. The only way to exit the main function was through rock, next was paper, then scissors, and lizard,spock, and finally win had the successful message. Next I began tracing back from the sink in win. In order to get to win I needed to pass spock. Spock required that the password have the 16th character as '*'. To get to spock I had to pass lizard and lizard required the 2nd character in the password to be '2'. Following that was meeting the conditions of scissor. Scissors required that the pointer to the 1st characer of the password be less than  T and R which was the equivalent of password < 'R'. Additionally the first character must not equal 'I' and it will be equal to the character 'A' to pass.
After scissors I analyzed paper since thats what gave access to scissors. In paper, the 8th character had to be was calculated to be the sum of 0x25 and 0x2e which resulted in the character 'S'. To get to the lizard function there was also a requirement that Uvar != 0 meaning that the result of the 8th character & 1 must be greater than 0. Too accomplish this, the 8th character - 0x25 must simply equal 0. This brought the conclusion that the 8th character = 0x25(%). To get to paper I analyzed rock and found that the 4th character needed to be '2'. Finally in main the password was required to be 16 characters or longer.

#####Code process:

To code the key generator I first wrote all the requirements that I found. 

Requirements:

- password >= 16
- 4th char = '2'
- 8th char = '%'
- 1st char = 'A'
- 2nd char = '2'
- 16th char = '*'

After that I began to code in java since it is my most practiced language. I first started by creating a generatekey function to use and imported random class to generate random values. Then created a charatcer array that I could add the values too. First I added the two starting and required values 'A' and '6'. Then generated random value for the 3rd charcter and assigned the 4th to '%'. next generated more random values until the 8th charcter and assigned that to be '%'. Again genereated random values for characters until the 16th character and assigned that '*'. Although the password could be longer I stuck with the password being equal to 16 characters for simplicity.


#####Valid keys:
- A6X2PFD%BGHWRKB*
- A6O2DZW%FMQRNSZ*
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
    return key.toString();

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