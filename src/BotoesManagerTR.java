import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesManagerTR extends BotoesManager {

    // Construtor que receba a janela principal como parâmetro
    public BotoesManagerTR(JFrame frame) {
        super(frame);
    }

    // Método para criar e adicionar um botão à GUI
    public void adicionarBotao(String nomeBotao) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraficoDeBarrasTR graficoBarras = new GraficoDeBarrasTR();
                graficoBarras.exibir();
            };
        };
        JButton botao = new JButton(nomeBotao); // Cria um novo botão
        botao.addActionListener(actionListener); // Adiciona o ouvinte de ação ao botão

        // Adicione o botão ao painel, não à janela principal
        super.getPanel().add(botao);
    }
}
