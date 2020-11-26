<?php

/**
 * SCRIPT SQL para criação do banco e das tabelas
 * 
 * CREATE DATABASE api_android;
 * USE api_android
 * CREATE TABLE user (
 *	id			int 		id NOT NULL PRIMARY KEY AUTO_INCREMENT,
 *	name		varchar(50),
 *	user		varchar(50),
 *	password	varchar(50)
 *)

 *CREATE TABLE jobs(
 *	id			int 		id NOT NULL PRIMARY KEY AUTO_INCREMENT,
 *	user_id		int,
 *	description	varchar(255),
 *	date		datetime
 *)
 */


/**
 * Model para manipulação dos dados dos jobs
 * 
 * @author Edson Melo de Souza
 * @date 2020-03
 * 
 */

setlocale( LC_ALL, 'pt_BR', 'pt_BR.iso-8859-1', 'pt_BR.utf-8', 'portuguese' ); 
date_default_timezone_set( 'America/Sao_Paulo' );

include('PDOConnection.php');

class JobsModel
{
    private static $pdo;

    function __construct()
    {
        self::$pdo = \PDOConnection::connection();
    }

    public function add($userId, $description)
    {
        try {
            $sql = "INSERT INTO jobs (user_id, description, date) VALUES (:userId, :description, :date)";
            $stmt = self::$pdo->prepare($sql);
            $stmt->bindValue(":userId", $userId, \PDO::PARAM_INT);
            $stmt->bindValue(":description", $description, \PDO::PARAM_STR);
            $stmt->bindValue(":date", date('Y-m-d H:i:s'));
            $stmt->execute();

            return true;
        } catch (PDOException $ex) {
            throw $ex;
        }
    }

    public function listJobs($userId)
    {
        try {
            $sql = "SELECT jobs.id, jobs.description, jobs.date 
                    FROM jobs 
                    INNER JOIN users 
                    ON jobs.user_id = users.id
                    WHERE jobs.user_id = :userId  
                    ORDER BY jobs.date DESC";

            $stmt = self::$pdo->prepare($sql);
            $stmt->bindValue(":userId", $userId, \PDO::PARAM_INT);
            $stmt->execute();

            return $stmt->fetchAll(\PDO::FETCH_OBJ);
        } catch (PDOException $ex) {
            throw $ex;
        }
    }
}
