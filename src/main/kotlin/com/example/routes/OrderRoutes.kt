package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrdersRoute() {

    get("/order") {
        if (orderStorage.isNotEmpty())
            call.respond(orderStorage)
        else
            call.respondText("No orders found", status = HttpStatusCode.OK)
    }
}

fun Route.getOrderRoute() {
    get("/order/{id?}") {
        val id = call.parameters["id"] ?: return@get call.respondText (
            "Missing order number", status = HttpStatusCode.BadRequest
        )
        val order = orderStorage.find() {it.number == id} ?: return@get call.respondText(
            "No order with number $id found", status = HttpStatusCode.NotFound
        )

        call.respond(order)
    }
}

fun Route.totalizeOrderRoute() {
    get("/order/{id?}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText("Missing order number", status = HttpStatusCode.BadRequest)
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "No order with number $id found", status = HttpStatusCode.NotFound
        )
        val total = order.contents.sumOf { it.price * it.amount }
        call.respond(total)
    }
}

