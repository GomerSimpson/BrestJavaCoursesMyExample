<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <style>
            .main {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: blanchedalmond;
            }


            html {
                min-height: 100%
            }
            body {
                min-height: 100%;
                background: #fff;
                font: 14px/125% Tahoma;
            }

            .row {
                margin: 1em 0
            }
            label {
                display: inline-block;
                width: 120px;
                text-align: left
            }
            label {
                display: inline-block;
                width: 120px;
                text-align: left
            }
            input[type="text"]{
                margin: 0;
                padding: 2px;
                border: 1px solid;
                border-color: #999 #ccc #ccc;
                border-radius: 2px
            }

            .features-table
            {
                width: 100%;
                margin: 0 auto;
                border-collapse: separate;
                border-spacing: 0;
                border: 0;
                text-shadow: 0 1px 0 #fff;
                color: #2a2a2a;
                background: #fafafa;
                background-image: -moz-linear-gradient(top, #fff, #eaeaea, #fff); /* Firefox 3.6 */
                background-image: -webkit-gradient(linear,center bottom,center top,from(#fff),color-stop(0.5, #eaeaea),to(#fff));
                margin-top:20px;
                margin-bottom:20px;
            }
            .features-table td
            {
                height: 50px;
                padding: 0 20px;
                border-bottom: 1px solid #cdcdcd;
                box-shadow: 0 1px 0 white;
                -moz-box-shadow: 0 1px 0 white;
                -webkit-box-shadow: 0 1px 0 white;
                text-align: center;
                vertical-align: middle;
                display: table-cell;
            }
            .features-table tbody td
            {
                text-align: center;
                width: 150px;
            }
            .features-table td.grey
            {
                background: #efefef;
                background: rgba(144,144,144,0.15);
                border-right: 0px;
            }
            .features-table td.green
            {
                background: #e7f3d4;
                //                background: rgba(184,243,85,0.3);
            }
            .features-table td:nowrap
            {
                white-space: nowrap;
            }
            .features-table thead td
            {
                font-size: 120%;
                font-weight: bold;
                -moz-border-radius-topright: 10px;
                -moz-border-radius-topleft: 10px;
                border-top-right-radius: 10px;
                border-top-left-radius: 10px;
                border-top: 1px solid #eaeaea;
            }
            .features-table tfoot td
            {
                font-size: 120%;
                font-weight: bold;
                -moz-border-radius-bottomright: 10px;
                -moz-border-radius-bottomleft: 10px;
                border-bottom-right-radius: 10px;
                border-bottom-left-radius: 10px;
                border-bottom: 1px solid #dadada;
            }
        </style>
    </head>

    <body>
        <div class="main">
            <c:if test="${userExistedError}">
                <script type="text/javascript"> alert("ERROR while updating user. User, with such login, has already existed")</script>
            </c:if>

            <form action="${pageContext.request.contextPath}/mvc/updateUser" method="put">
                <table>
                    <tbody>
                    <input type="text" id="id" name="id" value="${user.userId}" hidden="true"/><br>
                    <tr>
                        <td><label for="login">login</label></td>
                        <td><input type="text" id="login" name="login" value="${user.login}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">name</label></td>
                        <td><input type="text" id="name" name="name" value="${user.userName}" /></td>
                    </tr>
                    <input type="reset" name="Reset" value="reset">
                    <input type="submit" value="update the user" />
                    </tbody>
                </table>
            </form>
            <a href="${pageContext.request.contextPath}/mvc/">REFERENCE TO LIST WITH ALL USERS</a>
        </div>

    </body>
</html>