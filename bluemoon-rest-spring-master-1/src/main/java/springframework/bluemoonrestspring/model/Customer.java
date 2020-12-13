package springframework.bluemoonrestspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import springframework.bluemoonrestspring.rest.temp.JacksonCustomCustomerDeserializer;
import springframework.bluemoonrestspring.rest.temp.JacksonCustomCustomerSerializer;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "customers")
@JsonSerialize(using = JacksonCustomCustomerSerializer.class)
@JsonDeserialize(using = JacksonCustomCustomerDeserializer.class)
public class Customer extends Person{

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Appliance> appliances;


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @JsonIgnore
    protected Set<Appliance> getAppliancesInternal(){
        if(this.appliances == null){
            this.appliances = new HashSet<>();
        } return this.appliances;
    }

    public List<Appliance> getAppliances(){
        List<Appliance> sortedAppliance = new ArrayList<>(getAppliancesInternal());
        PropertyComparator.sort(sortedAppliance, new MutableSortDefinition("name",true,true));
        return Collections.unmodifiableList(sortedAppliance);
    }


}