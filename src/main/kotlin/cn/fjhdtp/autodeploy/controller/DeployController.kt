package cn.fjhdtp.autodeploy.controller

import cn.fjhdtp.autodeploy.service.DeployService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/deploy")
class DeployController(private val deployService:DeployService) {

    @PostMapping("/")
    fun deploy(jars:MultipartFile, basePackages: Array<String>):String {

        deployService.deployByBasePackages(listOf(jars), listOf(*basePackages))

        return "ok"
    }
}