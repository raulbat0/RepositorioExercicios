/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituracaracteres;
/**
 *
 * @author Raúl
 */
public class Main {

    public static void main(String[] args) {
        /*
         * Para executar, altere a Stream abaixo (aAbBABacafe) para a Stream de desejada.
         */
        char vogal = LocalizaVogal.primeiraLetra(new CharStream("aAbBABacafe"));
        if (vogal != LocalizaVogal.VAZIO) {
            System.out.println("\nCaracter encontrado: " + vogal);
        } else {
            System.out.println("\nCaracter nao localizado.");
        }
    }
}
