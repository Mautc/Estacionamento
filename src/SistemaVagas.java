import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class SistemaVagas {
    private static ArrayList<Vagas> ListaVagas = new ArrayList<>();
    private static ArrayList<Veiculos> ListaVeiculos = new ArrayList<>();

    private static String vermelho = "\u001B[31m";
    private static String verde = "\u001B[32m";
    private static String amarelo = "\u001B[33m";
    private static String reset = "\u001B[0m";

    public static void main(String[] args) {


        Menus();

    }

    public static void Menus() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nInsira O Número de Uma das Opções \n");
            System.out.println("[0] Sair do Sistema \n[1] Cadastro de Vagas \n[2] Entrada de Veículos \n[3] Saída de Veículos \n[4] Relatório dos Veículos \n[5] Histórico de Veículos");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    continuar = false;
                    System.out.println("Fechando o Sistema de Vagas...");
                    break;
                case 1:
                    CadastroVagas();
                    break;
                case 2:
                    EntradaVeiculos();
                    break;
                case 3:
                    SaidaVeiculos();
                    break;
                case 4:
                    RelatorioVeiculos();
                    break;
                case 5:
                    HistoricoVeiculos();
                    break;
            }
        }
    }

    public static void CadastroVagas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o número da Vaga");
        int numVaga = sc.nextInt();

        for (Vagas vaga : ListaVagas) {
            if (vaga.getNumVagas() == numVaga) {
                System.out.println("A Vaga Número" + vermelho + numVaga + reset + "Já Existe");
                return;
            }
        }

        System.out.println("Insira o Tamanho da Vaga");
        System.out.println("[1] Pequeno");
        System.out.println("[2] Médio");
        System.out.println("[3] Grande");
        int tamanhovaga = sc.nextInt();

        switch (tamanhovaga) {
            case 1: {
                System.out.println("Você Inseriu a Vaga Pequena");
                break;
            }
            case 2: {
                System.out.println("Você Inseriu a Vaga Média");
                break;
            }
            case 3: {
                System.out.println("Você Inseriu a Vaga Grande");
                break;
            }
            default: {
                Menus();
            }
        }

        boolean disponibilidade = true;

        Vagas vaga = new Vagas(numVaga, tamanhovaga, disponibilidade);
        ListaVagas.add(vaga);


        System.out.println("Vaga Cadastrada:" + vaga.toString());
    }

    public static void EntradaVeiculos() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira a Placa do Carro:");
        String placa = sc.nextLine();

        System.out.println("Insira o Modelo do Carro:");
        String modelo = sc.nextLine();

        System.out.println("Insira o Tamanho do Carro:");
        System.out.println("[1] Pequeno");
        System.out.println("[2] Médio");
        System.out.println("[3] Grande");
        int tamanhoVeiculo = sc.nextInt();
        sc.nextLine();

        System.out.println("Números De Vagas Disponíveis são:");
        boolean vagaDisponivel = false;
        for (Vagas vaga : ListaVagas) {
            if (vaga.getTamanhoVaga() == tamanhoVeiculo && vaga.isDisponibilidade()) {
                System.out.println(verde + vaga.getNumVagas() + reset);
                vagaDisponivel = true;
            }
        }
        if (!vagaDisponivel) {
            System.out.println("Nenhuma Vaga Disponível do tamanho " + vermelho + tamanhoVeiculo + reset);
            return;
        }

        System.out.println("Escolha o Número da sua Vaga");
        int escolhanumvaga = sc.nextInt();
        sc.nextLine();

        Vagas vagaSelecionada = null;
        for (Vagas vaga : ListaVagas) {
            if (vaga.getNumVagas() == escolhanumvaga) {
                vaga.setDisponibilidade(false);
                vagaSelecionada = vaga;
                System.out.println("Vaga " + vaga.getNumVagas() + " alterada para indisponível");
                break;
            }
        }

        LocalTime horadaentrada = null;
        while (horadaentrada == null) {
            System.out.println("Insira a Hora de Entrada do Carro (HH:mm):");
            String horaentrada = sc.nextLine();

            try {
                horadaentrada = LocalTime.parse(horaentrada);
                System.out.println("Hora de Entrada registrada:" + horadaentrada);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Por favor, insira no formato HH:mm.");
            }
        }
        Veiculos veiculos = new Veiculos(placa, modelo, tamanhoVeiculo, horadaentrada, null, vagaSelecionada);
        ListaVeiculos.add(veiculos);

        System.out.println("Veículo Cadastrado: " + veiculos.toString());
    }


    public static void SaidaVeiculos() {
        Scanner sc = new Scanner(System.in);

        boolean veiculoencontrado = false;
        String placa = "";

        while (!veiculoencontrado) {
            System.out.println("Insira a Placa do Carro");
            placa = sc.nextLine();


            for (Veiculos veiculos : ListaVeiculos) {
                if (veiculos.getPlaca().equals(placa)) {
                    veiculoencontrado = true;
                    break;
                }
            }

            if (!veiculoencontrado) {
                System.out.println("Veículo da Placa " + placa + " Não Encontrado!");
                break;
            }
        }


        System.out.println("Veículo da Placa " + placa + " Encontrado!");

        System.out.println("Insira a Hora da Saida do Veículo");
        String horasaida = sc.nextLine();

        LocalTime horadasaida = null;
        try {
            horadasaida = LocalTime.parse(horasaida);
            System.out.println("Hora da Saída Registrada " + horadasaida);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de hora inválido. Por favor, insira no formato HH:mm.");
            return;
        }


        for (Veiculos veiculo : ListaVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                veiculo.setHoraSaida(horadasaida);

                LocalTime HoraEntrada = veiculo.getHoraEntrada();
                LocalTime HoraSaida = veiculo.getHoraSaida();

                Duration duracao = Duration.between(HoraEntrada, HoraSaida);

                int horas = (int) (duracao.toMinutes() / 60.0);

                int minutos = (int) (duracao.toMinutes() % 60);

                System.out.printf("Tempo de Duração na Vaga são de: %s %d horas%s e %s%d minutos%s%n", amarelo, horas, reset, amarelo, minutos, reset);

                double pagarvalor;
                if (horas < 1) {
                    pagarvalor = 5.00;
                }
                if (horas > 1 && horas <= 3 && minutos == 0) {
                    pagarvalor = 10.00;
                }
                if (horas >= 3 && minutos > 0) {
                    pagarvalor = 15.00;
                } else {
                    System.out.println("O Programa detectou algum erro!!");
                    break;
                }

                System.out.printf("O Valor Total a pagar é: R$ %.2f", verde + pagarvalor + reset);
                break;
            }
        }
    }

    public static void RelatorioVeiculos() {
        System.out.println("\nRelatório de Vagas Ocupadas:\n");

        if (ListaVeiculos.size() == 0) {
            System.out.println("\nNão há veículos cadastrados.");
            return;
        }

        boolean vagaocupadaencontrada = false;
        for (Vagas vaga : ListaVagas) {
            if (vaga.isDisponibilidade()) {
                vagaocupadaencontrada = true;
                System.out.println("Vaga Ocupada: " + vermelho + vaga.getNumVagas() + reset + " Tamanho: " + amarelo + vaga.getTamanhoVagaDescricao() + reset);
            }
            for (Veiculos veiculo : ListaVeiculos) {
                if (veiculo.getVagas().getNumVagas() == vaga.getNumVagas()) {
                    System.out.println("Placa do Veículo: " + amarelo + veiculo.getPlaca() + reset);
                    break;
                }
            }
        }

        if (!vagaocupadaencontrada) {
            System.out.println("Nenhuma vaga está ocupada!");
        }
    }

    public static void HistoricoVeiculos() {
        System.out.println("\nHistórico de Permanência dos Veículos:");

        if (ListaVeiculos.size() == 0) {
            System.out.println("Não há veículos no histórico.");
            return;
        }

        for (Veiculos veiculo : ListaVeiculos) {
            LocalTime entrada = veiculo.getHoraEntrada();
            LocalTime saida = veiculo.getHoraSaida();

            if (saida != null) {
                Duration duracao = Duration.between(entrada, saida);
                double horas = duracao.toMinutes() / 60.0;
                double valorPago;


                if (horas <= 1) {
                    valorPago = 5.00;
                } else if (horas <= 3) {
                    valorPago = 10.00;
                } else {
                    valorPago = 15.00;
                }

                int numveiculo = 0;
                for (Veiculos veiculo2 : ListaVeiculos) {
                    numveiculo++;


                    System.out.printf("%n--------------------------------------------%n");
                    System.out.printf("Veiculo %s %d %s", verde, numveiculo, reset);
                    System.out.printf("\nPlaca do Veículo: %s%n", veiculo.getPlaca());
                    System.out.printf("Hora de Entrada: %s%n", entrada);
                    System.out.printf("Hora de Saída: %s%n", saida);
                    System.out.printf("Tempo de Permanência: %.2f horas%n", horas);
                    System.out.printf("Valor Pago: R$ %.2f%n", valorPago);
                    System.out.printf("%n--------------------------------------------%n");}
                } else{
                    System.out.printf("\nPlaca do Veículo: %s %s %s %s(ainda no estacionamento)%s%n", amarelo, veiculo.getPlaca(), reset, verde, reset);
                }
            }
        }

    }