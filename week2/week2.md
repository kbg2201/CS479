# Week 2 - Simple Static Analysis
This week we learned more on the static analysis of malware by practicing using tools to help us identify functions the malware uses that are threats to our computers and data.
## Lab 1 - 3

### 1. Executive Summary:
This malware is accessing memory and files and trying to connect with a network.
        
### 2. Indicators of Compromise:
The malware indicator was the strings that were produced and common functions used in malware.
### 3. Mitigations:
To stop the malware from accessing the network there could be restrictions to the network. I also believe encryption of the files would be best to stop the malware from accessing it.
### 4. Evidence:
I immediately discovered that the malware was suspicious from uploading it to virusTotal and it was flagged my multiple anitvirus detectors. Next I ran the program with strings and discovered the Kernel32.dll file which revealed that the malware was doing manipulation of memory, files or hardware. There are only a few random strings which are suspicious and lead me to believe that the malware is packed. Next I tried to identify where it was packed and FSG 1.0 appeared in PeID. After I tried to identify more function that the malware was suing with Dependency Walker and found the functions Ws2_32.dll but there were a bunch of errors that did not allow me to see further. 
![alt text](image.png)
I also tried to dig deeper into used functions with PE Viewer but I was unable to see any of the information and it did not work.

# Lab 1 - 4

### 1. Executive Summary:
        
### 2. Indicators of Compromise:
        
### 3. Mitigations:
        
### 4. Evidence: