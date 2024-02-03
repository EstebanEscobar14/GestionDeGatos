package raven.forms;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import raven.chart.ChartLegendRenderer;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.category.DefaultCategoryDataset;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.line.LineChart;
import raven.chart.pie.PieChart;
import raven.components.SimpleForm;
import raven.utils.DateCalculator;

/**
 * Clase que representa un formulario de panel de control (dashboard).
 * Este formulario contiene gráficos de pastel, gráficos de líneas y gráficos de barras.
 * Se utiliza para visualizar estadísticas y datos importantes.
 * 
 * @author Esteban
 */
public class DashboardForm extends SimpleForm {

    /**
     * Constructor de la clase DashboardForm. Inicializa y configura los
     * gráficos y datos iniciales.
     */
    //private DefaultPieDataset<String> datosActuales;
    public DashboardForm() {
        // Inicializar datosActuales
        //datosActuales = new DefaultPieDataset<>();
        init();
    }

    @Override
    public void formRefresh() {
        lineChart.startAnimation();
        pieChart1.startAnimation();
        pieChart2.startAnimation();
        pieChart3.startAnimation();
        pieChart4.startAnimation();
        pieChart5.startAnimation();
        pieChart6.startAnimation();
        barChart1.startAnimation();
        barChart2.startAnimation();
    }

    @Override
    public void formInitAndOpen() {
        System.out.println("Iniciar");
    }

    @Override
    public void formOpen() {
        System.out.println("Abrir");
    }

    /**
     * Inicializa y configura los componentes del formulario.
     */
    private void init() {
        setLayout(new MigLayout("wrap,fill,gap 10", "fill"));
        createPieChart();
        createLineChart();
        createBarChart();
    }

    /**
     * Crea los gráficos de pastel y los agrega al formulario.
     */
    private void createPieChart() {

        pieChart1 = new PieChart();
        JLabel header1 = new JLabel("Familia");
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart1.setHeader(header1);
        pieChart1.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart1.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart1.setDataset(createPieDataUno());
        add(pieChart1, "split 3,height 290");

        pieChart2 = new PieChart();
        JLabel header2 = new JLabel("Ocio");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart2.setHeader(header2);
        pieChart2.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart2.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart2.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart2.setDataset(createPieDataDos());
        add(pieChart2, "height 290");

        pieChart3 = new PieChart();
        JLabel header3 = new JLabel("Trabajo");
        header3.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart3.setHeader(header3);
        pieChart3.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart3.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart3.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart3.setDataset(createPieDataTres());
        add(pieChart3, "height 290");

        pieChart4 = new PieChart();
        JLabel header4 = new JLabel("Mascotas");
        header4.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart4.setHeader(header4);
        pieChart4.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart4.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart4.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart4.setDataset(createPieDataCuatro());
        add(pieChart4, "split 3,height 290");

        pieChart5 = new PieChart();
        JLabel header5 = new JLabel("Salud");
        header5.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart5.setHeader(header5);
        pieChart5.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart5.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart5.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart5.setDataset(createPieDataCinco());
        add(pieChart5, "height 290");

        pieChart6 = new PieChart();
        JLabel header6 = new JLabel("Viaje");
        header6.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart6.setHeader(header6);
        pieChart6.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart6.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart6.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart6.setDataset(createPieDataSeis());
        add(pieChart6, "height 290");
    }

    /**
     * Crea el gráfico de líneas y lo agrega al formulario.
     */
    private void createLineChart() {
        lineChart = new LineChart();
        lineChart.setChartType(LineChart.ChartType.LINE);
        lineChart.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        add(lineChart);
        createLineChartData();
    }

    /**
     * Crea el gráfico de barras y lo agrega al formulario.
     */
    private void createBarChart() {
        // BarChart 1
        barChart1 = new HorizontalBarChart();
        JLabel header1 = new JLabel("Ingreso Mensual");
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;"
                + "border:0,0,5,0");
        barChart1.setHeader(header1);
        barChart1.setBarColor(Color.decode("#f97316"));
        barChart1.setDataset(createData());
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        panel1.add(barChart1);
        add(panel1, "split 2,gap 0 20");

