<?php header('Content-Type:charset=utf-8');
$hostname_localhost ="localhost";
$database_localhost ="hotspots";
$username_localhost ="root";
$password_localhost ="root";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);

mysql_select_db($database_localhost, $localhost);

$unm =$_REQUEST['Username'];
$pass=$_REQUEST['Password'];
$query_search = "select * from register_details where Username = '".$unm."' AND Password = '".$pass."'";
$query_exec = mysql_query($query_search) or die(mysql_error());

$rows = mysql_num_rows($query_exec);
//echo $rows;

		$json["data"]= array();
		
		if(mysql_num_rows($query_exec))
		{
			$json["data"]= array();
		    while($row=mysql_fetch_assoc($query_exec))
			{
		       $product = array();
				$product["dl"] = $row["Data_Limit"];
				
				
                array_push($json["data"], $product);
			}
			echo json_encode($json);
		}
		else
		{
		  echo "0";
		}
		
		

 //$rows = mysql_query("INSERT INTO tbl_use(username, password) VALUES('$username', '$password')");
 
?>
