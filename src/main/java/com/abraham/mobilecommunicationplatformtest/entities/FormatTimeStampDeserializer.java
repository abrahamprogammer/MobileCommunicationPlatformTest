package com.abraham.mobilecommunicationplatformtest.entities;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class FormatTimeStampDeserializer extends JsonDeserializer<Timestamp> {

	/**
	 * This method is necessary because Java use TimeStamps that are number of MilliSeconds since 01-01-1970 and
	 * the timeStamps extracted from these JSON are number of seconds, not milliseconds, so, without this manual
	 * deserializer the result date will be always '01-01-1970, 00:00:00'
	 */
    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        long valueLong = Long.parseLong(node.toString()) * 1000;
        Instant instant = Instant.ofEpochMilli(valueLong);
        return Timestamp.from(instant);
    }
}
