/**
 * Esta classe, GraficoDeBarrasDG, representa um gráfico de barras que exibe a entrada e saída de Itens de Distribuição Gratuita
 * por mês para um ano selecionado.
 */
public class GraficoDeBarrasDG extends GraficoBase {

    /**
     * Construtor da classe GraficoDeBarrasDG.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoDeBarrasDG(String anoSelecionado) {
        super("Entrada - DG", "Saida - DG",
              "Gráfico de Barras - Entrada e Saída de Itens de Distribuição Gratuita por Mês", anoSelecionado);
    }

    /**
     * Este método retorna o título do gráfico.
     * @return O título do gráfico.
     */
    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Itens de Distribuição Gratuita por Mês";
    }
}
