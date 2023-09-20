class GraficoDeBarrasVacinas1 extends GraficoBase {
    public GraficoDeBarrasVacinas1() {
        super("Entrada - Vacinas1", "Saida - Vacinas1", "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês", "Mês (de jun de 2022 a mai de 2023)");
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Vacinas (Crianças) por Mês";
    }
}

