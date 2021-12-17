package grails5

import grails.gorm.transactions.Transactional

@Transactional
class LookupService {

   Map show(Map params){
       Map response
       List data

       if(params.domainName.equalsIgnoreCase("warehouse")) {
           List<Warehouse> warehouses = Warehouse.findAllByIsDelete(false,[readOnly:true])
           data=warehouses.collect{
               [id:it.id,name:it.name]
           }
           response =[data:data,meta:[domain:params.domainName,total:warehouses.size()]]



       }else if(params.domainName.equalsIgnoreCase("country")){
           data=Country.executeQuery(" SELECT new Map(c.id as id, "+
           " c.name as name ) "  + " FROM Country c " +
           " ORDER BY c.name " )
           response = [data:data,meta:[domain:params.domainName,total:data.size()]]


       }else if(params.domainName.equalsIgnoreCase("customer")){
           List<Customer> customers = Customer.findAllByIsDelete(false,[readOnly:true])
           data=customers.collect{
               [id:it.id,name:it.name]
           }
           response =[data:data,meta:[domain:params.domainName,total:customers.size()]]


       }else if(params.domainName.equalsIgnoreCase("material")){
           List<Material> materials = Material.findAllByIsDelete(false,[readOnly:true])
           data=materials.collect{
               [id:it.id,name:it.name]
           }
           response =[data:data,meta:[domain:params.domainName,total:materials.size()]]
       }

       return response


   }
}
