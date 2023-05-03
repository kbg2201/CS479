# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
### MakeFile:
~~~
all: 
    shellcode.S
    as shellcode.S -o shellcode.o
    ld shellcode.o -o shellcode --oformat=binary
    rm shellcode.o
~~~

### Buffer Code:
~~~

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define SHELLCODE_PATH "/home/aggie/Documents/shellcode"

int main(int argc, char** argv) {

    char exec_me[200];
    int bytes_read;
    FILE * shellcode_file;
    void* foo;

    shellcode_file = fopen(SHELLCODE_PATH, "rb");

    if (shellcode_file == NULL) {
        printf("Error opening file %s: %ld\n", SHELLCODE_PATH, (long) shellcode_file);
        return 2;
    }

    bytes_read = fread(exec_me, 1, 200, shellcode_file);

    printf("Shellcode length: %ld\n", ftell(shellcode_file));

    if (!bytes_read) {
        printf("Error: %d bytes read in shellcode file %s\n", bytes_read, SHELLCODE_PATH);
        return 1;
    }

    pclose(shellcode_file);

    foo = ((void*(*)()) exec_me)();

    return 0;
}
~~~

### Shell Code
- How do we ask from assembly to open a terminal?

I will need to use system calls to talk to the operating system then execute those system calls (execve(2)). According to the manual page execve() executes the program referred to by pathname. This causes the program that is currently being run by the calling process to be replaced with a new program, with newly initialized stack, heap, and (initialized and uninitialized) data segments. The parameter will need to be char ** PATHNAME - address - /bin/sh0, arg = address - null. It will be a char ptr to char *(args). Before sys call instruct we need to set ID of execve we want. What is the ID of execve? according to the the linux table the syscal is  59 = 0x3B.  The OS will find where it goes according too the stack overflow post. It should go to RAX.
The RAX = 0x3B, the rdi = binsh_address, rsi = null_address, rdx  = null_address. I need to do  syscall inst. Then the OS will jump in,$___(shell prompt). 

- To put 0x3B in RAX- mov  $0x3B, %RAX
- RSI null_address is pushed onto the stack by pushq $0
- The goal is for the rsi and rdx with a 0 by mov %rsp %rdx - %rsp %rsi
- rdi = /bin/sh0 = 2f 62 64 6E 2F 73 68 00 because of little endianness it will be pushq 00 68 73 2F 6E 69 62 2F
- call syscall.

~~~
.section .text
.globl _start

_start:
movq $0, %rdx
movq $0, %rsi
movq 0x0068732F6E69622F, %rax
pushq %rax
movq %rsp, %rdi
movq %rsi, %rsi
movq %rdx, %rdx
movq $0x3B, %rax
syscall

~~~

The way I hoped this would work is by assigning 0 rsi and rdx, then assigning the first argument as /bin/sh0 in hex and then using rdx and rsi as their own pointers to null to set them to null and then assign rax 0x3B for execve and the call should have executed it but unfortunately it did not operate as expected and did not open the terminal.

Debugging commands:
- run = r
- breakpoint: b *0x_____
- examine = x/i, x/i $rip
- one instruction = si
- all registers = info reg
- follow rdx = x/x $ rdx
- x/s $rdi
- quit = q

This is the number of errors I received that I got stuck on debugging.

![Screenshot from 2023-04-26 10-34-06](https://user-images.githubusercontent.com/111537927/234657179-56e3da45-3ad0-49ff-a45f-758ff2cfba51.png)

I fixed some then had problems with the string.
Probably due to the spaces and the hex value....
![Screenshot from 2023-04-26 11-08-30](https://user-images.githubusercontent.com/111537927/234665296-066cc54b-f738-4012-a389-12fc378b13e9.png)

It compiled.

Then I kept getting permission errors and once i used root I got errors that my shellcode file did not exist even though it did after some research I could not find what the source of my problem was. Next I ran the shellcode_tester file with gdb since it was the only one that seemed to work  but I wasnt confident if that was what I should do and SO I set breakpoints and went through the code but could not discern if there were any logical errors that were stopping the shellcode from running. I also didnt recognize  the commands for shellcode that I thought I should be looking at. According to my python script my shellcode is 180 byes...

![Screenshot from 2023-04-26 22-09-27](https://user-images.githubusercontent.com/111537927/234765281-707b86e2-92a1-4779-b009-bb3041b30785.png)
![Screenshot from 2023-04-26 22-10-20](https://user-images.githubusercontent.com/111537927/234765287-88c9f592-4e41-450f-916b-fed8a67dc8a7.png)

I did try looking for a call to nothing to observe where my commands on the stack are but I could not find it. Here is an image of my disassemble main.

![Screenshot from 2023-04-29 10-50-31](https://user-images.githubusercontent.com/111537927/235317653-1485ca83-3f95-4631-a4bb-b6765338de94.png)






