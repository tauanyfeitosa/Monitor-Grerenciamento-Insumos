import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BotoesManager {
    private JPanel panel; // Adicione um campo para armazenar o painel;
    private JFrame frame;

    public BotoesManager(JFrame frame) {
        this.frame = frame;
        panel = new JPanel(); // Inicialize o painel no construtor
    }
    //Método para criar e adicionar um botão à GUI
    public void adicionarBotao(String nomeBotao) {

    }

    public JPanel getPanel() {
        return panel;
    }

}
