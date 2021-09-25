#include <stdio.h>
#include "blackboxTests.h"
#include "circular_buffer.h"

struct circularBuffer buffer;

void buffer_size()
{
    addElement(&buffer, 10);
    printBuffer(&buffer);
}