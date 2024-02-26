package com.devsu.challenge.msjavachallengeclient.util;

import com.devsu.challenge.msjavachallengeclient.exception.UnProcessableEntityException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClassManagementUtil {

    public static <T,U> void updateFields(T target, U source){
        List<Field> fields = Arrays.stream(source.getClass().getDeclaredFields()).toList();
        fields = fields.stream().filter(field -> !field.getName().equals("serialVersionUID")).toList();
        fields.forEach(field -> {
            field.setAccessible(true);
            try{
                Object value = field.get(source);
                if(Objects.nonNull(value)){
                    Field targetField;
                    try{
                        targetField = target.getClass().getDeclaredField(field.getName());
                    }catch (Exception e){
                        targetField = target.getClass().getSuperclass().getDeclaredField(field.getName());
                    }
                    targetField.setAccessible(true);
                    if (targetField.getType().isEnum()) {
                        Enum<?>[] enumConstants = targetField.getType().asSubclass(Enum.class).getEnumConstants();
                        Enum.valueOf(targetField.getType().asSubclass(Enum.class), (String) value);
                        for (Enum<?> enumValue : enumConstants) {
                            if (enumValue.name().equals(value)) {
                                targetField.set(target, enumValue);
                                break;
                            }
                        }
                    } else {
                        targetField.set(target, value);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new UnProcessableEntityException("Ha ocurrido un error en el proceso");
            }
        });
    }

}
