# Week 3 - Dynamic Analysis

## Lab 3 - 1    
### 1. Executive Summary:


        
### 2. Indicators of Compromise:

### 3. Mitigations:

### 4. Evidence:

The beginning of investigating this malware started with running it through virus total since its just a fast general way to gather first inpressions. Immediately it is recognized as malware and shows it was packed with PEninja also shows something is flagged with win32. to get mmore specific I ran the file with dependency walker. Dependency walker showed the kernel32 function known to manipulate memory, files, and hardware.Next, I ran it with strings and another function that appeared is ws2_32.dll used for connecting to a network. That makes sense because virus total also detected communication with an ip address. Strings also showed a website www.practicalmalwareanalysis.com and some folderpaths being accessed. I finally began testing the malware dynamically by starting wireshark and running the malware to observe what was occuring. I observed that the protocol ssdp appeared which i observed that the protocol ssdp appeared which i googled and it came up for discovering network services. I believe that to reaffirm the fact that the malware is attempting to contact another network. I googled and it came up for discovering network services. I believe that to reaffirm the fact that the malware is attempting to contact another network. When using regshot there were 10 changes although im not sure what the significance of that is.

# Lab 3 - 2
### 1. Executive Summary:

        
### 2. Indicators of Compromise:

        
### 3. Mitigations:

    
### 4. Evidence:

To start off with this analysis I ran it through virustotal and it was flagged to be malicious. It also showed it was packed using C++. In strings the functions: winnet.dll, advapi32.dll, ws2_32.dll, and kernel32. Next I used dependency walker to analyze the functions and found that Kernel32 was creating threads. Advapi was creating services and deleting services. Ws2_32 is accessing addresses. Winnet is showing a request to http and some website. I attempted to run the program but it wouldnt run because it is .dll and so I was unable to observe the effects it had using wireshark and regshot. 

# Lab 3 - 3

### 1. Executive Summary:

        
### 2. Indicators of Compromise:

        
### 3. Mitigations:

    
### 4. Evidence:
