#include "soundgen.h"
#include <soc/syscon_reg.h>
#include <soc/sens_reg.h>
#include <soc/soc.h>
#include <soc/rtc.h>
#include <driver/dac.h>


int clk_8m_div = 0;


int startSound(int freq)
{   
    //Multiplying the constant with the frequency to be able to figure out the frequencystep.
    int sumOfFreqandConstant = 65536 *freq;
    //Getting the frequency steps value.
    int frequency_step = sumOfFreqandConstant / RTC_FAST_CLK_FREQ_APPROX;
    
    //Enables the CW generator
    SET_PERI_REG_MASK(SENS_SAR_DAC_CTRL1_REG, SENS_SW_TONE_EN);

    //Connects the generator to DAC Channel 1
    SET_PERI_REG_MASK(SENS_SAR_DAC_CTRL2_REG, SENS_DAC_CW_EN1);

    //Writes a value to the SENS_SW_FSTEP which can be used to adjust the frequency
    REG_SET_FIELD(SENS_SAR_DAC_CTRL1_REG, SENS_SW_FSTEP, frequency_step);

    //inverting MSB in the SENS_DAC_INV1
    REG_SET_FIELD(SENS_SAR_DAC_CTRL2_REG, SENS_DAC_INV1, 2);

    //Making sure that the scale and dc fields are set to 0
    REG_SET_FIELD(SENS_SAR_DAC_CTRL2_REG, SENS_DAC_SCALE1, 0);
    REG_SET_FIELD(SENS_SAR_DAC_CTRL2_REG, SENS_DAC_DC1, 0);
    
    //Activating the DAC
    dac_output_enable(DAC_CHANNEL_1);

    return freq;
}
//Stopping the sound by disabling the DAC
void stopSound()
{
    dac_output_disable(DAC_CHANNEL_1);
}