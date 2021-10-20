<?php
$csv = "files/concap.csv";
$file_out = file($csv);
unset($file_out[$_GET["index"]+1]);
file_put_contents($csv, $file_out);
?>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="refresh" content="1; url=./index.php">
</head>
</html>
