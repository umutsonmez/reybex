package grails5

import grails.gorm.transactions.Transactional

@Transactional
class StockMovementService {

    Map show(Map params){
        List data = StockMovement.executeQuery("SELECT new Map(material.matCode as mat_Code, " +
                " sm.id as id, "+
                " material.name as material_name, " +
                " sm.enteredQuantity as entered_Quantity, " +
                " sm.description as description, " +
                " sm.dateCreated as date_Created, " +
                " warehouse.name as warehouse_name) " +
                " FROM StockMovement sm " +
                " JOIN sm.material as material " +
                " LEFT JOIN sm.warehouse as warehouse " +
                " WHERE sm.isCancelled != true" +
                " ORDER BY sm.dateCreated DESC")
        return [data: data, total: data.size()]
    }

    Map getStocks(Map params){
        List data = StockMovement.executeQuery( " SELECT new Map(material.matCode as mat_code," +
                " SUM(sm.enteredQuantity) as entered_quantity, " +
                " material.name as material_name) " +
                " FROM StockMovement sm " +
                " JOIN sm.material as material " +
                " LEFT JOIN sm.warehouse as warehouse " +
                " GROUP BY material.id,warehouse.id")

        return [data:data]
    }

    Map save(Map params){
        StockMovement stockMovement = new StockMovement()
        stockMovement.properties = params

        stockMovement.dateCreated = new Date()

        stockMovement.beforeQuantity = StockMovement.executeQuery("SELECT SUM(sm.enteredQuantity) " +
                " FROM StockMovement sm " +
                " JOIN sm.material as material " +
                " JOIN sm.warehouse as warehouse" +
                " WHERE sm.isCancelled != true " +
                " AND material.id = :materialId AND warehouse.id = :warehouseId ",
                [materialId: stockMovement.materialId, warehouseId: stockMovement.warehouseId])[0] ?: 0

        stockMovement.afterQuantity = stockMovement.beforeQuantity + stockMovement.enteredQuantity
        stockMovement.save(flush:true)

        return [stockMovement: stockMovement]

    }
}

