#include <stdio.h>
#include <stdlib.h>

#include "single_linked_list.h"
#include "double_linked_list.h"

void app_main() {
        
        printf("\n\n\n");
        printf("Single Linked List\n\n\n");
        printf("----------------------------------------------\n");

        //MAIN METHOD FOR SINGLE LINKED LIST

        list_t List;
        initSingleLinkedList(&List);
        addElementSingleLinkedList(&List, 100);
        addElementSingleLinkedList(&List, 200);
        addElementSingleLinkedList(&List,500);
        addElementSingleLinkedList(&List, 75);
        addElementSingleLinkedList(&List, 85);
        addElementSingleLinkedList(&List, 10);
        addElementSingleLinkedList(&List, 600);
        addElementSingleLinkedList(&List, 3);
        addElementSingleLinkedList(&List, 1000);
        addElementSingleLinkedList(&List, 2);
        addElementSingleLinkedList(&List, 1); 
        addElementSingleLinkedList(&List, 40);
        addElementSingleLinkedList(&List, 30);
       
        int removeFirstElement = removeFirstElementSingleLinkedList(&List);
        printf("Removing 1st value...\nThe #1 value is: %d\n", removeFirstElement);
        int removelastval = removeLastElementSingleLinkedList(&List);
        printf("Removing last value...\nThe last value is:  %d\n", removelastval);

        printf("\n\n\n");
        printf("----------------------------------------\n");
        printf("Double Linked List\n\n\n");

        // MAIN METHOD FOR DOUBLE LINKED LIST

        head_t list;
        initDoubleLinkedList(&list);
        addElementDoubleLinkedList(&list,100);//
        addElementDoubleLinkedList(&list,200);//
        addElementDoubleLinkedList(&list,500);//
        addElementDoubleLinkedList(&list,75);//
        addElementDoubleLinkedList(&list,85);//
        addElementDoubleLinkedList(&list, 10);//
        addElementDoubleLinkedList(&list, 600);//
        addElementDoubleLinkedList(&list,3);//
        addElementDoubleLinkedList(&list,1000);//
        addElementDoubleLinkedList(&list,2);//
        addElementDoubleLinkedList(&list,30);//
        addElementDoubleLinkedList(&list,40);//
        addElementDoubleLinkedList(&list,1);//
        
        int removeFirstElementD = removeFirstElementDoubleLinkedList(&list);
        printf("Removing 1st value...\nThe #1 value is: %d\n", removeFirstElementD);
        int removelastvalD = removeLastElementDoubleLinkedList(&list);
        printf("Removing last value...\nThe last value is:  %d\n", removelastvalD);

}