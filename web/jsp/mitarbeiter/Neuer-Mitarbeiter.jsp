<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 28.10.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../html/header/header_subsub.html" %>
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
        <%Seitenaufbau.getMitarbeiterMenu(out, request.getCookies());%>
      </table>
    </td>
    <td class="main" valign="top">
      <table style="width: 100%; valign:top" border="0">
        <th style="text-align: center" colspan="3">Bestell&uumlbersicht</th>
        <tr>
          <td>
            <form method="post" action="/NeuerMitarbeiter">
            <table style="width:100%;">
              <tr>
                <td>Vorname</td><td><input type="text" name="vorname"></td><td>Nachname</td><td><input type="text" name="nachname"></td>
              </tr>
              <tr>
                <td>Benutzername</td><td><input type="text" name="benutzername"></td><td>Kennwort</td><td><input type="password" name="password"></td>
              </tr>
              <tr>
                <td><input type="submit" name="eintragen" value="Anlegen"></td>
              </tr>
            </table>
            </form>
          </td>
        </tr>
      </table>
    </td>
    <%Login.getLogin(out, request.getCookies());%>

  </tr>
</table>

<%@include file="../../html/footer/footer.jsp" %>
