#include <stdio.h>
#include <limits.h>
#include "circular_buffer.h"
#include <stdbool.h>

static volatile int count = 0;


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
    

    if(count == 0 && !bufferPtr->full)
    {
      bufferPtr->empty = 1;
    }
    else if(count > 0 && !bufferPtr->full)
    {
      bufferPtr->empty = 0;
    }

  if(count >= bufferPtr->maxLength)
  {
    bufferPtr->full = 1;
  }
    
    
    
      if (bufferPtr->empty)
      {
        
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is Empty %d\n", bufferPtr->data[bufferPtr->tail]);
        bufferPtr->tail++;
        bufferPtr->tail = bufferPtr->tail % bufferPtr->maxLength;
        count ++;
        return value;
      }

      else if (!bufferPtr->empty && !bufferPtr->full)
      {
        
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is not full %d\n", bufferPtr->data[bufferPtr->tail]);
        bufferPtr->tail++;
        bufferPtr->tail = bufferPtr->tail % bufferPtr->maxLength;
        count ++;
        return value;
      }
      else if (bufferPtr->full)
      {
        
        bufferPtr->data[bufferPtr->tail] = value;
        printf("The new inserted value is FULL %d\n", bufferPtr->data[bufferPtr->tail]);
        bufferPtr->tail++;
        bufferPtr->tail = bufferPtr->tail % bufferPtr->maxLength;
        count++;
        return value;
      }
      
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
      count--;
      //bufferPtr->tail = bufferPtr->maxLength;
      return value;
    }
    
    

    return INT_MIN;
  }

  int removeHead(struct circularBuffer* bufferPtr)
  {
    
    if (count > 0 && !bufferPtr->full)
    {
      bufferPtr->empty = 0;
    }
    else if (count == 0 && !bufferPtr->full)
    {
      bufferPtr->empty = 1;
    }


    if (bufferPtr->empty)
    {
      return INT_MIN;
    }

    else
    {
      
      int value = bufferPtr->data[bufferPtr->head];
      bufferPtr->head ++;
      bufferPtr->head = bufferPtr->head % bufferPtr->maxLength;
      count--;
      return value;
    }
    
    return INT_MIN;
  }
  void printBuffer(struct circularBuffer* bufferPtr){
    int tempHead = bufferPtr->head;


    

      while (tempHead < bufferPtr->tail)
      {
        
        printf("The elements in the buffer is: %d\n", bufferPtr->data[tempHead]);
        tempHead++;
        
      }
      
      
      
      printf("The head is pointing at: %d\n",bufferPtr->head);
      printf("The tail is pointing at: %d\n",bufferPtr->tail);
      
    
    //tempHead = tempHead -1;
    /*printf("The value of the head is %d\n", bufferPtr->data[bufferPtr->head]);
    printf("the value of the tail is: %d\n", bufferPtr->data[bufferPtr->tail]);*/
  }
