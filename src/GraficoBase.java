import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.*;
import java.awt.*;

abstract class GraficoBase {
    private final String entradaColumn;
    private final String saidaColumn;
    private final String titulo;
    private final String eixoXLabel;

    protected GraficoBase(String entradaColumn, String saidaColumn, String titulo, String eixoXLabel) {
        this.entradaColumn = entradaColumn;
        this.saidaColumn = saidaColumn;
        this.titulo = titulo;
        this.eixoXLabel = eixoXLabel;
    }

    protected DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            CSVParser parser = CSVParser.parse(
                Paths.get("dados.csv").toFile(),
                java.nio.charset.StandardCharsets.UTF_8,
                CSVFormat.DEFAULT.withFirstRecordAsHeader()
            );

            int saldo = 0;

            for (CSVRecord record : parser) {
                String mes = record.get("Mes");

                int entrada = Integer.parseInt(record.get(entradaColumn));
                int saida = Integer.parseInt(record.get(saidaColumn));

                saldo += entrada - saida;

                dataset.addValue(entrada, "Entrada", mes);
                dataset.addValue(saida, "Saída", mes);
                dataset.addValue(saldo, "Saldo", mes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    protected JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
            titulo,
            eixoXLabel,
            "Quantidade de Insumos",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        return chart;
    }

    protected abstract String getTitulo();

    protected void exibir() {
        JFrame frame = new JFrame(getTitulo());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1400, 700));
        frame.getContentPane().add(chartPanel);

        frame.pack();
        frame.setVisible(true);
    }
}


