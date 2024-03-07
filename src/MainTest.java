import com.zonni.model.Conta;

public class MainTest {

    public static void main(String[] args) {
        Conta conta = new Conta("Lucas", "0001", 43215324);
        conta.depositar(1000);
        conta.depositar(120);
        conta.depositar(270);
        conta.sacar(100);
        conta.sacar(100);
        conta.depositar(40);
        conta.sacar(20);
        conta.depositar(275);
        conta.sacar(1000);
        System.out.println("Restou = " + conta.getSaldo());
        System.out.println("Somatorio de transações = " + conta.calculaMovimentacao());
        System.out.println("Rendimento = " + conta.calculaRendimento());

    }
}