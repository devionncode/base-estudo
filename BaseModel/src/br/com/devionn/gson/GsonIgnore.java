package br.com.devionn.gson;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Target;

@Target(FIELD)
public @interface GsonIgnore {

}
