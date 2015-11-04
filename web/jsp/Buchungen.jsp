<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 30.10.2015
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../html/header/header_sub.html" %>
<%@page import="wak.system.server.Login" %>
<%@ page import="wak.system.server.Seitenaufbau" %>
<table style="width:100%; valign:top; border-spacing: 0pt" border="0">
    <tr class="menu">
        <td colspan="3" class="menu"><% Seitenaufbau.getMenu(out, request.getCookies());%>
        </td>
    </tr>
    <tr>
        <td class="kat_nav">
            <table width="100%">
                <%Seitenaufbau.getKategorie(out);%>
            </table>
        </td>
        <td class="main" valign="top">
            <table style="width: 100%; valign:top" border="0">
                <th style="text-align: center" colspan="3">Bestellübersicht</th>
                <tr>
                    <%Seitenaufbau.getBestelluebersichtKD(out, request.getCookies());%>
                </tr>
            </table>
        </td>
        <%Login.getLogin(out, request.getCookies());%>

    </tr>
</table>

<%@include file="../html/footer/footer.html" %>
