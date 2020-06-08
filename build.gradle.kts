repositories {
    mavenCentral()
    //maven ("https://dl.bintray.com/kotlin/kotlin-eap")
}

plugins {
    application
    kotlin("jvm") version "1.3.72"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.glassfish.jersey.containers:jersey-container-servlet:2.31")

    runtimeOnly("org.glassfish.jersey.inject:jersey-hk2:2.31")
    runtimeOnly("org.glassfish.jersey.media:jersey-media-json-jackson:2.31")

    runtimeOnly("jakarta.xml.bind:jakarta.xml.bind-api:2.3.3")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("net.jqwik:jqwik:1.3.0")

    testImplementation("org.glassfish.jersey.test-framework:jersey-test-framework-core:2.31") {
        exclude("junit")
    }
    testImplementation("org.glassfish.jersey.test-framework.providers:jersey-test-framework-provider-grizzly2:2.31") {
        exclude("junit")
    }
}


tasks {
    withType(Wrapper::class.java) {
        distributionType = Wrapper.DistributionType.ALL
        gradleVersion = "6.5"
    }

    withType(Test::class.java) {
        useJUnitPlatform {
            includeEngines = setOf("junit-jupiter", "jqwik")
        }

        testLogging {
            showStandardStreams = true
        }
    }

}
