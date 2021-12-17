package grails5

import grails.gorm.transactions.Transactional

@Transactional
class SalesHeadService {

    StockMovementService stockMovementService

    Map show(Map params) {
        if (params.id) {
            SalesHead salesHead = SalesHead.read(params.id.toLong())
            return [salesHead: [id          : salesHead.id,
                                docDate     : salesHead.docDate,
                                docNumber   : salesHead.docNumber,
                                customer    : [id: salesHead.customer.id, customerNo: salesHead.customer.customerNo, name: salesHead.customer.name],
                                city        : salesHead.city,
                                district    : salesHead.district,
                                shippingCost: salesHead.shippingCost,
                                items       : salesHead.items.grep { it.isDelete != true }.collect {
                                    [id         : it.id,
                                     material   : [id     : it.material.id,
                                                   matCode: it.material.matCode,
                                                   name   : it.material.name],
                                     warehouse  : [id: it.warehouse?.id],
                                     taxAmount  : it.taxAmount,
                                     quantity   : it.quantity,
                                     netPrice   : it.netPrice,
                                     taxRate    : it.taxRate,
                                     totalAmount: it.totalAmount
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
                    " WHERE sh.isDelete != true ")
            return [data: data, total: data.size()]
        }


    }

    Map save(Map params) {
        SalesHead salesHead
        if (params.id) {
            salesHead = SalesHead.get(params.id)
            List idsInDb = salesHead.items.grep{!it.isDelete}*.id
            List newIds = params.items*.id
            idsInDb.each {
                if (!newIds.contains(it)) {
                    SalesItem salesItem = SalesItem.get(it)
                    salesItem.isDelete = true

                    salesItem.save(flush:true)

                    StockMovement stockMovement=StockMovement.findBySalesItem(salesItem)
                    stockMovement?.isCancelled=true
                    stockMovement?.save(flush:true)
                }
            }

        } else {
            salesHead = new SalesHead()

        }
        salesHead.properties = params

        salesHead.save(flush: true)
        params.items.each {
            SalesItem salesItem
            Boolean doStockMovement = false
            if (it.id) {
                salesItem = SalesItem.get(it.id)
            } else {
                salesItem = new SalesItem()
                doStockMovement = true
            }

            salesItem.properties = it
            salesItem.salesHead = salesHead
            salesItem.save(flush: true)
            if (doStockMovement) {
                stockMovementService.save([material       : [id: salesItem.materialId],
                                           warehouse      : [id: salesItem.warehouseId],
                                           enteredQuantity: (-1) * salesItem.quantity,
                                           salesItem:[id: salesItem.id],
                                           description    : salesHead.docNumber + " Belge Sipariş ürün çıkışı"])
            }

        }

        return [id: salesHead?.id]
    }

}
