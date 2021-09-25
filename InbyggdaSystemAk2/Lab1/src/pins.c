#include "pins.h"
#include "driver/gpio.h"

void initPins(){
    gpio_config_t configLEDA;
    configLEDA.pin_bit_mask = (uint64_t) 1 << 12;
    configLEDA.mode = GPIO_MODE_OUTPUT;
    gpio_config(&configLEDA);

    gpio_config_t configLEDB;
    configLEDB.pin_bit_mask = (uint64_t) 1<<13;
    configLEDB.mode = GPIO_MODE_OUTPUT;
    gpio_config(&configLEDB);

    gpio_config_t configPButtonA;
    configPButtonA.pin_bit_mask = (u_int64_t)1 << 26;
    configPButtonA.mode = GPIO_MODE_INPUT;
    configPButtonA.pull_up_en = 1;
    gpio_config(&configPButtonA);

    gpio_config_t configPButtonB;
    configPButtonB.pin_bit_mask = (u_int64_t)1 << 27;
    configPButtonB.mode = GPIO_MODE_INPUT;
    configPButtonB.pull_up_en = 1;
    gpio_config(&configPButtonB);

    
}
void setLEDA(uint8_t level)
{
    if (level !=0)
    {
        gpio_set_level(GPIO_NUM_12, 1);
    }
    else{
        gpio_set_level(GPIO_NUM_12, 0);
    }
    
}
void setLEDB(uint8_t level)
{
    if (level !=0)
    {
        gpio_set_level(GPIO_NUM_13, 1);
    }
    else{
        gpio_set_level(GPIO_NUM_13, 0);
    }
    
}

uint8_t isButtonAPressed()
{
    
    if (gpio_get_level(GPIO_NUM_26) == 0){
        printf("Button A is pressed");
        return 1;
    }
    else{
        return 0;
    }
  
}

uint8_t isButtonBPressed()
{
    
    if (gpio_get_level(GPIO_NUM_27) == 0){
        printf("Button B is pressed");
        return 1;
    }
    else{
        return 0;
    }
}