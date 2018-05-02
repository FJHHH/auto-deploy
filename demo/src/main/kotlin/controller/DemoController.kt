package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.ITestService

@RestController
@RequestMapping("/demo")
class DemoController(val service: ITestService) {

    @GetMapping("/hello")
    fun hello(): String {
        return service.runTest()
    }

}