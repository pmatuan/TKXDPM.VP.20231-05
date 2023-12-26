package vn.hust.aims.subsystem.payment;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Provider {
  String value() default "";
}
