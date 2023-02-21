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
The result of `xor eax, eax` is the register eax being set to zero. In other word its clears the EAX register.
