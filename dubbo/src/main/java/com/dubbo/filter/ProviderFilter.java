package com.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * fuquanemail@gmail.com
 */
@Activate(group = Constants.PROVIDER, order = -120000)
public class ProviderFilter implements Filter {

    public static final String EXTENSION_NAME = "providerFilter";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        System.err.println("ProviderFilter ---------------------");

        return invoker.invoke(invocation);
    }
}
