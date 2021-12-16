package grails5


import grails.rest.*
import grails.converters.*

class StockMovementController {
    static responseFormats = ['json', 'xml']
    def stockMovementService

    def show() {
        render(stockMovementService.show(params) as JSON)
    }

    def getStocks()
    {
        render(stockMovementService.getStocks(params)as JSON)
    }

    def save(){
        render(stockMovementService.save(request.JSON)as JSON)
    }
}
