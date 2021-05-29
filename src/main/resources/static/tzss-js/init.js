/**
 * Created by Administrator on 2018/12/23.
 * 这个文件导入必须在jQuery之后
 */
/**
 * 设置屏幕宽度
 **/
function widnowInit(){
    let windowWidth = $(window).width();
    let windowHeight = $(window).height();

    if(parseInt(windowWidth)>1400){
        $(".container-box").css({
            "width":windowWidth+"px"
        })
    }else {
        $(".container-box").css({
            "width":"1400px"
        })
    }
    let contentHeight=windowHeight-$(".tass-head-nav").height()-1;//减去顶部nav的高度 1是nav的边框 border-box设置了还是占一个px?
    $(".container-box").css({
        "height":contentHeight+"px"
    })

    //console.log("当前窗口大小:",windowWidth,"  ",windowHeight);
}
//初始化
widnowInit();
//监听浏览器宽度的改变zh
window.onresize = function(){
    widnowInit();
};

/**
 * 启动jquery 滚动条插件
 * 只能同时有一个滚动条对象
 */

$(document).ready(function(){
    let param = {
        cursoropacitymax: 0.5,
        cursorcolor: "rgba(144,147,153,.5)"
    }
    $(".tass-scroll").niceScroll(param);
    $(".container-box >section").niceScroll(param);
})
