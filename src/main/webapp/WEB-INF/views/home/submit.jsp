<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Lấy dữ liệu từ trang contact.jsp
    String firstName = request.getParameter("firstname");
    String lastName = request.getParameter("lastname");
    String country = request.getParameter("country");
    String subject = request.getParameter("subject");

    // Lấy ngày giờ hiện tại
    java.util.Date date = new java.util.Date();

    // Tạo định dạng ngày giờ
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String dateTime = dateFormat.format(date);

    // Tạo nội dung để ghi vào tệp tin
    String fileContent = "Date/Time: " + dateTime + "\n"
            + "First Name: " + firstName + "\n"
            + "Last Name: " + lastName + "\n"
            + "Country: " + country + "\n"
            + "Subject: " + subject + "\n";

    // Ghi nội dung vào tệp tin
    String filePath = "/chuyendeweb/logContact.txt"; // Đường dẫn tới tệp tin .txt trên máy chủ
    try {
        java.io.FileWriter fileWriter = new java.io.FileWriter(filePath, true); // Mở tệp tin ở chế độ ghi tiếp
        java.io.BufferedWriter bufferedWriter = new java.io.BufferedWriter(fileWriter);
        bufferedWriter.write(fileContent);
        bufferedWriter.newLine(); // Xuống dòng
        bufferedWriter.close(); // Đóng tệp tin
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }

    response.sendRedirect("contact.jsp"); // Điều hướng trở lại trang contact.jsp
%>
