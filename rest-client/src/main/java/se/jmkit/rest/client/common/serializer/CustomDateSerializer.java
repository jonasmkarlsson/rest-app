package se.jmkit.rest.client.common.serializer;
//import java.io.IOException;
//import java.util.Date;
//
//import org.codehaus.jackson.JsonGenerator;
//import org.codehaus.jackson.JsonProcessingException;
//import org.codehaus.jackson.map.JsonSerializer;
//import org.codehaus.jackson.map.SerializerProvider;
//
//import se.jmkit.rest.common.constants.Constant;
//
//public class CustomDateSerializer extends JsonSerializer<Date> {
//
//    @Override
//    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
//        String formattedDate = Constant.DATE_FORMAT.format(value);
//        gen.writeString(formattedDate);
//    }
//
// }