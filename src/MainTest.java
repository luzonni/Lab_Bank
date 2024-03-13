import com.sun.tools.javac.Main;
import com.zonni.model.Client;
import com.zonni.model.Conta;
import com.zonni.window.ContaBuilder;
import com.zonni.window.UserBuilder;

import javax.swing.*;

public class MainTest {

    public MainTest() {
        UserBuilder userBuilder = new UserBuilder(this);
        Client client = userBuilder.getClient();
        ContaBuilder contaBuilder = new ContaBuilder(this, client);
        Conta conta = contaBuilder.getConta();
        System.out.println(conta);
    }

    public static void main(String[] args) {
        new MainTest();
    }
}