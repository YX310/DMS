/*<![CDATA[*/
function _showInfo(title, info) {
    console.log(info);
    if (info != null) {
        zeroModal.success({
            content: title,
            contentDetail: info,
        });
    }
}
/*]]>*/
