#include <stdio.h>
#include <stdlib.h>
#include <esp32/rom/ets_sys.h>
#include <esp_task_wdt.h>
#include "driver/gpio.h" 
#include "soc/rtc_wdt.h"
#include "double_linked_list.h"


#define LED_PIN_LEVEL_UP 27
#define LED_PIN_LEVEL_MIDDLE 14
#define LED_PIN_LEVEL_DOWN 12
#define BUTTON_PIN 26
#define POINTS_AT_IDLE 3
#define POINTS_AT_LOADING 4
#define POINTS_AT_MOVING 5
#define POINTS_AT_UNLOADING 6
#define BUFFER_SIZE 4

#define PUSH_TIME_US 250000 // 250 ms
int res = 0;
// Used to represent a travel need of a passenger.
struct travel_need {
    int origin;
    int destination;
};
//Used to see if elevator is full or empty
bool empty = false;
bool overWeight = false;
//Used to see how many travelers is in the elevator
static volatile int travelers = 0;

head_t list;

// Used to not allow button pushes that are too close to each other in time
static volatile uint64_t lastPush = -PUSH_TIME_US;
static volatile uint64_t lastPush1 = -PUSH_TIME_US;
static volatile uint64_t lastPush2 = -PUSH_TIME_US;
static volatile uint64_t finalPush = -PUSH_TIME_US;

//Just a counter keeping track of which travel need is next to process.
static volatile int travel_need_counter = 0;
static volatile int prev_travel_need_counter = 500;

static volatile int current_Destination = 0;
static volatile int previous_Destination = 0;

// This data structure holds information about 
static volatile struct travel_need travel_needs[50];




void idle();
void moving();
void loading();
void unloading();
//Getters for our destination and previous destination
int getDestination();
int getPrevDestination();
//Getters for our origin and previous origin.
int getOrigin();
int getPrevOrigin();
uint8_t isButtonPushed();







void (*stateFuncPtr)();



void waitMS(unsigned int milis)
{
    TickType_t delay = milis / portTICK_PERIOD_MS;
vTaskDelay(delay);
}

 



// This function is called when button is pushed
static void handle_push(void *arg) 
{

    // Disable interrupts
    gpio_intr_disable(BUTTON_PIN);

    // Get the current time 
    uint64_t now = 0;
    now = esp_timer_get_time();
        
        


    // If enough time passed, we should consider this event as a genuine push
         if ((now - lastPush) > PUSH_TIME_US)
        {
       
        lastPush = now;
         


        

        //Increase travel need counter

        if (isButtonPushed() == 1)
            {

                
                travel_need_counter++;
                
                
                

                //Get next travel need from list and do something with it
                struct travel_need current_travel_need = travel_needs[travel_need_counter];

                //Updating the elevator level.
                uint32_t level = current_travel_need.destination;
                //Update travelers

             if (travel_need_counter > 49)
                {
                    travel_need_counter = 0;
                }

            //Elevator simulator for travelling from first level to any other level.
            
            
            
            


                    
            }

    }

    
    
    


    // Re-enable interrupts
    

}






