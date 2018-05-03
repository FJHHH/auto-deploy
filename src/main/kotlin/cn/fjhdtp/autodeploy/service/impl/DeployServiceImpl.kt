package cn.fjhdtp.autodeploy.service.impl

import cn.fjhdtp.autodeploy.context.manager.ApplicationContextManager
import cn.fjhdtp.autodeploy.service.DeployService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*


const val UPLOAD_PATH = "/Users/fjh/Desktop/upload/";

@Component
class DeployServiceImpl(@Autowired private val manager: ApplicationContextManager):DeployService, ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun deploy(jars: List<MultipartFile>, configClassNames: List<String>) {
        val paths = saveJars(jars)
        val classLoader = URLClassLoader(paths.toTypedArray(), this.javaClass.classLoader)
        val classes = mutableListOf<Class<*>>()
        for (name in configClassNames) {
            classes.add(classLoader.loadClass(name))
        }
        manager.registerApplication("test", applicationContext, classLoader, *classes.toTypedArray())
    }

    override fun deployByBasePackages(jars: List<MultipartFile>, basePackages: List<String>, subAppName: String) {
        val paths = saveJars(jars)
        val classLoader = URLClassLoader(paths.toTypedArray(), this.javaClass.classLoader)
        manager.registerApplication(subAppName, applicationContext, classLoader, *basePackages.toTypedArray())
    }

    /**
     * 保存jar文件，返回路径数组
     */
    private fun saveJars(jars: List<MultipartFile>): List<URL> {
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val dirPath = generateDir(uuid)

        return jars.map {
            val path = "$dirPath/${it.originalFilename}"
            val file = File(path)
            it.transferTo(file)
            file.toURI().toURL()
        }
    }

    private fun generateDir(uuid: String): String {
        val path = "$UPLOAD_PATH$uuid"
        val dir = File(path)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return path
    }
}