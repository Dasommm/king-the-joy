/**
 * noteForm의 js파일
 */

//javaScript
function handleMouseMove(){
	var rangeValueElement = document.getElementById("range_t");
	var rangeValue = rangeValueElement.value;
	document.getElementById("range-value").innerHTML = rangeValue;
}

function handleMouseMoveSleep() {
	var rangeValueElement = document.getElementById("range_n");
	var rangeValue = rangeValueElement.value;
	console.log(rangeValueElement);
	console.log(rangeValue);
	document.getElementById("range-value-sleep").innerHTML = rangeValue;
}

