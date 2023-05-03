# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales

### Disclaimer: I have modeled this after the link that was given in the posted assignmnet. 

### The Code:

This is the code that I gathered from Ghidra that was used in the Pizza program. The code basically replicates a pizza transaction with a costumer. I believe that the use of printf allows us to use %p to expose its vulnerability.


![Screenshot from 2023-05-02 20-02-19](https://user-images.githubusercontent.com/111537927/235828898-fe196cce-f0b6-41b5-adae-cae4c8069295.png)

Here is how I reached the segmentation fault. 


![Screenshot from 2023-05-02 20-57-52](https://user-images.githubusercontent.com/111537927/235830421-3dba45e7-bb27-4cbf-823f-e57489a42732.png)
