#ifndef SAMPLER_H_
#define SAMPLER_H

void startSampling(int freq);

void stopSampling();

/*** Computes the average frequency of the signal that has been (or is being) sampled.*/
float getFrequency();
#endif