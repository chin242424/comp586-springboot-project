package springframework.bluemoonrestspring.repository;

import org.springframework.dao.DataAccessException;
import springframework.bluemoonrestspring.model.Customer;
import java.util.Collection;

public interface CustomerRepository {

    Collection<Customer> findByLastName(String lastName) throws DataAccessException;
    Customer findById(int id) throws DataAccessException;
    void save(Customer customer) throws DataAccessException;
    Collection<Customer> findAll() throws DataAccessException;
    void delete(Customer customer) throws DataAccessException;
}
