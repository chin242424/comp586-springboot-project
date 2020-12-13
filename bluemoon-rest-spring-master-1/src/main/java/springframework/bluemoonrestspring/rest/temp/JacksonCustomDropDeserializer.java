package springframework.bluemoonrestspring.rest.temp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.Drop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JacksonCustomDropDeserializer extends StdDeserializer<Drop> {

    public JacksonCustomDropDeserializer() {
        this(null);
    }

    public JacksonCustomDropDeserializer(Class<Drop> t) {
        super(t);
    }


    @Override
    public Drop deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Drop drop = new Drop();
        Appliance appliance = new Appliance();
        ObjectMapper mapper = new ObjectMapper();
        Date dropDate = null;
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode appliance_node = node.get("appliance");
        appliance = mapper.treeToValue(appliance_node, Appliance.class);
        int dropId = node.get("id").asInt();
        String dropDateStr = node.get("date").asText(null);
        String description = node.get("description").asText(null);
        try {
            dropDate = formatter.parse(dropDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        if (!(dropId == 0)) {
            drop.setId(dropId);
        }
        drop.setDate(dropDate);
        drop.setDescription(description);
        drop.setAppliance(appliance);
        return drop;
    }
}
