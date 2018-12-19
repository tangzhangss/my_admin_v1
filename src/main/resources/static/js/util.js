
/**
 * 开启图片懒加载
 */
layui.use('flow', function(){
    let flow = layui.flow;
    //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
    flow.lazyimg();
});

const mylayer = new Object();
/**
 * 弹出框设置
 */
mylayer.msg = function (text) {
    layui.use('layer', function(){
        let l = layui.layer;
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
mylayer.openModal = function(jquery_attr,title,area){
    title = undefined==title?['静态框','font-size:18px;']:title;
    area = undefined==area?['1000px', '600px']:area;
    layui.use('layer', function() {
        let l = layui.layer;
        l.open({
            type: 1,
            content: jquery_attr,
            shadeClose:true,
            area:area,
            title:title
            //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        });
    });
}
/**
 *图片上传
 * elem 绑定元素  如:#demo
 * fuc 回调函数
 **/
mylayer.upload=function(elem,fuc,fucerr){
    layui.use('upload', function(){
        let upload = layui.upload;
        //执行实例
        upload.render({
            elem:elem,//绑定元素
            url: '/util/upload_picture', //上传接口
            done: function(res){
                //上传完毕回调
                fuc(res.data);
            },error: function(res){
                //请求异常回调
                fucerr(res.data);
            }
        });
    });
}
