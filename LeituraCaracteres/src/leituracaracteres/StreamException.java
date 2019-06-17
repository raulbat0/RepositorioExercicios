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
public class StreamException extends RuntimeException {

    private static final long serialVersionUID = -1;

    public StreamException() {
        super("Sem mais caracteres a serem processados");
    }
}
