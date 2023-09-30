/**
 * Esta classe, GraficoDeBarrasVacinasCriancas, representa um gráfico de barras que exibe a entrada e saída de Vacinas (Crianças)
 * por mês para um ano selecionado.
 */
public class GraficoDeBarrasVacinasCriancas extends GraficoBase {

    /**
     * Construtor da classe GraficoDeBarrasVacinasCriancas.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoDeBarrasVacinasCriancas(String anoSelecionado) {
        super("Entrada - Vacinas1", "Saida - Vacinas1",
              "Gráfico de Barras - Entrada e Saída de Vacinas (Crianças) por Mês", anoSelecionado);
    }

    /**
     * Este método retorna o título do gráfico.
     * @return O título do gráfico.
     */
    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Vacinas (Crianças) por Mês";
    }
}
