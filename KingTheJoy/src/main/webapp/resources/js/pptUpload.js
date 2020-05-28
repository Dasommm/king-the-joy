$(function(){
    $('.file-input').on('change', function(e){
        var file = this.files[0]
        $( '#file-list-' + $(this).data('id') ).append('<span>' + file.name + '</span>')
    })
    
    $('.file-input-button').on('click', function(e){
        $( '#file-input-' + $(this).data('id') ).trigger('click');
    })
})