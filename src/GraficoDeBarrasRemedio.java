/**
 * Esta classe, GraficoDeBarrasRemedio, representa um gráfico de barras que exibe a entrada e saída de Medicamentos
 * por mês para um ano selecionado.
 */
public class GraficoDeBarrasRemedio extends GraficoBase {

    /**
     * Construtor da classe GraficoDeBarrasRemedio.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoDeBarrasRemedio(String anoSelecionado) {
        super("Entrada - Remedios", "Saida - Remedios",
              "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês", anoSelecionado);
    }

    /**
     * Este método retorna o título do gráfico.
     * @return O título do gráfico.
     */
    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês";
    }
}
