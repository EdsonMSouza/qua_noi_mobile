<?php

ini_set("display_errors",1);
ini_set("display_startup_erros",1);
error_reporting(E_ALL);

// Allow from any origin
if (isset($_SERVER['HTTP_ORIGIN'])) {
    header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
    header('Access-Control-Allow-Credentials: true');
    header('Access-Control-Max-Age: 8640000');    // cache for 100 days
}

// Access-Control headers are received during OPTIONS requests
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {

    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_METHOD']))
        header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']))
        header("Access-Control-Allow-Headers: {$_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']}");
}

$json = file_get_contents('php://input');
$obj = json_decode($json);

if ($obj === null && json_last_error() !== JSON_ERROR_NONE) {
    echo json_encode([["type" => "request_incorreto"]]);
    die();
}

include('class/UserModel.php');

/**
 *  Novo Usuário
 *  Formato: {"type":"new", "nome":"value", "usuario":"value", "senha":"value"}
 *  Ex.....: {"type":"new", "name":"Edson Melo de Souza", "user":"edson.melo", "password":"edson123"}
 * 
 */
switch ($obj->type) {
    case 'new':
        try {
            $user = new UserModel();

            if (empty($obj->name) == true || empty($obj->user) == true || empty($obj->password) == true) {
                echo json_encode([["type" => "request_incorreto"]]);
                die();
            }

            $result = $user->add($obj->name, $obj->user, $obj->password);
            if ($result != null) {
                echo json_encode([["type" => $result]]);
            } else {
                echo json_encode([["type" => "erro_ao_criar"]]);
            }
        } catch (Exception $ex) {
            echo json_encode([["type" => $ex->getMessage()]]);
        }
        break;

        /**
         *  Realiza o Login
         *  Formato: {"type":"login", "user":"value", "password":"value"}
         *  Ex.....: {"type":"login", "user":"edson.melo", "password":"edson123"}
         */
    case 'login':
        try {
            $login = new UserModel();

            if (empty($obj->user) == true || empty($obj->password) == true) {
                echo json_encode([["type" => "request_incorreto"]]);
                die();
            }
            $result = $login->login($obj->user, $obj->password);
            if ($result != null) {
                echo json_encode($result);
            } else {
                echo json_encode([["type" => "login_incorreto"]]);
            }
        } catch (Exception $ex) {
            echo json_encode([["type" => $ex->getMessage()]]);
        }
        break;
}
