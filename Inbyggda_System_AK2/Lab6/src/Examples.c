#include "inttypes.h"
#include "stdio.h"
//This is used internally and hidden for others.

/*
 * Suppose you want to update the firmwareof your MCUand you need to include a new bootloader 
 * with it. Describe the steps taken by the bootloader(the one installed, not the new one)to upload 
 * the firmware (including the new bootloader) specifying what parts go into RAM and what parts go into ROM.
*/

/*
 * 1) Copy current loader into RAM
 * 2) Execute current loader from RAM
 * 3) copy new code into RAM
 * 4) if cheksum OK copy new code into ROM
 * 5) reset
*/


/*
 * •𝑓(𝑛)=2^𝑛+1   Omega                 
 * •𝑔(𝑛)=2𝑛^2𝑙𝑜𝑔2^𝑛 Omega
 * •ℎ(𝑛)=3𝑛 Big O                       
 * •𝑖(𝑛)=10𝑛^2 Tetha , omega , Big O 
 * 
 * 
*/
