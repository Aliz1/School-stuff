#include <stdio.h>
#include <stdlib.h>
#include "circular_buffer.h"
#include <esp32/rom/ets_sys.h>
#include "blackboxTests.h"
#include "whiteboxTests.h"


/*
 * Change the value of BUFFER_SIZE if you want to change the size of the buffer. 
 */ 
#define BUFFER_SIZE 4





  void app_main() {

    // Some code to help you get started
    struct circularBuffer buffer;
    int *buffer_data = (int*) malloc(BUFFER_SIZE * sizeof(int));
    initCircularBuffer(&buffer, buffer_data, BUFFER_SIZE);

    
    
    printf("\n\n\n");
    printf("For example, output the results of your tests here. ");
    printf("\n\n");   

    // BEGINNING OF WHITE BOX TESTING

    //White box test 1 Initialize buffer
    initCircularBufferTest();

    //White box test 2 Covered in the black box test 6, 7, 8 and 9

    //White box test 3.1 Add element shows one extra value because the tail = 1 now and has an unchanged random value
    //addElementBeginning();

    //White box test 3.2
    //addElementEmptyBuffer_notStart();

    //White box test 3.3
    //addElement_non_emptyBuffer();

    //White box test 3.4
    //addElementTo_Buffer_Size_minus1();

    //White box test 4.1 Remove value
    //removeValue_bufferOneElement();

    //White box test 4.2
    //removeValue_fullBuffer();

    //White box test 4.3
    //removeValue_emptyBuffer();

    //White box test 5.1 Remove head
    //removeHead_OneElement();

    //White box test 5.2
    //removeHead_twoElements();

    //White box test 5.3
    //removeHead_threeElements();
    
    //White box test 5.4
    //removeHead_emptyBuffer();

    printf("\nPerhaps with some delay...");

    printf("\n\n\n");
    
    ets_delay_us(2000000ul);
    //BEGGINING OF BLACK BOX TESTING

    //Blackbox Test 1:
    //AddRemoveElement();

    //Black box test 2:
    //AddDelTwoElements();

    //Black box test 3:
    buffer_size();

        /*addElement(&buffer,10);
        addElement(&buffer,20);
        addElement(&buffer,30);
        addElement(&buffer,40);
        printf("\n\n");
        printBuffer(&buffer); 
        removeHead(&buffer);
        printf("\nAfter removing first head \n\n");
        printBuffer(&buffer); 
        removeHead(&buffer);
        printf("\nAfter removing second head \n\n");
        printBuffer(&buffer);
        removeHead(&buffer);
        printf("\nAfter removing third head \n\n");
        printBuffer(&buffer);
        removeHead(&buffer);
        printf("\nAfter removing fourth head \n\n");
        printBuffer(&buffer);
        addElement(&buffer, 50);
        printBuffer(&buffer);*/

    //Black box test 4
    //buffer_size_plus1();

    //Black box test 5
    //test1_buffersize_plus1();

    //Black box test 6
    //containsInEmptyBuffer();

    //Black box test 7
    //containsNotEmptyBuffer();

    //Black box test 8
    //containsTwoElements();

    //Black box test 9
    //addBuffer_SizeElements();

    printf("\nbetween your printouts!");

    printf("\n\n\n");
    free(buffer_data);
}

