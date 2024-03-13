package com.zonni.window;

import com.zonni.error.NoContaBuilderException;
import com.zonni.error.NoUserBuilderException;
import com.zonni.model.Client;
import com.zonni.model.Conta;
import com.zonni.model.Conta_Commerce;
import com.zonni.model.Conta_Kids;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContaBuilder extends JFrame implements ActionListener {
    private JTextField nomeField, cpfField;
    private JComboBox typeField;
    private JButton criarButton, cancelarButton;

    private Client client;
    private Conta conta;

    private Object lock;

    public ContaBuilder(Object lock, Client client) {
        this.lock = lock;
        this.client = client;
        setTitle("Criar Usuário");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        JLabel clientLabel = new JLabel("Cliente (CPF):");
        JLabel cpfClientLabel = new JLabel(client.getCPF());

        JLabel nomeLabel = new JLabel("Agencia");
        nomeField = new JTextField();

        JLabel cpfLabel = new JLabel("Numero:");
        cpfField = new JTextField();

        JLabel tipoLabel = new JLabel("Tipo:");
        String[] tipos = {"Normal", "Kids", "E-Commerce"};
        typeField = new JComboBox<>(tipos);

        criarButton = new JButton("Criar");
        criarButton.addActionListener(this);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);

        add(clientLabel);
        add(cpfClientLabel);
        add(nomeLabel);
        add(nomeField);
        add(cpfLabel);
        add(cpfField);
        add(tipoLabel);
        add(typeField);
        add(cancelarButton);
        add(criarButton);

        setVisible(true);
    }

    // Método actionPerformed para lidar com eventos dos botões
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == criarButton) {
            // Lógica para criar o usuário com os dados fornecidos
            String agencia = nomeField.getText();
            String numero = cpfField.getText();
            int typeIndex = typeField.getSelectedIndex();
            if(!agencia.isBlank() || !numero.isBlank()) {
                switch (typeIndex) {
                    case 0: this.conta = new Conta(client, agencia, Integer.parseInt(numero));
                    break;
                    case 1: this.conta = new Conta_Kids(client, agencia, Integer.parseInt(numero));
                    break;
                    case 2: this.conta = new Conta_Commerce(client, agencia, Integer.parseInt(numero));
                    break;
                    default:
                        throw new RuntimeException("Type conte not exists");
                }

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

    public Conta getConta() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(conta == null)
            throw new NoContaBuilderException("User not builded");
        return conta;
    }

}
