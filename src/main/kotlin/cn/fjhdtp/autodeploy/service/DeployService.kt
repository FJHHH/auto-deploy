package cn.fjhdtp.autodeploy.service

import org.springframework.web.multipart.MultipartFile

interface DeployService {
    fun deploy(jars:List<MultipartFile>, configClassNames: List<String>)

    fun deployByBasePackages(jars:List<MultipartFile>, basePackages: List<String>, subAppName: String)
}