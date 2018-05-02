package cn.fjhdtp.autodeploy.aware

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component


@Component
class AutoDeployApplicationContextAware: ApplicationContextAware {
    private lateinit var applicationContext:ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }
}