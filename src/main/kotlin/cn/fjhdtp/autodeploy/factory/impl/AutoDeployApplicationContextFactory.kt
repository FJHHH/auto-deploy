package cn.fjhdtp.autodeploy.factory.impl

import cn.fjhdtp.autodeploy.context.AutoDeployApplicationContext
import cn.fjhdtp.autodeploy.factory.ApplicationContextFactory
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


private val contextInitBlock: AutoDeployApplicationContext.() -> Unit = {
    registerBean("simpleUrlHandlerMapping", SimpleUrlHandlerMapping::class.java, *kotlin.arrayOf())
    registerBean("requestMappingHandlerMapping", RequestMappingHandlerMapping::class.java, *kotlin.arrayOf())
    refresh()
}

@Component
class AutoDeployApplicationContextFactory:ApplicationContextFactory {
    override fun build(parent: ApplicationContext, classLoader: ClassLoader, vararg classes: Class<*>): ApplicationContext {
        return AutoDeployApplicationContext(parent, classLoader, *classes).apply(contextInitBlock)
    }

    override fun build(parent: ApplicationContext, classLoader: ClassLoader, vararg classes: String): ApplicationContext {
        return AutoDeployApplicationContext(parent, classLoader, *classes).apply(contextInitBlock)
    }
}