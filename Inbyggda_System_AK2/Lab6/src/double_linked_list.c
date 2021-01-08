#include<stdlib.h>
#include <stdio.h>
#include<limits.h>
#include"double_linked_list.h"



/*Author: Ali Zreim
  Dator id: aj8295
  Programmering av inbyggda system*/
  int addElementDoubleLinkedList(struct doubleLinkedList* list, int value){
    //Instansvariabler för pekare samt allokering av minne för newElement pekaren.
      dNode_t *newElement = (dNode_t*)malloc(sizeof(dNode_t));
      dNode_t *currValue = list->first;
      dNode_t *prevValue = currValue;
      dNode_t *lastNode = list->first;

    //om minne inte allokerats för newElement.
  if(newElement == NULL)  
   {  
       printf("\nOVERFLOW");
       return INT_MIN;  
   }  

    //Om minne för newElement allokerats så ger vi newElement ett värde.
   else  
   {  
      newElement->data = value;
      printf("NEW ELEMENT DATA: %d\n\n", newElement->data);
      }

    /*Ifall list.first är tom ska den få värdet av newElement.
    Första värdet i listan blir värdet av newElement*/

   while(list->first==NULL)  
   {  
       newElement->next = NULL;  
       newElement->previous=NULL;   
       list->first=newElement; 
       currValue = list->first; 
       prevValue = currValue;
       lastNode = list->first;
       printf("First inserted value: %d\n", list->first->data);
   }

   //loopen kör tills currValue och prevValue blir = NULL

       
           newElement->previous = currValue;
           currValue->next = newElement;
           newElement->next = NULL;
           currValue =newElement;
           currValue->next = NULL;
           currValue->previous = currValue;
       /*Skriver ut listan varje gång den får ett nytt tal*/

       //printf("The list in order: %d\n", prevValue->data); 
       

       

       


  
      return value;
      
} 


// initierar listan
void initDoubleLinkedList(struct doubleLinkedList* list) {
  list->first = NULL;
  list->last = NULL;
}

void printList(struct doubleLinkedList* list)
{
  
  while (list->first!=NULL)
  {
    printf("The list in order: %d\n",list->first->data);
    list->first = list->first->next;
  }
  
  
}