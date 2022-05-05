/*!
 * zeroModal.js
 * http://git.oschina.net/cylansad/zeroModal
 *
 * Copyright 2016, Sad
 */
(function(obj) {

    if (typeof module !== 'undefined' && typeof exports === 'object' && define.cmd) {
        module.exports = obj;
    } else if (typeof define === 'function' && define.amd) {
        define(function() {
            return obj;
        });
    } else {
        window.zeroModal = obj;
    }

}((function($, undefined) {

    var zeroModal = {};

    /**
     * 默认的参数
     * @type {Object}
     */
    var defaultOpt = {
        unique: '', // 模态框的唯一值，默认系统生成UUID，不建议自定义
        title: '', //标题
        content: '', //显示内容
        url: false, //如果输入url，将会根据url返回的内容显示在弹出层中
        iframe: false, //是否需要嵌入iframe，默认false，该参数需要和url一起使用
        width: '400px', //宽度（px、pt、%）
        height: '200px', //高度（px、pt、%）
        transition: false, //是否需要出场动画，默认false
        opacity: 0.2, // 遮罩层的透明度
        overlay: false, //是否需要显示遮罩层，默认true
        overlayClose: false, //是否允许点击遮罩层直接关闭弹出层，默认否
        max: false, // 是否允许最大化
        resize: false, // 是否允许调整大小
        resizeAfterFn: undefined, // 调整大小后触发的事件
        ok: false, //是否启用“ok”按钮，默认false
        okTitle: '确定', //“ok”按钮的显示值，默认为“确定”
        okFn: false, //点击“ok”按钮触发的事件
        cancel: false, //是否启用“cancel”按钮，默认false
        cancelTitle: '关闭', //“cancel”按钮的显示值，默认为“取消”
        cancelFn: true, //点击“cancel”按钮触发的事件
        buttonTopLine: true,
        buttons: [], //自定义的按钮，使用了自定义按钮ok与cancel按钮将不会生效；格式：[{ className: 'zeromodal-btn zeromodal-btn-primary', name: '开始导出' }]
        esc: false, //是否需要按esc键退出弹出层，默认false
        //callbacks
        onOpen: false, //弹出层弹开前事件
        onLoad: false, //弹出层加载显示内容前事件
        onComplete: false, //弹出层加载显示内容后事件
        onCleanup: false, //弹出层关闭前事件
        onClosed: false //弹出层关闭后事件
    };

    // 临时变量,是否已显示
    var _tmp_variate_ishow = false;
    // 临时变量,最后的zindex值
    var _tmp_last_zindex = 1000;
    // 临时变量，记录打开的模态框参数
    var _tmp_zemoModal_opt = {};

    /**
     * 显示模态框
     * @param  {[type]} opt [description]
     * @return {[type]}     [description]
     */
    zeroModal.show = function(opt) {
        // 初始化
        var params = _initParams(opt);

        // 渲染
        _render(params);

        // 重新定位
        _tmp_variate_ishow = true;
        $(window).resize(function() {
            if (_tmp_variate_ishow) {
                _resize(params);
            }
        });

        return params.unique;
    };

    /**
     * 关闭指定模态框
     * @param  {[type]} unique [description]
     * @return {[type]}        [description]
     */
    zeroModal.close = function(unique) {
        _close({ unique: unique });
        _tmp_variate_ishow = false;
        delete _tmp_zemoModal_opt[unique];
    };

    /**
     * 关闭全部模态框
     * @return {[type]} [description]
     */
    zeroModal.closeAll = function() {
        $('[role="zeromodal-loading"]').remove();
        $('.zeromodal-overlay').remove();

        $('.zeromodal-container').each(function() {
            var jobj = $(this);
            var unique = jobj.attr("zero-unique-container");
            if (unique !== undefined && _tmp_zemoModal_opt[unique] !== undefined) {
                var opt = _tmp_zemoModal_opt[unique];

                if (typeof opt.onCleanup === 'function') {
                    opt.onCleanup();
                }
                jobj.remove();
                if (typeof opt.onClosed === 'function') {
                    opt.onClosed();
                }

                delete _tmp_zemoModal_opt[unique];
            }
        });

        _tmp_variate_ishow = false;
    };

    /**
     * 显示alert框
     * @param  {[type]} content [description]
     * @return {[type]}         [description]
     */
    zeroModal.alert = function(content) {
        var _opt = {
            iconClass: 'show-zero2 zeromodal-icon-info',
            iconText: ''
        };

        var params = {};
        $.extend(params, _opt);

        if (typeof content === 'object') {
            $.extend(params, content);
        } else {
            params.content = content;
        }
        _buildAlertInfo(params);
    };

    /**
     * 显示错误alert框
     * @param  {[type]} content [description]
     * @return {[type]}         [description]
     */
    zeroModal.error = function(content) {
        var params = {
            iconDisplay: '<div class="show-zero2 zeromodal-icon zeromodal-error"><span class="x-mark"><span class="line left"></span><span class="line right"></span></span></div>'
        };

        if (typeof content === 'object') {
            $.extend(params, content);
        } else {
            params.content = content;
        }
        _buildAlertInfo(params);
    };

    /**
     * 显示正确alert框
     * @param  {[type]} content [description]
     * @return {[type]}         [description]
     */
    zeroModal.success = function(content) {
        var params = {
            iconDisplay: '<div class="show-zero2 zeromodal-icon zeromodal-success"><span class="line tip"></span><span class="line long"></span><div class="placeholder"></div></div>'
        };

        if (typeof content === 'object') {
            $.extend(params, content);
        } else {
            params.content = content;
        }
        _buildAlertInfo(params);
    };

    /**
     * 显示confirm框
     * @param  {[type]} content [description]
     * @param  {[type]} okFn    [description]
     * @return {[type]}         [description]
     */
    zeroModal.confirm = function(content, okFn) {
        var _opt = {
            iconClass: 'show-zero2 zeromodal-icon-question',
            iconText: '',
        };

        var params = {};
        $.extend(params, _opt);
        if (typeof okFn === 'function') {
            params.okFn = okFn;
        }
        params.cancel = true;

        if (typeof content === 'object') {
            $.extend(params, content);
        } else {
            params.content = content;
        }
        _buildAlertInfo(params);
    };


    // 初始化
    function _initParams(opt) {
        var params = {};
        $.extend(params, defaultOpt);
        $.extend(params, opt);
        if (typeof params.unique === 'undefined' || params.unique === '') {
            params.unique = _getUuid();
        }
        // 将参数记录到临时变量中
        _tmp_zemoModal_opt[params.unique] = params;
        return params;
    }

    // 渲染
    function _render(opt) {
        if (typeof opt.onOpen === 'function') { opt.onOpen(); }

        _buildOverlay(opt);
        _buildModal(opt);
    }

    // 关闭
    function _close(opt) {
        if (typeof opt === 'object') {
            if (typeof opt.onCleanup === 'function') {
                opt.onCleanup();
            }

            $('[zero-unique-overlay="' + opt.unique + '"]').remove();
            $('[zero-unique-container="' + opt.unique + '"]').remove();
            $('[zero-unique-loading="' + opt.unique + '"]').remove();

            if (typeof opt.onClosed === 'function') {
                opt.onClosed();
            }
        }
    }

    /**
     * 构建遮罩层
     * @param  {[type]} opt [description]
     * @return {[type]}     [description]
     */
    function _buildOverlay(opt) {
        _tmp_last_zindex++;

        var _width = $(document).width();
        var _height = $(document).height();

        // 是否需要显示遮罩层
        if (opt.overlay) {
            var _overlay = $('<div zero-unique-overlay="' + opt.unique + '" class="zeromodal-overlay" style="opacity:' + opt.opacity + ';z-index:' + _tmp_last_zindex + ';width:' + _width + 'px;height:' + _height + 'px"></div>');
            $('body').append(_overlay);

            // 是否允许点击遮罩层关闭modal
            if (opt.overlayClose) {
                _overlay.css('cursor', 'pointer');
                _overlay.click(function() {
                    _close(opt);
                });
            } else {
                _overlay.click(function() {
                    _shock($('[zero-unique-container="' + opt.unique + '"]'));
                });
            }
        }
    }

    /**
     * 构建模态框
     * @param  {[type]} opt [description]
     * @return {[type]}     [description]
     */
    function _buildModal(opt) {
        _tmp_last_zindex++;

        //// 获取modal的宽度和高度
        var _width = opt.width.replace('px', '');
        var _height = opt.height.replace('px', '');
        var _wwidth = $(window).width();
        var _wheight = $(window).height();
        // 如果width为%值，则计算具体的width值
        if (_width.indexOf('%') !== -1) {
            _width = (_wwidth * parseInt(_width.replace('%', '')) / 100);
        }
        // 如果height为%值，则计算具体的height值
        if (_height.indexOf('%') !== -1) {
            _height = (_wheight * parseInt(_height.replace('%', '')) / 100);
        }
        if (typeof _width === 'string') _width = parseInt(_width);
        if (typeof _height === 'string') _height = parseInt(_height);

        //// 获取modal的位置
        var _left = (_wwidth - _width) / 2;
        var _top = $(window).scrollTop() + Math.ceil(($(window).height() - _height) / 3);

        //// 构建容器
        var _container = $('<div zero-unique-container="' + opt.unique + '" class="zeromodal-container" style="z-index:' + _tmp_last_zindex + ';width:' + _width + 'px;height:' + _height + 'px;left:' + _left + 'px;top:' + (opt.transition ? _top - 50 : _top) + 'px"></div>');

        //// 构建头部
        var _headerHtml = '<div zeromodal-unqiue-header="' + opt.unique + '" class="zeromodal-header">';
        _headerHtml += '        <div title="关闭" zero-close-unique="' + opt.unique + '" class="zeromodal-close">×</div>';
        if (opt.max) {
            _headerHtml += '    <div title="最大化/取消最大化" zero-max-unique="' + opt.unique + '" class="zeromodal-max"></div>';
        }
        _headerHtml += '        <span class="modal-title">' + opt.title + '</span>';
        _headerHtml += '   </div>';
        var _header = $(_headerHtml);
        _container.append(_header);
        $('body').append(_container);

        // 绑定关闭事件
        $('[zero-close-unique="' + opt.unique + '"]').click(function() {
            _close(_tmp_zemoModal_opt[$(this).attr('zero-close-unique')]);
        });
        // 绑定最大化事件
        $('[zero-max-unique="' + opt.unique + '"]').click(function() {
            if ($(this).attr('max') !== '1') {
                _resize(_tmp_zemoModal_opt[$(this).attr('zero-max-unique')], '90%', '85%');
                $(this).attr('max', '1');
            } else {
                _resize(_tmp_zemoModal_opt[$(this).attr('zero-max-unique')]);
                $(this).attr('max', '0');
            }
            _resizeBodyHeight(opt); // 重置body的高度
        });

        // 出场动画
        if (opt.transition) {
            $('.zeromodal-container').animate({ top: _top }, 300);
        }

        //// 构建内容区
        var _body = $('<div zero-unique-body="' + opt.unique + '" class="zeromodal-body"></div>');
        _container.append(_body);
        _resizeBodyHeight(opt); // 重置body的高度

        // 构建拖拽区
        if (opt.resize) {
            _container.append('<div zero-unique-sweep-tee="' + opt.unique + '" class="zeromodal-sweep-tee"></div>');
            _drag(opt.unique, opt); // 绑定拖拽事件
        }

        if (typeof opt.onLoad === 'function') { opt.onLoad(); }

        // 如果url为空，则直接显示content的内容
        if (!opt.url) {
            // 如果是div方式，则设置overflow-y属性，同时通过ajax获取内容
            $('[zero-unique-body="' + opt.unique + '"]').addClass('zeromodal-overflow-y');

            _body.html(opt.content);
            if (typeof opt.onComplete === 'function') { opt.onComplete(); }
        } else {
            _body.html('<div class="zeromodal-loading1"></div>');
            // 如果iframe为true，则通过iframe的方式加载需要显示的内容
            if (opt.iframe) {
                var _iframe = $('<iframe src="' + opt.url + '" class="zeromodal-frame"></iframe>');
                _body.append(_iframe);
                _iframe.load(function() {
                    $('.zeromodal-loading1').remove();
                    if (typeof opt.onComplete === 'function') { opt.onComplete(); }
                });
            } else {
                // 如果是div方式，则设置overflow-y属性，同时通过ajax获取内容
                $('[zero-unique-body="' + opt.unique + '"]').addClass('zeromodal-overflow-y');
                $.ajax({
                    url: opt.url,
                    dataType: "html",
                    type: "get",
                    success: function(data) {
                        _body.append(data);
                        $('.zeromodal-loading1').remove();
                        if (typeof opt.onComplete === 'function') { opt.onComplete(); }
                    }
                });
            }
        }

        //// 构建尾部区
        _buildFooter(opt, _container);

        if (opt.esc) {
            $('body').one("keyup", function(e) {
                if (e.keyCode === 27) {
                    _close(opt);
                }
            });
        }
    }


    /**
     * 构建尾部
     * @param  {[type]} opt [description]
     * @param  {[type]} _container [description]
     * @return {[type]}     [description]
     */
    function _buildFooter(opt, _container) {
        if (opt.ok || opt.cancel || (opt.buttons !== undefined && opt.buttons.length > 0)) {
            var _footer = '<div class="zeromodal-footer">';
            _footer += opt.buttonTopLine ? '<div class="zeromodal-line"></div>' : '';
            _footer += '        <div zeromodal-btn-container="' + opt.unique + '" class="zeromodal-btn-container"></div>';
            _footer += '   </div>';
            _container.append(_footer);

            if (opt.buttons !== undefined && opt.buttons.length > 0) {
                // 显示自定义的按钮
                var _buttonHtml = '';
                for (var i = 0; i < opt.buttons.length; i++) {
                    var b = opt.buttons[i];
                    _buttonHtml += '<button class="' + (b.className || '') + '"' + (b.attr !== undefined ? ' ' + b.attr : '') + '>' + b.name + '</button>';
                }
                $('[zeromodal-btn-container="' + opt.unique + '"]').append(_buttonHtml);

            } else {
                // 显示默认提供的按钮
                if (opt.ok) {
                    var _ok = $('<button zeromodal-btn-ok="' + opt.unique + '" class="zeromodal-btn zeromodal-btn-primary">' + opt.okTitle + '</button>');
                    $('[zeromodal-btn-container="' + opt.unique + '"]').append(_ok);
                    _ok.click(function() {
                        if (typeof opt.okFn === 'function') {
                            var _r = opt.okFn();
                            if (typeof _r === 'undefined' || _r) {
                                _close(opt);
                            }
                        } else {
                            _close(opt);
                        }
                    });
                }
                if (opt.cancel) {
                    var _cancel = $('<button zeromodal-btn-cancel="' + opt.unique + '" class="zeromodal-btn zeromodal-btn-default">' + opt.cancelTitle + '</button>');
                    $('[zeromodal-btn-container="' + opt.unique + '"]').append(_cancel);
                    _cancel.click(function() {
                        if (typeof opt.cancelFn === 'function') {
                            var _r = opt.cancelFn();
                            if (typeof _r === 'undefined' || _r) {
                                _close(opt);
                            }
                        } else {
                            _close(opt);
                        }
                    });
                }
            }
        }
    }

    /**
     * 构建提示框、确认框等内容
     */
    function _buildAlertInfo(opt) {
        // 初始化
        if (typeof opt === 'undefined' || typeof opt.cancelTitle === 'undefined') {
            opt.cancelTitle = '取消';
        }

        var params = _initParams(opt);
        // params.width = '260px';
        // params.height = '140px';
        params.esc = true;
        params.ok = true;
        params.buttonTopLine = false;
        if (typeof _okFn !== 'undefined') {
            params.okFn = _okFn;
        }
        if (typeof cancelFn !== 'undefined') {
            params.cancelFn = cancelFn;
        }

        var _content = params.content || '';
        var _contentDetail = params.contentDetail || '';
        params.content = '';

        // 渲染
        _render(params);

        // 渲染内容
        var icon;
        if (typeof params.iconDisplay !== 'undefined') {
            icon = $(params.iconDisplay);
        } else {
            icon = $('<div class="zeromodal-icon ' + params.iconClass + '">' + params.iconText + '</div>');
        }
        var text = $('<div class="zeromodal-title1">' + _content + '</div><div class="zeromodal-title2">' + _contentDetail + '</div>');
        $('[zero-unique-body="' + params.unique + '"]').append(icon);
        $('[zero-unique-body="' + params.unique + '"]').append(text);
        $('[zero-unique-body="' + params.unique + '"]').removeClass('zeromodal-overflow-y');

        // 给按钮添加focus
        $('[zeromodal-btn-ok="' + params.unique + '"]').focus();

        // 重新定位
        _tmp_variate_ishow = true;
        $(window).resize(function() {
            if (_tmp_variate_ishow) {
                _resize(params);
            }
        });
    }

    /**
     * 重新设置大小及位置
     * @param  {[type]} opt [description]
     * @return {[type]}     [description]
     */
    function _resize(opt, width, height) {
        // 遮罩层
        $('[zero-unique-overlay="' + opt.unique + '"]').css("width", $(document).width() + 'px').css("height", $(document).height() + 'px');

        // 弹出层
        var _wwidth = $(window).width();
        var _wheight = $(window).height();
        var _width = width !== undefined ? width.replace('px', '') : opt.width.replace('px', '');
        var _height = height !== undefined ? height.replace('px', '') : opt.height.replace('px', '');

        if (_width.indexOf('%') !== -1) {
            _width = (_wwidth * parseInt(_width.replace('%', '')) / 100);
        }
        if (_height.indexOf('%') !== -1) {
            _height = (_wheight * parseInt(_height.replace('%', '')) / 100);
        }
        if (typeof _width === 'string') _width = parseInt(_width);
        if (typeof _height === 'string') _height = parseInt(_height);
        var _left = (_wwidth - _width) / 2;
        var _top = $(window).scrollTop() + Math.ceil(($(window).height() - _height) / 3);
        $('[zero-unique-container="' + opt.unique + '"]').css('width', _width + 'px').css('height', _height + 'px').css('left', _left + 'px').css('top', _top + 'px');
    }

    /**
     * 重新设置位置
     * @param  {[type]} opt [description]
     * @return {[type]}     [description]
     */
    function _resizePostion(opt) {
        var _wwidth = $(window).width();
        var _wheight = $(window).height();
        var _width = parseInt($('[zero-unique-container="' + opt.unique + '"]').css('width').replace('px', ''));
        var _height = parseInt($('[zero-unique-container="' + opt.unique + '"]').css('height').replace('px', ''));

        var _left = (_wwidth - _width) / 2;
        var _top = $(window).scrollTop() + Math.ceil(($(window).height() - _height) / 3);
        $('[zero-unique-container="' + opt.unique + '"]').css('left', _left + 'px').css('top', _top + 'px');
    }

    /**
     * 重置设置body的高度
     * @param  {[type]} opt [description]
     * @return {[type]}     [description]
     */
    function _resizeBodyHeight(opt) {
        var headerHeight = $('[zeromodal-unqiue-header="' + opt.unique + '"]').height();
        var buttonHeight = (opt.ok || opt.cancel || (opt.buttons !== undefined && opt.buttons.length > 0)) ? 60 : 0;

        var height = $('[zero-unique-container="' + opt.unique + '"]').height() - headerHeight - 10 - buttonHeight;
        $('[zero-unique-body="' + opt.unique + '"]').css('height', height);
    }

    /**
     * 元素左右晃动
     * @param  {[type]} jobj [description]
     * @return {[type]}      [description]
     */
    function _shock(jobj) {
        if (jobj.length === 0) {
            return;
        }
        var left = jobj.position().left;
        for (var i = 0; i < 2; i++) {
            jobj.animate({ left: left - 2 }, 50);
            jobj.animate({ left: left }, 50);
            jobj.animate({ left: left + 2 }, 50);
        }
        jobj.animate({ left: left }, 50);
    }

    /**
     * 获取uuid
     * @returns {string}
     */
    function _getUuid() {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
        s[8] = s[13] = s[18] = s[23] = "";
        var uuid = s.join("");
        return uuid;
    }

    /**
     * 拖动改变大小(改变container、body)
     * @param  {[type]} unique [description]
     * @param  {[type]} opt    [description]
     * @return {[type]}        [description]
     */
    function _drag(unique, opt) {
        var timer;
        var mouse_x;
        var mouse_y;
        var moved = false;

        var item = $('[zero-unique-sweep-tee="' + unique + '"]')[0];
        var itemBody = $('[zero-unique-body="' + unique + '"]')[0];
        document.onmousemove = function(e) {
            if ($('[zero-unique-container="' + opt.unique + '"]').size() === 0) {
                return;
            }
            mouse_x = e.pageX;
            mouse_y = e.pageY;
            if (timer !== undefined) {
                moved = true;
            }
        };
        item.onmousedown = function() {
            // 禁用允许选中
            document.onselectstart = function() {
                return false;
            };
            // offsetTop以及offsetTop必须要放在mousedown里面，每次都要计算
            var margin_top = mouse_y - item.offsetTop;
            var margin_left = mouse_x - item.offsetLeft;

            timer = setInterval(function() {
                if (timer && moved) {
                    var to_x = mouse_x - margin_left;
                    var to_y = mouse_y - margin_top;
                    $('.zeromodal-container').css('width', to_x + 3 + 'px').css('height', to_y + 3 + 'px');
                }
            }, 5);
        };
        document.onmouseup = function() {
            if ($('[zero-unique-container="' + opt.unique + '"]').size() === 0) {
                return;
            }

            // 启用允许选中
            document.onselectstart = function() {
                return true;
            };

            clearInterval(timer);
            timer = undefined;
            moved = false;

            // 重新定位
            _resizePostion(opt);
            // 重新设置body高度
            _resizeBodyHeight(opt);

            // 重置大小后触发的事件
            if (opt.resizeAfterFn !== undefined && typeof opt.resizeAfterFn === 'function') {
                opt.resizeAfterFn(opt);
            }
        };
    }

    return zeroModal;

}(jQuery))));
