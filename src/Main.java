import java.util.InputMismatchException;
import java.util.Scanner;
import contas.ContaCorrente;
import contas.ContaPoupanca;
public class Main {
    public static String valoresAceitos(String aceitos) {
        return ("ERRO: Valor inválido! opções aceitas: " + aceitos + ".\nCancelando operação!");
    }
    public static void main(String[] args) {
        int operacao, contaUtilizada;
        double valor;
        ContaCorrente contaCorrente = new ContaCorrente(0);
        ContaPoupanca contaPoupanca =  new ContaPoupanca(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao sistema bancário! 0 para finalizar operações.");
        do {
            System.out.println("Digite a operação desejada: 1-Deposito 2-Saque 3-Transferencia(entre as contas)");
            try {
                operacao = scanner.nextInt();
                scanner.nextLine(); // para limpar a entrada
                System.out.println(
                    switch (operacao) {
                        case 0 -> { // Finalizar
                            yield "Finalizando operações";
                        }
                        case 1 -> { // Deposito
                            System.out.println("Selecione a conta para efetuar o Deposito: 1-Corrente 2-Poupança");
                            contaUtilizada = scanner.nextInt();
                            scanner.nextLine();
                            if (contaUtilizada == 1 || contaUtilizada == 2){
                                System.out.println("Digite o valor para depósito: ");
                                try { valor = scanner.nextDouble(); } 
                                catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    yield valoresAceitos("Número inteiro ou decimal");
                                }
                                scanner.nextLine();
                                yield (contaUtilizada == 1) ? contaCorrente.depositar(valor) : contaPoupanca.depositar(valor);
                            } else { yield "Opção inválida, cancelando operação."; }
                        }
                        case 2 -> { // Saque
                            System.out.println("Selecione a conta para efetuar o saque: 1-Corrente 2-Poupança");
                            contaUtilizada = scanner.nextInt();
                            scanner.nextLine();
                            if (contaUtilizada == 1 || contaUtilizada == 2){
                                System.out.println("Digite o valor para depósito: ");
                                try { valor = scanner.nextDouble(); } 
                                catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    yield valoresAceitos("Número inteiro ou decimal");
                                }
                                scanner.nextLine();
                                yield (contaUtilizada == 1) ? contaCorrente.sacar(valor) : contaPoupanca.sacar(valor);
                            } else { yield "Opção inválida, cancelando operação."; }
                        }
                        case 3 -> { // Transferencia
                            System.out.println("Selecione a conta para efetuar a transferencia(origem): 1-Corrente 2-Poupança");
                            contaUtilizada = scanner.nextInt();
                            scanner.nextLine();
                            if (contaUtilizada == 1 || contaUtilizada == 2){
                                System.out.println("Digite o valor para transferencia: ");
                                try { valor = scanner.nextDouble(); } 
                                catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    yield valoresAceitos("Número inteiro ou decimal");
                                } 
                                scanner.nextLine();
                                yield (contaUtilizada == 1) ? contaCorrente.transferir(contaPoupanca,valor) : contaPoupanca.transferir(contaCorrente,valor);
                            } else { yield "Opção inválida, cancelando operação."; }
                        }
                        default -> { yield "Operação inválida!"; }
                    }
                );
            } catch (InputMismatchException e) {
                scanner.nextLine(); // para limpar a entrada
                System.out.println(valoresAceitos("Número inteiro"));
                operacao = 1;
            }
        } while (operacao != 0);
        scanner.close();
    }
}