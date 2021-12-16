package grails5

import grails.rest.*
import grails.converters.*

class CountryController {
	static responseFormats = ['json', 'xml']

    def countryService

    def show() {
        render (countryService.show(params) as JSON)
    }

    def save() {
        render (countryService.save(request.JSON) as JSON)
    }
}
