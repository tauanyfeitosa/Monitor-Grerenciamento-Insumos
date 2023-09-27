import org.jfree.chart.JFreeChart; // Importa a classe JFreeChart do pacote org.jfree.chart para criar gráficos.
import org.jfree.chart.ChartFactory; // Importa a classe ChartFactory do pacote org.jfree.chart para criar gráficos.
import org.jfree.chart.ChartPanel; // Importa a classe ChartPanel do pacote org.jfree.chart para exibir gráficos em uma interface gráfica.
import org.jfree.chart.plot.PlotOrientation; // Importa a classe PlotOrientation do pacote org.jfree.chart.plot para definir a orientação do gráfico.
import org.jfree.data.category.DefaultCategoryDataset; // Importa a classe DefaultCategoryDataset do pacote org.jfree.data.category para criar o conjunto de dados.
import javax.swing.*; // Importa o pacote javax.swing para criar interfaces gráficas.
import java.awt.*; // Importa o pacote java.awt para trabalhar com componentes gráficos.

/**
 * Esta classe abstrata, GraficoBase, serve como base para classes que criam gráficos usando JFreeChart.
 */
abstract class GraficoBase {
    private final String entradaColumn;
    private final String saidaColumn;
    private final String titulo;
    private final String eixoXLabel;
    private final String anoSelecionado; // Adicione o ano selecionado
    private final ManipulacaoCSV manipulacaoCSV;

    /**
     * Construtor da classe GraficoBase.
     * @param entradaColumn A coluna que contém os valores de entrada.
     * @param saidaColumn A coluna que contém os valores de saída.
     * @param titulo O título do gráfico.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoBase(String entradaColumn, String saidaColumn, String titulo, String anoSelecionado) {
        this.entradaColumn = entradaColumn;
        this.saidaColumn = saidaColumn;
        this.titulo = titulo;
        this.eixoXLabel = "Meses referente ao ano de " + anoSelecionado;
        this.anoSelecionado = anoSelecionado; // Inicialize o ano selecionado
        this.manipulacaoCSV = new ManipulacaoCSV(anoSelecionado, entradaColumn, saidaColumn);
    }

    /**
     * Este método cria um conjunto de dados para o gráfico.
     * @return Um DefaultCategoryDataset contendo os dados processados.
     */
    protected DefaultCategoryDataset createDataset() {
        return manipulacaoCSV.createDataset(); // Use o dataset retornado pela classe ManipulacaoCSV
    }

    /**
     * Este método cria o gráfico usando o conjunto de dados fornecido.
     * @param dataset O conjunto de dados para o gráfico.
     * @return Um objeto JFreeChart configurado com os dados.
     */
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

    /**
     * Este método abstrato deve ser implementado pelas classes filhas para obter o título do gráfico.
     * @return O título do gráfico.
     */
    protected abstract String getTitulo(); // Método abstrato

    /**
     * Este método exibe o gráfico em uma janela Swing.
     */
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
