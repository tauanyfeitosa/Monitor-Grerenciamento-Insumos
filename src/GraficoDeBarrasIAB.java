/**
 * Esta classe, GraficoDeBarrasIAB, representa um gráfico de barras que exibe a entrada e saída de Itens de Atenção Básica
 * por mês para um ano selecionado.
 */
public class GraficoDeBarrasIAB extends GraficoBase {

    /**
     * Construtor da classe GraficoDeBarrasIAB.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoDeBarrasIAB(String anoSelecionado) {
        super("Entrada - IAB", "Saida - IAB",
              "Gráfico de Barras - Entrada e Saída de Itens de Atenção Básica por Mês", anoSelecionado);
    }

    /**
     * Este método retorna o título do gráfico.
     * @return O título do gráfico.
     */
    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Itens de Atenção Básica por Mês";
    }
}
