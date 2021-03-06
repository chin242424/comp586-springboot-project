package springframework.bluemoonrestspring.rest.temp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.model.Customer;
import springframework.bluemoonrestspring.model.Drop;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class JacksonCustomCustomerSerializer extends StdSerializer<Customer> {
    public JacksonCustomCustomerSerializer() {
        this(null);
    }

    public JacksonCustomCustomerSerializer(Class<Customer> t)
    {
        super(t);
    }

    @Override
    public void serialize(Customer customer, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        Format formatter = new SimpleDateFormat("yyyy/MM/dd");
        jgen.writeStartObject();
        if (customer.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", customer.getId());
        }

        jgen.writeStringField("firstName", customer.getFirstName());
        jgen.writeStringField("lastName", customer.getLastName());
        jgen.writeStringField("address", customer.getAddress());
        jgen.writeStringField("city", customer.getCity());
        jgen.writeStringField("telephone", customer.getTelephone());

        jgen.writeArrayFieldStart("appliances");
        for (Appliance appliance : customer.getAppliances()) {
            jgen.writeStartObject();
            if (appliance.getId() == null) {
                jgen.writeNullField("id");
            } else {
                jgen.writeNumberField("id", appliance.getId());
            }
            jgen.writeStringField("name", appliance.getName());
            jgen.writeStringField("purchaseDate", formatter.format(appliance.getPurchaseDate()));

            ApplianceType applianceType = appliance.getType();
            jgen.writeObjectFieldStart("type");
            jgen.writeNumberField("id", applianceType.getId());
            jgen.writeStringField("name", applianceType.getName());
            jgen.writeEndObject();

            if (appliance.getCustomer().getId() == null) {
                jgen.writeNullField("customer");
            } else {
                jgen.writeNumberField("customer", appliance.getCustomer().getId());
            }

            jgen.writeArrayFieldStart("drops");
            for (Drop drop : appliance.getDrops()) {
                jgen.writeStartObject();
                if (drop.getId() == null) {
                    jgen.writeNullField("id");
                } else {
                    jgen.writeNumberField("id", drop.getId());
                }
                jgen.writeStringField("date", formatter.format(drop.getDate()));
                jgen.writeStringField("description", drop.getDescription());
                jgen.writeNumberField("appliance", drop.getAppliance().getId());
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
    }


}
