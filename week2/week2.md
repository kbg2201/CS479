# Week 2 - Simple Static Analysis
This week we learned more on the static analysis of malware by practicing using tools to help us identify functions the malware uses that are threats to our computers and data.
## Lab 1 - 3

### 1. Executive Summary:
This malware is accessing memory and files and trying to connect with a network.
        
### 2. Indicators of Compromise:
The malware indicator was the strings that were produced and common function used in malware.
### 3. Mitigations:
To stop the malware from accessing the network there could be restrictions to the network. I also believe encryption of the files would be best to stop the malware from accessing it.
### 4. Evidence:
I immediately discovered that the malware was suspicious from uploading it to virusTotal and it was flagged my multiple anitvirus detectors. Next I ran the program with strings and discovered the Kernel32.dll file which revealed that the malware was doing manipulation of memory, files or hardware. There are only a few random strings which are suspicious and lead me to believe that the malware is packed. Next I tried to identify where it was packed and FSG 1.0 appeared in PeID. After I tried to identify more function that the malware was suing with Dependency Walker and found the functions Ws2_32.dll but there were a bunch of errors that did not allow me to see further. I also tried to dig deeper into used functions with PE Viewer but I was unable to see any of the information and it did not work.

# Lab 1 - 4

### 1. Executive Summary:
This malware was ofuscated to hide some of its function of reaching networks and messing with the system directory.
        
### 2. Indicators of Compromise:
The indicator of compromise was the functions involved given by strings.
        
### 3. Mitigations:
Mitigations for this would be require a key to access the directory or files within it so that way it is not being messed with. Or a firewall to prevent the malware from functioning.
    
### 4. Evidence:
When running this file in Virus Total it was immediately identified as a threat from multiple antivirus detectors. When I ran it with strings a lot of information came up. There were multiple functions including Kernel32.dll which was being used to read/write/move files according to the other strings and access the system directory. Advapi32.dll also came up to access the service manager and registry. The strings also showed .rdata and .rsrc and .data. Although there were a bit of strings available I believe the malware to be packed and still hiding some functionality. When I ran it through PeID I found it was packed using simple Microsoft visual C++. I may be wrong but I do believe that to mean the file was obfuscated. I then opened the file in dependency walker and found It was in fact opening threads and messing with the directory. Another function that wsnt in strings came up and that was Ws2_32.dll.