package ru.spbu.frogsoill.domain.util;

import java.util.Objects;

public final class ValidationUtil {
    public static void validateNotEmpty(String variableUnderValidation, String errorMessage) {
        if (Objects.isNull(variableUnderValidation) || variableUnderValidation.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
