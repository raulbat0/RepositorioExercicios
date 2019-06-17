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
public class CharStream implements Stream {

    private final String stream;

    private final char[] letras;

    private int currentIndex;

    public CharStream(String stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Null stream");
        }
        this.stream = stream;
        this.letras = stream.toCharArray();
    }

    @Override
    public char getNext() {
        if (!hasNext()) {
            throw new StreamException();
        }
        return letras[currentIndex++];
    }

    @Override
    public boolean hasNext() {
        return currentIndex < letras.length;
    }

    @Override
    public String toString() {
        return stream;
    }
}
