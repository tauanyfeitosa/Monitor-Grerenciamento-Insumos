class GraficoDeBarrasVacinas extends GraficoBase {
    public GraficoDeBarrasVacinas() {
        super("Entrada - Vacinas2", "Saida - Vacinas2", "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês", "Mês (de jun de 2022 a mai de 2023)");
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Vacinas (Adultos e Idosos) por Mês";
    }
}

