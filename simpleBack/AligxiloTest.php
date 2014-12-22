<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);

echo "AligxiloTest<br>";

print_r($_POST);

print_r(json_encode($_POST));

echo file_put_contents("data", json_encode($_POST). "\n", FILE_APPEND);