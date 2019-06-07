plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.20")
    application
}

repositories {
    jcenter()
    maven("https://repo.spring.io/milestone")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.projectreactor.netty:reactor-netty:0.9.0.M1")
//    implementation("io.netty:netty-tcnative-boringssl-static:2.0.25.Final")

    implementation("commons-io:commons-io:2.5")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "io.pivotal.reactor.netty.experiment.ReactorNetty"
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Djavax.net.debug=ssl")
}

tasks.register("plain-netty", JavaExec::class) {
    jvmArgs = listOf("-Djavax.net.debug=ssl")
    classpath = sourceSets["main"].runtimeClasspath
    main = "io.pivotal.reactor.netty.experiment.PlainNetty"
}

tasks.register("reactor-netty", JavaExec::class) {
    jvmArgs = listOf("-Djavax.net.debug=ssl")
    classpath = sourceSets["main"].runtimeClasspath
    main = "io.pivotal.reactor.netty.experiment.ReactorNetty"
}
