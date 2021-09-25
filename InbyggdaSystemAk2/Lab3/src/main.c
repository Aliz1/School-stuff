#include <stdio.h>
#include <esp_task_wdt.h>
#include "soundgen.h"
#include "sampler.h"
#include "pins.h"
#include "random.h"

// blinks an LED
void blinkLED(int ms, int times)
{
   for (int i = 0; i < times; i++)
   {
     setLED(1);
     vTaskDelay(pdMS_TO_TICKS(ms));
     setLED(0);
     vTaskDelay(pdMS_TO_TICKS(ms));
   }  

}

// absolute value of a float
float absval(float a)
{
   if (a < 0)
   return -a;
   else
   return a;
}

void app_main()
{
    initPins();
    while (1)
    {
      //flash an led to show the game is starting
      blinkLED(100,5);

      //Get a random frequency
      int ranFreq = getRandomFrequency(300, 600);

      //delay 5 seconds
      TickType_t delayfive = 5000 / portTICK_PERIOD_MS;

      TickType_t delayTwo = 2000 / portTICK_PERIOD_MS;

      //Starting the tone generator for 5 seconds and then turning it off for 5
      int actualFreq = startSound(ranFreq);
      vTaskDelay(delayfive);
      stopSound();
      //Printing the actual frequency to see if its possible to win or not
      printf("The actual frequency is: %d\n ", actualFreq);
      vTaskDelay(delayfive);

      //Starting the sampling 
      startSampling(1600);
      vTaskDelay(delayTwo);
      stopSampling();
      //Right after we stop the sampling there is an interference between ADC and DAC
      int playerFreq = getFrequency();
      //Making sure it's the right frequency read by the sampler
      printf("The players frequency =%d\n", playerFreq);

      //If player wins
      if (absval(playerFreq - (float) actualFreq) < 50)
      {
        printf("Player Wins!!!!!!\n");
        blinkLED(100,20);
      }
      //If player loses.
      else
      {
        printf("Player LOST!!!!\n");

        blinkLED(1000,2);
      }
      stopSound();
      
    }
        
        

}
