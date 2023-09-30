import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

/**
 * Esta classe, BotoesManager, é responsável por gerenciar a criação e ação de botões em um painel Swing.
 */
public class BotoesManager {
    private JPanel panel; // O painel que conterá os botões.
    private JFrame frame; // A janela onde o painel será exibido.

    /**
     * Construtor da classe BotoesManager.
     * @param frame A janela onde o painel será exibido.
     */
    public BotoesManager(JFrame frame) {
        this.frame = frame;
        this.panel = new JPanel(new GridLayout(4, 2)); // GridLayout com 4 linhas e 2 colunas para organizar os botões.
    }

    /**
     * Retorna o painel que contém os botões.
     * @return O painel Swing.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Adiciona um botão ao painel com um nome específico, uma classe específica e um ano selecionado.
     * @param nomeBotao O texto a ser exibido no botão.
     * @param classeEspecifica A classe que representa o tipo específico de gráfico.
     * @param anoSelecionado O ano selecionado para filtrar os dados do gráfico.
     */
    public void adicionarBotao(String nomeBotao, Class<?> classeEspecifica, String anoSelecionado) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Cria uma instância do tipo de gráfico específico com o ano selecionado e exibe o gráfico.
                    GraficoBase grafico = (GraficoBase) classeEspecifica.getDeclaredConstructor(String.class).newInstance(anoSelecionado);
                    grafico.exibir();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        JButton botao = new JButton(nomeBotao); // Cria um novo botão com o nome especificado.
        botao.addActionListener(actionListener); // Adiciona um ouvinte de ação ao botão.

        panel.add(botao); // Adicione o botão diretamente ao painel.
    }
}
