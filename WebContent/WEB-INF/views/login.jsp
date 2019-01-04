<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
    <title>Please Login</title>
    
    <style>
    	.col-centered {
    		float: none;
    		margin: 0 auto;
    		transform: translateY(50%);
    		width: 400px;
    	}
    </style>
  </head>
  <body>
    <div class="row">
		<div class="col-centered">
		<form action="login" method="post">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label> <input
					type="email" class="form-control" id="username" name="username"
					aria-describedby="emailHelp" placeholder="Enter email">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control" id="password" name="password"
					placeholder="Password">
			</div>
			<div class="form-check">
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
		</div>
	</div>
    	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
  </body>
</html>