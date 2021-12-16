package grails5

class UrlMappings {

    static mappings = {
//        delete "/$controller/$id(.$format)?"(action:"delete")
//        get "/$controller(.$format)?"(action:"index")
//        get "/$controller/$id(.$format)?"(action:"show")
//        post "/$controller(.$format)?"(action:"save")
//        put "/$controller/$id(.$format)?"(action:"update")
//        patch "/$controller/$id(.$format)?"(action:"patch")
        "/country/$id?" (controller: "country"){ action = [GET:"show", POST:"save", PUT:"save", DELETE:"remove"] }
        "/warehouse/$id?" (controller: "warehouse"){ action = [GET:"show", POST:"save", DELETE:"remove"] }
        "/stockMovement/$id?" (controller: "stockMovement"){ action = [GET:"show", POST:"save", DELETE:"remove"] }
        "/stock" (controller: "stockMovement"){ action = [GET:"getStocks"] }
        "/customer/$id?" (controller: "customer"){ action = [GET:"show", POST:"save", DELETE:"remove"] }
        "/material/$id?" (controller: "material"){ action = [GET:"show", POST:"save", DELETE:"remove"] }
        "/salesHead/$id?" (controller: "salesHead"){ action = [GET:"show", POST:"save", DELETE:"remove"] }
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
