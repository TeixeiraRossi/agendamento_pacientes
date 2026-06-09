package org.example.annotation;

public @interface Obrigatorio {
    String mensagem() default "Campo obrigatório";
}
