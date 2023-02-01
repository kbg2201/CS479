# Week 1 - Simple Static Analysis

During this week I had navigated through multiple tools used to identify properties of malware and dive deeper into how malware is disguised and used. I used the tools Strings, PEid virustotal, and resourcehacker. I learned about how malware can be packed or obfuscated to diguise itself and make it harder for malware analysts. I also learned about the common functions that malware uses.

## Lab 1 - 1

### 1. Executive Summary:
        Through examining the program using different tools, it is found that this program is malware. The malware is contacting some IP address. It is also manipulates files.
### 2. Indicators of Compromise:
        The indicator of malware was that it used kernel32.dll which is common for malware which is specifically used to manipulate files and memory according to the textbook.
### 3. Mitigations:
        I suppose that a firewall could block this malware easily.
### 4. Evidence:
        To find the Indicators and come to the conclusion that this is in fact malware I initially ran the program with strings. This showed some of the functions that were occuring such as the attempt to connect to a network. I also ran the program through PEid which displayed all of the malware fuctions being used and I was able to google the purpose of it all.

## Lab 1 - 2

### 1. Executive Summary:
        Through examining this program using different tools, it was found that it is malware. This malware also manipulates files and access the advanced core windows components like the registry.

### 2. Indicators of Compromise:
        The indicators for this program being malware was that it was packed using UPX. once unpack I found a link and the functions used that are common in malware such as kernel32.dll and advapi32.dll and winnet.dll
### 3. Mitigations:
        I believe that firewall would block this malware and fix the problem.
### 4. Evidence:
        To discover the components of this lab I mainly used strings and the textbook to find the purpose of the common malware functions.