void app_main() {
    
    //Initialize travel needs (50 randomly generated travel needs)
    travel_needs[0].origin = 2; travel_needs[0].destination = 1; //Origin = 2 Dest = 1 if 
    travel_needs[1].origin = 1; travel_needs[1].destination = 0;// Origin = 1 Dest = 0 // loading
    travel_needs[2].origin = 1; travel_needs[2].destination = 2;// Origin = 1 Dest = 2 // moving
    travel_needs[3].origin = 0; travel_needs[3].destination = 2;// Origin = 0 Dest = 2 // moving
    travel_needs[4].origin = 2; travel_needs[4].destination = 1;// Origin = 2 Dest = 1 // Loading
    travel_needs[5].origin = 0; travel_needs[5].destination = 2;// Origin = 0 Dest = 2//moving
    travel_needs[6].origin = 1; travel_needs[6].destination = 2;// Origin = 1 Dest = 2//moving
    travel_needs[7].origin = 1; travel_needs[7].destination = 0;// Origin = 1 Dest = 0//loading
    travel_needs[8].origin = 0; travel_needs[8].destination = 1;// Origin = 0 Dest = 1//loading
    travel_needs[9].origin = 1; travel_needs[9].destination = 0;// Origin = 1 Dest = 0
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


    initDoubleLinkedList(&list);

    //Initialize our function pointer
    stateFuncPtr = idle;
    
    
    
    
    

    gpio_config_t configPIN_LEVEL_UP;
    gpio_config_t configPIN_LEVEL_MIDDLE;
    gpio_config_t configPIN_LEVEL_DOWN; 
    gpio_config_t configBUTTON_PIN;
    

    // Configure pin LED_PIN_LEVEL_UP as output 
    configPIN_LEVEL_UP.pin_bit_mask = 1 << LED_PIN_LEVEL_UP;
    configPIN_LEVEL_UP.mode = GPIO_MODE_OUTPUT;

    // Configure pin LED_PIN_LEVEL_MIDDLE as output 
    configPIN_LEVEL_MIDDLE.pin_bit_mask = 1<< LED_PIN_LEVEL_MIDDLE;
    configPIN_LEVEL_MIDDLE.mode = GPIO_MODE_OUTPUT;
    
    // Configure pin LED_PIN_LEVEL_DOWN as output 
    configPIN_LEVEL_DOWN.pin_bit_mask = 1<< LED_PIN_LEVEL_DOWN;
    configPIN_LEVEL_DOWN.mode = GPIO_MODE_OUTPUT;
    

    // Configure pin BUTTON_PIN as input, pull up and with interrupts on the negative edge
    configBUTTON_PIN.pin_bit_mask = 1<<BUTTON_PIN;
    configBUTTON_PIN.mode = GPIO_MODE_INPUT;
    configBUTTON_PIN.pull_up_en = 1;
    configBUTTON_PIN.intr_type = GPIO_INTR_NEGEDGE;



    // Activate the interrupts for the GPIOs
    gpio_config(&configPIN_LEVEL_UP);
    gpio_config(&configPIN_LEVEL_MIDDLE);
    gpio_config(&configPIN_LEVEL_DOWN);
    gpio_config(&configBUTTON_PIN);
    gpio_set_level(GPIO_NUM_27, 0);
    gpio_set_level(GPIO_NUM_12, 0);
    gpio_set_level(GPIO_NUM_14, 0);
    
    res = gpio_install_isr_service(0);
    ESP_ERROR_CHECK(res);

    // Add a handler to the ISR for pin BUTTON_PIN
    res = gpio_isr_handler_add(BUTTON_PIN, handle_push, NULL);
    ESP_ERROR_CHECK(res);
    
    // This is where you most likely put your main elevator code. 
    while(1) {
        
        addElementDoubleLinkedList(&list, 2);
        addElementDoubleLinkedList(&list, 3);
        addElementDoubleLinkedList(&list, 4);
        addElementDoubleLinkedList(&list, 5);
        addElementDoubleLinkedList(&list, 6);
        addElementDoubleLinkedList(&list, 7);
        printList(&list);

        
     /*   printf("Current level: %d\n", travel_need_counter);


    
        
        if(stateFuncPtr == idle) 
        {
            printf("\nCurrent state: IDLE\n");
            if (getDestination() == 0) {
	        printf("Current position: LOWER LEVEL\n\n");
	
        }
            else if (getDestination() == 2) 
            {
	            printf("Current position: UPPER LEVEL\n\n");	
                printf("current level: %d\n", getDestination());
            }
            else if (getDestination() == 1) 
            {
	        printf("Current position: MID LEVEL\n\n");	
            }
        }



        else if(stateFuncPtr == moving) 
        {
            

            printf("Current state: MOVING\n");

            if (getDestination() == 2) 
            {
	            printf("Current position: MOVING TO LAST FLOOR\n");
            }

            else if (getDestination() == 0) 
            {
	            printf("Current position: MOVING TO THE FIRST FLOOR\n");
            }
            else if (getDestination() == 1)
            {
                printf("Current position: MOVING TO THE MIDDLE FLOOR\n");
            }
      
        }

    
            else if (stateFuncPtr == loading)
            {
                printf("Current state: LOADING\n");

                if (getPrevDestination() == 0) 
                {
	                printf("Current position: LOWER LEVEL\n");

                }
                else if (getPrevDestination() == 2) 
                {
	                printf("Current position: UPPER LEVEL\n");	
                }
                else if (getPrevDestination() == 1) 
                {
	                printf("Current position: MID LEVEL\n");	
                }

      
    }

            else if (stateFuncPtr == unloading)
            {
                printf("Current state: UNLOADING\n");

                if (getDestination() == 0) 
                {
	                printf("Current position: LOWER LEVEL\n");

                }
                else if (getDestination() == 2) 
                {
	                printf("Current position: UPPER LEVEL\n");	
                }
                else if (getDestination() == 1) 
                {
	                printf("Current position: MID LEVEL\n");	
                }

            }
        handle_push(app_main);
        stateFuncPtr();
        vTaskDelay(10);*/
    
    }
    
    
    
    

      

       
}
int getDestination()
{
    return travel_needs[travel_need_counter].destination;
}
int getPrevDestination()
{
    return travel_needs[travel_need_counter-1].destination;
}
int getOrigin()
{
    return travel_needs[travel_need_counter].origin;
}
int getPrevOrigin()
{
    return travel_needs[travel_need_counter-1].origin;
}

