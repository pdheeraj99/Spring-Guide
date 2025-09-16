package io.mawa.spring.core.validation.conversion;

import org.springframework.core.convert.converter.Converter;
import java.math.BigDecimal;
import java.util.Currency;

public class StringToMoneyConverter implements Converter<String, Money> {

    @Override
    public Money convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        // Expects format like "123.45 USD"
        String[] parts = source.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid money format. Expected '[amount] [currency_code]', e.g., '123.45 USD'");
        }
        BigDecimal amount = new BigDecimal(parts[0]);
        Currency currency = Currency.getInstance(parts[1].toUpperCase());
        return new Money(amount, currency);
    }
}
