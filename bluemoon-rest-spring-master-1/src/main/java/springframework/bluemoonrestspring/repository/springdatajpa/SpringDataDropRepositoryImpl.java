package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import springframework.bluemoonrestspring.model.Drop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("spring-data-jpa")
public class SpringDataDropRepositoryImpl implements DropRepositoryOverride{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Drop drop) throws DataAccessException {
        String dropId = drop.getId().toString();
        this.em.createQuery("DELETE FROM Drop drop WHERE id=" + dropId).executeUpdate();
        if (em.contains(drop)) {
            em.remove(drop);
        }
    }
}
