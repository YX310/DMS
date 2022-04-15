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
    const tt = y + "-" + mm + "-" + d;
    document.getElementById("time").value=tt;
}
setInterval('t()',500);