package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import springframework.bluemoonrestspring.model.Appliance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("spring-data-jpa")
public class SpringDataApplianceRepositoryImpl implements  ApplianceRepositoryOverride{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Appliance appliance) {
        String applianceId = appliance.getId().toString();
        this.em.createQuery("DELETE FROM Drop drop WHERE appliance_id=" + applianceId).executeUpdate();
        this.em.createQuery("DELETE FROM Appliance appliance WHERE id=" + applianceId).executeUpdate();
        if (em.contains(appliance)) {
            em.remove(appliance);
        }
    }
    }

