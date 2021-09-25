#include <stdio.h>
#include <stdlib.h>
#include <esp32/rom/ets_sys.h>
#include <esp_task_wdt.h>
#include "driver/gpio.h"
#include "pins.h"
#include "single_linked_list.h"
#include "travel_need.h"
#include <esp_task_wdt.h>

/**
 * Author: Ali Zreim
 * University: Malmö Universitet
 * id: aj8295
 * Course id: Da267A
*/


#define LED_PIN_LEVEL_UP 27
#define LED_PIN_LEVEL_MIDDLE 14
#define LED_PIN_LEVEL_DOWN 12
#define BUTTON_PIN 26

#define PUSH_TIME_US 250000 // 250 ms

void idle();
void moving();
void loading();
void unloading();

void waitMS(unsigned int milis){
    TickType_t delay = milis / portTICK_PERIOD_MS;
vTaskDelay(delay);
}


int currentOrigin;
int currentDestination;
int prevDestination;
static volatile int destinationStates;
static volatile int movingToOrigin;
static volatile int passedIdleOnce;

// Used to store stuff
struct singleLinkedList list;

void (*stateFuncPtr);

// Used to not allow button pushes that are too close to each other in time
static volatile uint64_t lastPush = -PUSH_TIME_US;
static volatile uint64_t lastPushIdle = -PUSH_TIME_US;

//Just a counter keeping track of which travel need is next to process.
static volatile int travel_need_counter = 0;

// This data structure holds information about 
static volatile struct travel_need travel_needs[50];
static volatile struct travel_need initializeElevator[1];

// This function is called when button is pushed
static void handle_push(void *arg) {

    // Disable interrupts
    gpio_intr_disable(BUTTON_PIN);

    // Get the current time 
    uint64_t now = esp_timer_get_time();


    // If enough time passed, we should consider this event as a genuine push
    if ((now - lastPush) > PUSH_TIME_US  && stateFuncPtr == idle) {
       
        lastPush = now;

        //Get next travel need from list and do something with it
            
                struct travel_need current_travel_need = travel_needs[travel_need_counter];
                addElementSingleLinkedList(&list, current_travel_need);

        //Increase travel need counter
        travel_need_counter = (travel_need_counter + 1) % 50;
        destinationStates = currentDestination;
        

    } // else ignore


        
    // Re-enable interrupts
    gpio_intr_enable(BUTTON_PIN);
    
}



