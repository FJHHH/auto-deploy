package cn.fjhdtp.autodeploy.servlet

import cn.fjhdtp.autodeploy.context.manager.ApplicationContextManager
import org.springframework.beans.BeansException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.HandlerExecutionChain
import org.springframework.web.servlet.HandlerMapping
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.servlet.http.HttpServletRequest

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class AutoDeployHandlerMapping(private val manager: ApplicationContextManager): HandlerMapping {

    override fun getHandler(request: HttpServletRequest): HandlerExecutionChain? {
        val subApp: String? = request.getHeader("subApplication")
        if (StringUtils.isEmpty(subApp)) {
            return null
        }
        val applicationContext = manager.getApplication(subApp!!) ?: return null
        return try {
            val handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping::class.java)
            handlerMapping.getHandler(request)
        } catch (e: BeansException) {
            null
        }
    }
}