CKEDITOR.editorConfig = function( config )
{
	config.extraPlugins = 'imagebrowser';
	config.imageBrowser_listUrl = "../../../../../pages/thb/bitmap/connector.jsf";
	
	// Define changes to default configuration here. For example:
	config.toolbarCanCollapse = true,
	config.enterMode = CKEDITOR.ENTER_BR,
	config.shiftEnterMode = CKEDITOR.ENTER_P,
	config.colorButton_enableMore = true,
  	config.bodyId = 'content',
	config.entities = false,
	config.forceSimpleAmpersand = false,
	config.fontSize_defaultLabel = '12px',
	config.font_defaultLabel = 'Arial',
	config.emailProtection = 'encode',
	config.contentsLangDirection = 'ltr',
	config.language = 'de',
	config.contentsLanguage = 'de',
	config.browserContextMenuOnCtrl = false,
	config.allowedContent= true,
	config.image_previewText = CKEDITOR.tools.repeat('Get-Simple - the best CMS for your purposes. Install it, test it, enjoy it. Free, OpenSource and userfriendly', 50 ),

	config.toolbarLocation = 'top',
	config.skin='kama',
		
	config.removePlugins = 'elementspath',
	
	//config.enableMoreFontColors = true ;
	//config.fontColors = '000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,808080,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF' ;

	//config.fontFormats	= 'p;h1;h2;h3;h4;h5;h6;pre;address;div' ;
	config.font_names		= 'Arial;Arial Narrow;Cambria;Comic Sans MS;Courier New;Segoe UI;Tahoma;Times New Roman;Verdana;Sparkasse;Barcode3of9X',
	config.fontSize_sizes		= '4pt;5pt;5.5pt;6pt;7pt;8pt;9pt;10pt;11pt;12pt;14pt;16pt;18pt;20pt';
	
	// The following value defines which File Browser connector and Quick Upload
	// "uploader" to use. It is valid for the default implementaion and it is here
	// just to make this configuration file cleaner.
	// It is not possible to change this value using an external file or even
	// inline when creating the editor instance. In that cases you must set the
	// values of LinkBrowserURL, ImageBrowserURL and so on.
	// Custom implementations should just ignore it.
	//var _FileBrowserLanguage	= 'php' ;	// asp | aspx | cfm | lasso | perl | php | py
	//var _QuickUploadLanguage	= 'php' ;	// asp | aspx | cfm | lasso | perl | php | py

	// Don't care about the following two lines. It just calculates the correct connector
	// extension to use for the default File Browser (Perl uses "cgi").
	//var _FileBrowserExtension = _FileBrowserLanguage == 'perl' ? 'cgi' : _FileBrowserLanguage ;
	//var _QuickUploadExtension = _QuickUploadLanguage == 'perl' ? 'cgi' : _QuickUploadLanguage ;

	
	//config.filebrowserImageBrowseUrl  = CKEDITOR.basePath + 'filemanager/browser/default/browser.html?Connector=../../../../../../pages/thb/bitmap/connector.jsf';
	//config.filebrowserWindowWidth  = config.screenWidth * 0.7 ;	// 70% ;
	//config.filebrowserWindowHeight = config.screenHeight * 0.7 ;	// 70% ;

};