<%@ include file="../../html/header/header_subsub.html"%>
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
        <th style="text-align: center" colspan="3">Neue Kategorie anlegen</th>
        <tr>
          <td>
            <form method="post" action="/KategorieAnlegen" enctype="multipart/form-data">
              <table>
                <tr>
                  <td>Kategoriename:</td>
                  <td><input type="text" name = "name"></td>
                </tr>
                <tr>
                  <td>Oberkategorie (optional)</td>
                  <td>
                  <select name="oberkategorie" size="1" style="width: 200px;">
                    <option value=""></option>
                    <%Seitenaufbau.getKategorieOptionen(out);%>
                  </select>
                  </td>

                </tr>
                <tr>
                  <td>Bild</td><td><input type="file" name="bild" accept="image/*"> </td>
                </tr>
                <tr>
                  <td>

                  </td>
                  <td><input type="submit" value="Kategorie hinzuf&uuml;gen"></td>
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

<%@include file="../../html/footer/footer.html"%>

