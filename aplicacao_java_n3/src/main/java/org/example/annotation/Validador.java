package org.example.annotation;

import java.lang.reflect.Field;

public class Validador {
    public static void validar(Object objeto) {
        Class<?> classe = objeto.getClass();

        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Obrigatorio.class)) {
                campo.setAccessible(true);
                try {
                    Object valor = campo.get(objeto);
                    if (valor == null || valor.toString().trim().isEmpty()) {
                        Obrigatorio anotacao = campo.getAnnotation(Obrigatorio.class);
                        throw new IllegalArgumentException("Erro de Validação: " + anotacao.mensagem());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro no reflection", e);
                }
            }
        }
    }
}
