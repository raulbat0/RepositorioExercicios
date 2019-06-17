/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituracaracteres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Raúl
 */
public class LocalizaVogal {

    public static final char VAZIO = ' ';
    private static final String REGEX_VOGAIS = "(?i)[aáàãâÁÀÃÂeéêÉÊiíÍoóõôÓÕÔuúÚ]";
    private static final String REGEX_CARACTER_ESPECIAL = "[^\\w]";
    private static final String DIGITO_REGEX = "\\d";

    private LocalizaVogal() {
    }

    public static char primeiraLetra(Stream input) {
        if (input == null) {
            throw new IllegalArgumentException("Null stream");
        }

        Map<Character, Boolean> vogaisEncontradas = new LinkedHashMap<>();
        Map<Character, List<Character>> predecessores = new HashMap<>();

        char letraAnterior = ' ';
        while (input.hasNext()) {
            char letraAtual = input.getNext();

            registraVogalCaracter(vogaisEncontradas, letraAtual);
            computaPredecessor(predecessores, letraAtual, letraAnterior);

            letraAnterior = letraAtual;
        }

        return localizaVogal(vogaisEncontradas, predecessores);
    }

    private static Character localizaVogal(Map<Character, Boolean> vogaisEncontradas,
            Map<Character, List<Character>> predecessores) {
        for (Map.Entry<Character, Boolean> ocorrenciaVogal : vogaisEncontradas.entrySet()) {
            if (!ocorrenciaVogal.getValue()) {
                continue;
            }

            for (Character vogalPredecessora : predecessores.get(ocorrenciaVogal.getKey())) {
                if (!isConsoante(vogalPredecessora)) {
                    continue;
                }

                for (Character consoantePredecessor : predecessores.get(vogalPredecessora)) {
                    if (isVogal(consoantePredecessor)) {
                        return ocorrenciaVogal.getKey();
                    }
                }
            }
        }
        return VAZIO;
    }

    private static void computaPredecessor(Map<Character, List<Character>> predecessores, char letraAtual, char letraAnterior) {
        List<Character> letras = predecessores.get(letraAtual);
        if (letras == null) {
            letras = new ArrayList<>();
            predecessores.put(letraAtual, letras);
        }
        if (letraAnterior != ' ') {
            letras.add(letraAnterior);
        }
    }

    private static void registraVogalCaracter(Map<Character, Boolean> vogaisEncontradas,char letraAtual) {
        if (!isVogal(letraAtual)) {
            return;
        }
        if (vogaisEncontradas.get(letraAtual) == null) {
            vogaisEncontradas.put(letraAtual, true);
        } else {
            vogaisEncontradas.put(letraAtual, false);
        }
    }

    private static boolean isVogal(char caracter) {
        return isVogal(String.valueOf(caracter));
    }

    private static boolean isConsoante(char vogalPredecessora) {
        String caracter = String.valueOf(vogalPredecessora);
        return !isVogal(caracter)
                && !isCaracterEspecial(caracter)
                && !isDigito(caracter);
    }

    private static boolean isVogal(String caracter) {
        return caracter.matches(REGEX_VOGAIS);
    }

    private static boolean isDigito(String caracter) {
        return caracter.matches(DIGITO_REGEX);
    }

    private static boolean isCaracterEspecial(String caracter) {
        return caracter.matches(REGEX_CARACTER_ESPECIAL);
    }
}
