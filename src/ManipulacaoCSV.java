import java.io.IOException; // Importa a classe IOException para tratamento de exceções de E/S (entrada/saída).
import java.nio.file.Paths; // Importa a classe Paths do pacote java.nio.file para manipulação de caminhos de arquivo.
import java.util.ArrayList; // Importa a classe ArrayList do pacote java.util para criar uma lista de registros.
import java.util.Collections; // Importa a classe Collections do pacote java.util para ordenação de registros.
import java.util.Comparator; // Importa a classe Comparator do pacote java.util para definir uma regra de comparação personalizada.
import java.util.List; // Importa a classe List do pacote java.util para trabalhar com listas.
import java.util.TreeMap; // Importa a classe TreeMap do pacote java.util para armazenar saldos por ano de forma ordenada.
import org.apache.commons.csv.CSVFormat; // Importa a classe CSVFormat do pacote org.apache.commons.csv para definir o formato de leitura do arquivo CSV.
import org.apache.commons.csv.CSVParser; // Importa a classe CSVParser do pacote org.apache.commons.csv para fazer parsing do arquivo CSV.
import org.apache.commons.csv.CSVRecord; // Importa a classe CSVRecord do pacote org.apache.commons.csv para representar um registro do CSV.
import org.jfree.data.category.DefaultCategoryDataset; // Importa a classe DefaultCategoryDataset do pacote org.jfree.data.category para criar o conjunto de dados.

/**
 * Esta classe, ManipulacaoCSV, é responsável por ler um arquivo CSV, processar seus dados
 * e criar um conjunto de dados para uso posterior.
 */
public class ManipulacaoCSV {
    
    // Atributos para armazenar o ano selecionado, a coluna de entrada e a coluna de saída
    protected String anoSelecionado;
    protected String entradaColumn;
    protected String saidaColumn;

    /**
     * Construtor da classe ManipulacaoCSV.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     * @param entradaColumn A coluna que contém os valores de entrada.
     * @param saidaColumn A coluna que contém os valores de saída.
     */
    public ManipulacaoCSV(String anoSelecionado, String entradaColumn, String saidaColumn){
        this.anoSelecionado = anoSelecionado;
        this.entradaColumn = entradaColumn;
        this.saidaColumn = saidaColumn;
    }

    /**
     * Este método cria um conjunto de dados a partir de um arquivo CSV.
     * @return Um DefaultCategoryDataset contendo os dados processados.
     */
    protected DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Parse do arquivo CSV
            CSVParser parser = CSVParser.parse(
                    Paths.get("dados.csv").toFile(),
                    java.nio.charset.StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader()
            );

            List<CSVRecord> records = new ArrayList<>(); // Criando uma lista de registros

            // Adicione os registros do CSVParser à lista
            for (CSVRecord record : parser) {
                records.add(record);
            }

            // Classifique os registros com base no ano
            Collections.sort(records, new Comparator<CSVRecord>() {
                @Override
                public int compare(CSVRecord record1, CSVRecord record2) {
                    String ano1 = record1.get("Ano");
                    String ano2 = record2.get("Ano");
                    return ano1.compareTo(ano2);
                }
            });

            // TreeMap para armazenar saldos por ano
            TreeMap<String, Integer> saldosPorAno = new TreeMap<>(); 
            int saldoAnterior = 0;

            // Processa os registros para calcular os saldos por ano
            for (CSVRecord record : records) {
                String ano = record.get("Ano");
                int entrada = Integer.parseInt(record.get(entradaColumn));
                int saida = Integer.parseInt(record.get(saidaColumn));

                saldoAnterior = saldoAnterior + entrada - saida;

                // Atualize o saldo no TreeMap com base no ano
                saldosPorAno.put(ano, saldoAnterior);
            }

            System.out.println(saldosPorAno);
            
            int contador = 0;
            int saldo = 0;

            // Processa os registros novamente para criar o conjunto de dados final
            for (CSVRecord record : records) {
                String ano = record.get("Ano");
                String mes = record.get("Mes");

                if (ano.equals(anoSelecionado)) {
                    int entrada = Integer.parseInt(record.get(entradaColumn));
                    int saida = Integer.parseInt(record.get(saidaColumn));
                    int anoAnterior = Integer.parseInt(ano) - 1;
                    String anoString = String.valueOf(anoAnterior);
                    contador += 1;

                    if (contador == 1 && saldosPorAno.containsKey(anoString)) {
                        saldo = saldosPorAno.get(anoString) + entrada - saida;
                    } else {
                        saldo = saldo + entrada - saida;
                    }

                    dataset.addValue(entrada, "Entrada", mes);
                    dataset.addValue(saida, "Saída", mes);
                    dataset.addValue(saldo, "Saldo", mes);

                    System.out.println(saldo);
                }
            }

            // Agora, você tem um TreeMap com anos como chaves e saldos correspondentes

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}