        // BarChart 2
        barChart2 = new HorizontalBarChart();
        JLabel header2 = new JLabel("Gasto Mensual");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;"
                + "border:0,0,5,0");
        barChart2.setHeader(header2);
        barChart2.setBarColor(Color.decode("#10b981"));
        barChart2.setDataset(createData());
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        panel2.add(barChart2);
        add(panel2);
    }

    /**
     * Crea un conjunto de datos de ejemplo para los gráficos de barras.
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createData() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        Random random = new Random();
        dataset.addValue("Julio", random.nextInt(100));
        dataset.addValue("Junio", random.nextInt(100));
        dataset.addValue("Mayo", random.nextInt(100));
        dataset.addValue("Abril", random.nextInt(100));
        dataset.addValue("Marzo", random.nextInt(100));
        dataset.addValue("Febrero", random.nextInt(100));
        return dataset;
    }

    /**
     * Crea un conjunto de datos de ejemplo para el gráfico de pastel "Familia".
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createPieDataUno() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.addValue("Razones", 0);
        return dataset;
    }

    /**
     * Crea un conjunto de datos de ejemplo para el gráfico de pastel "Ocio".
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createPieDataDos() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.addValue("Razones", 0);
        return dataset;
    }

    /**
     * Crea un conjunto de datos de ejemplo para el gráfico de pastel "Trabajo".
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createPieDataTres() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.addValue("Razones", 0);
        return dataset;
    }

    /**
     * Crea un conjunto de datos de ejemplo para el gráfico de pastel
     * "Mascotas".
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createPieDataCuatro() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.addValue("Razones", 0);
        return dataset;
    }

    /**
     * Crea un conjunto de datos de ejemplo para el gráfico de pastel "Salud".
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createPieDataCinco() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.addValue("Razones", 0);
        return dataset;
    }

    /**
     * Crea un conjunto de datos de ejemplo para el gráfico de pastel "Viaje".
     *
     * @return Conjunto de datos de ejemplo.
     */
    private DefaultPieDataset createPieDataSeis() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.addValue("Razones", 0);
        return dataset;
    }

    /**
     * Actualiza los datos de un gráfico de pastel con un conjunto de datos de
     * usuario.
     *
     * @param categoria Categoría del gráfico de pastel a actualizar.
     * @param usuarioDataset Conjunto de datos del usuario.
     */
    public void actualizarGraficaPie(String categoria, DefaultPieDataset<String> usuarioDataset) {
        // Crear una variable local datosActuales
        DefaultPieDataset<String> datosActuales = new DefaultPieDataset<>();

        // Copiar los datos existentes de la gráfica correspondiente a datosActuales
        switch (categoria) {
            case "Familia":
                datosActuales = pieChart1.getDataset();
                break;
            case "Trabajo":
                datosActuales = pieChart2.getDataset();
                break;
            case "Ocio":
                datosActuales = pieChart3.getDataset();
                break;
            case "Mascotas":
                datosActuales = pieChart4.getDataset();
                break;
            case "Salud":
                datosActuales = pieChart5.getDataset();
                break;
            case "Viajes":
                datosActuales = pieChart6.getDataset();
                break;
            default:
                break;
        }

        // Iterar sobre las claves en el conjunto de datos del usuario
        for (String clave : usuarioDataset.getKeys()) {
            double valorUsuario = usuarioDataset.getValue(clave).doubleValue();

            // Verificar si la clave ya existe en el conjunto de datos actual
            if (datosActuales.getIndex(clave) >= 0) {
                // La clave ya existe, actualizar el valor
                datosActuales.setValue(clave, datosActuales.getValue(clave).doubleValue() + valorUsuario);
            } else {
                // La clave no existe, agregar un nuevo valor
                datosActuales.setValue(clave, valorUsuario);
            }
        }

        // Asignar el conjunto de datos actualizado a la gráfica de pastel correspondiente
        switch (categoria) {
            case "Familia":
                pieChart1.setDataset(datosActuales);
                break;
            case "Trabajo":
                pieChart2.setDataset(datosActuales);
                break;
            case "Ocio":
                pieChart3.setDataset(datosActuales);
                break;
            case "Mascotas":
                pieChart4.setDataset(datosActuales);
                break;
            case "Salud":
                pieChart5.setDataset(datosActuales);
                break;
            case "Viajes":
                pieChart6.setDataset(datosActuales);
                break;
            default:
                break;
        }
    }

    /**
     * Crea el conjunto de datos para el gráfico de líneas con datos de ejemplo.
     */
    private void createLineChartData() {
        DefaultCategoryDataset<String, String> categoryDataset = new DefaultCategoryDataset<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        Random ran = new Random();
        int randomDate = 30;
        for (int i = 1; i <= randomDate; i++) {
            String date = df.format(cal.getTime());
            categoryDataset.addValue(ran.nextInt(700) + 5, "Ocio", date);
            categoryDataset.addValue(ran.nextInt(700) + 5, "Trabajo", date);
            categoryDataset.addValue(ran.nextInt(700) + 5, "Familia", date);
            categoryDataset.addValue(ran.nextInt(700) + 5, "Viajes", date);
            categoryDataset.addValue(ran.nextInt(700) + 5, "Mascota", date);
            categoryDataset.addValue(ran.nextInt(700) + 5, "Salud", date);

            cal.add(Calendar.DATE, 1);
        }

        /**
         * Control the legend we do not show all legend
         */
        try {
            Date date = df.parse(categoryDataset.getColumnKey(0));
            Date dateEnd = df.parse(categoryDataset.getColumnKey(categoryDataset.getColumnCount() - 1));

            DateCalculator dcal = new DateCalculator(date, dateEnd);
            long diff = dcal.getDifferenceDays();

            double d = Math.ceil((diff / 10f));
            lineChart.setLegendRenderer(new ChartLegendRenderer() {
                @Override
                public Component getLegendComponent(Object legend, int index) {
                    if (index % d == 0) {
                        return super.getLegendComponent(legend, index);
                    } else {
                        return null;
                    }
                }
            });
        } catch (ParseException e) {
            System.err.println(e);
        }

        lineChart.setCategoryDataset(categoryDataset);
        lineChart.getChartColor().addColor(Color.decode("#38bdf8"), Color.decode("#fb7185"), Color.decode("#34d399"));
        JLabel header = new JLabel("Datos En Linea");
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;"
                + "border:0,0,5,0");
        lineChart.setHeader(header);
    }

    private LineChart lineChart;
    private HorizontalBarChart barChart1;
    private HorizontalBarChart barChart2;
    private PieChart pieChart1;
    private PieChart pieChart2;
    private PieChart pieChart3;
    private PieChart pieChart4;
    private PieChart pieChart5;
    private PieChart pieChart6;

}
