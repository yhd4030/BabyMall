/**
 @Name：Dialoger v1.0.1 web弹出框
 @Author：宝宝
 @Date：2017-02-18
 @PS:定义的CSS样式为PC端的，移动端需要重新定义CSS做适配；
 */

;!function(window, undefined){
    "use strict";
    var utils = {
        hasClass: function(elem, cls) {
            cls = cls || '';
            if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
            return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
        },
        addClass: function(elem, cls) {
            if (!utils.hasClass(elem, cls)) {
                elem.className = elem.className == '' ? cls : elem.className + ' ' + cls;
            }
        },
        removeClass: function(elem, cls) {
            if (utils.hasClass(elem, cls)) {
                var newClass = ' ' + elem.className.replace(/[\t\r\n]/g, '') + ' ';
                while (newClass.indexOf(' ' + cls + ' ') >= 0) {
                    newClass = newClass.replace(' ' + cls + ' ', ' ');
                }
                elem.className = newClass.replace(/^\s+|\s+$/g, '');
            }
        },
        //载入CSS配件
        link: function(href, fn, cssname){

            var head = document.head, link = document.createElement('link');
            if(typeof fn === 'string') cssname = fn;
            var app = (cssname || href).replace(/\.|\//g, '');
            var id = 'dialoger-'+app, timeout = 0;

            link.rel = 'stylesheet';
            link.href = href;
            link.id = id;

            if(!document.querySelector('#'+ id)){
                head.appendChild(link);
            }

            if(typeof fn !== 'function') return;

            //轮询css是否加载完毕
            (function poll() {
                if(++timeout > 8 * 1000 / 100){
                    return window.console && console.error('dialoger.css: Invalid');
                };
                parseInt(document.querySelector('#'+ id).style.width) === 1989 ? fn() : setTimeout(poll, 100);
            }());
        },
        //移除对象
        removeElem: function(id){
            var ele = document.querySelector('#'+ id);
            if(ele) {
                document.body.removeChild(ele);
            }
        },
        lockScroll: function() {
            utils.addClass(document.body, 'dialoger-open');
        },
        unLockScroll: function() {
            utils.removeClass(document.body, 'dialoger-open');
        },
        //判断是否为DOM对象
        isDOM: ( typeof HTMLElement === 'object' ) ?
            function(obj){
                return obj instanceof HTMLElement;
            } :
            function(obj){
                return obj && typeof obj === 'object' && obj.nodeType === 1 && typeof obj.nodeName === 'string';
            }
    }
    //默认内置方法
    var dialoger = {
        v: '1.0.1',
        ready: function(callback){
            dialoger.modalItems = [];
            var cssname = 'skindialogercss', ver = '1110';
            utils.link('css/mc-dialoger.css?v='+ dialoger.v + ver, callback, cssname);
            return this;
        },
        /*
        @Name: alert弹出框
        @Param opt.mes: 内容 【必填】String
        @Param opt.stylename: 样式名称 String
        @Param opt.title：标题 【可选】String：提示
        @Param opt.btnTxt: 按钮文字 【可选】String
        @Param opt.timeout: 定时关闭 【可选】Number
        @Param opt.callback: 回调函数 【可选】Function
        */
        alert: function(opt){

            !opt ? opt = {} : '';
            !opt.btnTxt ? opt.btnTxt = '确定' : '';

            var ID = 'DIALOG_ALERT';

            utils.removeElem(ID);

            var AlertElement = document.createElement('div');
            AlertElement.className = 'mc-dialoger-bg';
            AlertElement.id = ID;

            if(opt.stylename) {
                utils.addClass(AlertElement, opt.stylename);
            }

            var alertHtml = '<div class="popup alert-poput">';

            if(opt.title) {
                alertHtml += '<div class="title">'+ opt.title +'</div>';
            }

            alertHtml += '<div class="text">'+opt.mes+'</div>';
            alertHtml += '<div class="foot">';
            alertHtml += '<span class="button primary" id="alertbtn">'+opt.btnTxt+'</span>';
            alertHtml += '</div>';
            alertHtml += '</div>';

            AlertElement.innerHTML = alertHtml;
            document.body.appendChild(AlertElement);
            utils.lockScroll();

            //closeAlert
            function closeAlert () {
                typeof callback === 'function' && callback();
                document.querySelector('#alertbtn').removeEventListener('click',closeAlert);
                utils.unLockScroll();
                document.body.removeChild(AlertElement);
            }

            document.querySelector('#alertbtn').addEventListener('click', closeAlert, false)

            if(opt.timeout){
                closeAlert();
            }

            return false;
        },
        /*
        @Name: confirm确认提示框
        @Param opt.stylename 【可选】String：样式名
        @Param opt.title：标题 【可选】String：提示
        @Param opt.mes: 内容 【必填】String
        @Param opt.btns: 按钮文字 【可选】Array
        */
        confirm: function(opt){

            !opt ? opt = {} : '';
            !opt.callback ? opt.callback = function(){} : '';
            !opt.btns ? opt.btns = [{txt: '取消', callback: null}, {txt: '确定', callback: opt.callback}] : '';

            var ID = 'DIALOG_CONFIRM';
            utils.removeElem(ID);

            var ConfirmElement = document.createElement('div');
            ConfirmElement.className = 'mc-dialoger-bg';
            ConfirmElement.id = ID;

            if(opt.stylename) {
                utils.addClass(ConfirmElement, opt.stylename);
            }

            var confirmHtml = '<div class="popup">';

            if(opt.title) {
                confirmHtml += '<div class="title">'+ opt.title +'</div>';
            }
            confirmHtml += '<div class="text">'+ opt.mes +'</div>';
            confirmHtml += '<div class="foot">';

            if(opt.btns.length > 0) {
                opt.btns.forEach(function(item, index) {
                    var stylename = '';
                    if(item.stylename) {
                        stylename = item.stylename;
                    }
                    confirmHtml += '<span class="button confirm-btn '+stylename+'" data-index="'+ index +'">'+ item.txt +'</span>';
                })
            }

            confirmHtml += '</div>';
            confirmHtml += '</div>';


            ConfirmElement.innerHTML = confirmHtml;
            document.body.appendChild(ConfirmElement);

            //close confirm
            function closeConfirm(index) {
                var btnObj = opt.btns[index];
                typeof btnObj.callback === 'function' && btnObj.callback();
                document.querySelectorAll('.confirm-btn').forEach(function(item, index) {
                    item.removeEventListener('click',function(){})
                })
                document.body.removeChild(ConfirmElement);
            }

            //绑定事件
            document.querySelectorAll('.confirm-btn').forEach(function(item, index) {
                item.addEventListener('click', function(){
                    closeConfirm(item.getAttribute('data-index'));
                }, false)
            })

        },
        /**
         * @Name Loading层
         * @param ops.mes     提示文字
         * @param ops.type    loader skin
         * 调用方式：
         * dialoger.loading.open({mes: 'title text'});
         */
        loading: {
            open: function(ops){
                !ops ? ops = {} : '';

                if(!ops.mes){
                    ops.mes = '正在加载';
                }
                if(!ops.type){
                    ops.type = 1;
                }

                var loader = '<div class="loader"></div>';
                if(ops.type == 2) {
                    loader = '<div class="loader-ball-pulse"><div></div><div></div><div></div></div>';
                }

                var ID = "MC_DIALOG_LOADING";
                var LoadElement = document.createElement('div');
                LoadElement.className = 'mc-dialoger-loading';
                LoadElement.id = ID;

                var loadHtml ='<div class="box">\
								'+ loader +'<div class="text">'+ops.mes+'</div>\
							</div>';

                LoadElement.innerHTML = loadHtml;
                document.body.appendChild(LoadElement);

            },
            close: function(){
                utils.removeElem('MC_DIALOG_LOADING');
            },
        },
        /**
         * @Name Toast提示层
         * @param opt.mes       提示文字String 【必填】
         * @param opt.type      类型String success or error 【可选】
         * @param opt.timeout   多久后消失Number 毫秒 【默认：2000ms】【可选】
         * @param opt.callback  回调函数Function 【可选】
         */
        toast: function(opt){

            !opt ? opt = {} : '';
            !opt.type ? opt.type = '' : '';
            !opt.timeout ? opt.timeout = 2000 : '';


            clearTimeout(timer);

            var ID = 'DIALOG_TOAST';
            utils.removeElem(ID);

            var ToastElement = document.createElement('div');
            ToastElement.className = 'mc-dialoger-toast';
            ToastElement.id = ID;

            var toastHtml = '<div class="toast'+(opt.type ? ' toast-icon': '')+'">';
            if(opt.type){
                toastHtml += '<span class="icon '+ opt.type +'"></span>';
            }
            toastHtml += '<div class="title">'+opt.mes+'</div>';
            toastHtml += '</div>';

            ToastElement.innerHTML = toastHtml;
            document.body.appendChild(ToastElement);

            var timer = setTimeout(function(){
                utils.removeElem(ID);
                typeof opt.callback === 'function' ? opt.callback() : "";
            },opt.timeout)
        },
        /**
         * @Name Notify 通知
         * @param opt.mes       提示文字String 【必填】
         * @param opt.timeout   多久后消失Number 毫秒 【默认：2000ms】【可选】
         * @param opt.callback  回调函数Function 【可选】
         */
        notify: function(opt) {
            !opt ? opt = {} : '';
            !opt.timeout ? opt.timeout = 3000 : '';

            clearTimeout(timer);

            var ID = 'DIALOG_NOTIFY';
            utils.removeElem(ID);

            var NotifyElement = document.createElement('div');
            NotifyElement.className = 'mc-dialoger-notify';
            NotifyElement.id = ID;

            var notifyHtml = '<div class="text">' +opt.mes+ '</div>';

            NotifyElement.innerHTML = notifyHtml;

            document.body.appendChild(NotifyElement);

            var timer = setTimeout(function(){
                utils.addClass(NotifyElement, 'notify-out');

                var retime = setTimeout(function() {
                    utils.removeElem(ID);
                    clearTimeout(retime);
                }, 500)
                typeof opt.callback === 'function' ? opt.callback() : "";
            },opt.timeout)

        },
        /*
        *Name Modal 弹出层
        *@param opt.id   	ModalID 必填
        *@param opt.title   标题
        *@param opt.stylename 样式名称
        *@param opt.body    body ContentString 【必填】
        */
        modal: {
            open: function(opt) {
                !opt ? opt = {} : '';
                if(!opt.body || !opt.id) {
                    return false;
                }

                if(utils.isDOM(opt.body)) {
                    opt.body = opt.body.innerHTML;
                }

                var ModalID = 'modal_'+opt.id;
                var isHasElem = document.querySelector('#'+ModalID);
                if(isHasElem) {
                    utils.addClass(isHasElem, 'show');
                    return false;
                }

                var ModalElement = document.createElement('div');
                ModalElement.className = 'mc-dialoger-modal show';
                ModalElement.id = ModalID;

                if(opt.stylename) {
                    utils.addClass(ModalElement, opt.stylename);
                }

                var modalHtml = '<div class="modal-box">';
                modalHtml += '<i class="close" id="close_'+ModalID+'"></i>';

                if(opt.title) {
                    modalHtml +=  '<div class="title">'+ opt.title +'</div>';
                }
                modalHtml +=	'<div class="body">'+ opt.body +'</div>';
                modalHtml += '</div>';

                ModalElement.innerHTML = modalHtml;
                document.body.appendChild(ModalElement);
                document.querySelector('#close_'+ModalID).addEventListener('click', function handler() {
                    dialoger.modal.close(ModalElement.id);
                }, false)
            },
            close: function(id) {
                var ModalElement = document.querySelector('#'+id);
                utils.removeClass(ModalElement, 'show');
            }
        }



    }


    dialoger.ready();

    /** 内置成员 */
    window.dialoger = {
        $loading: dialoger.loading,
        $alert: dialoger.alert,
        $confirm: dialoger.confirm,
        $toast: dialoger.toast,
        $notify: dialoger.notify,
        $modal: dialoger.modal
    };

}(window)