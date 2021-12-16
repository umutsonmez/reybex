package grails5

import grails.gorm.transactions.Transactional

@Transactional
class MaterialService {

   Map show(Map params){
       Map response
       if(params.id){
           Material material = Material.read(params.id?.toLong())
           response = [material: material]
       } else {
           List<Material> materials= Material.findAllByIsDelete(false,[readOnly: true])
           response = [data: materials]
       }
       return response
   }

    Map save(Map params){
        Material material

        if (params.id) {
            material=Material.get(params.id)
        }
        else{
            material=new Material()
        }
        material.properties=params

        material.save(flush:true)
        return[id:material?.id]
    }
    void remove(Map params){
        Material material = Material.get(params.id)
        material.isDelete = true
        material.save(flush:true)
    }
}
