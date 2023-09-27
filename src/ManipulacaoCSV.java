import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jfree.data.category.DefaultCategoryDataset;

public class ManipulacaoCSV {
    
    protected String anoSelecionado;
    protected String entradaColumn;
    protected String saidaColumn;

    public ManipulacaoCSV(String anoSelecionado, String entradaColumn, String saidaColumn){
        this.anoSelecionado = anoSelecionado;
        this.entradaColumn = entradaColumn;
        this.saidaColumn = saidaColumn;
    }

    protected DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
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

            TreeMap<String, Integer> saldosPorAno = new TreeMap<>(); 
            int saldoAnterior = 0;

            for (CSVRecord record : records) {
                String ano = record.get("Ano");
                int entrada = Integer.parseInt(record.get(entradaColumn));
                int saida = Integer.parseInt(record.get(saidaColumn));

                saldoAnterior = saldoAnterior + entrada - saida;
                System.out.println(saldoAnterior);

                // Atualize o saldo no TreeMap com base no ano
                saldosPorAno.put(ano, saldoAnterior);
            }

            System.out.println(saldosPorAno);
            int contador = 0;
            int saldo = 0;

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
                }
            }

            // Agora, você tem um TreeMap com anos como chaves e saldos correspondentes

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }

}
