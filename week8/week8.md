# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
### MakeFile:
~~~
all: shellcode.S
    as shellcode.S -o shellcode.o
    ld shellcode.o -o shellcode --oformat=binary
    rm shellcode.o
~~~

### Buffer Code:
~~~

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define SHELLCODE_PATH "/YOUR/PATH/HERE"

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
.text
.global_start
_start:
movq %rsp %rdx
pushq %rdx
movq %rsp %rsi
movq rsp %rdx
movq 00 68 73 2F 6E 69 62 2F
pushq %rax
movq %rsp %rdi
movq $0x3B %rax
sys call
~~~

Debugging command:
- run = r
- breakpoint: b *0x_____
- examine = x/i, x/i $rip
- one instruction = si
- all registers = info reg
- follow rdx = x/x $ rdx
- x/s $rdi
- quit = q
