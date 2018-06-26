package pl.jdomanski.k47.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToAmountConverter implements Converter<String, Long> {

    @Override
    public Long convert(String amount) {
        Long result = (long) (Double.valueOf(amount)*100);
        return result;
    }
}
