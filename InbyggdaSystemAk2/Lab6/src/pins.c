#include "pins.h"
#include "driver/gpio.h"
#include <esp_task_wdt.h>
#include <esp32/rom/ets_sys.h>
#define LED_PIN_LEVEL_UP 27
#define LED_PIN_LEVEL_MIDDLE 14
#define LED_PIN_LEVEL_DOWN 12

/**
 * Author: Ali Zreim
 * University: Malmö Universitet
 * id: aj8295
 * Course id: Da267A
*/


void waitMS1(unsigned int milis)
{
    TickType_t delay = milis / portTICK_PERIOD_MS;
vTaskDelay(delay);
}
void initPins(){

    gpio_config_t configPinLEDBottom;
    configPinLEDBottom.pin_bit_mask = (uint64_t) 1 << 12;
    configPinLEDBottom.pull_up_en = 0;
    configPinLEDBottom.pull_down_en = 0;
    configPinLEDBottom.mode = GPIO_MODE_OUTPUT;
    configPinLEDBottom.intr_type = 0;
    gpio_config(&configPinLEDBottom);
    
    gpio_config_t configPinLEDMiddle;
    configPinLEDMiddle.pin_bit_mask = (uint64_t) 1 << 14;
    configPinLEDMiddle.pull_up_en = 0;
    configPinLEDMiddle.pull_down_en = 0;
    configPinLEDMiddle.mode = GPIO_MODE_OUTPUT;
    configPinLEDMiddle.intr_type = 0;
    gpio_config(&configPinLEDMiddle);
    
    gpio_config_t configPinLEDTop;
    configPinLEDTop.pin_bit_mask = (uint64_t) 1 << 27;
    configPinLEDTop.pull_up_en = 0;
    configPinLEDTop.pull_down_en = 0;
    configPinLEDTop.mode = GPIO_MODE_OUTPUT;
    configPinLEDTop.intr_type = 0;
    gpio_config(&configPinLEDTop);
    

     gpio_config_t configPinBUTTON;
    configPinBUTTON.pin_bit_mask = (uint64_t) 1 << 26;
    configPinBUTTON.mode = GPIO_MODE_INPUT;
    configPinBUTTON.pull_up_en = 1;
    configPinBUTTON.pull_down_en = 0;
    configPinBUTTON.intr_type = GPIO_PIN_INTR_NEGEDGE;
    
    gpio_config(&configPinBUTTON);
    
    
    
    

    
}

void middleToBottom()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);

    waitMS1(5000);
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 1);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);
}



void topToBottom()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 1);

    waitMS1(5000);
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 1);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);
}

void botToMiddle()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 1);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);

    waitMS1(5000);
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);
}

void topToMiddle()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 1);

    waitMS1(5000);
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);
}

void botToTop()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 1);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);

    waitMS1(5000);
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 1);
}

void middleToTop()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 1);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);

    waitMS1(5000);
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 1);
}

void turnOffLEDs()
{
    gpio_set_level(LED_PIN_LEVEL_MIDDLE, 0);
    gpio_set_level(LED_PIN_LEVEL_DOWN, 0);
    gpio_set_level(LED_PIN_LEVEL_UP, 0);
}