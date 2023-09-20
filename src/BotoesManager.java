import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class BotoesManager {
    private JPanel panel;
    private JFrame frame;

    public BotoesManager(JFrame frame) {
        this.frame = frame;
        this.panel = new JPanel(new GridLayout(4, 2)); // GridLayout com 2 linhas e 1 coluna
    }

    public JPanel getPanel() {
        return panel;
    }

    public void adicionarBotao(String nomeBotao, Class<?> classeEspecifica) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GraficoBase grafico = (GraficoBase) classeEspecifica.getDeclaredConstructor().newInstance();
                    grafico.exibir();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        JButton botao = new JButton(nomeBotao);
        botao.addActionListener(actionListener);
        
        panel.add(botao); // Adicione o botão diretamente ao painel
    }
}
