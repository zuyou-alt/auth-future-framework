package auth.future.component.common.utils;

import cn.hutool.core.exceptions.UtilException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware {
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public SpringUtil() {
    }

    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }

    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }
    public static ListableBeanFactory getBeanFactory() {
        ListableBeanFactory factory = null == beanFactory ? applicationContext : beanFactory;
        if (null == factory) {
            throw new UtilException("No ConfigurableListableBeanFactory or ApplicationContext injected, maybe not in the Spring environment?");
        } else {
            return factory;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return getBeanFactory().getBean(clazz);
    }

    public static String getProperty(String key) {
        return null == applicationContext ? null : applicationContext.getEnvironment().getProperty(key);
    }
}
