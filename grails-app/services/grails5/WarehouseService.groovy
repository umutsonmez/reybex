package grails5

import grails.gorm.transactions.Transactional

@Transactional
class WarehouseService {

    Map show(Map params){
        Map response
        if(params.id){
            Warehouse warehouse=Warehouse.read(params.id?.toLong())
            response = [warehouse:warehouse]
        }else{
            List<Warehouse> warehouses =Warehouse.findAllByIsDelete(false, [readOnly:true])
            response =[data:warehouses,total:warehouses.size()]
        }
        return response
    }

    Map save(Map params) {

        Warehouse warehouse
        if (params.id) {
            warehouse=Warehouse.get(params.id)
        }
        else{
            warehouse=new Warehouse()
        }
        warehouse.properties=params
        warehouse.save(flush:true)
        return[id:warehouse?.id]
    }

    void remove(Map params) {

        Warehouse warehouse = Warehouse.get(params.id)
        warehouse.isDelete = true
        warehouse.save(flush:true)
    }
}
