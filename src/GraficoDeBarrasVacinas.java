/**
 * Esta classe, GraficoDeBarrasVacinas, representa um gráfico de barras que exibe a entrada e saída de Vacinas (Adultos e Idosos)
 * por mês para um ano selecionado.
 */
public class GraficoDeBarrasVacinas extends GraficoBase {

    /**
     * Construtor da classe GraficoDeBarrasVacinas.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoDeBarrasVacinas(String anoSelecionado) {
        super("Entrada - Vacinas2", "Saida - Vacinas2",
              "Gráfico de Barras - Entrada e Saída de Vacinas (Adultos e Idosos) por Mês", anoSelecionado);
    }

    /**
     * Este método retorna o título do gráfico.
     * @return O título do gráfico.
     */
    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Vacinas (Adultos e Idosos) por Mês";
    }
}
