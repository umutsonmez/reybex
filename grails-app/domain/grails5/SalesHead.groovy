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
    boolean isDelete=false

    static mapping = {
        version false
    }
    static constraints = {
        district nullable: true
    }
}
