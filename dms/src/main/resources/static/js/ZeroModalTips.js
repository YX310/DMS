/*<![CDATA[*/
function _showInfo(title, info) {
    console.log(info);
    if (window.performance.navigation.type !== 2 && info != null) {
        zeroModal.success({
            content: title,
            contentDetail: info,
        });
    }
}
/*]]>*/
