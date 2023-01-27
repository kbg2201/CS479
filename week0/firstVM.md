# CS479/579 Reverse Engineering at NMSU
## Karina Gonzales
---
#### Summary
> This repo will hold my reports on reverse engineering malware samples from "Practical Malware Analysis".
#### System Setup
>1. My reverse engineering system was set up by first partitioning my hardrive for some space to install the Linux Ubuntu OS onto. Once the space was partitioned I created a bootable USB containing the Ubuntu iso using Rufus. After experimenting with the difference from the NTFS and Fat32 settings I was able to boot into the USB successfully. Next I downloaded Ubuntu, disabled secure boot, and downloaded VirtualBox to set up a safe environment for when we deal with malware. I initially had struggles with setting up the VM with Windows 10 but after allocating the correct amount of RAM I was finally able to downlaod Windows 10. Once Windows 10 was downloaded I disabled the network to set it up. The virtual machine is now ready to be tested on.

> 2. We are using isolation to safely handle the malware that we are going to be using in this class. This way if the malware takes over our virtual machine it will not carry over into our main operating system and instead will be isolated on a different OS.

> 3. Throughout the semester we will be adding tools to analyze the Malware on a VM.