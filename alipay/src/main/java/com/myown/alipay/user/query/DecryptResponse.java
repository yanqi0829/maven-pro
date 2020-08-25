package com.myown.alipay.user.query;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipayEncrypt;
import com.alipay.api.internal.util.AlipaySignature;

public class DecryptResponse {

    public static void main(String[] args) throws AlipayApiException {
        //公钥
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAszOvRkSL5ps4zkzbEkvGf3Rfs9IolDWRj9YgC3rHBWbyJGNlNp7srxbxlO2y25lMo9+2eMLW1S8DPNMorv9LFP5CUW/1t5S6oszgXbGh5cRW9ktc05q0yMjkXmti986flcGKYHHwIzAcBbuE51M1m9mC4T+udSYFyQt/G43ieYUx7EbeDHMTjHJXAMZxcG+1PPcZDXlG/GIE3T7zZBuKdiJAKGBCTldnEPOHnU02sYYw8gGD265H1IYuJ6kvFUWj4J01a94BPE19RsPNUnoAu4vYiRLJnFLDX1UsylwZ/071wz529l8Gxz5CM1gTkM6mKCwXCDnB+NWh/81Pge6fcwIDAQAB";

        //私钥
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfbBDdF4apb2YMcViskPZloPOQGiWkkO3LMGYYfDdz1WEjYKXtvotcuxhGyea0uFjwfFY4dAZzZLzAWFDtoS+PPdUgzGpIsnetIlpdelqBBqLipHCSVcn/DED9spS/xukPp+wWwa7MUUb7ivo8/azj3wb7wzV0RdL/9orcb3Ogpot9hkLMZ6D73o4awA7HIMtTORcQ1Y3/EpmY22lRcptKAvW668X2zHJAoZthROCSxmBC0Y/GwMI+73QEthMfXJJQ+RAf1VIKmrsX6eCvwMkKNEFHm0Sl1ZcdfgQaP0qFfGn0Qh75nmYDbbGb38N0/QiacOxHWxSiEMWa5F1PGSg3AgMBAAECggEBAJMMONnWM23hxP1BJ4u1ArFnLoICvCn5TH2ZHPME0JlVfo4FPcf3c9CK+q+A1/5l9BOOfHECBFk32CqKy8sEXDwByq7AHmEDzQOzpiqHCyfsGR54kyVHYFSS1verWy0xqhO4zjmHWVY4ugKXhI3xZyGgvG5I9utS5HcYz6jYXI0W129ohTOyT++/qnlTMmYJ1IBcdPTBB9Z48/431TdaIL1gDimWaXtXLU00LZs8PYzGNwh3XWjT9njEGnKJo+M/NTF/DXUqXc+B96JxzhTGIxg566m7UvaRv3nlOcrmc/oLy4oxwqJ7qCy/nMJ/NfXnxNquoVHdxLNY814eUVXbm8ECgYEA79mD1PNd4BD4lmShK8R+hRZ74A+aEUAz2VpH+ziDY070AT6xDcvID83wLfexCMN1caCUmeOrSbWJoIMnkbPSwVtfqbFfNfEWuo6PKKXQDttxnckEgQm9JZKOQX18qpKvxKf84HG+vb2sUAKkhBpSyT+4jBQlQuh/X/SNlOXakwcCgYEAqign2Jvc208qZO8Ssyrl/PsnQFVaxbK6wsFIYaigpv8hCBPT/AvPwkhFHtlULjtBovHTxkKvbfaa5JQYwaa/A2TkN+fpJICTxJ6OgnKo/zxIRG5J8KK9w/sblZYX7dODTIR2UJiIVK6le9iV8uAiKOp7AOdznvxpxbOLdXuChVECgYA5noeNIOnsaidN+d6+JPZAAqa/rP/WJRZPoOCp+WmW8eCsa+Mc7VeidFa+tEJTWKFeKrWIQqlji7kEVn2rAwgea1UP7wxwaPqM1rKNkUfqSc2Bmkj6ttw5qnuCQCMy1wpis/iL8sdfzbEXh4r+MVSyBBzRigVpjXEMHDPnEVDxAQKBgDvX3ZOv1XAIGqfe8Ru0RajAh+B3ZHJNVqOyKIu4K7Av4GUf3Bkz6kCw/CZKVvHfg0kUdd+vilVJOtaTQ1WwGFPQQvdqh7N/yPiIryQvwUHq6Juwki6gfc8UXHIylH3fOrlJiPb1flUSCz9QQARoUXtEZDhALojGy0htyelqo8/RAoGBAK8d9sqqwB1YZa5wKh/nY+peCQo4LUprTt1jqkUTIVDPMS9kZFIOuHehbGLzIxbwg+Lxr4X65JCq3/tz1UPxLWOw9ZjH1wmiz1NeCjfj1p1fnzU99fidERRhIxkev4S93Hux+yAFKXM1LxPSEAHeZZs5u78PJiNDd0C3L5PhHOq9";


        String content = "QKvJCDwU0mw8Z9ZNDQO6f9OsGvFsLQTKUKUJMw929EfOFN6DxJEU2dOsdb6UUeIO5qWNKFws9mHobLbrCf41hMC5hA3r3CS7y/1zeqkqV7Tg1X+xLEkL6qV8+/vQDsuN0zgFXyv2qY4ORzL81w1u9tblXJkAz6+JACPGm2sDp44zCtsx+EAhUTRfMyl/TQsNan2qp6PCkzS6Hlukeq4lAlY0sov1ozW08nd9TVSOcvZQn+EN/0VfLvCaX4mdcdaDcwQgil5yP2y2TrRl1xnblyLfEfkOof3UUnsD9IrNE5F431ECYnwQFIHG8syUyEcP2Spmj6Ugu4vq9ti1upSIHg==";
        String miyao = "8du/viAWWYZssrJORb/Y7w==";
        //公钥-加密
//        String rsaEncrypt = AlipaySignature.rsaEncrypt(content, publicKey, "UTF-8");
        //私钥-解密
        String rsaDecrypt = AlipaySignature.decrypt(content, privateKey, "UTF-8","RSA2");

        System.out.println("加密前长度 ：" + content.length());
//        System.out.println("加密后 ：" + rsaEncrypt);
        System.out.println("解密后 ：" + rsaDecrypt);
        System.out.println("解密后长度 ：" + rsaDecrypt.length());

        System.out.println(AlipayEncrypt.decryptContent("1j0OeLmTJW5sanPJ5D4Z6sincOo7Tsd77vHiqO9dTFc=","AES",miyao,"UTF-8"));
        System.out.println(AlipayEncrypt.decryptContent("uV7FgGxWJftcYXaf6xwZWQ==","AES",miyao,"UTF-8"));
    }
}
