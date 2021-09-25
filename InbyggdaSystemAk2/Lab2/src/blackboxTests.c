#include "blackboxTests.h"
#include <stdio.h>
#include "circular_buffer.h"


/*
*   Author: Ali Zreim
*   University: Malmö Universitet
*   Id: aj8295
*   Course id: DA267a
*/

struct circularBuffer buffer;

void AddRemoveElement()
{
    addElement(&buffer,12);
    printf("________________________________________________\n");
    printBuffer(&buffer);
    printf("________________________________________________\n");
    removeHead(&buffer);
    printf("After removing the head\n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
}

void AddDelTwoElements()
{
    addElement(&buffer, 20);
    addElement(&buffer, 14);
    printf("________________________________________________\n");
    printBuffer(&buffer);
    printf("________________________________________________\n");

    removeHead(&buffer);
    printf("\nAfter removing the first head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    removeHead(&buffer);
    printf("\nAfter removing the second head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
}


void buffer_size()
{
    addElement(&buffer,10);
    addElement(&buffer,20);
    addElement(&buffer,30);
    addElement(&buffer,40);
    printf("________________________________________________\n");
    printBuffer(&buffer);
    printf("________________________________________________\n");
    removeHead(&buffer);
    printf("\nAfter removing first head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);

    removeHead(&buffer);
    printf("\nAfter removing second head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    removeHead(&buffer);
    printf("\nAfter removing third head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    removeHead(&buffer);
    printf("\nAfter removing fourth head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    
}

void buffer_size_plus1()
{
    addElement(&buffer,10);
    addElement(&buffer,20);
    addElement(&buffer,30);
    addElement(&buffer,40);
    addElement(&buffer,50);
    printf("________________________________________________\n");
    printBuffer(&buffer);
    printf("________________________________________________\n");
    removeHead(&buffer);
    printf("\nAfter removing first head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);

    removeHead(&buffer);
    printf("\nAfter removing second head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    removeHead(&buffer);
    printf("\nAfter removing third head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    removeHead(&buffer);
    printf("\nAfter removing fourth head \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
    addElement(&buffer, 10);
    printBuffer(&buffer);
    
}

/*void test1_buffersize_plus1()
{
    addElement(&buffer,12);
    addElement(&buffer,14);
    addElement(&buffer,16);
    addElement(&buffer,18);
    addElement(&buffer,120);
    printf("________________________________________________\n");
    printBuffer(&buffer);

    removeHead(&buffer);
    printf("\nAfter removing the first buffer \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);

    removeHead(&buffer);
    printf("\nAfter removing the second buffer \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);

    removeHead(&buffer);
    printf("\nAfter removing the third buffer \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);

    removeHead(&buffer);
    printf("\nAfter removing the fourth buffer \n");
    printf("________________________________________________\n");
    printBuffer(&buffer);
}*/

void containsInEmptyBuffer()
{
    int val = contains(&buffer, 20);
    printf("The value is: %d\n", val);
    printBuffer(&buffer);
}

void containsNotEmptyBuffer()
{
    addElement(&buffer, 15);
    printf("________________________________________________\n");
    contains(&buffer, 15);
    printf("________________________________________________\n");
    printBuffer(&buffer);
    printf("________________________________________________\n");
    clearBuffer(&buffer);
    printf("________________________________________________\n");
    printBuffer(&buffer);
}

void containsTwoElements()
{

    addElement(&buffer,14);
    addElement(&buffer,25);
    printf("________________________________________________\n");
    contains(&buffer,25);
    printf("________________________________________________\n");
    printBuffer(&buffer);
    printf("________________________________________________\n");
    clearBuffer(&buffer);
    printf("________________________________________________\n");
    printBuffer(&buffer);
}

void addBuffer_SizeElements()
{
    addElement(&buffer, 10);
    addElement(&buffer, 20);
    addElement(&buffer, 30);
    addElement(&buffer, 40);
    printf("________________________________________________\n");
    contains(&buffer, 40);
    printf("________________________________________________\n");
    clearBuffer(&buffer);
    printf("________________________________________________\n");
    printBuffer(&buffer);
}