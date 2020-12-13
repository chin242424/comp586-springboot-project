package springframework.bluemoonrestspring.service;

import org.springframework.dao.DataAccessException;
import springframework.bluemoonrestspring.model.*;

import java.util.Collection;


public interface ApplianceService {

    Appliance findApplianceById(int id) throws DataAccessException;
    Collection<Appliance> findAllAppliances() throws DataAccessException;
    void saveAppliance(Appliance appliance) throws DataAccessException;
    void deleteAppliance(Appliance appliance) throws DataAccessException;

    Drop findDropById(int dropId) throws  DataAccessException;
//    Collection<Drop> findDropsByApplianceId(int applianceId);
    Collection<Drop> findAllDrops() throws  DataAccessException;
    void saveDrop(Drop drop) throws DataAccessException;
    void deleteDrop(Drop drop) throws DataAccessException;

    Customer findCustomerById(int id) throws DataAccessException;
    Collection<Customer> findCustomerByLastName(String lastName) throws DataAccessException;
    Collection<Customer> findAllCustomers() throws DataAccessException;
    void saveCustomer(Customer customer) throws DataAccessException;
    void deleteCustomer(Customer customer) throws DataAccessException;

    ApplianceType findApplianceTypeById(int applianceTypeId);
    Collection<ApplianceType> findAllApplianceTypes() throws DataAccessException;
    Collection<ApplianceType> findApplianceTypes() throws DataAccessException;
    void saveApplianceType(ApplianceType applianceType) throws DataAccessException;
    void deleteApplianceType(ApplianceType applianceType) throws DataAccessException;

}
