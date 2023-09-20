class GraficoDeBarrasDG extends GraficoBase {
    public GraficoDeBarrasDG() {
        super("Entrada - DG", "Saida - DG", "Gráfico de Barras - Entrada e Saída de Medicamentos por Mês", "Mês (de jun de 2022 a mai de 2023)");
    }

    @Override
    protected String getTitulo() {
        return "Gráfico de Barras - Entrada e Saída de Itens de Distribuição Gratuita por Mês";
    }
}

