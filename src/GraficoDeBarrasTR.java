public class GraficoDeBarrasTR extends GraficoBase {
    public GraficoDeBarrasTR(String anoSelecionado) {
        super("Entrada - TR", "Saida - TR", 
        "Gráfico de Barras - Entrada e Saída de Testes Rápidos por Mês", anoSelecionado);
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Testes Rápidos por Mês";
    }
}

