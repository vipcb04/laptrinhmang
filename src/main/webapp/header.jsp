<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
    <div class="header-content">
        <form action="login" method="post">
            <input type="submit" name="action" value="logout" class="logout-btn">
        </form>
    </div>
</div>
<style>
    .header {
        background-color: #f1f1f1;
        padding: 10px;
        width: 100%;
    }
    .header-content {
        width: 80%;
        margin: 0 auto;
        display: flex;
        justify-content: flex-end;
    }
    .logout-btn {
        background: #333;
        border: none;
        color: #fff;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
        border-radius: 5px;
    }
    .logout-btn:hover {
        background: #555;
    }
</style>