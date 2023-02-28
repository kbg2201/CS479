import java.util.Random;

public class SerialGenerator {

// Generates a random character based on a supplied condition
public static char conditionalRandom(Condition cond, String chars) {
StringBuilder res = new StringBuilder();
for (char c : chars.toCharArray()) {
if (cond.test(c)) {
res.append(c);
}
}
return res.charAt(new Random().nextInt(res.length()));
}

// Functional interface for the condition in conditionalRandom
public interface Condition {
boolean test(char c);
}

public static void main(String[] args) {

String charOptions = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";

// Generate the set of possible characters as a byte array
byte[] validChars = charOptions.getBytes();

// Generate a random serial that is 19 characters long
byte[] serial = new byte[19];
new Random().nextBytes(serial);
for (int i = 0; i < serial.length; i++) {
if (serial[i] < 0) {
serial[i] = (byte) (-serial[i]);
}
serial[i] = validChars[serial[i] % validChars.length];
}

// We know that positions 4, 9, and 14 must be a '-' char
serial[4] = 0x2d;
serial[9] = 0x2d;
serial[14] = 0x2d;

// Logic from the paper() stage
serial[8] = (byte) conditionalRandom(c -> ((byte) (c ^ serial[10])) <= 9, charOptions);
serial[5] = (byte) conditionalRandom(c -> ((byte) (c ^ serial[13])) <= 9, charOptions);
byte iVar1 = (byte) (serial[10] ^ serial[8] + 0x30);
byte iVar2 = (byte) (serial[13] ^ serial[5] + 0x30);
serial[3] = iVar1;
serial[15] = iVar1;
serial[0] = iVar2;
serial[18] = iVar2;

// Logic from the scissors() stage
serial[1] = (byte) conditionalRandom(c -> (c + serial[2]) > 170, charOptions);
serial[16] = (byte) conditionalRandom(c -> (c + serial[17]) > 170 && serial[1] + serial[2] != c + serial[17], charOptions);

System.out.println(new String(serial));
}
}
