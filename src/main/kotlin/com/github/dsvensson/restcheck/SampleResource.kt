package com.github.dsvensson.restcheck


import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import kotlin.math.min
import kotlin.random.Random


@Path("simple")
@Produces(MediaType.APPLICATION_JSON)
class SimpleResource() {
    private val seed = Random.nextInt()

    @GET
    fun get(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int {
        println("SimpleResource instance $seed")
        return min(a + b, 10)
    }
}