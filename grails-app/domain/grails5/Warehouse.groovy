package grails5

class Warehouse {

    Integer id
    String code
    String name
    Boolean allowMinusStock
    Boolean isDelete = false

    static mapping = {
        version false
    }

    static constraints = {
        code nullable: true
        name nullable: true
        allowMinusStock nullable:true
    }
}
