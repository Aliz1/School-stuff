#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include "single_linked_list.h"

/*Author: Ali Zreim
  Dator id: aj8295
  Programmering av inbyggda system*/

	int addElementSingleLinkedList(list_t * list, int value) {
		//Instansvariabler för pekare samt allokering av minne för newElement pekaren.
		node_t * newElement = (node_t * ) malloc(sizeof(node_t));
		node_t *currValue = list->first;
		node_t *prevValue = currValue;
		node_t *lastNode = list->first;
	
	
		


	//om minne inte allokerats för newElement.	
	while (newElement == NULL) {
		printf("No memory allocated.");
		return INT_MIN;
	}
	  //Om minne för newElement allokerats så ger vi newElement ett värde.
	 if (newElement!=NULL) {

		newElement->data = value;
		
		printf("NEW ELEMENT DATA: %d\n\n", newElement->data);
		
	 }

	/*Ifall list.first(*head) är tom ska den få värdet av newElement.
    Första värdet i listan blir värdet av newElement*/
			while (list->first == NULL)
			{
			newElement->next = list->first;    
			list->first= newElement; //start = ny start position 
			printf("\n");
			printf("The first value inserted in the linked list: %d\n\n", list->first->data);
			}
	//loopen kör tills currValue = NULL
		  while(currValue != NULL)
		  {  
			  if (currValue == NULL)
			  {
				  return 0;
			  }

			//om currValue inte = NULL ska talen sorteras i länkade listan.
			  else if (currValue !=NULL)
			  {
				//Detta är starten för det som händer när vi endast har ett värde i listan. 
				  if (list->first->next == NULL)
				  {

					//Ifall nya värdet är mindre än tillgängliga värdet skall det nya värdet placeras först i listan.
					  if (newElement->data < currValue->data)
					  {
						  newElement->next = list->first;
						  list->first = newElement;
					  }

						//Ifall det nya värdet är större än tillgängliga värdet skall nya värdet placeras sist i listan. 
					  else if (newElement->data > currValue->data)
					  {
						  list->first->next = newElement;
						  newElement->next = NULL;
					  }
				  }

					//Ser till att talen sorteras även när vi inte endast har ett tal i listan. 
				  if (list->first->next!=NULL)
				  {

						/*ifall det nya värdet är större än förra värdet men mindre än tillgängliga värdet
         				skall talet placeras mellan dessa värden*/ 
 					if (newElement->data > prevValue->data && newElement->data < currValue->data)
						  {
							  prevValue->next = newElement;
							  newElement->next = currValue;
						  }
							/*ifall det nya värdet är mindre än första talet i listan skall den placeras först.*/
						  if (newElement->data < list->first->data)
						  {
							  newElement->next = list->first;
							  list->first = newElement;
							  printf("New first value is : %d\n",list->first->data);
						  }
						  
						  /*Allokerar sista värdet */
						  while(lastNode->next != NULL){

							  lastNode = lastNode->next;

							  /*Ifall vi nått det sista värdet och nya värdet är större än sista värdet skall
            					det nya värdet placeras sist i listan. */
							  if (lastNode->next == NULL && newElement->data > lastNode->data)
							  {
								  lastNode->next = newElement;
								  newElement->next = NULL;
							  }
							  
						  }
						  
						  
				  }
				  

				  /*Skriver ut listan varje gång den får ett nytt tal*/
				  printf("Linked list order: %d\n", currValue->data);
				  prevValue = currValue;
				  currValue = currValue->next;
				  
				  
				  
				  
			  }
			  

        
		
	      }
		
		
		return value;
	}
	
	
	//initierar listan
	void initSingleLinkedList(struct singleLinkedList * list)
	{
	list->first = NULL;
	}

	//tar bort första elementet från listan.
	int removeFirstElementSingleLinkedList(struct singleLinkedList * list)
	{

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

	//mer än ett tal i listan.
    else
	{
	list->first = list->first->next;
    }

  }

  return list->first->data;
} 

	int removeLastElementSingleLinkedList(struct singleLinkedList * list)
		{
		node_t *toDelete, *secondLastNode;

		//Ifall listan är tom
		if (list->first == NULL)

		{
			return INT_MIN;
		}

		//Om listan inte är tom pekar våra pekare mot (*head)
		else
		{
			secondLastNode = list->first;
			toDelete  = list->first;

		/*medan sista nodens pekare inte = NULL, går vi genom listan och 
  		håller ett öga på förra värdet.*/
		while (toDelete->next !=NULL)
		{
			secondLastNode = toDelete;
			toDelete = toDelete->next;
		}

		//Om sista noden == (*head) nollställer vi (*head)
		if (toDelete == list->first)
		{
			list->first = NULL;
		}

		//Om sista noden !== (*head) nollställer vi före detta nod
		else
		{
			secondLastNode->next = NULL;
		}

		free(toDelete);
		return secondLastNode->data;
		
		
	}
	
	

}
