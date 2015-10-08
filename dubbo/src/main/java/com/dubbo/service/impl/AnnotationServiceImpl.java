package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.service.AnnotationService;

/**
 * Spring-Engine 2015/9/20 21:08
 * fuquanemail@gmail.com
 */
@Service(version = "1.0.0")
public class AnnotationServiceImpl implements AnnotationService {
    @Override
    public String sayAnnotation() {
        return "Annotation";
    }
}
