#include "pins.h"
#include "driver/gpio.h"

void initPins(){
    gpio_config_t configLEDA;
    configLEDA.pin_bit_mask = (uint64_t) 1 << 12;
    configLEDA.mode = GPIO_MODE_OUTPUT;
    gpio_config(&configLEDA);
    
}
void setLED(uint8_t level)
{
    if (level !=0)
    {
        gpio_set_level(GPIO_NUM_12, 1);
    }
    else{
        gpio_set_level(GPIO_NUM_12, 0);
    }
    
}