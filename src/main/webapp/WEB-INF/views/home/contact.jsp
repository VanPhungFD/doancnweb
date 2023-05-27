
<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Form</title>
    <link rel="stylesheet" type="text/css" href="/static/css/contact.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function displayThankYouMessage() {
            var fname = document.getElementById("fname").value;
            var lname = document.getElementById("lname").value;
            var country = document.getElementById("country").value;
            var subject = document.getElementById("subject").value;

            if (fname === '' || lname === '' || country === '' || subject === '') {
                Swal.fire({
                    title: "Vui lòng điền vào trường này!",
                    text: "Vui lòng điền đầy đủ thông tin vào tất cả các trường.",
                    icon: "warning",
                    confirmButtonText: "OK"
                });
                return false; // Ngăn không submit form
            }

            // Xử lý tiếp khi dữ liệu hợp lệ
            // ...

            // Hiển thị thông báo thành công
            Swal.fire({
                title: "Cảm ơn!",
                text: "Chúng tôi đã nhận được thông tin liên hệ của bạn.",
                icon: "success",
                confirmButtonText: "OK"
            }).then((result) => {
                if (result.isConfirmed) {
                    clearForm(); // Xóa các trường nhập liệu
                }
            });
        }

        function clearForm() {
            document.getElementById("contactForm").reset();
        }
    </script>
</head>
<body>

<div class="container">
    <h2>Contact Form</h2>
    <form id="contactForm" action="submit.jsp" method="post" onsubmit="displayThankYouMessage(); return false;">
        <label for="fname">Họ</label>
        <input type="text" id="fname" name="firstname" placeholder="Your name..">
        <label for="lname">Tên</label>
        <input type="text" id="lname" name="lastname" placeholder="Your last name..">
        <label for="email">Email</label>
                <input type="text" id="email" name="youremail" placeholder="Your Email name..">
        <label for="country">Quốc Gia</label>
        <select id="country" name="country">
            <option value="">-- Select country --</option>
            <option value="australia">Australia</option>
            <option value="canada">Canada</option>
            <option value="usa">USA</option>
            <option value="canada">Canada</option>
                        <option value="Việt Nam">USA</option>
                        <option value="nhật bản">Canada</option>
                                    <option value="china">USA</option>
        </select>
        <label for="subject">Message</label>
        <textarea id="subject" name="subject" placeholder="Để lại lời nhắn cho chúng tôi.." style="height:200px"></textarea>
        <div class="form-group">
            <input type="submit" value="Submit">
        </div>
    </form>
</div>

</body>
</html>
