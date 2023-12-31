package com.fastcampus.ch5.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Constraint(validatedBy = BookIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BookId {
    String message() default "{BookId.NewBook.bookId}";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
