<?php

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
    echo json_encode(["type:" => "Incorrect Request"]);
    die();
}

include('class/JobsModel.php');


switch ($obj->type) {

    /**
     *  Criar um novo Job
     *  Formato: {"type":"new", "user_id":"value", "description":"value"}
     */
    case 'new':
        try {
            $game = new JobsModel();
            $result = $game->add($obj->user_id, $obj->description);

            if ($result != null) {
                echo json_encode(["type" => $result]);
            } else {
                echo json_encode(["type" => "Job creation failed."]);
            }
        } catch (Exception $ex) {
            # se ocorreu erros com o banco, devolve o erro
            echo json_encode(["type" => $ex->getMessage()]);
        }
        break;

    /**
     *  Recupera a lista de Jobs
     *  Formato: {"type":"jobs", "user_id":"value"}
     */
    case 'jobs':
        try {
            $game = new JobsModel();
            $result = $game->listJobs($obj->user_id);

            if ($result != null) {
                echo json_encode($result);
            } else {
                echo json_encode([["type" => "No jobs found."]]);
            }
        } catch (Exception $ex) {
            echo json_encode([["type" => $ex->getMessage()]]);
        }
        break;

    default:
        echo json_encode([["type" => "Job request failed, check parameters."]]);
}
