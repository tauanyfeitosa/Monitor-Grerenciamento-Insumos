public class GraficoDeBarrasDG extends GraficoBase {
    public GraficoDeBarrasDG(String anoSelecionado) {
        super("Entrada - DG", "Saida - DG", 
        "Gráfico de Barras - Entrada e Saída de Itens de Distribuição Gratuita por Mês", anoSelecionado);
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Itens de Distribuição Gratuita por Mês";
    }
}

