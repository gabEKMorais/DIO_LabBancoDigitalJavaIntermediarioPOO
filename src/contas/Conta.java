package contas;
public abstract class Conta {
    protected double saldo;
    protected String tipo;
    public Conta(double saldoInicial, String tipoConta) {
        saldo = saldoInicial;
        tipo = tipoConta;
    }
    public String depositar(double valor){
        return setSaldo(valor);
    };
    public String sacar(double valor){
        if (valor > getSaldo()){ return "Saldo insuficiente para saque."; }
        return setSaldo(-valor);
    };
    private double getSaldo() {
        return saldo;
    }
    private String setSaldo(double valor) {
        saldo += valor;
        return "Seu novo saldo na conta " + tipo + " é: " + saldo;
    }
    public String transferir(Conta contaDestino, double valorTransferencia){
        if (valorTransferencia > getSaldo()){ return "Saldo insuficiente para transferencia."; }
        setSaldo(-valorTransferencia);
        return ("Seu novo saldo na conta " + tipo + " é: " + saldo + "\n" + contaDestino.setSaldo(valorTransferencia));
    }
}