#include "random.h"
#include <esp_task_wdt.h>


int getRandommsecs(int min, int max){

return esp_random() % (max - min + 1) + min;

}

