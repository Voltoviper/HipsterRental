<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 01.11.2015
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../html/header/header_subsub.html" %>
<%@page import="wak.system.server.*" %>
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
                <th style="text-align: center" colspan="3">Profil</th>
                <tr>
                    <%Seitenaufbau.getProfil(out, request.getCookies());%>
                </tr>
            </table>
        </td>
        <%Login.getLogin(out, request.getCookies());%>

    </tr>
</table>

<%@include file="../html/footer/footer.html" %>
