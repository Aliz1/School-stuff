/*#include <driver/timer.h>
#include <esp_task_wdt.h>
#include <driver/adc.h>

volatile unsigned int value = 0;

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
    value = adc1_get_raw(ADC1_GPIO32_CHANNEL);
    TIMERG0.hw_timer[0].config.alarm_en= TIMER_ALARM_EN;
}

}
void app_main()
{
    timer_config_t timerConfig;
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


    //initialize the alarm
    timer_init(TIMER_GROUP_0, TIMER_0, &timerConfig);
    //set counter value
    timer_set_counter_value(TIMER_GROUP_0, TIMER_0, 0x00000000ULL);
    // using a counter of 2000, the alarm should be raised at 1 Hz
    timer_set_alarm_value(TIMER_GROUP_0, TIMER_0, 2000);
    //enable the interrupts
    timer_enable_intr(TIMER_GROUP_0, TIMER_0);
    //REGISTER THE ISR
    timer_isr_register(TIMER_GROUP_0, TIMER_0, timerISR,NULL, ESP_INTR_FLAG_IRAM, NULL);

    //start the timer
    timer_start(TIMER_GROUP_0, TIMER_0);

    while (1)
    {
        TickType_t delay = 3000/ portTICK_PERIOD_MS;
        vTaskDelay(delay);
        printf("Current frequency is %d\n", value);
    }
    
}
*/