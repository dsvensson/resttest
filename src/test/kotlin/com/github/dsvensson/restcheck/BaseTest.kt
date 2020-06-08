package com.github.dsvensson.restcheck

import net.jqwik.api.lifecycle.AfterProperty
import net.jqwik.api.lifecycle.BeforeProperty
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer
import org.glassfish.jersey.test.DeploymentContext
import org.glassfish.jersey.test.JerseyTest
import org.glassfish.jersey.test.ServletDeploymentContext.forServlet
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory
import org.glassfish.jersey.test.spi.TestContainerFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest : JerseyTest() {
    val seed = Random.nextInt()

    init {
        println("BaseTest instance $seed")
    }

    @BeforeProperty
    @BeforeAll
    fun before() = setUp()

    @AfterProperty
    @AfterAll
    fun after() = tearDown()

    override fun getTestContainerFactory(): TestContainerFactory =
            GrizzlyWebTestContainerFactory()

    override fun configureDeployment(): DeploymentContext =
            forServlet(ServletContainer(ResourceConfig().register(SimpleResource()))).build()
}