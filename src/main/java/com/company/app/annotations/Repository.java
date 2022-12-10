package com.company.app.annotations;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RequestScoped
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Repository {
}
