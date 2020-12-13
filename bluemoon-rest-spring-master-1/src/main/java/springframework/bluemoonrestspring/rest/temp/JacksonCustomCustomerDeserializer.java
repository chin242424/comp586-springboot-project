package springframework.bluemoonrestspring.rest.temp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.model.Customer;
import springframework.bluemoonrestspring.model.Drop;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class JacksonCustomCustomerDeserializer extends StdDeserializer<Customer> {
    public JacksonCustomCustomerDeserializer(Class<Customer> t) {
        super(t);
    }
    public JacksonCustomCustomerDeserializer() {
        this(null);
    }

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Customer customer = new Customer();
        String firstName = node.get("firstName").asText(null);
        String lastName = node.get("lastName").asText(null);
        String address = node.get("address").asText(null);
        String city = node.get("city").asText(null);
        String telephone = node.get("telephone").asText(null);
        if (node.hasNonNull("id")) {
            customer.setId(node.get("id").asInt());
        }

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setTelephone(telephone);
        return customer;
    }

}
