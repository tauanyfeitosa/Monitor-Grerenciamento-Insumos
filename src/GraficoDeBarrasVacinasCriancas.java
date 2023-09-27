public class GraficoDeBarrasVacinasCriancas extends GraficoBase {
    public GraficoDeBarrasVacinasCriancas(String anoSelecionado) {
        super("Entrada - Vacinas1", "Saida - Vacinas1", 
        "Gráfico de Barras - Entrada e Saída de Vacinas (Crianças) por Mês", anoSelecionado);
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Vacinas (Crianças) por Mês";
    }
}

