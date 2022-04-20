function t(){
    const now = new Date();
    const y = now.getFullYear();
    let mm = now.getMonth() + 1;
    let d = now.getDate();
    let h = now.getHours();
    let m = now.getMinutes();
    let s = now.getSeconds();
    if (mm<10)
        mm = "0" + mm
    if (d<10)
        d = "0" + d
    if (h<10)
        h = "0" + h
    if (m<10)
        m = "0" + m
    if (s<10)
        s = "0" + s
//	var ho=" AM";
//	if (h>12 && h<24)
//	var ho=" PM";
    const tt = y + "-" + mm + "-" + d + " " + h + ":" + m + ":" + s;
    document.getElementById("time").value=tt;
    document.getElementById("time2").value=tt;
}
//将时间戳转化成年月日时分秒
function formatDate (value) {
    if (typeof (value) == 'undefined') {
        return ''
    } else {
        let date = new Date(parseInt(value))
        let y = date.getFullYear()
        let MM = date.getMonth() + 1
        MM = MM < 10 ? ('0' + MM) : MM
        let d = date.getDate()
        d = d < 10 ? ('0' + d) : d
        let h = date.getHours()
        h = h < 10 ? ('0' + h) : h
        let m = date.getMinutes()
        m = m < 10 ? ('0' + m) : m
        let s = date.getSeconds()
        s = s < 10 ? ('0' + s) : s
        return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s
    }
}
setInterval('t()',500);