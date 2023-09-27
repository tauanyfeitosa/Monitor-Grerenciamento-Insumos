import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;

abstract class GraficoBase {
    private final String entradaColumn;
    private final String saidaColumn;
    private final String titulo;
    private final String eixoXLabel;
    private final String anoSelecionado; // Adicione o ano selecionado
    private final ManipulacaoCSV manipulacaoCSV;

    public GraficoBase(String entradaColumn, String saidaColumn, String titulo, String anoSelecionado) {
        this.entradaColumn = entradaColumn;
        this.saidaColumn = saidaColumn;
        this.titulo = titulo;
        this.eixoXLabel = "Meses referente ao ano de " + anoSelecionado;
        this.anoSelecionado = anoSelecionado; // Inicialize o ano selecionado
        this.manipulacaoCSV = new ManipulacaoCSV(anoSelecionado, entradaColumn, saidaColumn);
    }

    protected DefaultCategoryDataset createDataset() {
        return manipulacaoCSV.createDataset(); // Use o dataset retornado pela classe ManipulacaoCSV
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

    protected abstract String getTitulo(); // Método abstrato

    public void exibir() {
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