$(function(){
    $("#bt1").click(function(){
        //bt1();
        //requestBody();
        //requestBody2();

        //requestParam();

        requestParam2();
    });

    function requestParam2(){
        $.ajax({
            type:"POST",
            url:"request/requestParam2",
            dataType:"json",
            data:"id=1&requestId=2&arg=[1,2,3]",
            success:function(data){
                alert(data.code);
            },
            error:function(data){
                alert("error");
            }
        });


    }


    function requestParam(){
        var data = {"id":1,"requestId":2};
        $.ajax({
            type:"POST",
            url:"request/requestParam",
            dataType:"json",
            data:data,
            success:function(data){
                alert(data);
            },
            error:function(data){
                alert("error");
            }
        });


    }

    function requestBody2(){
        var data = [];

        for(var i=0;i<5;i++){
            data.push(i);
        }

        $.ajax({
            type:"POST",
            url:"request/requestBody2",
            dataType:"json",
            contentType:"application/json", //必须要有消息头设置
            data:JSON.stringify(data),
            success:function(data){
                alert(data);
            },
            error:function(data){
                alert("error");
            }
        });


    }


    function requestBody(){
        var saveData=[];
        var data1={"name":"test","desc":"gz"};
        var data2={"name":"ququ","desc":"gr"};
        saveData.push(data1);
        saveData.push(data2);
        $.ajax({
            type:"POST",
            url:"request/requestBody",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(saveData),
            success:function(data){
                alert(data);
            },
            error:function(data){
                alert("error");
            }
        });

    }
    function bt1(){
        $.ajax({
            type: "POST",
            url: "request/uri/1",
            dataType:"json",
            success: function(data){
                alert("ok");
            },
            error: function(res){
                alert("error");
            }
        });
    }


});