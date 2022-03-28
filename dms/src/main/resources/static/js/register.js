let code;

// exec for page load finish
window.onload = createCode

function createCode() {
    code = "";
    const codeLength = 4;
    const checkCode = document.getElementById("num");
    const codeChars = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    for (let i = 0; i < codeLength; i++) {
        code += codeChars[Math.floor(Math.random() * 52)];
    }
    if (checkCode)
    {
        checkCode.className = "code";
        checkCode.innerHTML = code;
    }
}
function validateCode()
{
    const inputCode = document.getElementById("inputCode").value;
    if (inputCode.length <= 0) {
        alert("请输入验证码！");
    }
    else if (inputCode.toUpperCase() !== code.toUpperCase()) {
        alert("验证码输入有误！");
        createCode();
    } else {
        return true
    }
    return false
}
