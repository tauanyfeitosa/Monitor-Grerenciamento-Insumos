import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
    // Declaração da variável para armazenar o arquivo CSV selecionado pelo usuário
    private static File selectedCSVFile;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                criarInterface();
            }
        });
    }

    private static void criarInterface() {
        JFrame mainFrame = new JFrame("Monitor de Gerenciamento de Insumos");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BotoesManager botoesManager = new BotoesManager(mainFrame);
        
        botoesManager.adicionarBotao("Gráfico de Insumos (Medicamentos)", GraficoDeBarrasRemedio.class);
        
        botoesManager.adicionarBotao("Gráfico de Insumos (Itens de Atenção Básica)", GraficoDeBarrasIAB.class);
        
        botoesManager.adicionarBotao("Gráfico de Insumos (Vacinas - Crianças)", GraficoDeBarrasVacinasCriancas.class);
        
        botoesManager.adicionarBotao("Gráfico de Insumos (Vacinas - Adultos)", GraficoDeBarrasVacinas.class);

        botoesManager.adicionarBotao("Gráfico de Insumos (Testes Rápidos)", GraficoDeBarrasTR.class);

        botoesManager.adicionarBotao("Gráfico de Insumos (Itens de Distribuição Gratuita)", GraficoDeBarrasDG.class);

        JPanel buttonPanel = botoesManager.getPanel(); // Obtenha o painel do BotoesManager

        JButton selecionarCSVButton = new JButton("Selecionar Arquivo CSV");
        selecionarCSVButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedCSVFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Arquivo CSV selecionado: " + selectedCSVFile.getName());

                    // Copia o arquivo selecionado para a raiz do projeto
                    try {
                        Files.copy(selectedCSVFile.toPath(), new File(selectedCSVFile.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(null, "Arquivo CSV copiado para a raiz do projeto.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao copiar o arquivo CSV para a raiz do projeto.");
                    }
                }
            }
        });

        buttonPanel.add(selecionarCSVButton);
        
        mainFrame.add(buttonPanel);
        mainFrame.pack();
        mainFrame.setSize(800, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
