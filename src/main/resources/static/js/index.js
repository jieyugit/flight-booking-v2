$(function(){
  $.ajax({
    url: "/user/getUserName",
    type: "post",
    success : function(data){
      if(data.code===200){
        $(".header").html("");
        $(".header").append(
          '<a href="./index.html" class="logo">SCU Airlines</a>'+
          '<a href="#" onclick=logout() class="button">SignOut</a>'+
          '<a class="button">'+data.result+'</a>'+
          '<a href="./order.html" class="button">Order</a>'+
          '<a href="#" class="button">Booking Ticket</a>'+
          '<a href="./index.html" class="button active">Home</a>'
        )
      }
    }
  })
})

function logout(){
  $.ajax({
    url: "/user/loginOut",
    type: "get",
    data: {},
    success: function(data) {
      if(data.code===200){
        window.location.href = "./index.html";
      }else{
        window.alert("失败咯~，找BUG吧！")
      }
    }
  });
}


let cityListfordepart = []; //出发
let cityListfordestin = []; //到达
$(function(){
  //获取CityList
  $.ajax({
    url: "/city/depart",
    type: "get",
    dataType: "json",
    data: {},
    success: function(data) {
      cityListfordepart = data

    },
    error: function(error) {
      console.log(error.message);
    }

  });
})


$(function(){
  //获取CityList
  $.ajax({
    url: "/city/destin",
    type: "get",
    dataType: "json",
    data: {},
    success: function(data) {
      cityListfordestin = data

    },
    error: function(error) {
      console.log(error.message);
    }

  });
})


// Activate and DeactivateReturn Date as per the value selected in Radio Buttons
function activateReturnDate(){
  const input = document.getElementById('return-date');
  if(document.getElementById('roundtrip').checked){
    document.getElementById('return-date').disabled = false; 
    input.setAttribute('required', '');
    
  }
  if(document.getElementById('one-way').checked){
    document.getElementById('return-date').disabled = true;
    input.setAttribute('required', '');
  }
}

// Prints the Error message on the Form.
function printError(message){
  document.getElementById('error-message').innerHTML = `Error: ${message} <br>`;
}


// Validate that the Source and Destination cities is among the list of cities of Array and they are not the same
function validateCityList(){
  let originIndex = false;
  let destinationIndex = false;
  let originCity = document.getElementById('origin').value;
  originCity = originCity.toLowerCase();
  let destinationCity = document.getElementById('destination').value;
  destinationCity = destinationCity.toLowerCase();
  //check if the source city is same as destination city
  if(originCity == destinationCity){
    printError("Origin and Destination city cannot be same");
    return false;  
  }

  // Matching the source and destination input with the array of cities
  cityListfordepart.forEach((city)=>{
    if(city == originCity){
      originIndex = true;
    }
  });

  cityListfordestin.forEach((city)=>{
    if(city == destinationCity){
      destinationIndex = true;
    }
  });

  if(!(originIndex)){
    printError("No available flights from the chosen Origin location");
    return false;   
  }
  if(!(destinationIndex)){
    printError("No available flights to the chosen Destination location");
    return false;   
  }
  return true;
}


// Validate that the Trip Type is selected
function validateTripType(){
  if(!(document.getElementById('one-way').checked || document.getElementById('roundtrip').checked)){
    printError("Please select your flight type ( Round Trip/One Way)");
    return false;
  }
  return true ;
} 



// function validateDate(){
//   let rdate = document.getElementById('roundtrip').value;
//   if(document.getElementById('roundtrip').checked && rdate == null){
//     printError("Please select the return date");
//     return false;
//   }
//   return true;
// }


form.addEventListener("submit", (e) => {
  if(!(validateCityList() && validateTripType())){
    e.preventDefault();
  }else{
    $.ajax({
      url: "/ticket/submit",
      type: "post",
      dataType: "json",
      data: $('#form').serialize(),
      success: function(data) {
        if(data.result.length !== 0){
          const data_origin = document.getElementById("origin").value
          const data_destination = document.getElementById("destination").value
          const data_depart_date = document.getElementById("depart-date").value
          const data_adults = document.getElementById("adults").value
          const data_children = document.getElementById("children").value
          window.location.href ="/detailtest.html?"+"origin="+data_origin+"&destination="+data_destination+"&depart_date="+data_depart_date+"&adults="+data_adults+"&children="+data_children+"&travel_class=Business Class"
        }else{
          window.alert("无票")
        }
        //console.log("test")
        // window.location.href = data.result
      },
      error: function(error) {
        console.log("test")
      }

    });

    // window.location.href = "./ticket.html?"
  }
});
