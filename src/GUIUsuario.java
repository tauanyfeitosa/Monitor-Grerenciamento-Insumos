import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * A classe `GUIUsuario` é responsável por criar a interface gráfica do aplicativo
 * de monitoramento de gerenciamento de insumos a partir de um arquivo CSV.
 */
public class GUIUsuario {
    private static File selectedCSVFile; // O arquivo CSV selecionado pelo usuário.
    private static JComboBox<String> comboBoxAno; // Um componente de seleção de ano na interface.
    private static JFrame mainFrame; // A janela principal da interface.
    private static String anoSelecionado = ""; // O ano selecionado pelo usuário.
    private static JPanel graphButtonPanel; // Um painel para botões que geram gráficos.

    /**
     * Método principal para criar a GUI do aplicativo.
     */
    public static void criarGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame = new JFrame("Monitor de Gerenciamento de Insumos");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                criarInterface();
                if (comboBoxAno.getItemCount() > 0) {
                    anoSelecionado = (String) comboBoxAno.getItemAt(0);
                    atualizarGraficos(anoSelecionado);
                }
            }
        });
    }

    /**
     * Cria a interface gráfica do aplicativo.
     */
    private static void criarInterface() {
        Set<String> anosSet = new HashSet<>();

        // Lê o arquivo CSV para obter os anos únicos
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length > 0 && columns[0].matches("\\d{4}")) {
                    anosSet.add(columns[0]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converte os anos únicos em um array e ordena em ordem reversa
        String[] anos = anosSet.toArray(new String[0]);
        Arrays.sort(anos, Collections.reverseOrder());

        // Cria um combobox para selecionar o ano
        comboBoxAno = new JComboBox<>(anos);

        // Define um ouvinte de ação para o combobox
        comboBoxAno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anoSelecionado = (String) comboBoxAno.getSelectedItem();
                atualizarGraficos(anoSelecionado);
            }
        });

        JLabel labelAno = new JLabel("Selecione o Ano:");

        BotoesManager botoesManager = new BotoesManager(mainFrame);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = botoesManager.getPanel();

        JButton selecionarCSVButton = new JButton("Selecionar Arquivo CSV");

        // Define um ouvinte de ação para o botão "Selecionar Arquivo CSV"
        selecionarCSVButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedCSVFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Arquivo CSV selecionado: " + selectedCSVFile.getName());
        
                    try {
                        // Copia o arquivo CSV selecionado para a raiz do projeto com o nome "dados.csv"
                        Files.copy(selectedCSVFile.toPath(), new File("dados.csv").toPath(), StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(null, "Arquivo CSV copiado como 'dados.csv' para a raiz do projeto.");
                        atualizarComboBoxAno();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao copiar o arquivo CSV para a raiz do projeto.");
                    }
                }
            }
        });

        graphButtonPanel = new JPanel();
        graphButtonPanel.setLayout(new BoxLayout(graphButtonPanel, BoxLayout.X_AXIS));

        mainPanel.add(labelAno);
        mainPanel.add(comboBoxAno);
        mainPanel.add(selecionarCSVButton);
        mainPanel.add(graphButtonPanel);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setSize(800, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Atualiza o combobox de seleção de ano com os anos encontrados no arquivo CSV.
     */
    private static void atualizarComboBoxAno() {
        Set<String> anosSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedCSVFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length > 0 && columns[0].matches("\\d{4}")) {
                    anosSet.add(columns[0]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] anos = anosSet.toArray(new String[0]);
        Arrays.sort(anos, Collections.reverseOrder());
        comboBoxAno.removeAllItems();
        for (String ano : anos) {
            comboBoxAno.addItem(ano);
        }
    }

    /**
     * Atualiza os gráficos com base no ano selecionado.
     * @param anoSelecionado O ano selecionado pelo usuário.
     */
    private static void atualizarGraficos(String anoSelecionado) {
        BotoesManager botoesManager = new BotoesManager(mainFrame);

        // Adiciona botões para gerar gráficos com base no ano selecionado
        botoesManager.adicionarBotao("Gráfico de Insumos (Medicamentos)", GraficoDeBarrasRemedio.class, anoSelecionado);
        botoesManager.adicionarBotao("Gráfico de Insumos (Itens de Atenção Básica)", GraficoDeBarrasIAB.class, anoSelecionado);
        botoesManager.adicionarBotao("Gráfico de Insumos (Vacinas - Crianças)", GraficoDeBarrasVacinasCriancas.class, anoSelecionado);
        botoesManager.adicionarBotao("Gráfico de Insumos (Vacinas - Adultos)", GraficoDeBarrasVacinas.class, anoSelecionado);
        botoesManager.adicionarBotao("Gráfico de Insumos (Testes Rápidos)", GraficoDeBarrasTR.class, anoSelecionado);
        botoesManager.adicionarBotao("Gráfico de Insumos (Itens de Distribuição Gratuita)", GraficoDeBarrasDG.class, anoSelecionado);

        JPanel buttonPanel = botoesManager.getPanel();
        graphButtonPanel.removeAll();
        graphButtonPanel.add(buttonPanel);
        graphButtonPanel.revalidate();
        graphButtonPanel.repaint();
    }
}