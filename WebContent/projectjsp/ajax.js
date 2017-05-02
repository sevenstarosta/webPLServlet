var xmlhttp

function showHint(val, temp, bool) {
	//alert("function called");
	if (bool == 1) {
		//alert(temp);
		var scoreval = parseInt(document.getElementById(temp).innerHTML) + val;
		var team = temp;
		//alert("scoreval = " + scoreval);
		//alert("team = " + team);
		document.getElementById(temp).innerHTML = parseInt(document.getElementById(temp).innerHTML) + val;
		//alert("current storedscore = " + document.getElementById("storedscore").value);
//		for (i =0; i<document.getElementsByName("storedscore").length;i++)
//		{
//			document.getElementsByName("storedscore")[i].value=scoreval;
//		}
//		//alert("new storedscore = " + document.getElementById("storedscore").value);
//		//alert("sets storedscore");
//		//alert("current refteam = " + document.getElementById("refteam").value);
//		for (i =0; i<document.getElementsByName("refteam").length;i++)
//		{
//			document.getElementsByName("refteam")[i].value=team;
//		}
//			
		//alert("new refteam = " + document.getElementById("refteam").value);
		//alert("sets refteam");
		
	} else {
		//alert("subtract");
		var scoreval = parseInt(document.getElementById(temp).innerHTML) - val;
		var team = temp;
		document.getElementById(temp).innerHTML = parseInt(document.getElementById(temp).innerHTML) - val;
//		for (i =0; i<document.getElementsByName("storedscore").length;i++)
//		{
//			document.getElementsByName("storedscore")[i].value=scoreval;
//		}
//		for (i =0; i<document.getElementsByName("refteam").length;i++)
//		{
//			document.getElementsByName("refteam")[i].value=team;
//		}
		
	}

	xmlhttp = GetXmlHttpObject();
	if (xmlhttp == null) {
		alert("Your browser does not support XMLHTTP!");
		return;
	}

	// Backend handler - response software component
	var url = "http://localhost:8080/webPLServlet/projectjsp/savescore.jsp";

	// http://localhost:8080/CS4640/welcomeMessage?StringSoFar=value		urlrewriting
	//alert("temp =" + temp);
	var score = parseInt(document.getElementById(temp).innerHTML);
	var team = temp;
	//alert("score =" + score);
	
	url = url + "?ScoreSoFar=" + score;
	url = url + "&teamnum=" + team;
	url = url + "&sid=" + Math.random();

	// How to configure XMLHttpRequest object?
	// Register the callback function named "stateChanged"
	xmlhttp.onreadystatechange = stateChanged; // could also just do = function () { *code* }
	// onreadystatechange has to be all lowercase

	// How to make an asynchronous request?
	xmlhttp.open("get", url, true);

	// How to send the request to the server?
	xmlhttp.send();

}

function stateChanged() {
	// The callback function processes the response from the server	
	if (xmlhttp.readyState == 4) {
		// Update the HTML DOM
		document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
	}
}

function GetXmlHttpObject() {
	// How to create an XMLHttpRequest object?
	if (window.XMLHttpRequest) // the current browser supports XMLHttpRequest object
		return new XMLHttpRequest();

	if (window.ActiveXObject) // IE6 and less
		return new ActiveXObject("Microsoft.XMLHTTP");

	return null;
}