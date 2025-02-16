
//login/signUp
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
            '<a href="#" class="button">Order</a>'+
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


let flightId = 0
// Add Passenger Fields as per the User Input for Number of Passengers
function addElement(adultNum, childNum) {
    var i = 0
    var pName = "<div class='form-group grid-temp'><span class='form-label required'>Name</span><input class='form-control p-name' type='text' pattern='[a-zA-Z ]{5,}' required name='pname"+i+1+"'></div>";
    var aAge = "<div class='form-group grid-temp'><span class='form-label required'> Age </span><input class='form-control adultp-age' min='13' max='120' type='number' required name='aAge"+i+1+"'></div>";
    var cAge = "<div class='form-group grid-temp'><span class='form-labelrequired'> Age </span><input class='form-control childp-age' min='1' max='12' type='number' required name='cAge"+i+1+"'></div>";
    var pGender ="<div class='form-group grid-temp'><span class='form-label required'>Gender</span><div class='form-gender-checkbox'><label for='genderp-male'><input type='radio' class='genderp-male' name='gender' required><span></span> Male</label><label for='genderp-female'><input type='radio' class='genderp-female' name='gender'><span></span>Female</label><label for='genderp-none'><input type='radio' class='genderp-none' name='gender'><span></span>Rather Not Say</label></div></div>";
    for(;i<adultNum;i++){
      document.getElementById('PassengerInfo').innerHTML += `<h4 class='plist'>Adult Passenger ${i+1}:</h4>`;
      document.getElementById('PassengerInfo').innerHTML += pName;
      document.getElementById('PassengerInfo').innerHTML += aAge;
      document.getElementById('PassengerInfo').innerHTML += pGender;
    }
    i = 0
    for(;i<childNum;i++){
      document.getElementById('PassengerInfo').innerHTML += `<h4 class='plist'>Child Passenger ${i+1}:</h4>`;
      document.getElementById('PassengerInfo').innerHTML += pName;
      document.getElementById('PassengerInfo').innerHTML += cAge;
      document.getElementById('PassengerInfo').innerHTML += pGender;
    }
}

// Displays the User Input from Homepage Form
function displayInfo(origin, destination, depart, rdate, adultNum, childNum){
  if(rdate=="" || rdate == null){
    document.getElementById('travel-type').innerHTML = "One-Way";
  }
  else{
    document.getElementById('travel-type').innerHTML = "Return Trip";
    document.getElementById('arrivalp').innerHTML = `<h4>Arrival Date : ${rdate}</h4>`;
  }
  document.getElementById('originp').innerHTML = origin.toUpperCase();
  document.getElementById('destinationp').innerHTML = destination.toUpperCase();
  document.getElementById('departp').innerHTML = depart;
  document.getElementById('adultp').innerHTML = adultNum;
  document.getElementById('childp').innerHTML = childNum;
}


// Calculate and print the cost of the ticket
function ticketCost (adultNum, childNum, traveltype) {

  let costAdult = rNum;
  // let roundtrip = document.getElementById('roundtrip').value;
  // if (roundtrip === true ) {
  //     costAdult = costAdult*2*0.75;
  //     }
  if (traveltype === "Business class") {
      costAdult = 2 * costAdult
  }
  if (traveltype === "First class") {
      costAdult = 1.5 * costAdult
  }
  let costChild = Math.floor(costAdult * 0.8);
  let AdultTot = costAdult * adultNum;
  let childTot = costChild * childNum;
  let total = AdultTot + childTot;
  if(childNum != 0){
    document.getElementById('child-cost').innerHTML = `Child Passengers: ${childNum} X ${costChild} = ${childTot} RMB`;
  }
  document.getElementById('adult-cost').innerHTML = `${adultNum} X ${costAdult} = ${AdultTot} RMB`;
  document.getElementById('ticket-cost').innerHTML = `${total} RMB`;
  document.getElementById('cost-text').innerHTML = `Total Cost: ${total} RMB`;
}


// Display Input Details and Ticket Cost at the time of page load
// window.addEventListener('load', () => {
//   const params = (new URL(document.location)).searchParams;
//   const origin = params.get('origin');
//   const destination = params.get('destination');
//   const depart = params.get('depart_date');
//   const rdate = params.get('return-date');
//   const adultNum = params.get('adults');
//   const childNum = params.get('children');
//   const travelType = params.get('travel-class');
//   flightId = params.get('flightId');
//   displayInfo(origin, destination, depart, rdate, adultNum, childNum);
//   addElement(adultNum, childNum);
//   ticketCost(adultNum, childNum, travelType);
//   document.getElementById('result-name').innerHTML = adultNum;
//
// })

//Define flight price and renew it from the back end
let rNum = Math.floor(Math.random() * (100)) + 50;
  const params = (new URL(document.location)).searchParams;
  const origin = params.get('origin');
  const destination = params.get('destination');
  const depart = params.get('depart_date');
  const rdate = params.get('return-date');
  const adultNum = params.get('adults');
  const childNum = params.get('children');
  const travelType = params.get('travel-class');
