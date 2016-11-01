function officelink(file) {
	try {
		new ActiveXObject("SharePoint.OpenDocuments.4").EditDocument(file);
		return false;
	} catch (e) {

		try {
			new ActiveXObject("SharePoint.OpenDocuments.3").EditDocument(file);
			return false;
		} catch (e) {

			try {
				document.getElementById("winFirefoxPlugin").EditDocument(file);
				return false;
			}

			catch (e2) {
				window
						.alert('Bitte registrieren Sie Ihr Office mit unregister: regsvr32 -u owssupp.dll  register: regsvr32 owssupp.dll als Administrator im office[12/14] Verzeichnis');
				return false;
			}

		}
	}
}