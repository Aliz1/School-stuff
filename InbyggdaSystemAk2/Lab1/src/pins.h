#ifndef PINS_H_
#define PINS_H_

#include <stdint.h>

void initPins();

void setLEDA(uint8_t);

void setLEDB(uint8_t);

uint8_t isButtonAPressed();

uint8_t isButtonBPressed();

#endif
