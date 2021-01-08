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
   while (currValue!=NULL && prevValue !=NULL)
   {

     if (currValue== NULL && prevValue == NULL)
     {
       return 0;
     }
     //om currValue samt prevValue inte = NULL ska talen sorteras i länkade listan.
     else if (currValue!=NULL && prevValue!=NULL)
     {
       //Detta är starten för det som händer när vi endast har ett värde i listan. 
       if (list->first->next == NULL && list->first->previous == NULL)
       {
        //Ifall nya värdet är mindre än tillgängliga värdet skall det nya värdet placeras först i listan.
         if (newElement->data < currValue->data)
         {
           newElement->next = list->first;
           list->first->previous = newElement;
           list->first->next = NULL;
           newElement->previous = NULL;
           list->first = newElement;
           currValue = list->first;
         }

        //Ifall det nya värdet är större än tillgängliga värdet skall nya värdet placeras sist i listan. 
        else if (newElement->data > currValue->data)
         {
           newElement->previous = list->first;
           list->first->next = newElement;
           newElement->next = NULL;
         }
         
        
       }
       //Ser till att talen sorteras även när vi inte endast har ett tal i listan. 
       if (list->first->next != NULL || list->first->previous != NULL){

        /*ifall det nya värdet är större än förra värdet men mindre än tillgängliga värdet
         skall talet placeras mellan dessa värden*/ 
         if (newElement->data > prevValue->data && newElement->data < currValue->data)
         {

           prevValue->next = newElement;
           newElement->previous = prevValue;
					 newElement->next = currValue;
           currValue->previous = newElement;
           
         }
         /*ifall det nya värdet är mindre än första talet i listan skall den placeras först.*/
         if (newElement->data < list->first->data)
         {
           newElement->next = list->first;
           list->first->previous = newElement;
           newElement->previous = NULL;
           list->first = newElement;
           currValue = list->first;
         }
          /*Allokerar sista värdet */
						while(lastNode->next != NULL)
            {

						 lastNode = lastNode->next;

            /*Ifall vi nått det sista värdet och nya värdet är större än sista värdet skall
            det nya värdet placeras sist i listan. */
							if (lastNode->next == NULL && newElement->data > lastNode->data)
							{
								  lastNode->next = newElement;
                  newElement->previous = lastNode;
								  newElement->next = NULL;
							}
							  
						}
         
       }
       /*Skriver ut listan varje gång den får ett nytt tal*/

       printf("The list in order: %d\n", currValue->data); 
       prevValue = currValue;
       currValue = currValue->next;

       

       
     }

   }
  
      return value;
      
} 

// initierar listan
void initDoubleLinkedList(struct doubleLinkedList* list) {
  list->first = NULL;
  list->last = NULL;
}

//tar bort första värdet i listan.
int removeFirstElementDoubleLinkedList(struct doubleLinkedList* list){
  //Checkar ifall listan är tom.
  if (list->first == NULL)
  {
    return INT_MIN;
  }
  // om listan inte är tom
  else if (list->first !=NULL)
  {
    //ifall vi endast har ett tal i listan
    if (list->first->next == NULL)
    {
      free(list->first);
    }

    else{
      list->first->previous = NULL;
      list->first = list->first->next;
    }
  }

  return list->first->data;
} 


int removeLastElementDoubleLinkedList(struct doubleLinkedList* list) {

  dNode_t *lastNode, *secondLastNode;
  //Ifall listan är tom
	if (list->first == NULL)

	{
		return INT_MIN;
	}
  //Om listan inte är tom pekar våra pekare mot (*head)
	else
	{
		secondLastNode = list->first;
		lastNode  = list->first;
  }
  /*medan sista nodens pekare inte = NULL, går vi genom listan och 
  håller ett öga på förra värdet.*/
		while (lastNode->next !=NULL)
		{
			secondLastNode = lastNode;
			lastNode = lastNode->next;
		}
    //Om sista noden == (*head) nollställer vi (*head)
		if (lastNode == list->first)
		{
			list->first = NULL;
		}
    //Om sista noden !== (*head) nollställer vi före detta nod
		else
		{
			secondLastNode->next = NULL;
		}
    //Frigör minnet.
		free(lastNode);
		return secondLastNode->data;
  

}





