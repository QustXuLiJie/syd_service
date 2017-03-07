/**
 * @Company
 * @Title: RestBaseFilter.java
 * @Package com.mobanker.shanyidai.esb.web.intercept
 * @author hantongyang
 * @date 2017-2-4 上午11:49:24
 * @version V1.0
 */
package com.mobanker.shanyidai.esb.application.intercept;

import com.alibaba.dubbo.rpc.*;
import com.mobanker.shanyidai.esb.common.utils.DubboUtil;

/**
 * @ClassName: CustomFilter
 * @Description: 基础的Filter接口
 *
 */
public abstract class CustomFilter implements Filter {

    /**
     * <p>Description: 只有rest的接口才会访问的接口 </p>
     * @author hantongyang
     * @date 2017-2-4 上午11:49:24
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     * @see Filter#invoke(Invoker, Invocation)
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (!DubboUtil.isRest(invoker.getUrl())) {//非rest的接口不进行DubboResult的转换
            return doInvoke(invoker, invocation);
        }
        return doRestInvoke(invoker, invocation);
    }

    /**
     * @Description: dubbo接口使用，抽象的需要几类来实现的执行方法
     * @author hantongyang
     * @date 2017-2-4 上午11:49:24
     * @param invoker
     * @param invocation
     * @return
     */
    public abstract Result doInvoke(Invoker<?> invoker, Invocation invocation) throws RpcException;

    /**
     * @Description: Rest接口使用，抽象的需要几类来实现的执行方法
     * @author hantongyang
     * @date 2017-2-4 上午11:49:24
     * @param invoker
     * @param invocation
     * @return
     */
    public abstract Result doRestInvoke(Invoker<?> invoker, Invocation invocation) throws RpcException;
}
