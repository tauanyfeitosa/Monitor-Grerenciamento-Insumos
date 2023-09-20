class GraficoDeBarrasRemedio extends GraficoBase {
    public GraficoDeBarrasRemedio() {
        super("Entrada - Remedios", "Saida - Remedios", "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês", "Mês (de jun de 2022 a mai de 2023)");
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês";
    }
}

