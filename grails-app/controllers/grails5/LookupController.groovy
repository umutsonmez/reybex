package grails5


import grails.rest.*
import grails.converters.*

class LookupController {
	static responseFormats = ['json', 'xml']
	
    LookupService lookupService

    def show(){
        render(lookupService.show(params) as JSON)
    }
}
