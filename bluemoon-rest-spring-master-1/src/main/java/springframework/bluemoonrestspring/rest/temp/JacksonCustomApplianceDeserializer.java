package springframework.bluemoonrestspring.rest.temp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.model.Customer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JacksonCustomApplianceDeserializer extends StdDeserializer<Appliance> {

    public JacksonCustomApplianceDeserializer() {
        this(null);
    }

    public JacksonCustomApplianceDeserializer(Class<Appliance> t) {
        super(t);
    }


    @Override
    public Appliance deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Appliance appliance = new Appliance();
        Customer customer = new Customer();
        ApplianceType applianceType = new ApplianceType();
        ObjectMapper mapper = new ObjectMapper();
        Date purchaseDate = null;
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode customer_node = node.get("customer");
        JsonNode type_node = node.get("type");
        customer = mapper.treeToValue(customer_node, Customer.class);
        applianceType = mapper.treeToValue(type_node, ApplianceType.class);
        int applianceId = node.get("id").asInt();
        String name = node.get("name").asText(null);
        String purchaseDateStr = node.get("purchaseDate").asText(null);
        try {
            purchaseDate = formatter.parse(purchaseDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        if (!(applianceId == 0)) {
            appliance.setId(applianceId);
        }
        appliance.setName(name);
        appliance.setPurchaseDate(purchaseDate);
        appliance.setCustomer(customer);
        appliance.setType(applianceType);
        return appliance;
    }
}
