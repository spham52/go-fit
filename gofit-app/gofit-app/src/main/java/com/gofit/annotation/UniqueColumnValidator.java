package com.gofit.annotation;

import com.gofit.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.gofit.entity.User;

public class UniqueColumnValidator implements ConstraintValidator<UniqueColumn, String> {

    @Autowired
    private EntityManager em;

    private String field;

    @Override
    public void initialize(UniqueColumn annotation) {
        this.field = annotation.field();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u." + field + " = :value", User.class);
        query.setParameter("value", s);
        return query.getResultList().isEmpty();
    }
}
