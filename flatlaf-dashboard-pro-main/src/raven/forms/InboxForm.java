package raven.forms;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import javax.swing.JOptionPane;
import raven.chart.data.pie.DefaultPieDataset;
import raven.components.SimpleForm;
import raven.menu.FormManager;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Esta clase representa un formulario de ingreso y gestión de gastos.
 * Proporciona una interfaz gráfica para que los usuarios ingresen información
 * sobre sus gastos y realicen acciones como agregar gastos a una gráfica de
 * pastel o exportar datos a archivos PDF y CSV.
 *
 * @author Esteban
 */
public class InboxForm extends SimpleForm {

    // Referencia al formulario del panel de control (DashboardForm)
    private DashboardForm dashboardForm;

    /**
     * Constructor de la clase InboxForm. Inicializa los componentes de la
     * interfaz y la instancia de DashboardForm.
     */
    public InboxForm() {
        initComponents();
        // Inicializa la instancia de DashboardForm aquí
        dashboardForm = new DashboardForm();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        agregarRazon = new textfield.TextField();
        gasto = new textfield.TextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        agregarGastoALaGrafica = new button.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        exportarPDF = new button.Button();
        ExportarCsv = new button.Button();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        agregarRazon.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        agregarRazon.setShadowColor(new java.awt.Color(51, 51, 255));

        gasto.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        gasto.setShadowColor(new java.awt.Color(51, 51, 255));

        jComboBox1.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Familia", "Trabajo", "Ocio", "Mascotas", "Salud", "Viajes" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Roboto", 3, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        agregarGastoALaGrafica.setBackground(new java.awt.Color(29, 162, 253));
        agregarGastoALaGrafica.setForeground(new java.awt.Color(245, 245, 245));
        agregarGastoALaGrafica.setText("AGREGAR GASTO");
        agregarGastoALaGrafica.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        agregarGastoALaGrafica.setRippleColor(new java.awt.Color(255, 255, 255));
        agregarGastoALaGrafica.setShadowColor(new java.awt.Color(29, 162, 253));
        agregarGastoALaGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGastoALaGraficaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        jLabel1.setText("AGREGAR RAZON");

        jLabel2.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        jLabel2.setText("AGREGAR GASTO");

        jLabel3.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        jLabel3.setText("CATEGORIA");

        exportarPDF.setBackground(new java.awt.Color(29, 162, 253));
        exportarPDF.setForeground(new java.awt.Color(245, 245, 245));
        exportarPDF.setText("EXPORTAR PDF");
        exportarPDF.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        exportarPDF.setRippleColor(new java.awt.Color(255, 255, 255));
        exportarPDF.setShadowColor(new java.awt.Color(29, 162, 253));
        exportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarPDFActionPerformed(evt);
            }
        });

        ExportarCsv.setBackground(new java.awt.Color(29, 162, 253));
        ExportarCsv.setForeground(new java.awt.Color(245, 245, 245));
        ExportarCsv.setText("EXPORTAR CSV");
        ExportarCsv.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        ExportarCsv.setRippleColor(new java.awt.Color(255, 255, 255));
        ExportarCsv.setShadowColor(new java.awt.Color(29, 162, 253));
        ExportarCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportarCsvActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 3, 12)); // NOI18N
        jLabel4.setText("1. Ingresa el importe gastado y la razon de tu gasto.");

        jLabel5.setFont(new java.awt.Font("Roboto", 3, 12)); // NOI18N
        jLabel5.setText("2. Ingresa la categoria a la que pertenece tu gasto.");

        jLabel6.setFont(new java.awt.Font("Roboto", 3, 12)); // NOI18N
        jLabel6.setText("3. Registra tu gasto.");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/resources/image/gastos.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto", 3, 12)); // NOI18N
        jLabel8.setText("4. Mira tu registro en la grafica y exportarlo a PDF o CSV");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(agregarRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(gasto, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ExportarCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exportarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarGastoALaGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(338, 338, 338)
                                .addComponent(jLabel2))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(339, 339, 339)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gasto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregarGastoALaGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 34, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExportarCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Maneja el evento cuando el usuario hace clic en el botón "Agregar Gasto".
     * Obtiene la información ingresada, la valida y la agrega a la gráfica de
     * pastel. También muestra un mensaje de éxito y abre el formulario
     * DashboardForm.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void agregarGastoALaGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGastoALaGraficaActionPerformed
        // Obtener el contenido de las variables
        String textoGasto = gasto.getText();
        String textoRazon = agregarRazon.getText();
        String textoCategoria = (String) jComboBox1.getSelectedItem();

        // Verificar si los campos están vacíos
        if (textoGasto.isEmpty() || textoRazon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese tanto la razón como el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si los campos están vacíos
        }

        try {
            // Intentar convertir el texto de gasto a un número
            double gastoDouble = Double.parseDouble(textoGasto);

            // Mostrar el contenido en el jTextArea1
            jTextArea1.append("Razon: " + textoRazon + "\n");
            jTextArea1.append("Gasto: " + textoGasto + "\n");
            jTextArea1.append("Categoria: " + textoCategoria + "\n");
            jTextArea1.append("-----------------------------------" + "\n");

            // Crear un nuevo conjunto de datos para la gráfica pieChart con los datos del usuario
            DefaultPieDataset<String> usuarioDataset = new DefaultPieDataset<>();
            usuarioDataset.setValue(textoRazon, gastoDouble);

            // Llamar al método actualizado y pasar la categoría y el conjunto de datos
            dashboardForm.actualizarGraficaPie(textoCategoria, usuarioDataset);
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Datos agregados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Mostrar el formulario DashboardForm
            FormManager.showForm(dashboardForm);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_agregarGastoALaGraficaActionPerformed
    /**
     * Maneja el evento cuando el usuario hace clic en el botón "Exportar a
     * PDF". Obtiene el contenido del área de texto, crea un documento PDF y lo
     * guarda. Muestra un mensaje de éxito o error según el resultado.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void exportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarPDFActionPerformed
        // Obtener el contenido de jTextArea1
        String contenido = jTextArea1.getText();

        if (contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para exportar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Configurar el archivo PDF de salida
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileToSave + ".pdf")) {
                // Crear un documento PDF
                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, fileOutputStream);
                document.open();

                // Agregar contenido al PDF
                Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD, new BaseColor(0, 102, 204)); // Azul oscuro
                Paragraph titulo = new Paragraph();

                titulo.add(new Chunk(" Registro De Gastos", fontTitulo));
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);

                document.add(Chunk.NEWLINE); // Espacio en blanco

                // Crear tabla para el contenido de jTextArea1
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setWidths(new int[]{3, 3, 3});

                // Encabezados de la tabla (con color diferente)
                PdfPCell cell = new PdfPCell(new Phrase("Razon"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(217, 217, 217)); // Gris claro
                Font bf = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE); // Specify font size and style
                cell.setPhrase(new Phrase("Razon", bf)); // Set the font for the text in the cell
                cell.setBackgroundColor(BaseColor.YELLOW); // Set background color
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Gasto"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(217, 217, 217)); // Gris claro
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Categoría"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(217, 217, 217)); // Gris claro
                table.addCell(cell);

                // Contenido de jTextArea1
                String[] lines = contenido.split("\n");

                for (String line : lines) {
                    String[] data = line.split(": ");
                    if (data.length == 2) {
                        // Categorías (con otro color)
                        if (data[0].equals("Categoria")) {
                            cell = new PdfPCell(new Phrase(data[1]));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBackgroundColor(new BaseColor(240, 240, 240)); // Gris más claro
                            table.addCell(cell);
                        } else {
                            table.addCell(data[1]);
                        }
                    }
                }

                document.add(table);

                // Cerrar el documento y mostrar mensaje de éxito
                document.close();
                JOptionPane.showMessageDialog(this, "PDF exportado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException | DocumentException e) {
                JOptionPane.showMessageDialog(this, "Error al exportar a PDF.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_exportarPDFActionPerformed
    /**
     * Maneja el evento cuando el usuario hace clic en el botón "Exportar a
     * CSV". Obtiene el contenido del área de texto, crea un archivo CSV y lo
     * guarda. Muestra un mensaje de éxito o error según el resultado.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void ExportarCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportarCsvActionPerformed
        // Obtener el contenido de jTextArea1
        String contenido = jTextArea1.getText();

        if (contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para exportar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Configurar el archivo Excel de salida
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Excel");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOutputStream = new FileOutputStream(fileToSave + ".xlsx")) {

                // Crear una hoja en el libro de Excel
                Sheet sheet = workbook.createSheet("Registro de Gastos");

                // Crear encabezados en la primera fila
                Row headerRow = sheet.createRow(0);
                String[] headers = {"Razon", "Gasto", "Categoria"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }

                // Contenido de jTextArea1
                String[] lines = contenido.split("\n");

                // Llenar el resto de las filas con los datos
                int rowNum = 1;
                Row row = null;

                for (String line : lines) {
                    // Ignorar líneas que no tienen el formato esperado
                    if (line.startsWith("Razon:") || line.startsWith("Gasto:") || line.startsWith("Categoria:")) {
                        String[] data = line.split(": ");
                        if (data.length == 2) {
                            if (data[0].equals("Categoria")) {
                                // Categorías
                                row.createCell(2).setCellValue(data[1]);
                            } else if (data[0].equals("Gasto")) {
                                // Gastos
                                row.createCell(1).setCellValue(data[1]);
                            } else if (data[0].equals("Razon")) {
                                // Razones
                                if (row != null) {
                                    sheet.createRow(rowNum++);
                                }
                                row = sheet.createRow(rowNum++);
                                row.createCell(0).setCellValue(data[1]);
                            }
                        }
                    }
                }

                // Escribir el libro de Excel al archivo
                workbook.write(fileOutputStream);

                JOptionPane.showMessageDialog(this, "Excel exportado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al exportar a Excel.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ExportarCsvActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button ExportarCsv;
    private button.Button agregarGastoALaGrafica;
    private textfield.TextField agregarRazon;
    private button.Button exportarPDF;
    private textfield.TextField gasto;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
