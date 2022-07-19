<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="./wackr.css" rel="stylesheet">

  <!-- Page starts here -->
  <title>Sign In</title>
</head>

<body>

<!-- Wackr Logo and text header -->
<header>
  <div class="wackrheader">
	<div class="logo-header">
		<a href="./home.html"><image src="assets/wackrlogo.png" alt="WackrLogo"></a>
	</div>
	<div class="text-header">
		<h1>Wackr</h1>
	</div>
  </div>
  <nav>
	<ul class="navigation">
		<li><a href="./home.html">Home</a></li>
		<li><a href="./about.html">About</a></li>
		<li><a href="./solutions.html">Solutions</a></li>
		<li><a href="./signin.html">Sign In</a></li>
	</ul>
  </nav>
</header>
<div class="content">
	<div class="obj-center">
		<h2>Sign In</h1> <!-- Something to break up white space on sides of screen -->
			<p>Organization greeting? Details about login portal?</p>
				<div>
					<p>New to Wackr? Create an account and get started.</p>
					<p class="signup-splash"></p>
					<form id="signup" class="signup-form" action="http://localhost:8080/process/ProcessUser.jsp">
						<b>
						<label for="email">Primary Email</label><br>
						<input type="text" id="email" name="email" placeholder="Primary email address"><br>
						<label for="password">Password</label><br>
						<input type="text" id="password" name="password" placeholder="Create a password"><br>
						<br>
						<input type="submit" value="Submit"><br>
						</b>
					</form>
					<br><span>Don't have an account?<br><br></span>
					<a href="./orgorcust.html"><button class="button-style">Register</button></a>
				</div>
	</div>
</div>



</body>	
	<!-- Page ends here -->
		
<footer class="site-footer">
	<p>Produced by Sentre Solutions</p>
</footer>

</html>