$(function(){
  $.ajax({
    url: "/ticket/submit?"+"origin="+origin+"&destination="+destination+"&depart_date="+depart+"&adults="+adultNum+"&children="+childNum+"&travel_class="+travelType,
    type: "get",
    dataType: "json",
    success: function(data) {
      $(".container").html("")
      $.each(data.result, function(i, e) {
        $(".container").append(
            '<div class="booking-form">'+
              '<div class="row">'+
                '<div class="col-md-6">'+
                  '<h4><span id="travel-type">'+e.id+'</span></h4>'+
                  '<h1><span id="originp">'+e.depart+'</span> to <span id="destinationp">'+e.destn+'</span></h1>'+
                  '<br>'+
                    '<h4>Departure Date : <span id="departp">'+e.depart_Time+'</span></h4>'+
                    '<span id="arrivalp"></span>'+
                    '<br>'+
            '<h4>DestnTime : <span id="departp">'+e.destn_Time +'</span></h4>'+
            '<span id="arrivalp"></span>'+
            '<br>'+
                '</div>'+
                '<div class="col-md-6">'+
                  '<br>'+
                    '<h3>Ticket Cost : '+e.price+'</h3>'+
        '<hr>'+
                '</div>'+
              '</div>'+
              '<div class="row">'+
                '<div class="col-md-8">'+
                '</div>'+
                '<div class="col-md-4">'+
                  '<br><br>'+

                      '<button class="submit-btn" onclick="sub(this)" name='+e.id+'>Book it</button>'+
                '</div>'+
              '</div>'+
            '</div>'+
            '<br><br>'
        )
      })
    },
    error: function(error) {
      window.alert(error.result)
      // window.location.href = "./index.html"
    }

  });
})

function sub(obj){
  window.location.href="./details.html?flightId="+obj.name+"&adults="+adultNum+"&children="+childNum+"&travel_class="+travelType
}


// Prints the Error message below the Passenger details.
function printErrorPassenger(message){
  document.getElementById('error-message-passenger').innerHTML = `Error: ${message} <br>`;
}




$(function () {
  showData()  //请求数据
  showPage()     //分页操作
});
let pageNum = 1; //设置首页页码
let pageSize = 5;  //设置一页显示的条数
let total;    //总条数
let  pages; //一共多少页


// const params = (new URL(document.location)).searchParams;
// const origin = params.get('origin');
// const destination = params.get('destination');
// const depart = params.get('depart_date');
// const rdate = params.get('return-date');
// const adultNum = params.get('adults');
// const childNum = params.get('children');
// const travelType = params.get('travel-class');

function showData() {
  $.ajax({
    type: "post",
    url: "/ticket/page",//对应controller的URL
    async: false,
    dataType: 'json',
    data: {
      "pageNum": pageNum,
      "pageSize": pageSize,
      "origin":origin,
      "destination":destination,
      "depart":depart,
    },
    success: function (res) {

      total = res.total;  //设置总条数
      pages = total/pageSize;

      // pages =  res.pages;
      // console.log(res);
      let depts = res.data;
      console.log(depts);

      $(".container").html("")
      $.each(res.data, function(i, e) {
        $(".container").append(
            '<div class="booking-form">'+
            '<div class="row">'+
            '<div class="col-md-6">'+
            '<h4><span id="travel-type">'+e.id+'</span></h4>'+
            '<h1><span id="originp">'+e.depart+'</span> to <span id="destinationp">'+e.destn+'</span></h1>'+
            '<br>'+
            '<h4>Departure Date : <span id="departp">'+e.depart_Time+'</span></h4>'+
            '<span id="arrivalp"></span>'+
            '<br>'+
            '<h4>DestnTime : <span id="departp">'+e.destn_Time +'</span></h4>'+
            '<span id="arrivalp"></span>'+
            '<br>'+
            '</div>'+
            '<div class="col-md-6">'+
            '<br>'+
            '<h3>Ticket Cost : '+e.price+'</h3>'+
            '<hr>'+
            '</div>'+
            '</div>'+
            '<div class="row">'+
            '<div class="col-md-8">'+
            '</div>'+
            '<div class="col-md-4">'+
            '<br><br>'+

            '<button class="submit-btn" onclick="sub(this)" name='+e.id+'>Book it</button>'+
            '</div>'+
            '</div>'+
            '</div>'+
            '<br><br>'
        )
      })
    },
    error: function(){
      console.log("test")
      // total = 20;
      // pages = 10;
    }


  });
}
function showPage() {
  layui.use('laypage', function () {
    let laypage = layui.laypage;
    //执行一个laypage实例
    laypage.render({
      elem: 'demo-laypage-normal-2',  //注意laypage是 ID，不用加 # 号
      count: total, //数据总数，从服务端得到
      limit: pageSize,   //每页条数设置
      limits: 2,			//可选每页显示条数
      curr: 1,                      //起始页
      prev: '上一页',               //上一页文本
      next: '下一页',                //下一页文本
      theme: '#FF5722',
      first: 1,                    //首页文本
      last: pages,                   //尾页文本
      layout: ['prev', 'page', 'next'],
      jump: function (obj, first) {  //触发分页后的回调
        //obj包含了当前分页的所有参数，第一次加载first为true
        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
        console.log(obj.limit); //得到每页显示的条数
        pageNum = obj.curr;  //改变当前页码
        pageSize = obj.limit;
        //首次不执行，一定要加此判断，否则初始时会无限刷新
        if (!first) {
          showData()  //加载数据
        }
      }
    });
  });
}


