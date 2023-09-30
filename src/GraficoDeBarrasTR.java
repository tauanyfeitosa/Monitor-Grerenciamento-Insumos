/**
 * Esta classe, GraficoDeBarrasTR, representa um gráfico de barras que exibe a entrada e saída de Testes Rápidos
 * por mês para um ano selecionado.
 */
public class GraficoDeBarrasTR extends GraficoBase {

    /**
     * Construtor da classe GraficoDeBarrasTR.
     * @param anoSelecionado O ano selecionado para filtrar os dados.
     */
    public GraficoDeBarrasTR(String anoSelecionado) {
        super("Entrada - TR", "Saida - TR",
              "Gráfico de Barras - Entrada e Saída de Testes Rápidos por Mês", anoSelecionado);
    }

    /**
     * Este método retorna o título do gráfico.
     * @return O título do gráfico.
     */
    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Testes Rápidos por Mês";
    }
}
