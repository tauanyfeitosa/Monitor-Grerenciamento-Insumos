public class GraficoDeBarrasVacinas extends GraficoBase {
    public GraficoDeBarrasVacinas(String anoSelecionado) {
        super("Entrada - Vacinas2", "Saida - Vacinas2", 
        "Gráfico de Barras - Entrada e Saída de Vacinas (Adultos e Idosos) por Mês", anoSelecionado);
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Vacinas (Adultos e Idosos) por Mês";
    }
}

