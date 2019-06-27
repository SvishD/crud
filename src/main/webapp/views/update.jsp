<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Update</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }

        /* Modal styles */
        .update-user .modal-dialog {
            max-width: 400px;
            margin: 10% auto;
        }

        .modal .modal-header, .modal .modal-body, .modal .modal-footer {
            padding: 20px 30px;
        }

        .modal .modal-content {
            border-radius: 3px;
        }

        .modal .modal-footer {
            background: #ecf0f1;
            border-radius: 0 0 3px 3px;
        }

        .update-user .modal-title {
            background: #435d7d;
            color: #fff;
            padding: 16px 30px;
            font-size: 24px;
            border-radius: 3px 3px 0 0;
        }

        .modal .form-control {
            border-radius: 2px;
            box-shadow: none;
            border-color: #dddddd;
        }

        .modal textarea.form-control {
            resize: vertical;
        }

        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }

        .modal form label {
            font-weight: normal;
        }
    </style>
</head>
<body>
<!-- Edit Modal HTML -->

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div  class="update-user">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form method="post" action="./update">

                            <h2 class="modal-title">Edit <b>User</b> </h2>

                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="hidden" name="id" value="<c:out value="${param.id}"/>">
                                    <label>Name</label>
                                    <input type="text" name="name" class="form-control" value="<c:out value="${user.name}"/>" required>
                                </div>
                                <div class="form-group">
                                    <label>Login</label>
                                    <input type="text" name="login" class="form-control" value="<c:out value="${user.login}"/>" required>
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="text" name="password" class="form-control" value="<c:out value="${user.password}"/>" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-default" href="./users">Cancel</a>
                                <input type="submit" class="btn btn-info" value="Save">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
