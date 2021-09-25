#ifndef WHITEBOXTESTS_H
#define WHITEBOXTESTS_H

//White box test 1
void initCircularBufferTest();

//White box test 3.1
void addElementBeginning();

//White box test 3.2
void addElementEmptyBuffer_notStart();

//White box test 3.3
void addElement_non_emptyBuffer();

//White box test 3.4
void addElementTo_Buffer_Size_minus1();

//White box test 4.1
void removeValue_bufferOneElement();

//White box test 4.2
void removeValue_fullBuffer();

//White box test 4.3
void removeValue_emptyBuffer();

//White box test 5.1
void removeHead_OneElement();

//White box test 5.2
void removeHead_twoElements();

//White box test 5.3
void removeHead_threeElements();

//White box test 5.4
void removeHead_emptyBuffer();



#endif