void app_main() {

    //Initialize travel needs (50 randomly generated travel needs)
    travel_needs[0].origin = 2; travel_needs[0].destination = 1;
    travel_needs[1].origin = 1; travel_needs[1].destination = 2;
    travel_needs[2].origin = 1; travel_needs[2].destination = 2;
    travel_needs[3].origin = 0; travel_needs[3].destination = 2;
    travel_needs[4].origin = 1; travel_needs[4].destination = 0;
    travel_needs[5].origin = 0; travel_needs[5].destination = 2;
    travel_needs[6].origin = 1; travel_needs[6].destination = 2;
    travel_needs[7].origin = 1; travel_needs[7].destination = 0;
    travel_needs[8].origin = 0; travel_needs[8].destination = 1;
    travel_needs[9].origin = 1; travel_needs[9].destination = 0;
    travel_needs[10].origin = 1; travel_needs[10].destination = 2;
    travel_needs[11].origin = 0; travel_needs[11].destination = 1;
    travel_needs[12].origin = 0; travel_needs[12].destination = 2;
    travel_needs[13].origin = 0; travel_needs[13].destination = 1;
    travel_needs[14].origin = 0; travel_needs[14].destination = 2;
    travel_needs[15].origin = 0; travel_needs[15].destination = 1;
    travel_needs[16].origin = 2; travel_needs[16].destination = 1;
    travel_needs[17].origin = 2; travel_needs[17].destination = 1;
    travel_needs[18].origin = 1; travel_needs[18].destination = 0;
    travel_needs[19].origin = 2; travel_needs[19].destination = 1;
    travel_needs[20].origin = 1; travel_needs[20].destination = 0;
    travel_needs[21].origin = 0; travel_needs[21].destination = 1;
    travel_needs[22].origin = 1; travel_needs[22].destination = 2;
    travel_needs[23].origin = 0; travel_needs[23].destination = 2;
    travel_needs[24].origin = 2; travel_needs[24].destination = 1;
    travel_needs[25].origin = 1; travel_needs[25].destination = 0;
    travel_needs[26].origin = 1; travel_needs[26].destination = 2;
    travel_needs[27].origin = 0; travel_needs[27].destination = 2;
    travel_needs[28].origin = 1; travel_needs[28].destination = 0;
    travel_needs[29].origin = 1; travel_needs[29].destination = 2;
    travel_needs[30].origin = 0; travel_needs[30].destination = 1;
    travel_needs[31].origin = 1; travel_needs[31].destination = 2;
    travel_needs[32].origin = 0; travel_needs[32].destination = 2;
    travel_needs[33].origin = 0; travel_needs[33].destination = 2;
    travel_needs[34].origin = 1; travel_needs[34].destination = 2;
    travel_needs[35].origin = 2; travel_needs[35].destination = 1;
    travel_needs[36].origin = 0; travel_needs[36].destination = 2;
    travel_needs[37].origin = 1; travel_needs[37].destination = 0;
    travel_needs[38].origin = 0; travel_needs[38].destination = 2;
    travel_needs[39].origin = 2; travel_needs[39].destination = 1;
    travel_needs[40].origin = 0; travel_needs[40].destination = 1;
    travel_needs[41].origin = 0; travel_needs[41].destination = 1;
    travel_needs[42].origin = 0; travel_needs[42].destination = 1;
    travel_needs[43].origin = 1; travel_needs[43].destination = 0;
    travel_needs[44].origin = 0; travel_needs[44].destination = 2;
    travel_needs[45].origin = 2; travel_needs[45].destination = 1;
    travel_needs[46].origin = 2; travel_needs[46].destination = 1;
    travel_needs[47].origin = 2; travel_needs[47].destination = 1;
    travel_needs[48].origin = 0; travel_needs[48].destination = 2;
    travel_needs[49].origin = 1; travel_needs[49].destination = 0;

    //Used to initialize the elevator.
    initializeElevator[0].origin = -1; initializeElevator[0].destination = 0;
    struct travel_need initElevator = initializeElevator[0];

    // Initialize pins
    initPins();
    //Initialize Function pointer
    stateFuncPtr = idle;
    //Initialize the linkedlist and insert the initialized elevator values. 
    initSingleLinkedList(&list);
    addElementSingleLinkedList(&list, initElevator);
    


    

    // Activate the interrupts for the GPIOs
    int res = gpio_install_isr_service(0);
    ESP_ERROR_CHECK(res);

    // Add a handler to the ISR for pin BUTTON_PIN
    res = gpio_isr_handler_add(BUTTON_PIN, handle_push, NULL);
    ESP_ERROR_CHECK(res);
    

    
    

    // Main elevator code
    while(1) 
    {
       vTaskDelay(10);

        //If the elevator is only initialized 

        if (list.first->data.origin == -1)
        {
            if (stateFuncPtr == idle)
            {
                printf("CurrentState: Idle\n");
                if (list.first->data.destination == 0)
                {
                    printf("Waiting for button push at LOWER LEVEL\n");
                }

                else if (list.first->data.destination == 1)
                {
                    printf("Waiting for button push at MIDDLE LEVEL\n");
                }

                else if (list.first->data.destination == 2)
                {
                    printf("Waiting for button push at UPPER LEVEL\n");
                }

                //Calls the funtion
                idle();
            }
            else if (stateFuncPtr == moving)
            {
                printf("CurrentState: Moving\n");


                //If it is moving to origin floor.
                if (movingToOrigin == 1)
            {
                if (list.first->next->data.origin == 0)
                {
                    printf("Moving to origin at the FIRST FLOOR\n");
                }
                else if (list.first->next->data.origin == 1)
                {
                    printf("Moving to origin at the MIDDLE FLOOR\n");
                }
                else if (list.first->next->data.origin == 2)
                {
                    printf("Moving to origin at the UPPER FLOOR\n");
                }
                
            }
            //If it is moving to the desired destination
            else
            {
                if (list.first->next->data.destination == 0)
                {
                    printf("Moving to the desired destination FIRST FLOOR\n");
                }
                else if (list.first->next->data.destination == 1)
                {
                    printf("Moving to the desired destination MIDDLE FLOOR\n");
                }
                else if (list.first->next->data.destination == 2)
                {
                    printf("Moving to the desired destination UPPER FLOOR\n");
                }

                
            }
            moving();
            }

            else if (stateFuncPtr == loading)
            {
                printf("CurrentState: Loading\n");
                loading();
            }
            else if (stateFuncPtr == unloading)
            {
                printf("CurrentState: Unloading\n");
                unloading();
            }
            
            
            
            
        }
        
        //If the elevator moved.
        else if (list.first->data.origin != -1)
        {
        
       if (stateFuncPtr == idle)
        {
            
            printf("CurrentState: Idle\n");

            if (list.first->data.destination == 0)
            {
                printf("Waiting for button push at LOWER LEVEL\n");
            }
            else if (list.first->data.destination == 1)
            {
                printf("Waiting for button push at MIDDLE LEVEL\n");
            }
            else if (list.first->data.destination == 2)
            {
                printf("Waiting for button push at UPPER LEVEL\n");
            }
            
            idle();
                
        }

        else if (stateFuncPtr == moving)
        {
            
            printf("CurrentState: MOVING\n");

            if (movingToOrigin == 1)
            {
                if (list.first->next->data.origin == 0)
                {
                    printf("Moving to origin at the FIRST FLOOR\n");
                }
                else if (list.first->next->data.origin == 1)
                {
                    printf("Moving to origin at the MIDDLE FLOOR\n");
                }
                else if (list.first->next->data.origin == 2)
                {
                    printf("Moving to origin at the UPPER FLOOR\n");
                }
                
                
                
            }
            else
            {
                if (list.first->next->data.destination == 0)
                {
                    printf("Moving to the desired destination FIRST FLOOR\n");
                }
                else if (list.first->next->data.destination == 1)
                {
                    printf("Moving to the desired destination MIDDLE FLOOR\n");
                }
                else if (list.first->next->data.destination == 2)
                {
                    printf("Moving to the desired destination UPPER FLOOR\n");
                }
                
                
                
            }
            moving();
        }
        else if (stateFuncPtr == loading)
        {
            
            printf("CurrentState: LOADING\n");

            loading();
        }
        else if (stateFuncPtr == unloading)
        {   
            
            printf("CurrentState: UNLOADING\n");
            unloading();
        }
        
        
        

        }
    }
    
      
    
    
       
}
void idle()
{

    uint64_t now = esp_timer_get_time();
    //Handle button push.
    if ((now - lastPushIdle) > PUSH_TIME_US){
        lastPushIdle = now;

        //If the button is pushed.
        if (gpio_get_level(BUTTON_PIN) == 0)
    {
        //If the origin floor is not at the same level as last destination.
        if (list.first->next->data.origin != list.first->data.destination )
        {
            stateFuncPtr = moving;
            movingToOrigin = 1;
            
        }
        //If it is at the same level
        else if (list.first->next->data.origin == list.first->data.destination)
        
        {
            stateFuncPtr = loading;
        }
        
        
    }

    }
    else
    {
        stateFuncPtr = idle;
    }

    //Turns on the LEDS according to the level the elevator is at.
    if (list.first->data.origin == -1 && list.first->data.destination == 0)
    {
        gpio_set_level(LED_PIN_LEVEL_DOWN, 1);
    }
    else if (list.first->data.origin == -1 && list.first->data.destination == 1)
    {
        gpio_set_level(LED_PIN_LEVEL_MIDDLE,1);
    }
    else if (list.first->data.origin == -1 && list.first->data.destination == 2)
    {
        gpio_set_level(LED_PIN_LEVEL_UP,1);
    }
    
    
    
    
}
void moving()
{
    //If it's moving to origin we turn off the LED's and start loading. 
 if (movingToOrigin)
 {
    turnOffLEDs();
    stateFuncPtr = loading;
    waitMS(5000);
    movingToOrigin = 0;
    
    
     
 }
 //If the elevator is moving towards the desired destination
 else
 {
     //Turns on the LED's accordingly.
     if (list.first->next->data.origin == 1 && list.first->next->data.destination == 0)
     {
        middleToBottom();    
     }
     else if (list.first->next->data.origin == 2 && list.first->next->data.destination == 0)
     {
         topToBottom();
         
     }
     else if (list.first->next->data.origin == 0 && list.first->next->data.destination == 1)
     {
         botToMiddle();
     }
     else if (list.first->next->data.origin == 2 && list.first->next->data.destination == 1)
     {
         topToMiddle();
     }
     else if (list.first->next->data.origin == 0 && list.first->next->data.destination == 2)
     {
         botToTop();
     }
     else if (list.first->next->data.origin == 1 && list.first->next->data.destination == 2)
     {
         middleToTop();
     }
    stateFuncPtr = unloading;
    waitMS(5000); 
    
    
 }
 
    
    
}
void loading()
{
    stateFuncPtr = moving;
    waitMS(5000);
    
    
}
void unloading()
{
    
    waitMS(5000);
    removeFirstElementSingleLinkedList(&list);
    stateFuncPtr = idle;
    
}