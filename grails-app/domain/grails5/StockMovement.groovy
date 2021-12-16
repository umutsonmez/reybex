package grails5

class StockMovement {


    Integer id
    Material material
    Integer beforeQuantity
    Integer enteredQuantity
    Integer afterQuantity
    Warehouse warehouse
    boolean isCancelled=false
    String description
    Date dateCreated

    static mapping = {
        version false
    }

    static constraints = {


        beforeQuantity nullable: true
        afterQuantity nullable: true
        dateCreated nullable: true
    }
}
