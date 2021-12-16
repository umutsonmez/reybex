package grails5

class Material {

    Integer id
    String matCode
    String sku
    String name
    String description
    Double netPrice
    Double grossPrice
    Integer taxRate
    boolean isDelete=false
    String pictureUrl

    static mapping = {
        version false
    }

    static constraints = {
        sku nullable: true
        description nullable: true
        netPrice nullable: true
        grossPrice nullable: true
        taxRate nullable: true
        pictureUrl nullable: true
    }
}
