public class GraficoDeBarrasRemedio extends GraficoBase {
    public GraficoDeBarrasRemedio(String anoSelecionado) {
        super("Entrada - Remedios", "Saida - Remedios", 
        "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês", anoSelecionado);
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês";
    }
}

