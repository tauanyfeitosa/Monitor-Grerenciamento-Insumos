public class GraficoDeBarrasIAB extends GraficoBase {
    public GraficoDeBarrasIAB(String anoSelecionado) {
        super("Entrada - IAB", "Saida - IAB", 
        "Gráfico de Barras - Entrada e Saída de Itens de Atenção Básica por Mês", anoSelecionado);
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Itens de Atenção Básica por Mês";
    }
}
