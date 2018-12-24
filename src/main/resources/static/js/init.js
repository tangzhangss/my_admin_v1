/**
 * Created by Administrator on 2018/12/23.
 */
/**
 * 设置屏幕宽度
 **/
function widnowInit(){
    let windowWidth = $(window).width();

    if(parseInt(windowWidth)>1400){
        $(".container-box").css({
            "width":windowWidth+"px"
        })
    }else {
        $(".container-box").css({
            "width":"1400px"
        })
    }
    console.log("当前窗口大小:",windowWidth);

}
//初始化
widnowInit();
//监听浏览器宽度的改变
window.onresize = function(){
    widnowInit();
};