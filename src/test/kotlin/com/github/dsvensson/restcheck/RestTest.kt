package com.github.dsvensson.restcheck

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RestTest : BaseTest() {
    @Test
    fun testSample() {
        val actual = target("simple")
                .queryParam("a", 1)
                .queryParam("b", 9)
                .request()
                .get()
                .readEntity(Int::class.java)
        assertEquals(10, actual)
    }

    @Test
    fun testSample2() {
        val actual = target("simple")
                .queryParam("a", -9)
                .queryParam("b", 9)
                .request()
                .get()
                .readEntity(Int::class.java)
        assertEquals(0, actual)
    }

    @Property
    fun testProper(@ForAll a: Int, @ForAll b: Int) {
        val actual = target("simple")
                .queryParam("a", a)
                .queryParam("b", b)
                .request()
                .get()
                .readEntity(Int::class.java)
        assertEquals(a + b, actual)
    }

    @Property
    fun testProper2(@ForAll a: Int, @ForAll b: Int) {
        val actual = target("simple")
                .queryParam("a", a)
                .queryParam("b", b)
                .request()
                .get()
                .readEntity(Int::class.java)
        assertEquals(a + b, actual)
    }
}