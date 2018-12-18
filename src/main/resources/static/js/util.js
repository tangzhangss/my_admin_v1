
/**
 * 开启图片懒加载
 */
layui.use('flow', function(){
    var flow = layui.flow;
    //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
    flow.lazyimg();
});

const layer = new Object();
/**
 * 弹出框设置
 */
layer.msg = function (text) {
    layui.use('layer', function(){
        var l = layui.layer;
        // layer.config({
        //     extend: 'util.css',
        //     skin:'layer-msg'
        // });
        l.msg(text);
    });
}
/**
 *静态框
 * @param jquery_attr  静态框的对应jquery对象
 */
layer.openModal = function(jquery_attr){
    layui.use('layer', function() {
        var l = layui.layer;
        l.open({
            type: 1,
            content: jquery_attr,
            shadeClose:true
            //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        });
    });
}
