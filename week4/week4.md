# Week 4 Chapter Report

## Questions and Answers:

- What is the difference between machine code and assembly?
The difference between machine code and assembly, is that machine code is specific to the microcode firmware (1's and 0's). As opposed to assembly which is arguably human readable so that way we can communicate with the machine code.

- If the ESP register is pointing to memory address 0x001270A4 and I execute a `push eax` instruction, what address will ESP now be pointing to?
ESP should be pointing at 0x001270A0.

- What is a stack frame?
The last in first out structure supported by x86 which has a top down format, it is used for short term storage only. The stack frame is  used for the management of data exchange between function calls.

- What would you find in a data section?
Static values and global values.

- What is the heap used for?
The heap is used for dynamic memory during program execution to create or eliminate values that the program no longer needs.

- What is in the code section of a program's virtual memory space?
Includes instructions fetched by the cpu to execute the programs tasks.

- What does the `inc` instruction do, and how many operands does it take?
Inc, Increments. 

- If I perform a `div` instruction, where would I find the remainder of the binary division (modulo)?
The remainder is stored in EDX.

- How does `jz` decide whether to jump or not?
It jumps if the Zero Flag is equal to 1.

- How does `jne` decide whether to jump or not?
jne will jump if the destination is not equal to the source.

- What does a `mov` instruction do?
It moves from one location to another.

- What does the `TF` flag do and why is it useful for debugging?
The tap flag is used for debugging, the x86 processor will execute only one instruction at a time if the flag is set.

- Why would an attacker want to control the EIP register inside a program they want to take control of?
EIP is the instruction pointer, you can control what is executed by the cpu.

- What is the AL register and how does it relate to EAX?
AL is one of three ways of refrencing to EAX which is one byte.

- What is the result of the instruction `xor eax, eax` and where is it stored?
The result of `xor eax, eax` is the register eax being set to zero. It clears the EAX register.

## Ghidra crackme

Ghidra is software from the NSA that we began to use this week. To use Ghidra, I had to first install OpenJDK17 onto my linux machine in order for Ghidra to work and for the crack me that we were given to be tested. Initially I transferred the Ghidra file and Crackme file to my Linux VM through the shared folder. Just getting that working took a while because I was experiencing issues with my guest additions in order to get the shared folder functioning so I ultimately decided to just copy the files over by being the root user. After transferring the files over I had to unzip them and also chnage the permission on the files to allow myself to access them. Additionaly, I needed openJDK17 to run the program. I encountered some issues with downloading the openJDK17 due to some certificate errors and was able to fix that using the commands 'sudo update-ca-certificates' and 'sudo apt-get updates'. Finally I was able to open and run Ghidra. Once I got Ghidra going I opened the crack me file with it and then was analyzing the main code that was being executed. I observed that it was calling a function method to check the key. I next clicked on that method and was able to see the code for that as well. The code included an if statement which specified that the input modulus 1223 must equal zero to be correct and since anything modulus itself is zero I was able to conclude that the password is 1223.