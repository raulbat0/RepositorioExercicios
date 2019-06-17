/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituracaracteres;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Raúl
 */
public class StreamTest {

    @Test
    public void notificaMaisCaracteresParaProcessar() {
        Stream stream = new CharStream("abcdef");
        assertThat(stream.hasNext(), is(equalTo(true)));
        assertThat(stream.getNext(), is(equalTo('a')));
    }

    @Test
    public void notificaCaracterNaoEncontrado() {
        Stream stream = new CharStream("Htsgasoisuosdu");
        while (stream.hasNext()) {
            stream.getNext();
        }
        assertThat(stream.hasNext(), is(equalTo(false)));
    }

    @Test
    public void processaTodosCaracteresDaStream() {
        String sequenciaCaracteres = "sunglass";
        char[] values = sequenciaCaracteres.toCharArray();

        Stream stream = new CharStream(sequenciaCaracteres);
        int i = 0;
        while (stream.hasNext()) {
            char actual = stream.getNext();
            assertThat(actual, is(equalTo(values[i++])));
        }
        assertThat(stream.hasNext(), is(equalTo(false)));
    }

    @Test(expected = StreamException.class)
    public void geraExcecaoQuandoStreamInvalida() {
        Stream stream = new CharStream("acb");
        for (int i = 0; i < 5; i++) {
            stream.getNext();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void geraExcecaoStreamInvalida() {
        CharStream caracterStream = new CharStream(null);
    }
}
