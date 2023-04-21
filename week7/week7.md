# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
---
#### DLL Injection

1. Prove that the loader is using DLL injection. (Don't forget a relevant snapshot in Ghidra.)

The Loader is using DLL Injection and we are able to tell because of analyzing the decompiled code in ghidra. While analyizing I found
with in one of the functions called toward the end of the main entry some suspicious libraries being used that indicate injection. the library calls include LoadLibraryA, GetProcAddress, OpenProcess, VirtualAllocEx, and CreateRemoteThread. The OpenProcess I believe could be used to target the desired process its injecting into. The createRemoteThread is certainly so it happens simultaneously with the process so it is undetected and disguised.

![ScreenShot1](https://user-images.githubusercontent.com/111537927/233527625-484c508b-a059-4c15-ad6e-12431e0d913b.png)



2. Identify the process that will be injected into. Seeing a string in Ghidra isn't sufficient -- explain how the process gets selected.

The process that will be injected into is explorer.exe. I assume this because it ia used in the ffunction call for the function that I labeled zero if like. This function is used to determine if it will accept the process. The process of deciding which process to inject to is involved with 2 different functions. The first function requires that the other function return 0 in order to return anf the other requires a 1. Here are the relevant functions that deal with selecting the process.

![ScreenShot3](https://user-images.githubusercontent.com/111537927/233546011-4f1bf314-44f0-4db2-8ef4-98e2f77e42a1.png)

![Screenshot2](https://user-images.githubusercontent.com/111537927/233546000-bbd88a99-2d84-42cb-a8f1-cba1e4b404ba.png)


3. Identify the entry point of the DLL injection. Where is DllMain?

The entry point of the DLL injection would be in the .dll file which is the function of malware. To find Dllmain I had to observe the .dll file. The .dll Main was named entry and had 3 parameters. 

![image](https://user-images.githubusercontent.com/111537927/233545901-b0073e51-886f-4208-b4c8-c039bfef6d41.png)

4. This malware does something every ______ seconds. How often, and where is the loop where that waiting happens?

This malware does something every 6000 seconds and it is from the lpStartAddress.

![screenshot5](https://user-images.githubusercontent.com/111537927/233546833-feabaac2-dee1-4738-b6b8-f18de84d5011.png)


5. What does the malware do every _______ seconds?

I believe that this malware creates the thread every 6000 seconds. Additionally there is a function call that is for the actual malware function. To determine what the function is actually doing I followed multiple path but I am unsure of which contains that actual malware function. because ivar is what is being returned I followed that path to a function that I named something else. my logic was the function didsomething which is what I name the original call and then did somethingelse the name of the seconday function. this is what that function contains. 


![Screenshot from 2023-04-20 22-31-29](https://user-images.githubusercontent.com/111537927/233548722-9ddb21e0-3c7c-4655-b1f3-66a6d4d555ca.png)

![Screenshot from 2023-04-20 22-31-55](https://user-images.githubusercontent.com/111537927/233548766-09392d0f-cec8-4370-8e2d-907ca7d8f603.png)

![Screenshot from 2023-04-20 22-32-24](https://user-images.githubusercontent.com/111537927/233548778-f68823d2-ebbf-4965-bdfa-9575d4b2f2b2.png)



 
