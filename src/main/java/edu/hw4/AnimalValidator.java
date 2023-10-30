package edu.hw4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class AnimalValidator {

    private final List<Function<Animal, Optional<ValidationError>>> validations = new ArrayList<>();

    public AnimalValidator() {
        registerValidation(BasicAnimalValidations::validateAge);
        registerValidation(BasicAnimalValidations::validateWeight);
        registerValidation(BasicAnimalValidations::validateSex);
        registerValidation(BasicAnimalValidations::validateType);
        registerValidation(BasicAnimalValidations::validateName);
    }

    private void registerValidation(Function<Animal, Optional<ValidationError>> validation) {
        validations.add(validation);
    }

    public Set<ValidationError> validateAll(Animal animal) {
        final Set<ValidationError> validationErrors = new HashSet<>();
        for (var validation : validations) {
            Optional<ValidationError> validationError = validation.apply(animal);
            validationError.ifPresent(validationErrors::add);
        }
        return validationErrors;
    }

    private final static class BasicAnimalValidations {

        private BasicAnimalValidations() {
        }

        private static Optional<ValidationError> validateAge(Animal animal) {
            if (animal.age() < 0) {
                return Optional.of(new ValidationError("age", "Age must be positive"));
            }
            return Optional.empty();
        }

        private static Optional<ValidationError> validateWeight(Animal animal) {
            if (animal.weight() < 0) {
                return Optional.of(new ValidationError("weight", "Weight must be positive"));
            }
            return Optional.empty();
        }

        private static Optional<ValidationError> validateSex(Animal animal) {
            if (animal.sex() == null) {
                return Optional.of(new ValidationError("sex", "Sex must be not null"));
            }
            return Optional.empty();
        }

        private static Optional<ValidationError> validateType(Animal animal) {
            if (animal.type() == null) {
                return Optional.of(new ValidationError("type", "Type must be not null"));
            }
            return Optional.empty();
        }

        private static Optional<ValidationError> validateName(Animal animal) {
            final int minNameSize = 3;
            if (animal.name().isBlank() || animal.name().length() < minNameSize) {
                return Optional.of(new ValidationError(
                    "name",
                    "Name must be not empty and contains minimum 3 symbols"
                ));
            }
            return Optional.empty();
        }
    }

}
