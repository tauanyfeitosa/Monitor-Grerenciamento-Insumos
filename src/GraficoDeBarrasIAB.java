class GraficoDeBarrasIAB extends GraficoBase {
    public GraficoDeBarrasIAB() {
        super("Entrada - IAB", "Saida - IAB", "Gráfico de Barras - Entrada e Saída de Itens de Atenção Básica por Mês", "Mês (de jun de 2022 a mai de 2023)");
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Itens de Atenção Básica por Mês";
    }
}