package com.zonni.window;

import com.zonni.error.NoUserBuilderException;
import com.zonni.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserBuilder extends JFrame implements ActionListener {
    private JTextField nomeField, cpfField, birthdayField;
    private JButton criarButton, cancelarButton;

    private Client client;

    private Object lock;

    public UserBuilder(Object lock) {
        this.lock = lock;
        setTitle("Criar Usuário");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel nomeLabel = new JLabel("Nome completo:");
        nomeField = new JTextField();

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JLabel birthdayLabel = new JLabel("Data de nascimento:");
        birthdayField = new JTextField();

        criarButton = new JButton("Criar");
        criarButton.addActionListener(this);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);

        add(nomeLabel);
        add(nomeField);
        add(cpfLabel);
        add(cpfField);
        add(birthdayLabel);
        add(birthdayField);
        add(cancelarButton);
        add(criarButton);

        setVisible(true);
    }

    // Método actionPerformed para lidar com eventos dos botões
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == criarButton) {
            // Lógica para criar o usuário com os dados fornecidos
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String birthday = birthdayField.getText();
            if(!nome.isBlank() || !cpf.isBlank() || !birthday.isBlank()) {
                this.client = new Client(nome, cpf, birthday);
            }
            synchronized(lock) {
                lock.notify();
            }
            dispose();
        } else if (e.getSource() == cancelarButton) {
            JOptionPane.showMessageDialog(this, "Não foi possivel criar o usuario!");
            synchronized(lock) {
                lock.notify();
            }
            dispose();
        }
    }

    public Client getClient() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(client == null)
            throw new NoUserBuilderException("User not builded");
        return client;
    }

}
