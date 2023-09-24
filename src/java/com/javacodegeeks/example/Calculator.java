package com.javacodegeeks.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JTextField resultsTxt;
    private JButton clearBtn;
    private JButton signBtn;
    private JButton percentBtn;
    private JButton divideBtn;
    private JButton sevenBtn;
    private JButton eightBtn;
    private JButton nineBtn;
    private JButton multiplyBtn;
    private JButton fourBtn;
    private JButton fiveBtn;
    private JButton sixBtn;
    private JButton minusBtn;
    private JButton oneBtn;
    private JButton twoBtn;
    private JButton threeBtn;
    private JButton addBtn;
    private JButton zeroBtn;
    private JButton equalBtn;
    private JButton digitBtn;
    private JPanel calculatorView;
    private Double leftOperand;
    private Double rightOperand;
    private Operation calcOperation;

    public Calculator() {

        sevenBtn.addActionListener(new NumberBtnClicked(sevenBtn.getText()));
        eightBtn.addActionListener(new NumberBtnClicked(eightBtn.getText()));
        nineBtn.addActionListener(new NumberBtnClicked(nineBtn.getText()));
        fourBtn.addActionListener(new NumberBtnClicked(fourBtn.getText()));
        fiveBtn.addActionListener(new NumberBtnClicked(fiveBtn.getText()));
        sixBtn.addActionListener(new NumberBtnClicked(sixBtn.getText()));
        oneBtn.addActionListener(new NumberBtnClicked(oneBtn.getText()));
        twoBtn.addActionListener(new NumberBtnClicked(twoBtn.getText()));
        threeBtn.addActionListener(new NumberBtnClicked(threeBtn.getText()));
        zeroBtn.addActionListener(new NumberBtnClicked(zeroBtn.getText()));

        percentBtn.addActionListener(new OperationBtnClicked(Operation.PERCENTAGE));
        multiplyBtn.addActionListener(new OperationBtnClicked(Operation.MULTIPLICATION));
        divideBtn.addActionListener(new OperationBtnClicked(Operation.DIVISION));
        minusBtn.addActionListener(new OperationBtnClicked(Operation.SUBTRACTION));
        addBtn.addActionListener(new OperationBtnClicked(Operation.ADDITION));
        equalBtn.addActionListener(new EqualBtnClicked());
        clearBtn.addActionListener(new ClearBtnClicked());
        signBtn.addActionListener(new SignBtnClicked());
        digitBtn.addActionListener(new DigitBtnClicked());
    }

    // Klasse für Number Buttons die geklickt wurden
    // ActionListener brauchen wir für die actionPerformed Methode
    private class NumberBtnClicked implements ActionListener {
        // der Value string speichert den Wert der geklickt wurde
        private String value;
        // constructor
        public NumberBtnClicked(String value) {
            this.value = value;
        }

        // override für die actionPerformed Methode
        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             wenn der leftOperand null ist dann wird value auf value gesetzt
             das resultsTxt.getText() + value ist hier aber eine Art Sicherheitsmaßnahme die auch dann den
             Wert setzt wenn man als erstes 0 eingegeben hat
             zum Beispiel -> erste Eingabe ist 0 und zweite Eingabe ist 10, dann wird das Ergebnis trotzdem
             10.
             Der Code der Seite https://examples.javacodegeeks.com/ ist hier in dieser Funktion falsch, man kann
             nicht 0 mit einer anderen Zahl addieren. Bei diesem Code kann man es.
            */
            if(leftOperand == null) {
                value = resultsTxt.getText() + value;
            } else if (leftOperand == 0.0) {
                rightOperand = Double.valueOf(value);
            } else{
                rightOperand = Double.valueOf(value);
            }

            resultsTxt.setText(value);

        }
    }

    private class OperationBtnClicked implements ActionListener {

        private Operation operation;

        public OperationBtnClicked(Operation operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcOperation = operation;
            leftOperand = Double.valueOf(resultsTxt.getText());
        }
    }

    private class ClearBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsTxt.setText("");
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class DigitBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsTxt.setText(resultsTxt.getText() + ".");

        }
    }

    private class EqualBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             zuerst wird sich der Operator der gewählt wurde geholt und dann die "applyAsDouble" Methode
             aufgerufen die aus der DoubleBinaryOperator Library kommt. Diese Methode rechnet den leftOperand
             mit dem rightOperand je nach Operator.
            */
            Double output = calcOperation.getOperator().applyAsDouble(leftOperand, rightOperand);

            /*
             setzt den resultsTxt auf den Wert von Output - wenn der Output keine Kommazahl ist
             wird er als Integer zu einem String konvertiert und wenn es eine Kommzahl ist dann
             ganz normal.
             Sinn dahinter ist das dann auch eine ganze Zahl ohne Kommastellen als Kommazahl (double)
             angezeigt werden würde. So wird es zu einem Integer konvertiert und auch als Ganzzahl dargestellt.
             */
            resultsTxt.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));

            // left und rightOperand werden auf 0 gesetzt
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class SignBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsTxt.setText("-"+ resultsTxt.getText());
        }
    }

    public static void main(String[] args) {
        // erstellt einen unsichtbaren Frame mit dem Titel "Calculator"
        JFrame frame = new JFrame("Calculator");

        // ersetzt den Content des frame's mit dem erstellen JPanel
        frame.setContentPane(new Calculator().calculatorView);

        // wenn man auf den schließen button drückt wird das programm geschlossen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setzt die größe des Fensters so das die Elemente genau rein passen
        frame.pack();

        // frame wird sichtbar
        frame.setVisible(true);
    }
}
