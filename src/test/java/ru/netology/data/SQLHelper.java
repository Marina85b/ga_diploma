package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.login"); // исправлено
    private static String password = System.getProperty("db.password");

    public static void clearDB() {
        val cleanOrder = "DELETE FROM order_entity;";
        val cleanPayment = "DELETE FROM payment_entity;";
        val cleanCreditRequest = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            runner.update(conn, cleanOrder);
            runner.update(conn, cleanPayment);
            runner.update(conn, cleanCreditRequest);
        } catch (Exception e) {
            e.printStackTrace(); // теперь видно реальную ошибку
        }
    }

    public static String getPaymentStatus() {
        val codesSQL = "SELECT status FROM payment_entity ORDER BY created DESC;";
        return getData(codesSQL);
    }

    public static String getCreditRequestStatus() {
        val codesSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC;";
        return getData(codesSQL);
    }

    public static String getOrderCount() {
        val codesSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            Long count = runner.query(conn, codesSQL, new ScalarHandler<>());
            return count == null ? "0" : count.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private static String getData(String query) {
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            Object result = runner.query(conn, query, new ScalarHandler<>());
            return result == null ? null : result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

