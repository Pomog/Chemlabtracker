package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

public class InputStringValidatorDataMatcher implements ArgumentMatcher<InputStringValidatorData> {

    private final String value;
    private final String field;
    private final String valueName;

    public InputStringValidatorDataMatcher(String value, String field, String valueName) {
        this.value = value;
        this.field = field;
        this.valueName = valueName;
    }

    @Override
    public boolean matches(InputStringValidatorData inputStringValidatorData) {
        return value.equals(inputStringValidatorData.getValue()) &&
                field.equals(inputStringValidatorData.getField()) &&
                valueName.equals(inputStringValidatorData.getValueName());
    }

}
