$(function(){

    const appendBusiness = function(data){
        var businessCode = '<a href="#" class="business-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#business-list')
            .append('<div>' + businessCode + '</div>');
    };

    //Loading books on load page
//    $.get('/books/', function(response)
//    {
//        for(i in response) {
//            appendBook(response[i]);
//        }
//    });

    //Show adding business form
    $('#show-add-business-form').click(function(){
        $('#business-form').css('display', 'flex');
    });

    //Closing adding business form
    $('#business-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

      //Show deleting business form
        $('#show-delete-business-form').click(function(){
            $('#business-delete-form').css('display', 'flex');
        });

        //Closing deleting business form
        $('#business-form').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
        });


    //Getting business
    $(document).on('click', '.business-link', function(){
        var link = $(this);
        var businessId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/businesses/' + businessId,
            success: function(response)
            {
                var code = '<span> Срок: ' + response.date + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    //Adding business
    $('#save-business').click(function()
    {
        var data = $('#business-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/businesses/',
            data: data,
            success: function(response)
            {
                $('#business-form').css('display', 'none');
                var business = {};
                business.id = response;
                var dataArray = $('#business-form form').serializeArray();
                for(i in dataArray) {
                    business[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBusiness(business);
            }
        });
        return false;
    });

  //Deleting business
    $('#delete-business').click(function()
    {
        var data = $('#business-delete-form form').serialize();
        $.ajax({
            method: "DELETE",
            url: '/businesses/' + businessId,
            data: data,
            success: function(response)
            {
                $('#business-delete-form').css('display', 'none');
                var business = {};
                business.id = response;
                var dataArray = $('#business-delete-form form').serializeArray();
                for(i in dataArray) {
                    business[dataArray[i]['id']] = dataArray[i]['value'];
                }
                appendBusiness(business);
            }
        });
        return false;
    });

 //Deleting all
    $('#delete-all').click(function()
    {
        var data = $('#businesses-delete-form form').serialize();
        $.ajax({
            method: "DELETE",
            url: '/businesses/',
            data: data,
            success: function(response)
            {
                $('#businesses-delete-form').css('display', 'none');
                var business = {};
                business.id = response;
                var dataArray = $('#businesses-delete-form form').serializeArray();
                for(i in dataArray) {
                    business[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBusiness(business);
            }
        });
        return false;
    });

});