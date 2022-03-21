/*div插入图片*/
function addText(o){
    var obj = $("#dataTextSend");
    var range, node;
    /*获取到输入框的光标*/
    if(!obj.hasfocus) {
        obj.focus();
    }
    var text = '<img src="/hotSpot/static/img/face/'+o+'.gif">';
    var range, node;
    if(window.getSelection && window.getSelection().getRangeAt) {
        range = window.getSelection().getRangeAt(0);
        range.collapse(false);
        node = range.createContextualFragment(text);
        var c = node.lastChild;
        range.insertNode(node);
        if(c) {
            range.setEndAfter(c);
            range.setStartAfter(c);
        }
        var j = window.getSelection();
        j.removeAllRanges();
        j.addRange(range);
    } else if(document.selection && document.selection.createRange) {
        document.selection.createRange().pasteHTML(text);
    }
}
/*文件input标签改变事件*/
regexImageFilter = /^(?:image\/bmp|image\/gif|image\/jpg|image\/jpeg|image\/png)$/i;
var imgReader = new FileReader();
function alter(){
    var pictureAndVideo = document.getElementById("pictureAndVideo").files[0];
    if (regexImageFilter.test(pictureAndVideo.type)) {
        imgReader.readAsDataURL(pictureAndVideo);
    }else {
        layer.msg("你选择的不是一个有效文件")
    }
}
imgReader.onload=function (ev){
    var imgAndVidShow = document.getElementById("imgAndVidShow");
    imgAndVidShow.style.display="block";
    imgAndVidShow.lastElementChild.setAttribute("src",ev.target.result);
}