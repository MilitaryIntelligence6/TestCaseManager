package com.tencent.wxpf.casemgr.config

import com.tencent.wxpf.casemgr.handler.Room
import com.tencent.wxpf.casemgr.mapper.TestCaseMapper
import com.tencent.wxpf.casemgr.service.CaseBackupService
import com.tencent.wxpf.casemgr.service.RecordService
import org.mybatis.spring.annotation.MapperScan
import org.springframework.web.socket.server.standard.ServerEndpointExporter
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer
import org.apache.tomcat.websocket.server.WsSci
import org.springframework.beans.factory.annotation.Autowired
import org.apache.catalina.Context
import org.apache.catalina.connector.Connector
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 配置类
 *
 * @author jeeffzheng
 * @date 2020/11/26
 */
@MapperScan("com.tencent.wxpf.casemgr.mapper")
@Configuration
open class ApplicationConfig {
    /**
     * http的端口
     */
    @Value("\${http.port}")
    private val port: Int? = null

    /**
     * https的端口
     */
    @Value("\${server.port}")
    private val httpsPort: Int? = null

    /**
     * tomcat用于找到被注解ServerEndpoint包裹的类
     */
    @Bean
    open fun serverEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter()
    }

    /**
     * 配置一个TomcatEmbeddedServletContainerFactory bean
     */
    @Bean
    open fun servletContainer(): ServletWebServerFactory {
        val tomcat: TomcatServletWebServerFactory = object : TomcatServletWebServerFactory() {
            override fun postProcessContext(context: Context) {
                // 如果要强制使用https，请松开以下注释
                // SecurityConstraint securityConstraint = new SecurityConstraint();
                // securityConstraint.setUserConstraint("CONFIDENTIAL");
                // SecurityCollection collection = new SecurityCollection();
                // collection.addPattern("/*");
                // securityConstraint.addCollection(collection);
                // context.addConstraint(securityConstraint);
            }
        }
        tomcat.addAdditionalTomcatConnectors(createStandardConnector())
        return tomcat
    }

    /**
     * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
     * 但是不能同时在application.properties中同时配置两个connector， 所以要以编程的方式配置HTTP
     * connector，然后重定向到HTTPS connector
     */
    private fun createStandardConnector(): Connector {
        // 默认协议为org.apache.coyote.http11.Http11NioProtocol
        val connector = Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL)
        connector.secure = false
        connector.scheme = "http"
        connector.port = port!!
        connector.redirectPort = httpsPort!!
        return connector
    }

    /**
     * 创建wss协议接口
     */
    @Bean
    open fun tomcatContextCustomizer(): TomcatContextCustomizer {
        return TomcatContextCustomizer { context: Context -> context.addServletContainerInitializer(WsSci(), null) }
    }

    /**
     * 给`WebSocket`的注入依赖
     * 你可能会发现WebSocket已经有了`Component`，为什么不使用`Resource`或者`Autowired`
     * 原因如下：
     * 因为@EndPointServer注解劫持了WebSocket的实例，这里就把Bean的管理权交给了tomcat，tomcat利用反射给每个线程生成每一个websocket实例
     * 通过这样的方式进行线程隔离，所以`WebSocket`下所有的this.xxx看起来应该是会有线程问题，其实本质上并不会因为多个请求而互相干扰
     * 所以两个依赖加上了static，然后通过这样的方式注入，表示两个依赖跟着`WebSocket`这个.class类型进入了方法区，而不是跟着实例进堆
     *
     * 感兴趣可以在websocket类中的方法打断点，进来一个请求去追寻方法栈
     * @see org.apache.tomcat.websocket.pojo.PojoEndpointServer.onOpen
     */
    @Autowired
    fun setWebsocketService(recordService: RecordService?,
                            caseMapper: TestCaseMapper?,
                            caseBackupService: CaseBackupService?) {
//        WebSocket.recordService = recordService;
//        WebSocket.caseMapper = caseMapper;
        Room.caseMapper = caseMapper
        Room.recordService = recordService
        Room.caseBackupService = caseBackupService
    }
}
