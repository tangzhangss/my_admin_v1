
var layer = new Object();
/**
 * 弹出框设置
 */
layer.msg = function (text) {
    layui.use('layer', function(){
        var layer = layui.layer;
        // layer.config({
        //     extend: 'util.css',
        //     skin:'layer-msg'
        // });
        layer.msg(text);
    });
}
/**
 * 开启图片懒加载
 */
layui.use('flow', function(){
    var flow = layui.flow;
    //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
    flow.lazyimg();
});