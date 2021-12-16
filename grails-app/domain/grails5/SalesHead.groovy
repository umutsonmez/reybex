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
    Double shippingCost
    boolean isDelete=false

    static hasMany = [items: SalesItem]

    static mapping = {
        version false
    }
    static constraints = {
        district nullable: true
    }
}
