package springframework.bluemoonrestspring.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import springframework.bluemoonrestspring.model.Drop;
import java.util.Collection;
import java.util.List;

@Repository
public interface DropRepository {

    void save(Drop drop) throws DataAccessException;

    List<Drop> findByApplianceId(Integer applianceId);

    Drop findById(int id) throws DataAccessException;

    Collection<Drop> findAll() throws DataAccessException;

    void delete(Drop drop) throws DataAccessException;
}
