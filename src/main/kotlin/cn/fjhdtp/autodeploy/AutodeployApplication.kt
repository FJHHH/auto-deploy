package cn.fjhdtp.autodeploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URL
import java.net.URLClassLoader

@SpringBootApplication
class AutodeployApplication

fun main(args: Array<String>) {
    runApplication<AutodeployApplication>(*args)
}