(function(factory, $) {
	window.utils = factory(window, $);

})(function(window,jQuery) {
	/**
	 * 将平台控件from对象属性数据转换成一个json对象数据
	 * 
	 */
	var post = function(url, param, callback, opt) {
		//debugger;
		var loading = null;
		/*
		 * if(opt && opt.loading){ loading = _loading(opt.target); }
		 */
		url = getAllUrl(url);
		$.post(url, param, function(result) {
			// result=result.data||{};
			callback(result.resultValue, result.successful, result.resultHint,
					result);
			if (loading) {
				loading.close();
			}
		});
	}

	var getAllUrl = function(url, isBaseUrl) {
		url = url || '';
		if (url.indexOf('http') != -1) {
			return url;
		}
		var serverName = '';
		if (url.indexOf('-server') != -1) {
			var args = url.split('-server');
			serverName = args[0].substring(args[0].lastIndexOf("\/") + 1,
					args[0].length)
					+ '-server';
		} else {
			if (url.length > 1 && url.substring(0, 1) != '/') {
				url = '/' + url;
			}
			var urls = url.split('/');
			if (urls.length > 2) {
				// serverName = urls[1]+'-'+urls[2]+'-server';
				serverName = urls[1] + '-server';
			}
			url = '/' + serverName + url;
		}
		var baseUrl = getRootPath();
		if (isBaseUrl) {
			return baseUrl + '/' + serverName + '/';
		}
		return baseUrl + url;
	}

	var getParamUrl = function(url, isBaseUrl) {
		url = url || '';
		if (url.indexOf('http') != -1) {
			return url;
		}
		var serverName = '';
		if (url.indexOf('-server') != -1) {
			var args = url.split('-server');
			serverName = args[0].substring(args[0].lastIndexOf("\/") + 1,
					args[0].length)
					+ '-server';
		} else {
			if (url.length > 1 && url.substring(0, 1) != '/') {
				url = '/' + url;
			}
			var urls = url.split('/');
			if (urls.length > 2) {
				// serverName = urls[1]+'-'+urls[2]+'-server';
				serverName = urls[1] + '-server';
			}
			url = '/' + serverName + url;
		}
		return url;
	}

	var getRootPath = function() {
		// 获取当前网址，如： http://localhost:8083/poseidon-web/demo/meun.jsp
		var curWwwPath = window.document.location.href;
		// 获取主机地址之后的目录，如：poseidon-web/demo/meun.jsp
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		// 获取主机地址，如： http://localhost:9000
		var localhostPaht = curWwwPath.substring(0, pos);
		// 获取带"/"的项目名，如：/poseidon-web
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		return localhostPaht;
	}
	
	var getWindowOjb = function(){
		if(window.top===window.self){
			alert("top = self");
			return window;
		}else if(window.top === window.parent){
			alert("top = parent");
			return window.parent;
		}else if(window.parent===window.self){
			alert("parent = self");
			return window.parent;
		}else{
			return window;
		}
	}

	var getParameter = function(name) {
		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
				.exec(window.location.href);
		return results == null ? "" : decodeURIComponent(results[1].replace(
				/\+/g, " "));
	}

	return utils = {
		post : post,
		getAllUrl : getAllUrl,
		getRootPath : getRootPath,
		getParamUrl:getParamUrl,
		getParameter : getParameter,
		getWindowOjb: getWindowOjb
		
	};
});