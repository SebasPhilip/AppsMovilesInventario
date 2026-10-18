package com.example.inventariomovilti.model

data class Producto (
    var sku: String,
    var nombre: String,
    var descripcion: String,
    var categoria: String,
    var marca: String,
    var modelo: String,
    var cantidad: Int,
    var stockMinimo: Int

)