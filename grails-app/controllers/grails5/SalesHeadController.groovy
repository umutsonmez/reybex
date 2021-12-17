package grails5


import grails.rest.*
import grails.converters.*

class SalesHeadController {
	static responseFormats = ['json', 'xml']
	
   SalesHeadService salesHeadService

    def show(){
        render(salesHeadService.show(params) as JSON)
    }
    def getSalesHead(){
        render(salesHeadService.show(params) as JSON)
    }
    def save(){
        render(salesHeadService.save(request.JSON)as JSON)
    }
}
