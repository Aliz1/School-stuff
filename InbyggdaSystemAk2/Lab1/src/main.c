#include <esp_task_wdt.h>
#include <pins.h>
#include <random.h>

void waitMS(unsigned int milis){
    TickType_t delay = milis / portTICK_PERIOD_MS;
vTaskDelay(delay);
}
void app_main() {
initPins();

while(1){

    setLEDA(1);
    setLEDB(1);
    waitMS(500);

    setLEDA(0);
    setLEDB(0);
    waitMS(500);

    setLEDA(1);
    setLEDB(1);
    waitMS(500);

    setLEDA(0);
    setLEDA(0);
    waitMS(500);

    setLEDA(1);
    setLEDB(0);
    waitMS(500);

    setLEDA(0);
    setLEDB(1);
    waitMS(300);

    setLEDA(0);
    setLEDB(0);
    

     int r = getRandommsecs(3000, 5000);
        waitMS(r);
        printf("random value: %d\n", r);
        setLEDA(1);
        setLEDB(1);


    uint8_t winner =0;

        while(!winner){
             if(isButtonAPressed())
             {
                 winner =1;
            }
             else if(isButtonBPressed())
             {
                 winner =2;
                  }

        }

               if (winner == 1){
                setLEDA(1);
                setLEDB(0);
                waitMS(500);
                setLEDB(0);
                setLEDA(0);
                waitMS(500);
                setLEDB(0);
                setLEDA(1);
                waitMS(500);
                setLEDB(0);
                setLEDA(0);
                waitMS(500);
                setLEDB(0);
                setLEDA(1);
                waitMS(500);
            }
        
            if (winner == 2){
                setLEDA(0);
                setLEDB(1);
                waitMS(500);
                setLEDB(0);
                setLEDA(0);
                waitMS(500);
                setLEDB(1);
                setLEDA(0);
                waitMS(500);
                setLEDB(0);
                setLEDA(0);
                waitMS(500);
                setLEDB(1);
                setLEDA(0);
                waitMS(500);
            }

     
            setLEDB(0);
            setLEDA(0);
            waitMS(5000);
}
}