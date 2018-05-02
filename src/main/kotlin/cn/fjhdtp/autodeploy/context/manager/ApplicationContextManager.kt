package cn.fjhdtp.autodeploy.context.manager

import cn.fjhdtp.autodeploy.factory.ApplicationContextFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ApplicationContextManager(@Autowired private val factory: ApplicationContextFactory) {

    private val contexts:MutableMap<String, ApplicationContext> = ConcurrentHashMap()

    fun registerApplication(name: String, parent: ApplicationContext, classLoader: ClassLoader, vararg classes: Class<*>) {
          contexts[name] = factory.build(parent, classLoader, *classes)
    }

    fun registerApplication(name: String, parent: ApplicationContext, classLoader: ClassLoader, vararg basePackages: String) {
        contexts[name] = factory.build(parent, classLoader, *basePackages)
    }

    fun getApplication(name: String):ApplicationContext? {
        return contexts[name]
    }
}