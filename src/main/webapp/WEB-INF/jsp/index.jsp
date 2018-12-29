<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Signature</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
			integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="jumbotron vertical-center">
	<div class="container">
		<div style="color: red">
			${error}
		</div>
		<div style="color: green">
			${message}
		</div>
		<br><br>
		Сreate an electronic signature of the file:
		<br><br>

		<form method="POST" action="${pageContext.request.contextPath}/sign" enctype="multipart/form-data">
			<table>
				<tr>
					<label class="btn btn-primary" for="my-file-selector">
						<input id="my-file-selector" name="file" type="file" style="display:none"
							onchange="$('#upload-file-info').html(this.files[0].name)">
						Select your file
					</label>
					<span class='label label-info' id="upload-file-info"></span>
				</tr>

				<tr>
					<td><input class="btn btn-primary" type="submit" value="Sign file"/></td>
				</tr>
			</table>
		</form>
		<hr>
		Verify an electronic signature of the file:
		<c:choose>
			<c:when test="${isOriginalFile}">
				<div style="color: green">This is the original message.</div><br>
			</c:when>
			<c:when test="${not isOriginalFile && not empty isOriginalFile }">
				<div style="color: red">This is not the original message.</div><br>
			</c:when>
		</c:choose>
		<form method="POST" action="${pageContext.request.contextPath}/verify" enctype="multipart/form-data">
			<table>
				<tr>
					<label class="btn btn-primary" for="my-sign-selector" style="margin-right: 10px">
						<input id="my-sign-selector" name="signature" type="file" style="display:none"
								onchange="$('#upload-sign-info').html(this.files[0].name)">
						Select your sign
					</label>
					<span class='label label-info' id="upload-sign-info"></span>
				</tr>
				<tr>
					<label class="btn btn-primary" for="my-key-selector" style="margin-right: 10px">
						<input id="my-key-selector" name="publicKey" type="file" style="display:none"
							onchange="$('#upload-key-info').html(this.files[0].name)">
						Select your key
						</label>
					<span class='label label-info' id="upload-key-info"></span>
				</tr>
				<tr>
					<label class="btn btn-primary" for="my-file1-selector">
						<input id="my-file1-selector" name="file" type="file" style="display:none"
								onchange="$('#upload-file1-info').html(this.files[0].name)">
						Select your file
					</label>
					<span class='label label-info' id="upload-file1-info"></span>
				</tr>
				<tr>
					<td><input class="btn btn-primary" type="submit" value="Verify file"/></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>