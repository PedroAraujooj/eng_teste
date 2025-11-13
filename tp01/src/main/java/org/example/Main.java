package org.example;

import java.util.Scanner;

public class Main {
    public static String classificarIMC(double imc) {
        if (imc < 16.0) {
            return "Magreza grave";
        }
        else if (imc == 16.0 || imc < 17.0) {
            return "Magreza moderada";
        }
        else if (imc == 17.0 || imc < 18.5) {
            return "Magreza leve";
        }
        else if (imc == 18.5 || imc < 25.0) {
            return "Saudável";
        }
        else if (imc == 25.0 || imc < 30.0) {
            return "Sobrepeso";
        }
        else if (imc == 30.0 || imc < 35.0) {
            return "Obesidade Grau I";
        }
        else if (imc == 35.0 || imc < 40.0) {
            return "Obesidade Grau II";
        }
        else {
            return "Obesidade Grau III";
        }
    }

    public static double calcularPeso(double peso, double altura) {
        if(altura > 3.5 || altura < 0.5){
            throw new IllegalArgumentException("Erro: altura inválida. Entre com um valor entre 0.5 e 3.5 metros.");
        }
        if(peso > 500 || peso < 0){
            throw new IllegalArgumentException("Erro: peso inválido. Entre com um valor entre 0 e 500 quilogramas.");
        }
        return peso / (altura * altura);
    }

    public static void programaIMC(String versao) {
        double peso, altura, imc;
        Scanner pScan = new Scanner(System.in);
        Scanner aScan = new Scanner(System.in);

        System.out.println("===========================");
        System.out.printf("Cálculo do IMC - Versão %s\n", versao);
        System.out.println("===========================");

        System.out.print("Insira o seu peso em quilogramas: ");
        peso = Double.parseDouble(pScan.nextLine());

        System.out.print("Insira o sua altura em metros: ");
        altura = Double.parseDouble(aScan.nextLine());

        imc = calcularPeso(peso, altura);

        System.out.printf("\nSeu índice de massa corporal é: %.2f kg/m²\n", imc);
        System.out.printf("Classificação: %s.\n", classificarIMC(imc));
    }

    public static void main(String[] args) {
        String versao = "1.0";
        try {
            programaIMC(versao);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}