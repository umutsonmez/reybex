package grails5

import grails.gorm.transactions.Transactional

@Transactional
class CountryService {

    Map show(Map params) {
        println params
        Map response
        if(params.id){
            Country country = Country.read(params.id?.toLong())
            response = [country: country]
        } else {
            List<Country> countries = Country.list([readOnly: true])
            response = [data: countries, total: countries.size()]
        }
        return response
    }

    Map save(Map params){
        Country country
        if(params.id){
            country = Country.get(params.id)
        } else {
            country = new Country()
        }
        country.properties = params
        country.save(flush: true)
        return [id: country?.id]
    }
}
