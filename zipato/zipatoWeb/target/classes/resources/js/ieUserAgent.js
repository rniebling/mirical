// Check to see if jQuery is loaded. If not, load it from the public jQuery CDN.

if (typeof jQuery == 'undefined') {
    // Load the latest jQuery library from jQuery
	var pathArray2 = window.location.pathname.split( '/' );

	document.write("\<script src='/".concat(pathArray2[1] , "/weblets/js/jquery-1.11.1.min.js' type='text/javascript'>\<\/script>"));
}

// Create new ieUserAgent object
var ieUserAgent = {
    init: function () {
        // Get the user agent string
        var ua = navigator.userAgent;
        this.compatibilityMode = false;
        
        // Detect whether or not the browser is IE
        var ieRegex = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
        if (ieRegex.exec(ua) == null)
            this.exception = "The user agent detected does not contai Internet Explorer.";

        // Get the current "emulated" version of IE
        this.renderVersion = parseFloat(RegExp.$1);
        this.version = this.renderVersion;

        // Check the browser version with the rest of the agent string to detect compatibility mode
        if (ua.indexOf("Trident/6.0") > -1) {
            this.version = 10; // IE 10
        }
        else if (ua.indexOf("Trident/5.0") > -1) {      
            this.version = 9;                   // IE 9
        }
        else if (ua.indexOf("Trident/4.0") > -1) {
            this.version = 8;                   // IE 8
        }
        else if (ua.indexOf("MSIE 7.0") > -1)
            this.version = 7;                       // IE 7
        else
            this.version = 6;                       // IE 6
    }
};

// Initialize the ieUserAgent object
ieUserAgent.init();