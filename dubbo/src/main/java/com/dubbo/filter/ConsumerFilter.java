package com.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;

/**
 * fuquanemail@gmail.com 2016/6/14 19:26
 * description:
 * 1.0.0
 */
@Activate(group = Constants.CONSUMER,order = -10000)
public class ConsumerFilter implements Filter {

    public static final String EXTENSION_NAME = "consumerFilter";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();

        System.err.println("ConsumerFilter context:" + JSON.toJSONString(context, true));

        return invoker.invoke(invocation);
    }
}
