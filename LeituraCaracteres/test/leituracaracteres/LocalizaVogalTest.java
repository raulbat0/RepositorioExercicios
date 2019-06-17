package leituracaracteres;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Raúl
 */
@RunWith(Parameterized.class)
public class LocalizaVogalTest {

    @Parameterized.Parameters(name = "input:{0} - output:{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new CharStream("aAbBABacafe"), 'e'},
                {new CharStream("AAAAAaaaa"), ' '},
                {new CharStream("afe"), 'e'},
                {new CharStream("cafezal"), 'e'},
                {new CharStream("aoBABssxacaee"), 'A'},
                {new CharStream("brasil"), 'i'},
                {new CharStream("yyyyy"), ' '},
                {new CharStream("acordar"), 'o'},
                {new CharStream(" "), ' '},
                {new CharStream("t"), ' '},
                {new CharStream("d"), ' '},
                {new CharStream("c--a--f--e"), ' '},
                {new CharStream("a[f--__e"), ' '},
                {new CharStream("---[]afe"), 'e'},
        });
    }

    /*
     * Stream
     */
    private final Stream stream;

    /*
     * Caracter Esperado - para fins do teste
     */
    private final char caractere;

    /*
     * Construtor da classe, recebe stream e qual caracter é esperado
     */
    public LocalizaVogalTest(Stream stream, char caractere) {
        this.stream = stream;
        this.caractere = caractere;
    }

    /*
     * Teste para verificar qual a vogal da stream e qual o caracter é esperado
     */
    @Test
    public void verificaVogalNaStream() {
        char actual = LocalizaVogal.primeiraLetra(stream);
        assertThat(actual, is(equalTo(caractere)));
    }
}