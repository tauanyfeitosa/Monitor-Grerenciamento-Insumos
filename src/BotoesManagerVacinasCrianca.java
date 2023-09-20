import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesManagerVacinas1 extends BotoesManager {

    // Construtor que receba a janela principal como parâmetro
    public BotoesManagerVacinas1(JFrame frame) {
        super(frame);
    }

    // Método para criar e adicionar um botão à GUI
    public void adicionarBotao(String nomeBotao) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraficoDeBarrasVacinas1 graficoBarras = new GraficoDeBarrasVacinas1();
                graficoBarras.exibir();
            };
        };
        JButton botao = new JButton(nomeBotao); // Cria um novo botão
        botao.addActionListener(actionListener); // Adiciona o ouvinte de ação ao botão

        // Adicione o botão ao painel, não à janela principal
        super.getPanel().add(botao);
    }
}

