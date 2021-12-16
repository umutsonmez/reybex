package grails5

import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    Map show(Map params){
        Map response
        if(params.id){
            Customer customer = Customer.read(params.id?.toLong())
            response = [customer: customer]
        } else {
            List<Customer> customers = Customer.findAllByIsDelete(false,[readOnly: true])
            response = [data: customers, total: customers.size()]
        }
        return response
    }

    Map save(Map params){

        Customer customer

        if (params.id) {
            customer=Customer.get(params.id)
        }
        else{
            customer=new Customer()
        }
        customer.properties=params

        customer.save(flush:true)
        return[id:customer?.id]
    }
    void remove(Map params){
        Customer customer = Customer.get(params.id)
        customer.isDelete = true
        customer.save(flush:true)
    }
}
