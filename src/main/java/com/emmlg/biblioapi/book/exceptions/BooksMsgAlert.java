package com.emmlg.biblioapi.book.exceptions;

import lombok.Getter;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@Getter
public class BooksMsgAlert extends RuntimeException {
    private final String code;


    public BooksMsgAlert(String code, String descripcion) {
        super(descripcion);
        this.code = code;
    }
    public static String getMessageFromProperties(String key, Object... params) {
        // Cargar el archivo 'messages.properties' usando ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("messages");

        // Recuperar el mensaje correspondiente a la clave 'key' y formatearlo con los parámetros 'params'
        return MessageFormat.format(bundle.getString(key), params);
    }

}
