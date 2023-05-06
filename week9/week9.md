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

  Also to keep this automated and get the information I need about the fault I had to locate the core file. That was the big struggle for me. There was a lot of confusion initially of how the core file is used. Once I learned that, I found the core file. The core file was located in the location "core._home_aggie_Downloads_pizza.1000.5ee8c735-2685-4494-a494-151979063b51.7826.2462538". Unfortunately for me I could not use the usual command to find the core file so I had to directly give the core file to be observed. I also had to run the command "ulimit -c unlimited" to make the environment for the core files unlimited. This allowed for the core files to actually generate in the loction they were supposed to.

  To my knowledge... The core file is generated when the program fails due to a segmentation fault. The core file conatins information of what was happening at the moment that it failed. This could allow us to get information that we need to find a way to create a buffer overflow to change the returun address and control the program by sending the address of our own code.                 

 
