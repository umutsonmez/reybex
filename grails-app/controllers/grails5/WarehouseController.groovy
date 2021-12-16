package grails5


import grails.rest.*
import grails.converters.*

class WarehouseController {
    static responseFormats = ['json', 'xml']

    def warehouseService



    def save() {
        render (warehouseService.save(request.JSON) as JSON)
    }


    def remove(){
        warehouseService.remove(params)
        render ([success: true] as JSON)
    }

    def show() {
        render(warehouseService.show(params) as JSON)
    }
}
