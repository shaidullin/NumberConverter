package com.mtuci.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Класс с тестами для проверки метода из класса NumberConverter
 */
@TestMethodOrder(OrderAnnotation.class)
public class NumberConverterTest {

  private static final String TAG = "TEST";

  @Test
  @Order(1)
  void convert_ValidateSignature() {
    Method[] declaredMethods = NumberConverter.class.getDeclaredMethods();
    String methodName = "convert";
    long convertCount = Stream.of(declaredMethods)
        .filter(method -> method.getName().equals(methodName))
        .count();
    assertEquals(1, convertCount,
        () -> String.format("Должен быть хотя бы и только один метод с названием '%s'.", methodName));

    Optional<Method> method = Stream.of(declaredMethods)
        .filter(m -> m.getName().equals(methodName))
        .findFirst();
    Method convertMethod = method.get();
    int parameterCount = convertMethod.getParameterCount();
    assertEquals(2, parameterCount,
        () -> String.format("Метод '%s' должен иметь два параметра.", methodName));
    Parameter[] parameters = convertMethod.getParameters();
    for (Parameter p : parameters) {
      assertEquals(int.class, p.getType(),
          () -> String.format("Параметр %s должен быть целочисленного типа.", p.getName()));
    }

    int modifiers = convertMethod.getModifiers();
    assertEquals(Modifier.PRIVATE, modifiers,
        () -> String.format("Метод '%s' должен быть приватным, и иметь только один модификатор доступа.", methodName));
    assertEquals(String.class, convertMethod.getReturnType(),
        () -> String.format("Возвращаемый тип метода '%s' должен быть строкой.", methodName));

  }

  @Test
  @Order(2)
  void convert_CheckLogic() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method convertMethod = NumberConverter.class.getDeclaredMethod("convert", int.class, int.class);
    convertMethod.setAccessible(true);
    NumberConverter numberConverter = new NumberConverter();
    for (int i = 0; i < 100; i++) {
      for (int radix = 2; radix <= 16; radix++) {
        assertEquals(Integer.toString(i, radix), convertMethod.invoke(numberConverter, i, radix));
      }
    }
  }

}
