import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.nio.file.Paths;

public class GraficoDeBarrasTR extends GraficoBase {
    
    // Método para criar o conjunto de dados para o gráfico de barras
    @Override
    protected DefaultCategoryDataset createDataset() {
        // Cria um novo conjunto de dados para o gráfico de barras
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Lê o arquivo CSV com os dados
            CSVParser parser = CSVParser.parse(
                    Paths.get("dados.csv").toFile(),
                    java.nio.charset.StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader()
            );

            int saldo = 0;

            // Itera pelas linhas do arquivo CSV
            for (CSVRecord record : parser) {
                // Obtém o mês do CSV
                String mes = record.get("Mes");
                
                // Obtém a quantidade de entrada e converte para inteiro
                int entrada = Integer.parseInt(record.get("Entrada - TR"));

                // Obtém a quantidade de saída e converte para inteiro
                int saida = Integer.parseInt(record.get("Saida - TR"));

                saldo += entrada - saida;

                // Adiciona os dados ao dataset do gráfico
                dataset.addValue(entrada, "Entrada", mes);
                dataset.addValue(saida, "Saída", mes);
                dataset.addValue(saldo, "Saldo", mes);
                System.out.println(entrada);
                System.out.println(saida);
                System.out.println(saldo);
                System.out.println(mes);
            }
        } catch (IOException e) {
            // Em caso de erro na leitura do arquivo CSV, imprime o erro
            e.printStackTrace();
        }

        // Retorna o conjunto de dados preenchido
        return dataset;
    }

    // Método para criar o gráfico de barras com base no dataset
    @Override
    protected JFreeChart createChart(DefaultCategoryDataset dataset) {
        // Cria um gráfico de barras usando a biblioteca JFreeChart
        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico de Barras - Entrada e Saída de Testes Rápidos por Mês", // Título do gráfico
                "Mês (de jun de 2022 a mai de 2023)", // Rótulo do eixo X
                "Quantidade de Insumos", // Rótulo do eixo Y
                dataset, // Conjunto de dados
                PlotOrientation.VERTICAL, // Orientação do gráfico
                true, // Inclui legenda
                true, // Inclui tooltips
                false // Inclui URLs
        );

        // Retorna o gráfico gerado
        return chart;
    }

    // Método para obter o título do gráfico
    @Override
    protected String getTitulo() {
        // Retorna o título do gráfico
        return "Gráfico de Barras - Entrada e Saída de Testes Rápidos por Mês";
    }
}
