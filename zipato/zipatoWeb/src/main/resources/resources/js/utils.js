/** Prevent Back-Button */
function noBack() {
	window.history.forward()
}

noBack();

window.onload = noBack;

window.onpageshow = function(evt) {
	if (evt.persisted)
		noBack()
}

window.onunload = function() {
	void (0)
}

/** page loading - performance output */
function performanceTiming() {
	if ( !($.browser.msie && $.browser.version < 9)) {
		
		var now = new Date().getTime();
		var timings = window.performance.timing;
		
		var page_load_time = now - timings.navigationStart;
		var redirect_time = timings.redirectEnd - timings.redirectStart;
		var unload_time = timings.unloadEventEnd - timings.unloadEventStart;
		var app_cache_time = timings.domainLookupStart - timings.fetchStart;
		var dns_time = timings.domainLookupEnd - timings.domainLookupStart;
		var tcp_time = timings.connectEnd - timings.connectStart;
		var request_time = timings.responseStart - timings.requestStart;
		var response_time = timings.responseEnd - timings.responseStart;
		
		console.log("Page load Time: " + page_load_time + "ms");
		console.log("Redirect Time: " + redirect_time + "ms");
		console.log("Unload Time: " + unload_time + "ms");
		console.log("App Cache Time: " + app_cache_time + "ms");
		console.log("DNS Time: " + dns_time + "ms");
		console.log("TCP Time: " + tcp_time + "ms");
		console.log("Request Time: " + request_time + "ms");
		console.log("Response Time: " + response_time + "ms");
	}
}

if (window.addEventListener) {
	window.addEventListener("load", performanceTiming, false);
} else if (window.attachEvent) {
	window.onreadystatechange = performanceTiming;
};

/** click on togglePanel titlebar */
$(document).on("click",
		".ui-panel:has(.ui-panel-titlebar-icon) .ui-panel-titlebar",
		function(e) {
			/*console.log("click:");*/
			$(this).find("a.ui-panel-titlebar-icon").click();
		});

$(document).on("click", ".ui-panel-titlebar-icon", function(e) {
	return false;
});

/** expand/collapse treenode on click label * */
$(document)
		.on(
				"click",
				".ui-treenode-content:has(.folderCollapsed) .ui-treenode-label",
				function(e) {
					console.log("click:");
					this.parentNode.firstChild.click();
					return false;
				});
