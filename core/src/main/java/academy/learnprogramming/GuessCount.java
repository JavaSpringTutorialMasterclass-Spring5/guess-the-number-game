package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER}) //adnotację będzie można dopisywać tylko nad polem, metodą albo parametrem
@Retention(RetentionPolicy.RUNTIME) //coś na kształt cyklu życia adnotacji,
@Qualifier //adnotacją @Qualifier można oadnotować naszą nową adnotację, co oznacza, że nasza adnotacja będzie działała tak jak oryginalna adnotacja @Qualifier
public @interface GuessCount {
}
