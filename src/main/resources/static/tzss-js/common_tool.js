/**
 * 通用JS 需要jquery支持
 **/
const COMMON_TOOL = {
    //友好时间转换
    friendlyTime: function(time1){

        var time1 = new Date(time1);//需要转换的时间

        var time2 = new Date();//当前时间
        var time = 0;//时间差
        //需要转换的不可能比当前时间大..如果有 自行添加判断
        time = time2 - time1;
        if (time < 1000) return "刚刚";
        time = parseInt(time / 1000);
        if (time > 86400) {
            var day = parseInt(time / (24 * 60 * 60));
            if (day == 1) {
                let min = time1.getMinutes();
                min = min > 9 ? min : '0' + min;
                return "昨天(" + time1.getHours() + ":" + min + ")";
            } else if (day < 30) {
                return day + "天前";
            } else if (day < 360) {
                var moth = parseInt(day / 30);
                return moth + "个月前";
            } else {
                var year = parseInt(day / 360);
                return year + "年前";
            }
            ;
        } else if (time > 3600) {
            var hour = parseInt(time / (60 * 60));
            return hour + "小时前";
        } else if (time > 60) {
            var hour = parseInt(time / 60);
            return hour + "分钟前";
        } else {
            return time + "秒前";
        }
    },
    postData:function(url,data,successFun=function x(){},failFun=function y(){},completeFun=function z(){}){
        $.ajax({
            url:url,
            type: "POST",
            data: data,
            success:successFun,
            fail:failFun,
            complete:completeFun
        })
    },
    deleteData:function(url,data,successFun=function x(){},failFun=function y(){},completeFun=function z(){}){
        $.ajax({
            url:url,
            type: "DELETE",
            data: data,
            success:successFun,
            fail:failFun,
            complete:completeFun
        })
    },
    putData:function(url,data,successFun=function x(){},failFun=function y(){},completeFun=function z(){}){
        $.ajax({
            url:url,
            type: "PUT",
            data: data,
            success:successFun,
            fail:failFun,
            complete:completeFun
        })
    },
    getData:function(url,data,successFun=function x(){},failFun=function y(){},completeFun=function z(){}){
        $.ajax({
            url:url,
            type: "GET",
            data: data,
            success:successFun,
            fail:failFun,
            complete:completeFun
        })
    },
    checkApiResult:function(result,successFun=function x(){}){
        if(result.code == 1){
            successFun();
        }else{
            alert(result.data);
        }
    },
    formatDate:function(date, fmt) {
        if(fmt == undefined){
            fmt = "yyyy-MM-dd HH:mm:ss";
        }

        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        let o = {
            'M+': date.getMonth() + 1,
            'd+': date.getDate(),
            'H+': date.getHours(),
            'm+': date.getMinutes(),
            's+': date.getSeconds()
        };
        for (let k in o) {
            if (new RegExp(`(${k})`).test(fmt)) {
                let str = o[k] + '';
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : ('00' + str).substr(str.length));
            }
        }
        return fmt;
    },
    tzssRoute:function(url,data,type='forward'){
        data = encodeURI(JSON.stringify(data), "utf-8");
        if(type == "forward"){
            window.location.href = "/tass-route?url="+url+"&data="+data;
        }else if(type == "redirect"){
            window.location.replace("/tass-route?url="+url+"&data="+data);
        }else{
            alert("参数错误");
        }

    },
    /**
     * 获取/设置页面滚动
     */
    getDocumentScrollTop:function(){
        let scrollTop = document.documentElement.scrollTop+document.body.scrollTop;
        return scrollTop;
    },
    setDocumentScrollTop:function(val){
        document.documentElement.scrollTop = val;
        document.body.scrollTop = val;
    },
    /**
     *此方法需要jquery的支持
     */
    scrollBottom:function(){
        $("body").animate(
            { scrollTop:$("body")[0].scrollHeight },
            200);
    },
}