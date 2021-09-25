#include <stdio.h>
#include <limits.h>
#include "circular_buffer.h"


//added to the tail and removed at the head
//tail points to recently added element.
//head points to the oldest element.
  void initCircularBuffer(struct circularBuffer* bufferPtr, int* data, int maxLength)
  {
    bufferPtr->data = data;
    bufferPtr->head = 0;
    bufferPtr->tail = 0;
    bufferPtr->maxLength = maxLength;
  }
  int contains(struct circularBuffer *bufferPtr, int value)
  
  {
    if (bufferPtr->head == bufferPtr->tail)
    {
      return INT_MIN;
    }
    
      for (int i = 0; i < bufferPtr->maxLength; i++)
      {
        if (bufferPtr->data[bufferPtr->tail] != value)
        {
          bufferPtr->tail-=1;
        }
      
        else if (bufferPtr->data[bufferPtr->tail] == value)
        {
          printf("The buffer contains the value: %d\n", value);
          bufferPtr->tail = bufferPtr->maxLength - 1;
          return value;
        }
          
      }
      
     

    bufferPtr->tail = bufferPtr->maxLength - 1;
    return INT_MIN;
    
  }

  int addElement(struct circularBuffer* bufferPtr, int value)
  {
      if (bufferPtr->tail == bufferPtr->head)
      {
        
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is %d\n", bufferPtr->data[bufferPtr->head]);
        bufferPtr->tail++;
        return value;
      }
      else if (bufferPtr->tail != bufferPtr->head && bufferPtr->tail < bufferPtr->maxLength)
      {

        
         bufferPtr->data[bufferPtr->tail] = value;
         printf("The new inserted value is %d\n", bufferPtr->data[bufferPtr->tail]);
         bufferPtr->tail ++;
         
         
         return value;
      }    
       else if (bufferPtr->tail == bufferPtr->maxLength-1)
         {
           bufferPtr->tail++;
           bufferPtr->data[bufferPtr->tail] = value;
           printf("The new inserted value #3 is %d\n", bufferPtr->data[bufferPtr->tail]);
           return value;
         }  
         else
         {
           bufferPtr->data[bufferPtr->head] = value;
           printf("Reached the end of array replacing first value with: %d\n", bufferPtr->data[bufferPtr->head]);
           bufferPtr->tail= bufferPtr->maxLength;
           return value;
         }
         bufferPtr->tail = bufferPtr->maxLength;
    return INT_MIN;
   
  }

  int removeValue(struct circularBuffer* bufferPtr, int value)
  {
    int Temptail = bufferPtr->tail;

    while (bufferPtr->data[Temptail] != value && Temptail != bufferPtr->head) 
    {
      Temptail--;
    }

    //Resets the tail if the value is not found
    if (Temptail == bufferPtr->head && bufferPtr->data[Temptail] != value)
    {
      printf("The value you tried to remove is non existent.\n");
      //bufferPtr->tail = bufferPtr->maxLength;
      return INT_MIN;
    }
    if (bufferPtr->data[Temptail] == value)
    {
      while(Temptail != bufferPtr->head){
      bufferPtr->data[Temptail] = bufferPtr->data[Temptail - 1];
      Temptail--;
      }
      bufferPtr->head++;
      //bufferPtr->tail = bufferPtr->maxLength;
      return value;
    }
    
    

    return INT_MIN;
  }

  int removeHead(struct circularBuffer* bufferPtr)
  {
    if (bufferPtr->head == 0 && bufferPtr->tail == 0)
    {
      return INT_MIN;
    }

    else if(bufferPtr->head < bufferPtr->tail)
    {
      
      int value = bufferPtr->data[bufferPtr->head];
      bufferPtr->head ++;
      //bufferPtr->tail--;
      return value;
    }
    
    return INT_MIN;
  }
  void printBuffer(struct circularBuffer* bufferPtr){
    int tempHead = bufferPtr->head;
    while (tempHead <= bufferPtr->tail && tempHead < bufferPtr->maxLength)
    {
     
      if (bufferPtr->tail > bufferPtr->maxLength && bufferPtr->tail == tempHead)
      {
      
       tempHead = bufferPtr->tail +1;
       printf("The index of tempHead: %d\n", tempHead);
       printf("The index of tail: %d\n", bufferPtr->tail);
      }
     
     else if (tempHead == bufferPtr->tail)
      {
        
        printf("The values in the buffer is: %d\n", bufferPtr->data[tempHead]);
        printf("The index of tail: %d\n", bufferPtr->tail);
        tempHead = bufferPtr->tail +1;
        
      }

      else if (tempHead < bufferPtr->tail)
      {
        
        printf("The values in the buffer is: %d\n", bufferPtr->data[tempHead]);
        
         tempHead++;
         
      }
      
      
      
      

    }
    printf("The index of head: %d\n", bufferPtr->head);
    printf("The index of tail: %d\n", bufferPtr->tail);
    //tempHead = tempHead -1;
    /*printf("The value of the head is %d\n", bufferPtr->data[bufferPtr->head]);
    printf("the value of the tail is: %d\n", bufferPtr->data[bufferPtr->tail]);*/
  }
