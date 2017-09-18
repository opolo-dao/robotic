<html>
<head>
    <title>Upload a file please</title>
</head>
<body>
<h1>Please upload a file</h1>
<form method="post" action="/robotic/admin/upload" enctype="multipart/form-data">
    <input name="name"/>
    <input type="file" name="file" accept="image/*"/>
    <input type="submit"/>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>
</body>
</html>