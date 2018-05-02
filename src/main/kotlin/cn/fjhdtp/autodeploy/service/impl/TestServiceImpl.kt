package cn.fjhdtp.autodeploy.service.impl

import org.springframework.stereotype.Component
import service.ITestService

@Component
class TestServiceImpl: ITestService {

    override fun runTest(): String {
        return "哈喽"
    }
}