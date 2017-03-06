package com.company.learn.pay.wxpay;

import com.company.learn.pay.wxpay.entity.WXPayResult;
import com.company.learn.pay.wxpay.util.HttpXmlUtils;
import com.company.learn.pay.wxpay.util.JdomParseXmlUtils;
import com.company.learn.pay.wxpay.util.WXSignUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by dell on 2017/2/28.
 */
@Controller("/wxpay/")
public class CallBack {

    /**
     * 微信支付回调
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/notifyUrlWeixin")
    public void notifyWeixinPayment(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader reader = request.getReader();

            String line = "";
            StringBuffer inputString = new StringBuffer();

            try {
                PrintWriter writer = response.getWriter();

                while ((line = reader.readLine()) != null) {
                    inputString.append(line);
                }
                if (reader != null) {
                    reader.close();
                }
                System.out.println("----[微信回调]接收到的报文---" + inputString.toString());
                if (!StringUtils.isEmpty(inputString.toString())) {
                    WXPayResult wxPayResult = JdomParseXmlUtils.getWXPayResult(inputString.toString());

                    if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code())) {
                        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
                        parameters.put("appid", wxPayResult.getAppid());
                        parameters.put("attach", wxPayResult.getAttach());
                        parameters.put("bank_type", wxPayResult.getBank_type());
                        parameters.put("cash_fee", wxPayResult.getCash_fee());
                        parameters.put("fee_type", wxPayResult.getFee_type());
                        parameters.put("is_subscribe", wxPayResult.getIs_subscribe());
                        parameters.put("mch_id", wxPayResult.getMch_id());
                        parameters.put("nonce_str", wxPayResult.getNonce_str());
                        parameters.put("openid", wxPayResult.getOpenid());
                        parameters.put("out_trade_no", wxPayResult.getOut_trade_no());
                        parameters.put("result_code", wxPayResult.getResult_code());
                        parameters.put("return_code", wxPayResult.getReturn_code());
                        parameters.put("time_end", wxPayResult.getTime_end());
                        parameters.put("total_fee", wxPayResult.getTotal_fee());
                        parameters.put("trade_type", wxPayResult.getTrade_type());
                        parameters.put("transaction_id", wxPayResult.getTransaction_id());

                        //反校验签名
                        String sign = WXSignUtils.createSign("UTF-8", parameters);

                        if (sign.equals(wxPayResult.getSign())) {
                            //TODO  对业务进行处理

//                            writer.write(HttpXmlUtils.backWeixin("SUCCESS", "OK"));
                        } else {
//                            writer.write(HttpXmlUtils.backWeixin("FAIL", "签名失败"));
                        }
                    } else {
//                        writer.write(HttpXmlUtils.backWeixin("FAIL", wxPayResult.getReturn_msg()));

                        System.out.println("---------微信支付返回Fail----------" + wxPayResult.getReturn_msg());
                    }

                    if (writer != null) {
                        writer.close();
                    }
                } else {
//                    writer.write(HttpXmlUtils.backWeixin("FAIL", "未获取到微信返回的结果"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
