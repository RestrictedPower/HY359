function errorAlert(txt) {
	Swal.fire({
		icon: 'error',
		title: 'Oops...',
		text: txt
	});
}

function logoutPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
			console.log("logout");
			window.location.href = '/HY-359/usermanagement.html';
        }
    };
    xhr.open('POST', 'Logout');
    xhr.setRequestHeader('Content-type','application/json');
    xhr.send();
}


function askInput(t, callback){
	    	Swal.fire({
			    title: t,
			    input: 'text',
			    showCancelButton: true        
			}).then((result) => {
		   	 	if (result.value) callback(result.value);
			});
}

function update(type){
	askInput("Enter new "+type+":", function(result){
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "UserManagement");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		   if (xhr.readyState === 4) {
		      console.log("ok?");
		   };
		}
		var data = {};
		data[type] = result;
		var jsonData = JSON.stringify(data);
		xhr.send(jsonData);
	});
}