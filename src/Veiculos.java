import java.time.LocalDate;
import java.time.LocalTime;

public class Veiculos {

    private String Placa;
    private String Modelo;
    private int TamanhoVeiculo;
    private LocalTime HoraEntrada;
    private LocalTime HoraSaida;
    private Vagas Vagas;

    public Veiculos(String placa, String modelo, int tamanhoVeiculo, LocalTime horaEntrada, LocalTime horaSaida, Vagas vagas) {
        this.Placa = placa;
        this.Modelo = modelo;
        this.TamanhoVeiculo = tamanhoVeiculo;
        this.HoraEntrada = horaEntrada;
        this.HoraSaida = horaSaida;
        this.Vagas = vagas;
    }

    public Vagas getVagas() {
        return Vagas;
    }

    public void setVagas(Vagas vagas) {
        this.Vagas = vagas;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getTamanhoVeiculo() {
        return TamanhoVeiculo;
    }

    public String getTamanhoVeiculoDescricao() {
        switch (TamanhoVeiculo) {
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

    public void setTamanhoVeiculo(int tamanhoVeiculo) {
        TamanhoVeiculo = tamanhoVeiculo;
    }

    public LocalTime getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        HoraEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return HoraSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        HoraSaida = horaSaida;
    }


    @Override
    public String toString() {
        return String.format("Detalhe dos Veículos%n" +
                        "------------------------%n" +
                        "Placa= %s%n" +
                        "Modelo= %s%n" +
                        "TamanhoVeiculo= %s%n" +
                        "HoraEntrada= %s%n" +
                        "HoraSaida= %s%n" +
                        "Vaga Selecionada= %s%n" +
                        "------------------------%n",
                Placa, Modelo, getTamanhoVeiculoDescricao(), HoraEntrada, HoraSaida != null ? HoraSaida : "Não Registrada", Vagas);
    }
}

