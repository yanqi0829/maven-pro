package com.myown.alipay.user.query;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayUserElectronicidUserQueryRequest;
import com.alipay.api.response.AlipayUserElectronicidUserQueryResponse;

/**
 * 电子身份证查询用户身份信息
 */
public class AlipayUserQuery {
    public static void main(String[] args) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","","","json","UTF-8","","RSA2");
        AlipayUserElectronicidUserQueryRequest request = new AlipayUserElectronicidUserQueryRequest();
        request.setBizContent("{" +
                "\"barcode\":\"二维码地址\"" +
                " }");
        AlipayUserElectronicidUserQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
            System.out.println(response.getBirthday());
        } else {
            System.out.println("调用失败");
        }
    }
}
