package com.company.learn.pay.alipay.config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 类名：AlipayConfig
 * 功能：基础配置类
 * 详细：设置帐户有关信息及返回路径
 * 版本：3.3
 * 日期：2012-08-10
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * <p/>
 * 提示：如何获取安全校验码和合作身份者ID
 * 1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 * 2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 * 3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
 * <p/>
 * 安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 * 解决方法：
 * 1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 * 2、更换浏览器或电脑，重新登录查询。
 * <p/>
 * 支付的流程就是本地根据配置好的参数和参数生成的签名,通过form表单,自动提交,生成链接提交给支付宝,
 * 支付宝验证处理完后,回调给return_url的地址,
 * 然后在本地通过上传前的参数和回调来的参数再次生成签名对比,来看是否数值有变化,
 * 这样双向签名认证后保证成功后用户在进行自己的业务逻辑处理
 * <p/>
 * 所有没用的配置信息,可以不用删掉,但是不能为空,不然支付宝会报错
 */
public class AlipayConfig {

    private static Properties properties = null;
    private final static String realPath = "";

    static {
        InputStreamReader reader = null;
        try {
            properties = new Properties();
            reader = new InputStreamReader(new FileInputStream(realPath), "utf-8");
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合作身份者ID，以2088开头由16位纯数字组成的字符串
     * <p/>
     * 我是参数内容我是写在了配置文件里面
     */
    public static String partner = properties.getProperty("partner");

    /**
     * 收款支付宝账号，一般情况下收款账号就是签约账号
     */
    public static String seller_id = properties.getProperty("seller_id");

    /**
     * 商户的私钥
     */
    public static String private_key = properties.getProperty("private_key");

    /**
     * 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
     */
    public static String alipay_public_key = properties.getProperty("alipay_public_key");

    /**
     * notify_url 交易过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
     * 这里不需要支付宝主动提供订单状态变化的回调的话,是暂时没有用的,我这里没用到
     */
    public static String notify_url = properties.getProperty("notify_url");

    /**
     * 付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
     * 不能写成http://localhost/
     */
    public static String return_url = properties.getProperty("return_url");

    /**
     * 网站商品的展示地址，不允许加?id=123这类自定义参数
     */
    public static String show_url = properties.getProperty("");//这里我也没用到

    /**
     * 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
     */
    public static String transport = "http";

    /**
     * 调试用，创建TXT日志文件夹路径;没用到
     */
    public static String log_path = properties.getProperty("");

    /**
     * 字符编码格式 目前支持 gbk 或 utf-8
     * 好像必须是小写的(没试过)
     */
    public static String input_charset = "utf-8";

    /**
     * 签名方式 不需修改
     */
    public static String sign_type = "MD5";

    /**
     * 支付类型 ，无需修改
     */
    public static String payment_type = "1";

    /**
     * 调用的接口名，无需修改
     */
    public static String service = "alipay.wap.create.direct.pay.by.user";


}