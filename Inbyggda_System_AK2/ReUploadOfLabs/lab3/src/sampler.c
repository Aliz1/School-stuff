#include <sampler.h>
#include<esp_task_wdt.h>
#include<driver/GPIO.h>
#include <driver/dac.h>
#include <driver/timer.h>
#include <freertos/FreeRTOS.h>
#include<freertos/timers.h>
#include <driver/adc.h>

volatile int frequency;
int freq[10];
int i = 0;
timer_config_t timerConfig;
 int cntr = 0;
 float prevValue;
 float currValue;
 int crossing = 0;
 float counter = 0;

 int storedValues[10];
 volatile float sum = 0;
 volatile float avarage =0;
 volatile int value = 0;
uint64_t timer_counter_value = 0x00000000ULL;
void IRAM_ATTR timerISR()
{

uint32_t is_t0 = TIMERG0.int_st_timers.t0;

if (is_t0)
{
    TIMERG0.int_clr_timers.t0= 1;

    adc_power_on();
    adc_gpio_init(ADC_UNIT_1, ADC1_GPIO32_CHANNEL);
    adc1_config_width(ADC_WIDTH_BIT_12);
    adc1_config_channel_atten(ADC1_GPIO32_CHANNEL,ADC_ATTEN_DB_11);

    //Storing the value of the last measured sample
    prevValue = currValue;
    //Giving the current value the value coming out from the adc
    currValue = adc1_get_raw(ADC1_GPIO32_CHANNEL);

    TIMERG0.hw_timer[0].config.alarm_en= TIMER_ALARM_EN;
}
}
//starts adc and makes it possible to insert it into the timers interrupt/action
void startSampling(int freq){


timerISR();



//esp_err_t result = gpio_pullup_en(32);

   
    //reload timer
    timerConfig.auto_reload = TIMER_AUTORELOAD_EN;
    // enable timer
    timerConfig.alarm_en= TIMER_ALARM_EN;
    //stop the timer
    timerConfig.counter_en= TIMER_PAUSE;
    //timer counts up
    timerConfig.counter_dir= TIMER_COUNT_UP;
    //divide the incoming signal of 80hz
    timerConfig.divider= 40000;
    //timer interrupt level
    timerConfig.intr_type= TIMER_INTR_LEVEL;

    



/*Configure timer*/
    //initialize the alarm
    timer_init(TIMER_GROUP_0, TIMER_0, &timerConfig);
    //set counter value
    timer_set_counter_value(TIMER_GROUP_0, TIMER_0, timer_counter_value);
    // using a counter of 2000, the alarm should be raised at 1 Hz
    timer_set_alarm_value(TIMER_GROUP_0, TIMER_0, 6000);
    //enable the interrupts
    timer_enable_intr(TIMER_GROUP_0, TIMER_0);
    //REGISTER THE ISR
    timer_isr_register(TIMER_GROUP_0, TIMER_0, timerISR,NULL, ESP_INTR_FLAG_IRAM, NULL);


    //start the timer
    timer_start(TIMER_GROUP_0, TIMER_0);
    
    printf("the value of adc = %d\n", adc1_get_raw(ADC1_GPIO32_CHANNEL));

}

void stopSampling(){
    adc_power_off();
    timerConfig.alarm_en= TIMER_ALARM_DIS;
}
//Even simpler: each time you take a sample increase a counter if you detect a
//crossing. That’s easily done if you just store the value of the last measured sample. 
//You can then divide the counter by twice the total duration of the signal, which you 
//need to keep track of, for example by storing the time when the first sample is retrieved. 


//Increase counter when a crossing is detected
//Detecting a crossing is done by storing the value of the last measured sample  
float getFrequency(){
    prevValue = currValue;
    currValue = adc1_get_raw(ADC1_GPIO32_CHANNEL);
    crossing = 0;
    int i =0;
    int twoTimesTheCounterValue;
    float total;
 /* Retrieve the counter value
       from the timer that reported the interrupt */
   uint64_t timer_get_counter_value = timer_group_get_counter_value_in_isr(TIMER_GROUP_0, TIMER_0);
   twoTimesTheCounterValue = timer_get_counter_value * 2;

    for ( i = 0; i < 1; i++)
    {


        /*
        What i don't understand here is that ADC_ATTEN_2_5db is (1) in integer. The values coming out from the A0 is between 200 and 3000 
        which is why this statement will never be true.
        I chose the ADC_ATTEN_2_5db because you mentioned in the lab descriptions that the avarage value is 1250mV. 
        But now i changed it to the average value i am getting from the A0
        */
       
       //if a crossing is detected increase the counters value by 1
        if (prevValue <2500 && currValue> 2500)
        
        {
            counter++;
        
        //  total = counter / twoTimesTheCounterValue; 
        }
        //divides the counter with 2 times the duration of the signal.
        total = counter / twoTimesTheCounterValue;
        
        prevValue = currValue*total;
        //shows the value of the current counter
        printf("The current value is %f\n ",  currValue);
        //if the previous value is < ADC_ATTEN_2_5db shows 1 otherwise 0
        printf("Is the previous value < Avarage: %d\n" , prevValue< 2500);
        //if the current value > ADC_ATTEN_2_5db shows 1 otherwise 0
        printf("Is the current value > Avarage: %d\n", currValue> 2500);
        //Shows the counters value
        printf("The counters value is: %f\n", total);
        //printf("The timer counter value is %lld\n", timer_get_counter_value);

        
    }
value = adc1_get_raw(ADC1_GPIO32_CHANNEL);
    for (int i = 0; i < 10; i++)
    {
        storedValues[i] = value;
        sum += storedValues[i];
    }

    for (int i = 0; i < 10; i++)
    {
        avarage = sum / 10;
    }
    printf("The Average value is %.2f\n", avarage);
    return prevValue;
   

}