package grails5


import grails.rest.*
import grails.converters.*

class CustomerController {
    static responseFormats = ['json', 'xml']

    CustomerService customerService

    def show(){
        render(customerService.show(params)as JSON)
    }
    def remove(){
        customerService.remove(params)
        render ([success: true] as JSON)
    }
    def save(){
        render(customerService.save(request.JSON)as JSON)
    }
}
