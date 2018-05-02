package cn.fjhdtp.autodeploy.factory

import org.springframework.context.ApplicationContext

interface ApplicationContextFactory {
    fun build(parent: ApplicationContext, classLoader: ClassLoader, vararg classes: Class<*>): ApplicationContext

    fun build(parent: ApplicationContext, classLoader: ClassLoader, vararg classes: String): ApplicationContext
}