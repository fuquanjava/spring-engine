package com.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;

/**
 * fuquanemail@gmail.com 2016/6/14 19:26
 * description:
 * 1.0.0
 */
public class AuthFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();

        System.err.println("context json:" + JSON.toJSONString(context, true));


        return invoker.invoke(invocation);
    }
}
