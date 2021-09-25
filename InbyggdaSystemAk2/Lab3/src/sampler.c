#include <driver/adc.h>
#include "sampler.h"
#include <stdio.h>
#include <esp_task_wdt.h>
#include <driver/timer.h>
#include "soundgen.h"

volatile int currVal = 0;
volatile int prevVal = 0;

volatile int crossingCounter;

int startsampleTime = 0;
int endsampleTime = 0;



void IRAM_ATTR timerISR()
{   
    uint32_t is_t0 = TIMERG0.int_st_timers.t0;
    if (is_t0)
    {
        
        TIMERG0.int_clr_timers.t0 = 1;
        //prevVal gets the old value of currVal
        prevVal = currVal;
        //currVal gets the raw value from adc channel 32 for the esp32
        currVal = adc1_get_raw(ADC1_CHANNEL_4);

        //The average value is 1400 taken from the arduino plotter. 
        if (currVal > 1400 && prevVal < 1400)
        {
            crossingCounter++;

        } 
    
        
        
        TIMERG0.hw_timer[0].config.alarm_en = TIMER_ALARM_EN;
    }
}

void startSampling(int freq)
{   



    //Getting the dividers value by dividing 8mHz with the frequency
    int divider = 80000000/freq;

        
    //Configuration the timer.
     timer_config_t timerConfig;

    // Reset the timer when the counter is reached.
    timerConfig.auto_reload = TIMER_AUTORELOAD_EN;

    // Enable the alarm
    timerConfig.alarm_en = TIMER_ALARM_EN;

    // Stop the alarm
    timerConfig.counter_en = TIMER_PAUSE;

    // Timer counts up
    timerConfig.counter_dir = TIMER_COUNT_UP;

    // Divisor of the clock with 80MHz 
    timerConfig.divider = divider; //Expected frequency = freq
    //Setting the interrup type to level mode.
    timerConfig.intr_type = TIMER_INTR_LEVEL;
        
    // set the initial value of the counter to 0
    timer_init(TIMER_GROUP_0, TIMER_0, &timerConfig);

    // using a counter of 1
    timer_set_alarm_value(TIMER_GROUP_0, TIMER_0, 1);

    // enable interrupts
    timer_enable_intr(TIMER_GROUP_0, TIMER_0);

    // register the ISR
    timer_isr_register(TIMER_GROUP_0, TIMER_0, timerISR, NULL, ESP_INTR_FLAG_IRAM, NULL);
            
    timer_start(TIMER_GROUP_0, TIMER_0);

    //Turns on the adc
    adc_power_on();

    //initializes the SAR ADC 1 on GPIO32 for the ESP32
    adc_gpio_init(ADC_UNIT_1, ADC1_CHANNEL_4);

    //Configures the capture width of ADC1 to 12 bits value: 4095
    adc1_config_width(ADC_WIDTH_BIT_12);


    /*Configures the channel Attenuation level to 11dB which gives it a full-scale voltage of 3.9V which is limited by the VDD_A 
    3.3V in our case. Best results between 150 to 2450 mV*/
    adc1_config_channel_atten( ADC_CHANNEL_4, ADC_ATTEN_DB_11);    

    gpio_pullup_en(32);
        
    //get the time when sampling start
    startsampleTime = esp_timer_get_time(); 
    
    //Reset the counter
    crossingCounter = 0;
            
}

//Stops the sampling and gives us the time it took to end the sample.
void stopSampling()
{
    adc_power_off();
        
    endsampleTime = esp_timer_get_time();

}

//Gives us the value of frequency.
float getFrequency()
{
    // Turning the time from us to ms
    int time = (endsampleTime - startsampleTime) / 1000;

    //Adjusting the counter to match our transformation
    float frequency = (crossingCounter * 1000) / time;

    return frequency;
}
