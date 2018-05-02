package cn.fjhdtp.autodeploy.context

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoDeployApplicationContext : AnnotationConfigApplicationContext {

    constructor(parent: ApplicationContext, classLoader: ClassLoader, vararg classes: Class<*>) :super() {
        this.parent = parent
        this.classLoader = classLoader
        register(*classes)
    }
    constructor(parent: ApplicationContext, classLoader: ClassLoader, vararg basePackages: String) :super() {
        this.parent = parent
        this.classLoader = classLoader
        scan(*basePackages)
    }
}