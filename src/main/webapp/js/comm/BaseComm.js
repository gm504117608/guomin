/**
 * 校验邮箱格式的正确性
 * 校验成功返回true， 否则false
 * @param value 邮箱值
 * @returns {boolean}
 */
function isEmail(value) {
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!reg.test(value)) {
        return false;
    }else{
        return true;
    }
}

/**
 * 判断传入参数是否为空
 * 为空返回true，否则false
 * @param sInputs
 * @returns {boolean}
 */
function isNull(sInputs){
    if (typeof sInputs == "object" ) {
        return false;
    }
    if(typeof(sInputs) == "undefined" || sInputs == null || sInputs == ""){
        return true;
    }
    return false;
}

/**
 * ajax调用封装操作
 *
 * @param url
 *            url请求路径
 * @param parameters
 *            共享参数（json串）
 * @param fnSuccessCallback
 *            (必需) 成功回调方法，方法格式：function(mData){}
 * @param fnFailureCallback
 *            失败回调方法，方法格式：function(oRequest, sTextStatus, oErrorThrown)
 *            此回调可以根据情况覆盖框架默认错误处理，框架默认错误处理已满足大部分需求。
 * @param bSync
 *            (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，
 *            请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
 * @param timeOut
 *            ajax响应超时时间
 * @param isLoading
 *            是否启动弹出层屏蔽所有的操作等待请求的完成 （默认为false）； true ： 启动屏蔽层；false ： 不启动屏蔽层
 */
function fw_ajax(url, parameters, fnSuccessCallback, fnFailureCallback, bSync, timeOut, isLoading){
    if(isNull(url)){
        alert("请求url不能为空！");
        return;
    }
    if(isNull(bSync)) {
        bSync = true;
    }
    if(isNull(timeOut)){
        timeOut = 10000;
    }
    if(isNull(isLoading)){
        isLoading = false;
    }
    $.ajax({
        url : url
        ,data : parameters
        ,dataType : 'JSON'
        ,cache : false
        ,async : bSync
        ,contentType : "application/x-www-form-urlencoded; charset=utf-8"
        ,type : 'POST'
        ,timeout : timeOut
        // 在请求出错时调用。传入 XMLHttpRequest 对象，描述错误类型的字符串以及一个异常对象（如果有的话）
        ,error : function (xhr, textStatus, errorThrown) {
            //console.log(textStatus);
            //console.log(errorThrown);
            //console.log(xhr);
            if(!fnFailureCallback){
                alert('数据请求错误！');
            }else{
                fnFailureCallback(xhr, textStatus, errorThrown);
            }
        }
        // 当请求成功时调用。传入返回后的数据，以及包含成功代码的字符串。
        ,success : function (data, textStatus) {
            if(!fnSuccessCallback){
                alert("操作成功！");
            }else{
                fnSuccessCallback(data);
            }
        }
        // 在发送请求之前调用，并且传入一个 XMLHttpRequest 作为参数。
        ,beforeSend : function(xhr){
            if(isLoading){ // 为true需要打开屏蔽层
                openLoadingModal();
            }
        }
        // 当请求完成之后调用这个函数，无论成功或失败。传入 XMLHttpRequest 对象，以及一个包含成功或错误代码的字符串。
        ,complete : function(xhr, textStatus){
            if(isLoading){ // 为true需要关闭屏蔽层
                hideLoadingModal();
            }
        }
    });
}

/**
 * ajax提交操作弹出正在加载中提示并屏蔽操作
 * 直到提交操作完成或者出现错误关闭弹出层；
 * 打开弹出层；
 *
 * @param tip 提示信息
 */
function openLoadingModal(name){
    if(!document.getElementById('loadingModal')){
        if(isNull(name)){
            name = '请稍等，系统正在拼命加载中...';
        }
        var div = document.createElement('div');
        div.innerHTML =
            '<div class="modal fade" id="loadingModal">' +
            '<div style="width: 300px;height: 20px;z-index: 20000;position: absolute;text-align: center;' +
            'left: 50%;top: 50%;margin-left: -100px;margin-top: -10px">' +
            '<div class="progress progress-striped active" style="margin-bottom: 0;">' +
            '<div class="progress-bar" style="width: 100%;"></div></div>' +
            '<h3>' + name + '</h3></div></div>';
        document.body.appendChild(div);
    }
    $("#loadingModal").modal({backdrop: 'static', keyboard: false});
}

/**
 * ajax提交操作弹出正在加载中提示并屏蔽操作
 * 直到提交操作完成或者出现错误关闭弹出层；
 * 关闭弹出层；
 *
 */
function hideLoadingModal(){
    $("#loadingModal").modal("hide");
}
