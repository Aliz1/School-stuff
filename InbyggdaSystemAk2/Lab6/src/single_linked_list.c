#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include "single_linked_list.h"

/**
 * Author: Ali Zreim
 * University: Malmö Universitet
 * id: aj8295
 * Course id: Da267A
*/

	struct travel_need addElementSingleLinkedList(list_t * list, struct travel_need value) {


		//Instansvariabler för pekare samt allokering av minne för newElement pekaren.
		node_t * newElement = (node_t * ) malloc(sizeof(node_t));
		
		//Lägger in värden för destination och origin
		newElement->data.destination = value.destination;
		newElement->data.origin = value.origin;


		//Om listan är tom
		if (list->first == NULL)
		{
			newElement->next = NULL;
			list->first = newElement;
			return value;
		}

		//Om den inte är tom
		else
		{
			//En temporär variabel som håller värdet av head
			struct singleLinkedListElement *tempHead = list->first;

			while (tempHead->next != NULL)
			{
				tempHead = tempHead->next;
			}
			tempHead->next = newElement;
			newElement->next = NULL;
			return value;
			
		}
		return value;
		
	}
	
	
	//initierar listan
	void initSingleLinkedList(struct singleLinkedList * list)
	{
	list->first = NULL;
	}


	//tar bort första elementet från listan.
	struct travel_need removeFirstElementSingleLinkedList(struct singleLinkedList * list)
	{
		struct travel_need travel_needs;
		travel_needs.origin = 0;
		travel_needs.destination = 0;

	//Checkar ifall listan är tom.	
  	if (list->first == NULL)
  	{
		printf("Empty list!\n");
  	}
	// om listan inte är tom
  	else
  	{
		  
		  struct singleLinkedListElement *toDelete;
		  toDelete = list->first;
		  list->first = list->first->next;
		  free(toDelete);

  	}

  return travel_needs;
} 
