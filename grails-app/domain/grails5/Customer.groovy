package grails5

class Customer {

    Integer id
    Integer customerNo
    String name
    String phone
    String city
    String district
    String address
    Country country
    boolean isDelete=false
    static mapping = {
        version false
    }
    static constraints = {
        district nullable: true
        address nullable: true
        city nullable: true
        phone nullable: true
        country nullable: true
        customerNo nullable: true
    }
}
