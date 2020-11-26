<?php

/**
 * Model para manipulação de dados dos usuários
 * 
 * @author Edson Melo de Souza
 * @date 2020-03
 * 
 */

include('PDOConnection.php');


class UserModel
{
    private static $pdo;

    function __construct()
    {
        self::$pdo = \PDOConnection::connection();
    }

    public function add($name, $user, $password)
    {
        try {
            $sql = "INSERT INTO users (name, user, password) VALUES (:name, :user, :password)";
            $stmt = self::$pdo->prepare($sql);
            $stmt->bindValue(":name", $name, \PDO::PARAM_STR);
            $stmt->bindValue(":user", $user, \PDO::PARAM_STR);
            $stmt->bindValue(":password", $password, \PDO::PARAM_STR);
            $stmt->execute();

            return true;
        } catch (PDOException $ex) {
            throw $ex;
        }
    }

    public function login($user, $password)
    {
        try {
            $sql = "SELECT id, name, user, password FROM users WHERE user = :user AND password = :password";
            $stmt = self::$pdo->prepare($sql);
            $stmt->bindValue(":user", $user, \PDO::PARAM_STR);
            $stmt->bindValue(":password", $password, \PDO::PARAM_STR);
            $stmt->execute();

            return $stmt->fetchAll(\PDO::FETCH_OBJ);
        } catch (PDOException $ex) {
            throw $ex;
        }
    }
}
