package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.model.Drop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Profile("spring-data-jpa")
public class SpringDataApplianceTypeRepositoryImpl implements ApplianceTypeRepositoryOverride {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public void delete(ApplianceType applianceType) {
        this.em.remove(this.em.contains(applianceType) ? applianceType : this.em.merge(applianceType));

        Integer applianceTypeId = applianceType.getId();

        List<Appliance> appliances = this.em.createQuery("SELECT appliance FROM Appliance appliance WHERE type_id=" + applianceTypeId).getResultList();
        for (Appliance appliance : appliances) {
            List<Drop> drops = appliance.getDrops();
            for (Drop drop : drops) {
                this.em.createQuery("DELETE FROM Drop drop WHERE id=" + drop.getId()).executeUpdate();
            }
            this.em.createQuery("DELETE FROM Appliance appliance WHERE id=" + appliance.getId()).executeUpdate();
        }
        this.em.createQuery("DELETE FROM ApplianceType appliancetype WHERE id=" + applianceTypeId).executeUpdate();

    }
}
