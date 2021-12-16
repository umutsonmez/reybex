package grails5

class Country {

    Long id
    String code
    String name

    static mapping = {
        version false
    }

    static constraints = {
        code nullable: true, maxSize: 5
    }
}
