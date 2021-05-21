package me.tatteaid.utils.command.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {
    String name();

    String permission() default "";

    String[] aliases() default {};

    String description() default "";

    boolean playerOnly() default false;
}