//Initializing our states

void idle()
{
    
    
    uint64_t now = 0;
    now = esp_timer_get_time();
      if ((now - lastPush1) > PUSH_TIME_US)
        {
            lastPush1 = now;
            
            
            
            
    if (isButtonPushed() == 0)
    {
        stateFuncPtr = idle;
    }
    else if (isButtonPushed() == 1)
    {
       

     if (getPrevDestination() == getOrigin())
    {
        
        printf("TRANSITIONING FROM IDLE TO LOADING\n");
        addElementDoubleLinkedList(&list,POINTS_AT_IDLE);
        
        stateFuncPtr = loading;
    }

    else if (getPrevDestination() != getOrigin())
    {
        printf("TRANSITIONING FROM IDLE TO MOVING\n");
        addElementDoubleLinkedList(&list,POINTS_AT_IDLE);
        stateFuncPtr = moving;
    }
    
}
        }
        
}


void moving()
    {
    
    
    if (getPrevDestination() == 0 && getDestination() == 1)
            {
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 1); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
            } 
            else if (getPrevDestination() == 0 && getDestination() == 2)
            {
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 1); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 1);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
            }/*End of First level simulator*/
            
            //Simulator for middle level to any other level
            else if (getPrevDestination() == 1 && getDestination() == 0) 
            {
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 1); 
            }
            else if (getPrevDestination() == 1 && getDestination() == 2)
            {
              gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 1);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
            }/*End of Middle level simulator*/

            //Simulator for Last level to any other level
            else if (getPrevDestination() == 2 && getDestination() == 1)
            {
                gpio_set_level(LED_PIN_LEVEL_UP, 1);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
            }
            else if (getPrevDestination() == 2 && getDestination() == 0)
            {
                gpio_set_level(LED_PIN_LEVEL_UP, 1);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(4700);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 0); 
                waitMS(300);
                gpio_set_level(LED_PIN_LEVEL_UP, 0);
                gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
                gpio_set_level(LED_PIN_LEVEL_DOWN, 1);
            }
    
    addElementDoubleLinkedList(&list,POINTS_AT_MOVING);
    stateFuncPtr = unloading;
    
}

void loading()
{
    
    printf("Loading passengers\n");
    waitMS(2500);
    addElementDoubleLinkedList(&list,POINTS_AT_LOADING);
    stateFuncPtr = moving;
    
}

void unloading()
{
    printList(&list);
    printf("Unloading passengers\n");
    waitMS(2500);
    stateFuncPtr = idle;
    
}/*Done initializing states*/

uint8_t isButtonPushed()
{
    if (gpio_get_level(GPIO_NUM_26)== 0)
    {
        return 1;
        gpio_intr_enable(BUTTON_PIN);
    }
    else
    {
        return 0;
        gpio_intr_enable(BUTTON_PIN);
    }
    
}
