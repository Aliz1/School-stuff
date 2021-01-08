#include <stdio.h>
#include <stdlib.h>
#include "circular_buffer.h"
#include <esp32/rom/ets_sys.h>

    struct circularBuffer buffer;       

    //BEGGINING OF WHITE BOX TESTING FUNCTIONS

    //White box test 1 Initialization
    void initCircularBufferTest()
    {
       int maxLength = buffer.maxLength;
       int head = buffer.head;
       int tail = buffer.tail;
       printf(" The value of maxLength = %d\n The value of buffer.head = %d\n the value of tail = %d\n", maxLength, head, tail );
    }
    
    //White box test 3 Add element.
    //White box test 3.1
    void addElementBeginning()
    {
        addElement(&buffer,12);
        printBuffer(&buffer);
    }
    
    //White box test 3.2
    void addElementEmptyBuffer_notStart()
    {
        buffer.tail = 1;
        addElement(&buffer, 14);
        printBuffer(&buffer);
    }
    
    //White box test 3.3
    void addElement_non_emptyBuffer()
    {
        //This makes sure the buffer is not empty
        addElement(&buffer, 12);

        //Adding element to a non empty buffer with a random tail value

        /*buffer.tail = 2;
        addElement(&buffer, 15);
        printBuffer(&buffer);*/

        //Adding element to non empty buffer with a random buffer.head & tail value

        buffer.head = 2; // could have values from 0 to 4 incrementing this value will delete previously added element.
        buffer.tail = 3;// could have values from 0 to 4
        addElement(&buffer, 16);
        printBuffer(&buffer);
        printf("Does not print the value 12 since it got removed when we changed head value to 2 which is empty.");

        //Adding element to non empty buffer when head > tail
        /*buffer.head = 3;
        buffer.tail = 2;
        addElement(&buffer, 20);
        printBuffer(&buffer);
        printf("Does not print buffer since head is not allowed to be bigger than tail");*/
    }
    
    //White box test 3.4
    void addElementTo_Buffer_Size_minus1()
    {
        //Make the buffer contain BUFFER_SIZE - 1 elements.
        addElement(&buffer,10);
        addElement(&buffer,20);
        addElement(&buffer,30);

        //Add Element with different head and tail values
        //test 1
        buffer.head = 0;

        //Test 2
        //buffer.head = 2;
        //buffer.tail = 3;
        
        //Test 3
        //buffer.head = 0;
        //buffer.tail = 4;
        addElement(&buffer,40);
        printBuffer(&buffer);
    }

    //White box test 4 Remove value
    //White box test 4.1
    void removeValue_bufferOneElement()
    {
        addElement(&buffer, 12);
        int removedVal = removeValue(&buffer,12);
        printf("Removing the value: %d..\n ", removedVal);
        printBuffer(&buffer);
    }

    //White box test 4.2
    void removeValue_fullBuffer()
    {
        //We can try this with different buffer.head & tail values
        buffer.head = 0; // from 0 up to 4.
        addElement(&buffer, 10);
        addElement(&buffer, 20);
        addElement(&buffer, 30);
        addElement(&buffer, 40);
        buffer.tail = 4; // from 0 up to 4.
        printf("The head is currently located at: %d\nThe tail is currently located at: %d\n", buffer.head, buffer.tail);
        int removedVal = removeValue(&buffer, 30);
        printf("Removing the value: %d..\n", removedVal);
        printBuffer(&buffer);
    }

    //White box test 4.3
    void removeValue_emptyBuffer()
    {
        int removedVal = removeValue(&buffer, 55);
        printf("\nRemoving the value: %d\nShows INT_MIN if buffer is empty/value not found\n", removedVal);
    }
    
    //White box test 5 Remove head
    //White box test 5.1
    void removeHead_OneElement()
    {
        addElement(&buffer, 20);
        int val = removeHead(&buffer);
        printf("removing the head with value: %d...\n", val);
        printBuffer(&buffer);
    }
    
    //White box test 5.2
    void removeHead_twoElements()
    {
        //We can have two scenarios here head = 0 and head = 1 (0 by default) at head = 1 we have already removed 1 head.
        //buffer.head = 1;
        addElement(&buffer,10);
        addElement(&buffer, 20);
        int val = removeHead(&buffer);
        printf("removing the head with value: %d...\n\n", val);
        printBuffer(&buffer);
    }
    
    //White box test 5.3
    void removeHead_threeElements()
    {
        //We can have three scenarios here head = 0 , 1 , 2 (0 by default)
        //buffer.head = 1; // change into 2 when needed.
        addElement(&buffer, 10);
        addElement(&buffer, 20);
        addElement(&buffer, 30);
        int val = removeHead(&buffer);
        printf("removing the head with value: %d...\n\n", val);
        printBuffer(&buffer);
    }

    void removeHead_emptyBuffer()
    {
      int val = removeHead(&buffer);
      printf("Removing the head with the value: %d... \nINT_MIN if empty\n ", val);
    }
    
