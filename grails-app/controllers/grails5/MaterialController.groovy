package grails5


import grails.rest.*
import grails.converters.*

class MaterialController {
	static responseFormats = ['json', 'xml']
	
    MaterialService materialService

    def save(){
        render(materialService.save(request.JSON)as JSON)
    }

    def show(){
        render(materialService.show(params)as JSON)
    }

    def remove(){
        materialService.remove(params)
        render ([success: true] as JSON)
    }
}
