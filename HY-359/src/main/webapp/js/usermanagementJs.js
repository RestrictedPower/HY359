function errorAlert(txt) {
	Swal.fire({
		icon: 'error',
		title: 'Oops...',
		text: txt
	});
}

function logoutPOST() {
	/*
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
		const responseData = JSON.parse(xhr.responseText);
        if (xhr.readyState === 4 && xhr.status === 200) {
			console.log("Successfull login");
			window.location.href = '/HY-359/usermanagement.html';
        } else if (xhr.status !== 200) {
			if (xhr.status === 403) {
				errorAlert(responseData['error']);
				return;
			}
			errorAlert('Request failed. Returned status of ' + xhr.status);
        }
    };
	let myForm = document.getElementById('loginForm');
	let formData = new FormData(myForm);
	const data = {};
	formData.forEach((value, key) => (data[key] = value));
	var jsonData = JSON.stringify(data);
    xhr.open('POST', 'Login');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
*/
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