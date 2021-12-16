package grails5

import grails.gorm.transactions.Transactional

@Transactional
class SalesHeadService {

    Map show(Map params){

        List data = SalesHead.executeQuery("SELECT new Map( sh.id as id, " +
        " sh.docDate as docDate, " +
        " sh.docNumber as docNumber, " +
        " customer.customerNo as customerNo, " +
        " customer.name as customerName, " +
        " sh.orderEmail as orderEmail, " +
        " sh.city as city, " +
        " sh.district as district ) " +
        " FROM SalesHead sh " +
        " LEFT JOIN sh.customer as customer " +
        " WHERE sh.isDelete != true " )


        return [data: data, total: data.size()]
    }
}
