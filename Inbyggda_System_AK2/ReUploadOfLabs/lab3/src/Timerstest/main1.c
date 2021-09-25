/*#include <esp_task_wdt.h>
#include <driver/adc.h>

void  app_main(){

    adc_power_on();

    adc_gpio_init(ADC_UNIT_1,ADC1_GPIO32_CHANNEL);

    adc1_config_width(ADC_WIDTH_12Bit);

    adc2_config_channel_atten(ADC1_GPIO32_CHANNEL, ADC_ATTEN_DB_11);

    esp_err_t result = gpio_pullup_en(32);

    if (result == ESP_OK )
    {
        while (1)
        {
            int val = adc1_get_raw(ADC1_GPIO32_CHANNEL);
            printf("%d\n", val);
            vTaskDelay(pdMS_TO_TICKS(500));
        }
        
    }
    

}*/