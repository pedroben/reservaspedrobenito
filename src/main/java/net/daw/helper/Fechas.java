/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.helper;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author unolimitado
 */
public class Fechas {

    /**
     * Devuelve un objeto Date con la fecha del primer día del mes
     * @return Date
     */
    public static Date getPrimerDiaDelMes() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    /**
     * Devuelve un objeto Date con la fecha del último día del mes
     * @return Date
     */
    public static Date getUltimoDiaDelMes() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    /**
     * Devuelve como un entero el últimio dia del mes para un año y un mes dados
     * por parámetros
     * @param anyo String Ejemplo "2013"
     * @param mes String Ejemplo "1" que sería Febrero xq empieza en 0 
     * @return int Ejemplo 28 si le hemos pasado Febrero
     */
    public static int getUltimoDiaDelMes(int anyo, int mes) {
        Calendar cal = Calendar.getInstance();
        cal.set(anyo,mes,1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
