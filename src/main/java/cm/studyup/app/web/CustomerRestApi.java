package cm.studyup.app.web;

import cm.studyup.app.dtos.CustomerDto;
import cm.studyup.app.exceptions.CustomerNotFoundException;
import cm.studyup.app.services.BankAccountService;
import cm.studyup.app.shared.model.ResponseData;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class CustomerRestApi {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public ResponseEntity<ResponseData<List<CustomerDto>>> customers() {
        return new ResponseEntity<>(new
                ResponseData<>(bankAccountService.listCustomers()), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<ResponseData<Object>> getCustomer(@PathVariable(name = "id") Long customerId) {
        try {
            return new ResponseEntity<>(new
                    ResponseData<>(bankAccountService.getCustomer(customerId)), HttpStatus.OK);
        } catch (CustomerNotFoundException customerNotFoundException) {
            return new ResponseEntity<>(new
                    ResponseData<>(customerNotFoundException.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/customers/search")
    public ResponseEntity<ResponseData<List<CustomerDto>>> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return new ResponseEntity<>(new
                ResponseData<>(bankAccountService.searchCustomers("%" + keyword + "%")), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<ResponseData<CustomerDto>> saveCustomer(@Valid @RequestBody CustomerDto customerDTO){
        return new ResponseEntity<>(new
                ResponseData<>(bankAccountService.saveCustomer(customerDTO)), HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDto updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }
}
