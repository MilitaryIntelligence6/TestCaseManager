package com.tencent.wxpf.casemgr.util

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import kotlin.Throws
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import com.tencent.wxpf.casemgr.util.SpringUtils
import org.springframework.stereotype.Repository

/**
 * Spring相关函数
 *
 * @author jeeffzheng
 * @date 2020/9/3
 */
@Repository
class SpringUtils : BeanFactoryPostProcessor {
    @Throws(BeansException::class)
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        Companion.beanFactory = beanFactory
    }

    companion object {
        var beanFactory: ConfigurableListableBeanFactory? = null
            private set

        /**
         * 根据名称获取对象
         *
         * @param name bean的名称
         * @return Object 一个以所给名字注册的bean的实例
         * @throws org.springframework.beans.BeansException bean没找到的异常
         */
        @Throws(BeansException::class)
        fun <T> getBean(name: String?): T {
            return beanFactory!!.getBean(name) as T
        }

        /**
         * 根据类型获取对象
         *
         * @param clz 类型
         * @return 对象实体
         * @throws org.springframework.beans.BeansException bean没找到的异常
         */
        @Throws(BeansException::class)
        fun <T> getBean(clz: Class<T>?): T {
            return beanFactory!!.getBean(clz)
        }

        /**
         * 根据名称，查看工厂中是否含有此bean
         *
         * @param name bean名称
         * @return boolean
         */
        fun containsBean(name: String?): Boolean {
            return beanFactory!!.containsBean(name)
        }
    }
}
