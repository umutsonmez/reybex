package grails5

import org.grails.datastore.mapping.query.Query

class SalesItem {

    Integer id
    SalesHead salesHead
    Material material
    Integer quantity
    Double netPrice
    Double taxRate
    Double taxAmount
    Double totalAmount
    boolean isDelete=false

    static mapping = {
        version false
    }
    static constraints = {

    }
}
