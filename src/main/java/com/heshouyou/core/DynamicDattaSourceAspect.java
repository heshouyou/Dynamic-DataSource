package com.heshouyou.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author heshouyou
 * @Description  动态数据源通知
 * @Date 2018-01-16 14:41.
 */
@Aspect
@Order(-1)//保证在@Transactional之前执行
@Component
public class DynamicDattaSourceAspect {

    private Logger logger = Logger.getLogger(DynamicDattaSourceAspect.class);

    //改变数据源
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
        String dbid = targetDataSource.name();

        if (!DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
            logger.error("数据源 "+dbid+" 不存在使用默认的数据源 -> " +joinPoint.getSignature());
        }else {
            logger.debug("使用数据源：" + dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
        logger.debug("清除数据源 " + targetDataSource.name() + " !");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
