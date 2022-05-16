package ma.enset.bank_api.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.bank_api.dtos.CustomerDto;
import ma.enset.bank_api.entities.Customer;
import ma.enset.bank_api.exceptions.CustomerNotFoundException;
import ma.enset.bank_api.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDto>customers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return  bankAccountService.getcustomer(customerId);
    }
    @GetMapping("/customers/search")
    public List<CustomerDto> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
       CustomerDto customerDto1=  bankAccountService.saveCustomer(customerDto);
       return customerDto1;
    }

    @PutMapping("/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable(name = "id")Long customerId,@RequestBody CustomerDto customerDto){
        customerDto.setId(customerId);
        CustomerDto customerDto1=  bankAccountService.saveCustomer(customerDto);
        return customerDto1;
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }


}
