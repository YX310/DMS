/*<![CDATA[*/
function _showInfo(title, info) {
    console.log(info);
    console.log(window.performance.navigation.type);
    console.log(window.performance);
    if (info != null) {
        zeroModal.success({
            content: title,
            contentDetail: info,
        });
    }
}
/*]]>*/
