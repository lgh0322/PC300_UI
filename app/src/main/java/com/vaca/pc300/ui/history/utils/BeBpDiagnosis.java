package com.vaca.pc300.ui.history.utils;

public class BeBpDiagnosis {

    private int indicator = 0;

    public BeBpDiagnosis() {

    }

    /**
     * @param sys
     * @param dia blue, green, yellow, orange, red, (deepRed)
     */
    public BeBpDiagnosis(int sys, int dia) {
        if (sys < 90 && dia < 60) {
            indicator = 1;
        } else if (sys < 120 && dia < 80) {
            indicator = 2;
        } else if (sys < 140 && dia < 90) {
            indicator = 3;
        } else if (sys < 160 && dia < 100) {
            indicator = 4;
        } else if (sys < 180 && dia < 110) {
            indicator = 5;
        } else {
            indicator = 6;
        }
    }

    public int getIndicator() {
        return indicator;
    }

    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }
}
