package grails5

class SalesHead {

    Integer id
    Date docDate
    String docNumber
    Customer customer
    String orderEmail
    String city
    String district
    String address
    Double shippingCost = 0
    boolean isDelete=false

    static hasMany = [items: SalesItem]

    static mapping = {
        version false
    }
    static constraints = {
        district nullable: true
        docDate nullable: true
        docNumber nullable: true
        customer nullable: true
        orderEmail nullable: true
        city nullable: true
        address nullable: true
        shippingCost nullable: true

    }
}
