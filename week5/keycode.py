import string
import random
# Generates a random character based on a supplied condition
def yomommy(cond, chars):
 res = []
 for c in chars:
  if cond(c):
   res.append(c)
 return random.choice(res)

# Generate the set of possible characters as a byte array
char_options = string.ascii_lowercase + string.ascii_uppercase + string.digits + '-'
valid_chars = bytearray()
valid_chars.extend(map(ord, char_options))

# Generate a random serial that is 19 characters long
serial = [random.choice(valid_chars) for i in range(19)]
# We know that positions 4, 9, and 14 must be a '-' char
# This is technically done in the cracker() stage, but we'll just do it here...
serial[4] = 0x2d
serial[9] = 0x2d
serial[14] = 0x2d

# Logic from the paper() stage
serial[8] = yomommy(lambda x: (x ^ serial[10]) <= 9, valid_chars)
serial[5] = yomommy(lambda x: (x ^ serial[13]) <= 9, valid_chars)
iVar1 = (serial[10] ^ serial[8]) + 0x30
iVar2 = (serial[13] ^ serial[5]) + 0x30
serial[3] = iVar1
serial[15] = iVar1
serial[0] = iVar2
serial[18] = iVar2

# Logic from the scissors() stage
serial[1] = yomommy(lambda x: x + serial[2] > 170, valid_chars)
serial[16] = yomommy(lambda x: x + serial[17] > 170 and serial[1] + serial[2] != x + serial[17], valid_chars)
print("".join([chr(c) for c in serial])) 