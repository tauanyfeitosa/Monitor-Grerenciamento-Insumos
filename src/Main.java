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
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2)); // GridLayout com 2 linhas e 1 coluna
        
        BotoesManagerRemedio botaoManagerRemedio = new BotoesManagerRemedio(mainFrame);
        botaoManagerRemedio.adicionarBotao("Gráfico de Insumos (Medicamentos)");
        
        BotoesManagerIAB botaoManagerIAB = new BotoesManagerIAB(mainFrame);
        botaoManagerIAB.adicionarBotao("Gráfico de Insumos (Itens de Atenção Básica)");

        BotoesManagerVacinas1 botaoManagerVacinas1 = new BotoesManagerVacinas1(mainFrame);
        botaoManagerVacinas1.adicionarBotao("Gráfico de Insumos (Vacinas - Crianças)");

        BotoesManagerVacinas2 botaoManagerVacinas2 = new BotoesManagerVacinas2(mainFrame);
        botaoManagerVacinas2.adicionarBotao("Gráfico de Insumos (Vacinas - Adultos)");

        BotoesManagerTR botaoManagerTR = new BotoesManagerTR(mainFrame);
        botaoManagerTR.adicionarBotao("Gráfico de Insumos (Testes Rápidos)");

        BotoesManagerDG botaoManagerDG = new BotoesManagerDG(mainFrame);
        botaoManagerDG.adicionarBotao("Gráfico de Insumos (Itens de Distribuição Gratuita)");

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
        buttonPanel.add(botaoManagerRemedio.getPanel()); // Adicione o painel de botões do manager remédio
        buttonPanel.add(botaoManagerIAB.getPanel()); // Adicione o painel de botões do manager IAB
        buttonPanel.add(botaoManagerVacinas1.getPanel());
        buttonPanel.add(botaoManagerVacinas2.getPanel());
        buttonPanel.add(botaoManagerTR.getPanel());
        buttonPanel.add(botaoManagerDG.getPanel());
        buttonPanel.add(selecionarCSVButton);
        
        mainFrame.add(buttonPanel); // Adicione o painel de botões à janela principal

        mainFrame.pack();
        mainFrame.setSize(800, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
