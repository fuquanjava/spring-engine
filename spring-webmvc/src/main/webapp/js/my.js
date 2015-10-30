/**
 *  fuquan-pc by  2015/10/30.
 */
$(function(){
    $("#bt1").click(function(){
        bt1();
    });


    function bt1(){
        var json = "[{'id':1,'ids':['a','b']},{'id':1,'ids':['c','d']}]";
        $.ajax({
            type: "POST",
            url: "param/btn1.json",
            data: {"json":json},
            dataType:"json",
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            success: function(data){
                alert("ok");
            },
            error: function(res){
                alert("error");
            }
        });
    }


});