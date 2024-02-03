package raven.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Clase utilitaria para calcular y manipular fechas.
 * Permite obtener el texto de búsqueda entre dos fechas, la diferencia en días entre dos fechas,
 * y representaciones de fechas en diferentes formatos.
 *
 * @author Esteban
 */
public class DateCalculator {

    private Date dateStart;
    private Date dateEnd;

    /**
     * Constructor que inicializa la clase DateCalculator con fechas de inicio y fin.
     *
     * @param dateStart Fecha de inicio.
     * @param dateEnd   Fecha de fin.
     */
    public DateCalculator(Date dateStart, Date dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    /**
     * Constructor predeterminado que crea una instancia de DateCalculator sin fechas.
     */
    public DateCalculator() {
    }

    /**
     * Obtiene la fecha de inicio.
     *
     * @return La fecha de inicio.
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Establece la fecha de inicio.
     *
     * @param dateStart La fecha de inicio a establecer.
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Obtiene la fecha de fin.
     *
     * @return La fecha de fin.
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Establece la fecha de fin.
     *
     * @param dateEnd La fecha de fin a establecer.
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Obtiene el texto de búsqueda que representa el rango de fechas.
     *
     * @return El texto de búsqueda del rango de fechas.
     */
    public String getTextSearch() {
        ModelDate start = new ModelDate(dateStart);
        ModelDate end = new ModelDate(dateEnd);
        String date;
        if (start.year != end.year) {
            date = start.toString() + " - " + end.toString();
        } else if (start.month != end.month) {
            date = start.toStringNoYear() + " - " + end.toString();
        } else if (start.getDay() != end.getDay()) {
            date = start.toStringNoYear() + " - " + end.toStringNoMonth();
        } else {
            date = start.toString();
        }
        return date;
    }

    /**
     * Obtiene la diferencia en días entre las fechas de inicio y fin.
     *
     * @return La diferencia en días.
     */
    public long getDifferenceDays() {
        long diff = dateEnd.getTime() - dateStart.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Clase interna que representa una fecha con día, mes y año.
     */
    private class ModelDate {

        private Date date;
        private int day;
        private int month;
        private int year;

        /**
         * Constructor que inicializa ModelDate con una fecha dada.
         *
         * @param date La fecha a partir de la cual se inicializarán los valores.
         */
        public ModelDate(Date date) {
            this.date = date;
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            day = cal.get(Calendar.DATE);
            month = cal.get(Calendar.MONTH) + 1;
            year = cal.get(Calendar.YEAR);
        }

        /**
         * Obtiene la fecha.
         *
         * @return La fecha.
         */
        public Date getDate() {
            return date;
        }

        /**
         * Establece la fecha.
         *
         * @param date La fecha a establecer.
         */
        public void setDate(Date date) {
            this.date = date;
        }

        /**
         * Obtiene el día.
         *
         * @return El día.
         */
        public int getDay() {
            return day;
        }

        /**
         * Establece el día.
         *
         * @param day El día a establecer.
         */
        public void setDay(int day) {
            this.day = day;
        }

        /**
         * Obtiene el mes.
         *
         * @return El mes.
         */
        public int getMonth() {
            return month;
        }

        /**
         * Establece el mes.
         *
         * @param month El mes a establecer.
         */
        public void setMonth(int month) {
            this.month = month;
        }

        /**
         * Obtiene el año.
         *
         * @return El año.
         */
        public int getYear() {
            return year;
        }

        /**
         * Establece el año.
         *
         * @param year El año a establecer.
         */
        public void setYear(int year) {
            this.year = year;
        }

        /**
         * Convierte la fecha a una representación de cadena en el formato "dd MMM, yyyy".
         *
         * @return La representación de cadena de la fecha.
         */
        @Override
        public String toString() {
            SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy");
            return df.format(date);
        }

        /**
         * Convierte la fecha a una representación de cadena sin incluir el año en el formato "dd MMM".
         *
         * @return La representación de cadena de la fecha sin el año.
         */
        public String toStringNoYear() {
            SimpleDateFormat df = new SimpleDateFormat("dd MMM");
            return df.format(date);
        }

        /**
         * Convierte la fecha a una representación de cadena sin incluir el mes en el formato "dd, yyyy".
         *
         * @return La representación de cadena de la fecha sin el mes.
         */
        public String toStringNoMonth() {
            SimpleDateFormat df = new SimpleDateFormat("dd, yyyy");
            return df.format(date);
        }
    }
}
