

public class Vagas {
    private int numVagas;
    private int TamanhoVaga;
    private boolean Disponibilidade;


    public Vagas(int numVagas, int tamanhoVagas, boolean disponibilidade) {
        this.numVagas = numVagas;
        this.TamanhoVaga = tamanhoVagas;
        this.Disponibilidade = disponibilidade;
    }

    public int getNumVagas() {
        return numVagas;
    }

    public void setNumVagas(int numVagas) {
        this.numVagas = numVagas;
    }

    public int getTamanhoVaga() {
        return TamanhoVaga;
    }

    public String getTamanhoVagaDescricao() {
        switch (TamanhoVaga) {
            case 1:
                return "Pequeno";
                case 2:
                return "Médio";
            case 3:
                return "Grande";
            default:
                return "Desconhecido";
        }
    }

    public void setTamanhoVaga(int tamanhoVaga) {
        TamanhoVaga = tamanhoVaga;
    }

    public boolean isDisponibilidade() {
        return Disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        Disponibilidade = disponibilidade;
    }


    @Override
    public String toString() {

        String disponibilidadeDescricao = Disponibilidade ? "Disponível" : "Ocupada";

        return String.format("%nDetalhe das Vagas:%n" +
                "--------------------%n" +
                "Número da vaga= %d%n" +
                "Tamanho da Vaga= %s%n" +
                "Status= %s%n" +
                "--------------------", numVagas, getTamanhoVagaDescricao(), disponibilidadeDescricao);
    }
}
