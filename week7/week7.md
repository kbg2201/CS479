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
![Screenshot2](https://user-images.githubusercontent.com/111537927/233541666-aa9415c0-d643-4284-b63e-f1ebe50273b0.png)
![ScreenShot3](https://user-images.githubusercontent.com/111537927/233541690-939b30ae-d00b-48f5-a384-8e75b57e68ad.png)



3. Identify the entry point of the DLL injection. Where is DllMain?

4. This malware does something every ______ seconds. How often, and where is the loop where that waiting happens?

5. What does the malware do every _______ seconds?

 
