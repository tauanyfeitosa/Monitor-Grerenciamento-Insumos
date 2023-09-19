import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;

// Classe abstrata que serve como base para a criação de gráficos
abstract class GraficoBase {
    protected DefaultCategoryDataset dataset; // Conjunto de dados para o gráfico

    // Construtor da classe
    public GraficoBase() {
        dataset = createDataset(); // Inicializa o conjunto de dados chamando o método abstrato
    }

    // Método abstrato para criar o conjunto de dados (deve ser implementado nas classes filhas)
    protected abstract DefaultCategoryDataset createDataset();

    // Método abstrato para criar o gráfico (deve ser implementado nas classes filhas)
    protected abstract JFreeChart createChart(DefaultCategoryDataset dataset);

    // Método para exibir o gráfico em uma nova janela
    public void exibir() {
        JFrame frame = new JFrame(getTitulo()); // Cria uma nova janela com o título especificado
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a ação ao fechar a janela

        JFreeChart chart = createChart(dataset); // Cria o gráfico chamando o método abstrato
        ChartPanel chartPanel = new ChartPanel(chart); // Cria um painel para exibir o gráfico
        chartPanel.setPreferredSize(new Dimension(1400, 700)); // Define o tamanho do painel do gráfico
        frame.getContentPane().add(chartPanel); // Adiciona o painel do gráfico à janela

        frame.pack(); // Ajusta o tamanho da janela para se ajustar ao gráfico
        frame.setVisible(true); // Torna a janela visível
    }

    // Método abstrato para obter o título do gráfico (deve ser implementado nas classes filhas)
    protected abstract String getTitulo();
}
