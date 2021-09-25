#include <stdio.h>
#include <stdlib.h>
#include "circular_buffer.h"
#include <esp32/rom/ets_sys.h>
#include "whiteboxTests.h"
#include "blackboxTests.h"


/*
*   Author: Ali Zreim
*   University: Malmö Universitet
*   Id: aj8295
*   Course id: DA267a
*/

/*
 * Change the value of BUFFER_SIZE if you want to change the size of the buffer. 
 */ 
#define BUFFER_SIZE 4
struct circularBuffer buffer;




  void app_main() {

    // Some code to help you get started
    
    int *buffer_data = (int*) malloc(BUFFER_SIZE * sizeof(int));
   initCircularBuffer(&buffer, buffer_data, BUFFER_SIZE);

    
    
    printf("\n\n\n");
    printf("For example, output the results of your tests here. ");
    printf("\n\n");   

    // BEGINNING OF WHITE BOX TESTING

    //White box test 1 Initialize buffer
    //initCircularBufferTest();

    //White box test 2 Covered in the black box test 6, 7, 8 and 9

    //White box test 3.1 
    //addElementBeginning();

    //White box test 3.2
    //addElementEmptyBuffer_notStart();

    //White box test 3.3
   // addElement_non_emptyBuffer();

    //White box test 3.4
    //addElementTo_Buffer_Size_minus1();

    //White box test 4.1 Remove value
    //removeValue_bufferOneElement();

    //White box test 4.2
   // removeValue_fullBuffer();

    //White box test 4.3
    //removeValue_emptyBuffer();

    //White box test 5.1 Remove head
    //removeHead_OneElement();

    //White box test 5.2
    //removeHead_twoElements();

    //White box test 5.3
    //removeHead_threeElements();
    
    //White box test 5.4
   // removeHead_emptyBuffer();

    printf("\nPerhaps with some delay...");

    printf("\n\n\n");
    
    ets_delay_us(2000000ul);
    //BEGGINING OF BLACK BOX TESTING

    //Blackbox Test 1:
    printf("________________________________________________\n");
    printf("Black Box Test 1\n");
    AddRemoveElement();
    printf("________________________________________________\n");
   // ets_delay_us(2000000ul);

    //Black box test 2:

    printf("________________________________________________\n");
    printf("Black Box Test 2\n");
    AddDelTwoElements();
    printf("________________________________________________\n");
    //ets_delay_us(2000000ul);

    //Black box test 3:
    printf("________________________________________________\n");
    printf("Black Box Test 3\n");
    buffer_size();
    printf("________________________________________________\n");
    //ets_delay_us(2000000ul);
 
    //Black box test 4
    printf("________________________________________________\n");
    printf("Black Box Test 4\n");
    buffer_size_plus1();
    printf("________________________________________________\n");
   // ets_delay_us(2000000ul);

    //Black box test 5
    printf("________________________________________________\n");
    printf("Black Box Test 5\n Tested in Black Box test 4\n");
    /*test1_buffersize_plus1(); Tested in blackbox test 4*/

       

    //Black box test 6
    printf("________________________________________________\n");
    printf("Black Box Test 6\n");
    containsInEmptyBuffer();
    printf("________________________________________________\n");
    //ets_delay_us(2000000ul);


    //Black box test 7
    printf("________________________________________________\n");
    printf("Black Box Test 7\n");

    containsNotEmptyBuffer();
    printf("________________________________________________\n");
   // ets_delay_us(2000000ul);


    //Black box test 8
    printf("________________________________________________\n");
    printf("Black Box Test 8\n");
    containsTwoElements();
    printf("________________________________________________\n"); 
  //  ets_delay_us(2000000ul);
        

    //Black box test 9
    printf("________________________________________________\n");
    printf("Black Box Test 9\n");
    addBuffer_SizeElements();
    printf("________________________________________________\n");

    ets_delay_us(2000000ul);

    printf("\nbetween your printouts!");

    printf("\n\n\n");
    free(buffer_data);
}