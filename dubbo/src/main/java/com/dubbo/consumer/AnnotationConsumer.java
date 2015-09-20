package com.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.AnnotationService;
import org.springframework.stereotype.Component;

/**
 * Spring-Engine 2015/9/20 21:12
 * fuquanemail@gmail.com
 */
@Component
public class AnnotationConsumer {

    @Reference(version = "1.0.0")
    private AnnotationService annotationService;



}
