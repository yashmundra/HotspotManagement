<?php header('Content-Type:charset=utf-8');
$hostname_localhost ="localhost";
$database_localhost ="hotspots";
$username_localhost ="root";
$password_localhost ="root";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);

mysql_select_db($database_localhost, $localhost);

$username =$_REQUEST['a'];
$password =$_REQUEST['b'];
$name =$_REQUEST['c'];
$contact =$_REQUEST['d'];
$address =$_REQUEST['e'];
$limit =$_REQUEST['f'];

 $rows = mysql_query("INSERT INTO register_details VALUES('$username','$password','$name','$contact','$address','$limit')")or die(mysql_error());
 if($rows == 0) { 
 echo "No Such User Found"; 
 }
 else  {
	echo "new User Register"; 
}
?>

