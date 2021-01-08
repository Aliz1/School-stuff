/*
 * delay.inc
 *
 * Author:	Ali Zreim, Miran Harivandi
 *
 * Date:	2021-01-03
 */ 

;==============================================================================
; Delay of 1 µs (including RCALL)
;==============================================================================
delay_1_micros:   /* UPPGIFT: komplettera med ett antal NOP-instruktioner!!! */
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP	; 12 NOPS = 12 Cycles

	RET ; 4 cycles 12 + 4 = 16 cycles = 1 microsecond. 

;==============================================================================
; Delay of X µs
;	LDI + RCALL = 4 cycles
;	Högsta parametervärdet är 2047 minsta är -2048. Vi bör inte använda parametervärden under 0
;==============================================================================
delay_micros:   /* UPPGIFT: komplettera med ett antal NOP-instruktioner!!! */ ; 5 NOP INSTRUCTIONS TO GET 12 CYCLES vilket ger en genererad fördröjning med +0.44 jämfört med önskade fördröjningen

	DEC R24				;  Decreases the value in R24 with 1			1 cycle.
	CPI R24, 0			; more loops to do?								1 cycle
	NOP
	NOP
	NOP
	NOP
	NOP					; 5 NOP's = 5 cycles
	BRNE delay_micros	;	continue!					 ; if not branching = 1 cycle.
	RET					; 4 cycles which gives us 12 cycles which is equal to 12 NOP'S 

;==============================================================================
; Delay of X ms
;	LDI + RCALL = 4 cycles
;	Högsta parametervärdet är 2047 minsta är -2048. Vi bör inte använda parametervärden under 0
;==============================================================================
delay_ms:
	MOV R18, R24		; Copy R18 copies the content of R24 
loop_dms:
	LDI R24, 250		; R24 gets the value 250
	RCALL delay_micros	; Call the function delay_micros.
	LDI R24, 250
	RCALL delay_micros
	LDI R24, 250
	RCALL delay_micros
	LDI R24, 250
	RCALL delay_micros
	DEC R18				; Decreases the content of R18 with 1 
	CPI R18, 0			; more loops to do?
	BRNE loop_dms		;	continue!
	RET