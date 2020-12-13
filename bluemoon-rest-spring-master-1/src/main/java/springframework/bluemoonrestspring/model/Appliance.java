package springframework.bluemoonrestspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import springframework.bluemoonrestspring.rest.temp.JacksonCustomApplianceDeserializer;
import springframework.bluemoonrestspring.rest.temp.JacksonCustomApplianceSerializer;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "appliances")
@JsonSerialize(using = JacksonCustomApplianceSerializer.class)
@JsonDeserialize(using = JacksonCustomApplianceDeserializer.class)
public class Appliance extends NamedEntity {

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ApplianceType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appliance", fetch = FetchType.EAGER)
    private Set<Drop> drops;

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public ApplianceType getType() {
        return this.type;
    }

    public void setType(ApplianceType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonIgnore
    protected Set<Drop> getDropsInternal(){
        if(this.drops == null)
        {
            this.drops = new HashSet<>();
        }
        return this.drops;
    }

    public List<Drop> getDrops(){
        List<Drop> sortedDrops = new ArrayList<>(getDropsInternal());
        PropertyComparator.sort(sortedDrops, new MutableSortDefinition("date",false,false));
        return Collections.unmodifiableList(sortedDrops);
    }

}