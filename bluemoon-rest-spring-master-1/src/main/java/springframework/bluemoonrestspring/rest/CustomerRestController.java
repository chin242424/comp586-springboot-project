package springframework.bluemoonrestspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.bluemoonrestspring.model.Customer;
import springframework.bluemoonrestspring.service.ApplianceService;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")

@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ApplianceService applianceService;

    @RequestMapping(value = "/*/lastname/{lastName}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Customer>> getCustomersList(@PathVariable("lastName") String customerLastName) {
        if (customerLastName == null) {
            customerLastName = "";
        }
        Collection<Customer> customers = this.applianceService.findCustomerByLastName(customerLastName);
        if (customers.isEmpty()) {
            return new ResponseEntity<Collection<Customer>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Customer>>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Customer>> getCustomers() {
        Collection<Customer> customers = this.applianceService.findAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<Collection<Customer>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Customer>>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") int customerId) {
        Customer customer = null;
        customer = this.applianceService.findCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer, BindingResult bindingResult,
                                                UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || customer.getId() != null) {

            return new ResponseEntity<Customer>(headers, HttpStatus.BAD_REQUEST);
        }
        this.applianceService.saveCustomer(customer);
        headers.setLocation(ucBuilder.path("/api/customers/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Customer>(customer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") int customerId, @RequestBody @Valid Customer customer,
                                                   BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        boolean bodyIdMatchesPathId = customer.getId() == null || customerId == customer.getId();
        if (bindingResult.hasErrors() || !bodyIdMatchesPathId) {

            HttpHeaders headers = new HttpHeaders();
//
            return new ResponseEntity<Customer>(headers, HttpStatus.BAD_REQUEST);
        }
        Customer currentCustomer = this.applianceService.findCustomerById(customerId);
        if (currentCustomer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setCity(customer.getCity());
        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setLastName(customer.getLastName());
        currentCustomer.setTelephone(customer.getTelephone());
        this.applianceService.saveCustomer(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") int customerId) {
        Customer customer = this.applianceService.findCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.applianceService.deleteCustomer(customer);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

