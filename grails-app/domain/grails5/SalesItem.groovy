package grails5

class SalesItem {

    Integer id
    SalesHead salesHead
    Material material
    Integer quantity
    Warehouse warehouse
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
