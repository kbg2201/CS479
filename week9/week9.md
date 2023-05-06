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
