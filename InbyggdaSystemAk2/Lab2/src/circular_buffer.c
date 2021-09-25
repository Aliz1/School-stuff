#include <stdio.h>
#include <limits.h>
#include "circular_buffer.h"
#include <stdbool.h>

/*
*   Author: Ali Zreim
*   University: Malmö Universitet
*   Id: aj8295
*   Course id: DA267a
*/


static volatile int count = 0;
struct circularBuffer buffer;


  
  
    /*Initializes the buffer*/
  void initCircularBuffer(struct circularBuffer* bufferPtr, int* data, int maxLength)
  {
    
    bufferPtr->data = data;
    bufferPtr->head = 0;
    bufferPtr->tail = 0;
    bufferPtr->empty = 1;
    bufferPtr->maxLength = maxLength;
  }

  /*Checks if the buffer contains a certain value*/
  int contains(struct circularBuffer *bufferPtr, int value)
  {

    int tempTail = bufferPtr->tail;

    //Checks if the buffer is empty
    if (bufferPtr->empty)
    {
      return INT_MIN;
    }
      //If the buffer is not empty and buffer is not full
      for (int i = 0; i < bufferPtr->maxLength; i++)
      {
        if (bufferPtr->data[tempTail] != value && !bufferPtr->full)
        {
          tempTail-=1;
        } 

        //If the buffer is full
        else if (bufferPtr->full && bufferPtr->data[tempTail] != value)
        {
          tempTail+=1;
        }

        //If we found the value.
        else if (bufferPtr->data[tempTail] == value)
        {
          printf("The buffer contains the value: %d\n", value);
          return value;
        }
       
        
          
      }
      
    return INT_MIN;
    
  }

  int addElement(struct circularBuffer* bufferPtr, int value)
  {
      //If the buffer is empty
      if (bufferPtr->empty)
      {
        bufferPtr->tail = 0;
        bufferPtr->head = 0;
        count = 0;
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is: %d\n", bufferPtr->data[bufferPtr->tail]);
        bufferPtr->tail++;
        bufferPtr->tail = bufferPtr->tail % bufferPtr->maxLength;
        count ++;
        
      }

      //If not full nor empty
      else if (!bufferPtr->empty && !bufferPtr->full)
      {
        
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is: %d\n", bufferPtr->data[bufferPtr->tail]);
        bufferPtr->tail++;
        bufferPtr->tail = bufferPtr->tail % bufferPtr->maxLength;
        count ++;
       
      }

      //If full
      else if (bufferPtr->full)
      {
        if (bufferPtr->tail < bufferPtr->head)
        {
          bufferPtr->tail = bufferPtr->head;
        }
        
        bufferPtr->fullPlusOne = 1;
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is: %d\n", bufferPtr->data[bufferPtr->tail]);
        bufferPtr->tail++;
        bufferPtr->tail = bufferPtr->tail % bufferPtr->maxLength;
        
        
        
      }

      
      /*These checks if the buffer is empty / full*/
      if(count == 0 && !bufferPtr->full)
      {
        bufferPtr->empty = 1;
      }
     else if(count > 0 && !bufferPtr->full)
      {
        bufferPtr->empty = 0;
      }

     if(count >= bufferPtr->maxLength )
      {
        bufferPtr->full = 1;
      }
      
      return value;
   
  }

  int removeValue(struct circularBuffer* bufferPtr, int value)
  {
    
    


    int Temptail = bufferPtr->maxLength -1;

    if (bufferPtr->empty)
    {
      printf("There is no values to remove, the buffer is empty!\n");
      return INT_MIN;
    }

    if (count == 0)
    {
        bufferPtr->empty = 1;
        bufferPtr->full = 0;
        bufferPtr->fullPlusOne = 0;
    }

    //While currValue != desired value
    while (bufferPtr->data[Temptail] != value && Temptail != bufferPtr->head) 
    {
      Temptail--;
    }

    //When desired value is found.
    if (bufferPtr->data[Temptail] == value)
    {
      while(Temptail != bufferPtr->head){
      bufferPtr->data[Temptail] = bufferPtr->data[Temptail - 1];
      Temptail--;
      }
      bufferPtr->head = (bufferPtr->head +1) % bufferPtr->maxLength;

      
      count--;

      if (!buffer.empty)
      {
        return removeValue(&buffer, value);
      }
      
    }
    
    
    return value;
  }

  int removeHead(struct circularBuffer* bufferPtr)
  {
    if (bufferPtr->empty)
    {
      printf("Buffer is empty \n");
      return INT_MIN;
    }
    
    else
    {
      int value = bufferPtr->data[bufferPtr->head];

      if (bufferPtr->full)
      {
      
        
        bufferPtr->head = (bufferPtr->head + 1) % bufferPtr->maxLength;
        bufferPtr->tail = (bufferPtr->tail + 1) % bufferPtr->maxLength;
        count--;
      }
      else
      {
        bufferPtr->head = (bufferPtr->head + 1) % bufferPtr->maxLength;
        count--;
      }


      if (count == 0)
        {
          bufferPtr->empty = 1;
          bufferPtr->full = 0;
          bufferPtr->fullPlusOne = 0;
        }


      return value;
      
    }





  }
  int clearBuffer(struct circularBuffer *bufferPtr)
  {
    if (bufferPtr->empty)
    {
      printf("The buffer is already Empty!\n");
      return INT_MIN;
    }
    else
    {
      bufferPtr->empty = 1;
      bufferPtr->full = 0;
      bufferPtr->fullPlusOne = 0;
      printf("The buffer is now cleared.\n");
    }

    
    
    return INT_MIN;
  }
  void printBuffer(struct circularBuffer* bufferPtr){
    int tempHead = bufferPtr->head;
    //int zeroHead = 0;



        if (bufferPtr->empty)
        {
          printf("Buffer is empty!\n");
        }
        else{
          printf("The elements in the buffer is: ");
        if ((bufferPtr->head == bufferPtr->tail || bufferPtr->full)  && bufferPtr->fullPlusOne != 1)
        {
            for (int i = 0; i < bufferPtr->maxLength - bufferPtr->head; i++)
          {
            printf("(%d) ", bufferPtr->data[tempHead]);
            tempHead++;
          }
          printf("\n");
          
        }
        
        
        else if (bufferPtr->head < bufferPtr->tail && !bufferPtr->fullPlusOne)
        {
          for (int i = 0; i < bufferPtr->tail - bufferPtr->head; i++)
          {
            printf("(%d) ", bufferPtr->data[tempHead]);
            tempHead++;
          }
          
        }
        
        
        
        else
        {
          
          for (int i = 0; i < bufferPtr->maxLength - bufferPtr->head; i++)
          {
            printf("(%d) ", bufferPtr->data[tempHead]);
            tempHead = (tempHead +1 ) % bufferPtr->maxLength;
          }
          
        }
        }

      
      
      printf("\n");
      printf("The head is pointing at: %d\n",bufferPtr->head);
      printf("The tail is pointing at: %d\n",bufferPtr->tail);
      
    
    //tempHead = tempHead -1;
    /*printf("The value of the head is %d\n", bufferPtr->data[bufferPtr->head]);
    printf("the value of the tail is: %d\n", bufferPtr->data[bufferPtr->tail]);*/
  }