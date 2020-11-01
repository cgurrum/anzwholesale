package com.anz.wholesale.common.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneySerializer extends JsonSerializer<BigDecimal> {
    private static final String PATTERN = "#0.00";
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        DecimalFormat df = new DecimalFormat(PATTERN);
        if(value != null){
            gen.writeString(df.format(value));
        }else{
            gen.writeNull();
        }
    }
}
