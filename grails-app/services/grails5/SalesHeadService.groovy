package grails5

import grails.gorm.transactions.Transactional

@Transactional
class SalesHeadService {

    Map show(Map params){
        if(params.id){
            SalesHead salesHead = SalesHead.read(params.id.toLong())
            return [salesHead:[id: salesHead.id,
                    docDate: salesHead.docDate,
                    docNumber: salesHead.docNumber,
                    customer: [id: salesHead.customer.id, customerNo: salesHead.customer.customerNo, name:salesHead.customer.name],
                    city:salesHead.city,
                    district:salesHead.district,
                    shippingCost:salesHead.shippingCost,
                    items:salesHead.items.grep{it.isDelete != true}.collect{
                        [id: it.id,
                         material: [id: it.material.id, matCode: it.material.matCode,name:it.material.name],
                         taxAmount: it.taxAmount,
                        quantity:it.quantity,
                        netPrice:it.netPrice,
                        taxRate:it.taxRate,
                                totalAmount:it.totalAmount
                        ]
                    }]]
        } else {
        List data = SalesHead.executeQuery("SELECT new Map( sh.id as id, " +
        " sh.docDate as docDate, " +
        " sh.docNumber as docNumber, " +
        " customer.customerNo as customerNo, " +
        " sh.shippingCost as shippingCost, " +
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

}
