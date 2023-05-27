<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <title>Customer Feedback Form</title>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <style>
      html, body {
      min-height: 100%;
      }
      body, div, form, input, p {
      padding: 0;
      margin: 0;
      outline: none;
      font-family: Roboto, Arial, sans-serif;
      font-size: 14px;
      color: #666;
      line-height: 22px;
      }
      h1 {
      font-weight: 400;
      }
      h4 {
      margin: 15px 0 4px;
      }
      .testbox {
      display: flex;
      justify-content: center;
      align-items: center;
      height: inherit;
      padding: 3px;
      }
      form {
      width: 100%;
      padding: 20px;
      background: #fff;
      box-shadow: 0 2px 5px #ccc;
      }
      input {
      width: calc(100% - 10px);
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 3px;
      vertical-align: middle;
      }
      .email {
      display: block;
      width: 45%;
      }
      input:hover, textarea:hover {
      outline: none;
      border: 1px solid #095484;
      }
      th, td {
      width: 15%;
      padding: 15px 0;
      border-bottom: 1px solid #ccc;
      text-align: center;
      vertical-align: unset;
      line-height: 18px;
      font-weight: 400;
      word-break: break-all;
      }
      .first-col {
      width: 16%;
      text-align: left;
      }
      table {
      width: 100%;
      }
      textarea {
      width: calc(100% - 6px);
      }
      .btn-block {
      margin-top: 20px;
      text-align: center;
      }
      button {
      width: 150px;
      padding: 10px;
      border: none;
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      border-radius: 5px;
      background-color: #095484;
      font-size: 16px;
      color: #fff;
      cursor: pointer;
      }
      button:hover {
      background-color: #0666a3;
      }
      @media (min-width: 568px) {
      th, td {
      word-break: keep-all;
      }
      }
    </style>
  </head>
  <body>
    <div class="testbox">
      <form id="feedbackForm" action="/">
        <h1>Customer Feedback Form</h1>
        <p>Vui lòng dành vài phút để cung cấp cho chúng tôi phản hồi về dịch vụ của chúng tôi bằng cách điền vào Mẫu phản hồi khách hàng ngắn này. Chúng tôi đang tiến hành nghiên cứu này để đo lường mức độ hài lòng của bạn với chất lượng dịch vụ của chúng tôi. Chúng tôi cảm ơn bạn đã tham gia.</p>
        <hr />
        <h3>Trải nghiệm tổng thể với dịch vụ của chúng tôi</h3>
        <table>
          <tr>
            <th class="first-col"></th>
            <th>Very Good</th>
            <th>Good</th>
            <th>Fair</th>
            <th>Poor</th>
            <th>Very Poor</th>
          </tr>
          <tr>
            <td class="first-col">Bạn đánh giá trải nghiệm tổng thể của mình với dịch vụ của chúng tôi như thế nào?</td>
            <td><input type="radio" value="none" name="rate" /></td>
            <td><input type="radio" value="none" name="rate" /></td>
            <td><input type="radio" value="none" name="rate" /></td>
            <td><input type="radio" value="none" name="rate" /></td>
            <td><input type="radio" value="none" name="rate" /></td>
          </tr>
          <tr>
            <td class="first-col">Vui lòng cho biết mức độ hài lòng của bạn với tính toàn diện của ưu đãi của chúng tôi?</td>
            <td><input type="radio" value="none" name="satisfied" /></td>
            <td><input type="radio" value="none" name="satisfied" /></td>
            <td><input type="radio" value="none" name="satisfied" /></td>
            <td><input type="radio" value="none" name="satisfied" /></td>
            <td><input type="radio" value="none" name="satisfied" /></td>
          </tr>
          <tr>
            <td class="first-col">Bạn đánh giá về giá cả của hàng của chúng tôi như thế nào?</td>
            <td><input type="radio" value="none" name="prices" /></td>
            <td><input type="radio" value="none" name="prices" /></td>
            <td><input type="radio" value="none" name="prices" /></td>
            <td><input type="radio" value="none" name="prices" /></td>
            <td><input type="radio" value="none" name="prices" /></td>
          </tr>
          <tr>
            <td class="first-col">Bạn hài lòng như thế nào với tính kịp thời của việc giao hàng?</td>
            <td><input type="radio" value="none" name="timeliness" /></td>
            <td><input type="radio" value="none" name="timeliness" /></td>
            <td><input type="radio" value="none" name="timeliness" /></td>
            <td><input type="radio" value="none" name="timeliness" /></td>
            <td><input type="radio" value="none" name="timeliness" /></td>
          </tr>
          <tr>
            <td class="first-col">Bạn hài lòng với dịch vụ hỗ trợ khách hàng như thế nào?</td>
            <td><input type="radio" value="none" name="support" /></td>
            <td><input type="radio" value="none" name="support" /></td>
            <td><input type="radio" value="none" name="support" /></td>
            <td><input type="radio" value="none" name="support" /></td>
            <td><input type="radio" value="none" name="support" /></td>
          </tr>
          <tr>
            <td class="first-col">Bạn có muốn giới thiệu sản phẩm/dịch vụ của chúng tôi cho người khác không?</td>
            <td><input type="radio" value="none" name="recommend" /></td>
            <td><input type="radio" value="none" name="recommend" /></td>
            <td><input type="radio" value="none" name="recommend" /></td>
            <td><input type="radio" value="none" name="recommend" /></td>
            <td><input type="radio" value="none" name="recommend" /></td>
          </tr>
        </table>
        <h4>Chúng tôi nên thay đổi điều gì để đáp ứng mong đợi của bạn?</h4>
        <textarea rows="5"></textarea>
        <h4>Email</h4>
        <small>Chỉ khi bạn muốn nghe nhiều hơn từ chúng tôi!!!</small>
        <input class="email" type="text" name="email" />
        <div class="btn-block">
          <button type="submit" href="/">Send Feedback</button>
        </div>
      </form>
   </div>
   <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.6/dist/sweetalert2.all.min.js"></script>
   <script>
     function displayThankYouMessage(event) {
       event.preventDefault(); // Prevent default form submission behavior

       var rate = document.getElementsByName("rate");
       var satisfied = document.getElementsByName("satisfied");
       var prices = document.getElementsByName("prices");
       var timeliness = document.getElementsByName("timeliness");
       var support = document.getElementsByName("support");
       var recommend = document.getElementsByName("recommend");
       var feedback = document.querySelector("textarea");
       var email = document.getElementsByName("email")[0];

       // Check if all fields have been filled
       if (!checkFields(rate) || !checkFields(satisfied) || !checkFields(prices) || !checkFields(timeliness) || !checkFields(support) || !checkFields(recommend) || feedback.value.trim() === '') {
         Swal.fire({
           title: "Vui lòng điền đầy đủ!",
           text: "Vui lòng điền đầy đủ thông tin trong các trường!",
           icon: "warning",
           confirmButtonText: "OK"
         });
         return false; // Prevent form submission
       }

       // Further processing for valid data
       // ...

       // Display success message
       Swal.fire({
         title: "Thank You!",
         text: "Chúc tôi đã nhận được phản hồi của bạn!.",
         icon: "success",
         confirmButtonText: "OK"
       }).then((result) => {
         if (result.isConfirmed) {
           clearForm(); // Clear input fields
         }
       });
     }

     function checkFields(fields) {
       for (var i = 0; i < fields.length; i++) {
         if (fields[i].checked) {
           return true;
         }
       }
       return false;
     }

     function clearForm() {
       document.getElementById("feedbackForm").reset();
     }

     // Listen for form submit event
     document.getElementById("feedbackForm").addEventListener("submit", displayThankYouMessage);
   </script>
  </body>
</html>
