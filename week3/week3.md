# Week 3 - Dynamic Analysis

## Lab 3 - 1    
### 1. Executive Summary:

In summary, this malware was trying to reach an outside network and go to the website www.practicalmalware.com
        
### 2. Indicators of Compromise:
The indicators of compromise were actually nothing because when I ran the malware nothing noticible occured but when I used strings to analyze the malware I unfolded all of its red flags like accessing the website.
### 3. Mitigations:
Turn off the network connection so it doesn't reach untrusted websites.
### 4. Evidence:

The beginning of investigating this malware started with running it through virus total since its just a fast general way to gather first inpressions. Immediately it is recognized as malware and shows it was packed with PEninja also shows something is flagged with win32. To get more specific I ran the file with dependency walker. Dependency walker showed the kernel32 function known to manipulate memory, files, and hardware. Next, I ran it with strings and another function that appeared is ws2_32.dll used for connecting to a network. That makes sense because virus total also detected communication with an ip address. Strings also showed a website: 
www.practicalmalwareanalysis.com, and some folderpaths being accessed. I finally began testing the malware dynamically by starting wireshark and running the malware to observe what was occuring. I observed that the protocol ssdp appeared which i observed that the protocol ssdp appeared which i googled and it came up for discovering network services. I believe that to reaffirm the fact that the malware is attempting to contact another network. I googled and it came up for discovering network services. I believe that to reaffirm the fact that the malware is attempting to contact another network. When using regshot there were 10 changes although im not sure what the significance of that is.

# Lab 3 - 2
### 1. Executive Summary:
In summary, this malware was creating threads and trying to contact a website.
### 2. Indicators of Compromise:
I was unable to run this malware because it was a .dll file but the indicatore that I saw was the operations of the functions such as accessing addresses and creating threads.

### 3. Mitigations:
To stop this malware from reaching untrusted websites you could use a firewall, or block the function.
### 4. Evidence:

To start off with this analysis I ran it through virustotal and it was flagged to be malicious. It also showed it was packed using C++. In strings the functions: winnet.dll, advapi32.dll, ws2_32.dll, and kernel32. Next I used dependency walker to analyze the functions and found that Kernel32 was creating threads. Advapi was creating services and deleting services. Ws2_32 is accessing addresses. Winnet is showing a request to http and some website. I attempted to run the program but it wouldnt run because it is .dll and so I was unable to observe the effects it had using wireshark and regshot. 

# Lab 3 - 3

### 1. Executive Summary:

This malware was found to be a keylogger that messes with input an output of the system.
        
### 2. Indicators of Compromise:
The indicator of compromise for this malware was that when I ran it the window appeared and disappeared right away. Additionally the operations 
        
### 3. Mitigations:

    
### 4. Evidence:
To first analyze this malware I ran it through virus total and it was caught by 61 detectors. It also showed that it was packed using c++. It also showed a keylogger name. Next strings found nothing helpful and just bunch of random characters that were screaming at me. When using dependency walker all that came up was the function kernel32.dll which showed as using different functions such as deviceIOcontrol, createthread, createpipe, create directory etc. Next I ran the malware along the process monitor and it showed operation which create files and is querying for system information and mapping files. When I ran wireshark nothing suspicious came up. when comparing the before and after with regshot there were 10 changes. one change being cryptography seed.